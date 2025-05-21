package grafico.buscaminas;

import consola.buscaminas.Buscaminas;

import javax.swing.*;
import java.awt.*;

public class BuscaminasGUI extends JFrame {
    private Buscaminas juego;
    private JButton[][] botones;
    private JPanel panelTablero;
    private JLabel estadoLabel;
    private JButton modoBanderaBtn;
    private boolean modoBandera = false;

    public BuscaminasGUI() {
        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton facil = new JButton("Fácil");
        JButton medio = new JButton("Medio");
        JButton dificil = new JButton("Difícil");
        modoBanderaBtn = new JButton("Bandera: OFF");
        estadoLabel = new JLabel("Selecciona un nivel para comenzar");

        Font fuente = new Font("Arial", Font.BOLD, 18);
        Dimension tamanoBtn = new Dimension(120, 40);
        for (JButton b : new JButton[]{facil, medio, dificil, modoBanderaBtn}) {
            b.setFont(fuente);
            b.setPreferredSize(tamanoBtn);
        }

        facil.addActionListener(e -> iniciarJuego(1));
        medio.addActionListener(e -> iniciarJuego(2));
        dificil.addActionListener(e -> iniciarJuego(3));
        modoBanderaBtn.addActionListener(e -> cambiarModoBandera());

        panelControl.add(facil);
        panelControl.add(medio);
        panelControl.add(dificil);
        panelControl.add(modoBanderaBtn);
        panelControl.add(estadoLabel);

        add(panelControl, BorderLayout.NORTH);

        panelTablero = new JPanel();
        panelTablero.setBackground(Color.decode("#FFCCE5"));
        add(panelTablero, BorderLayout.CENTER);

        setSize(900, 900);
        setVisible(true);
    }

    private void cambiarModoBandera() {
        modoBandera = !modoBandera;
        modoBanderaBtn.setText("Bandera: " + (modoBandera ? "ON" : "OFF"));
    }

    private void iniciarJuego(int nivel) {
        juego = new Buscaminas(nivel);
        int filas = juego.getTableroVisible().length;
        int columnas = juego.getTableroVisible()[0].length;
        botones = new JButton[filas][columnas];

        panelTablero.removeAll();
        panelTablero.setLayout(new GridLayout(filas, columnas, 2, 2));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton btn = new JButton();
                btn.setFont(new Font("Arial", Font.BOLD, 16));
                btn.setBackground(Color.LIGHT_GRAY);
                final int x = i, y = j;
                btn.addActionListener(e -> manejarClick(x, y));
                botones[i][j] = btn;
                panelTablero.add(btn);
            }
        }

        panelTablero.revalidate();
        panelTablero.repaint();
        estadoLabel.setText("Juego iniciado.");
    }

    private void manejarClick(int x, int y) {
        if (modoBandera) {
            if (juego.getTableroVisible()[x][y] == 'F') {
                juego.getTableroVisible()[x][y] = '#';
            } else if (juego.getTableroVisible()[x][y] == '#') {
                juego.getTableroVisible()[x][y] = 'F';
            }
        } else {
            boolean continuar = juego.jugar(x, y, false);
            if (!continuar) {
                mostrarTableroFinal();
                estadoLabel.setText("Has perdido");
                JOptionPane.showMessageDialog(this, "Has perdido");
                return;
            } else if (juego.haGanado()) {
                mostrarTableroFinal();
                estadoLabel.setText("Has ganado");
                JOptionPane.showMessageDialog(this, "Has ganado");
                return;
            }
        }
        actualizarTablero();
    }

    private void actualizarTablero() {
        int filas = botones.length;
        int columnas = botones[0].length;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton boton = botones[i][j];
                char valor = juego.getTableroVisible()[i][j];

                boton.setText((valor == '#' || valor == 'F') ? String.valueOf(valor == 'F' ? "X" : "") : String.valueOf(valor));
                boton.setEnabled(valor == '#' || valor == 'F');
                estilizarBoton(boton, valor);
            }
        }
    }

    private void mostrarTableroFinal() {
        int filas = botones.length;
        int columnas = botones[0].length;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                char valor = juego.getTableroMinas()[i][j];
                JButton boton = botones[i][j];
                boton.setEnabled(false);
                boton.setText(valor == '*' ? "💣" : (valor == ' ' ? "" : String.valueOf(valor)));
                estilizarBoton(boton, valor);
            }
        }
    }

    private void estilizarBoton(JButton boton, char valor) {
        boton.setOpaque(true);
        switch (valor) {
            case '1' -> { boton.setForeground(Color.BLUE); boton.setBackground(new Color(220, 235, 255)); }
            case '2' -> { boton.setForeground(new Color(0, 128, 0)); boton.setBackground(new Color(210, 255, 210)); }
            case '3' -> { boton.setForeground(Color.RED); boton.setBackground(new Color(255, 210, 210)); }
            case '4' -> { boton.setForeground(new Color(128, 0, 128)); boton.setBackground(new Color(240, 220, 255)); }
            case '5' -> { boton.setForeground(Color.MAGENTA); boton.setBackground(new Color(255, 220, 240)); }
            case '6' -> { boton.setForeground(Color.CYAN); boton.setBackground(new Color(210, 255, 255)); }
            case '7' -> { boton.setForeground(Color.BLACK); boton.setBackground(new Color(230, 230, 230)); }
            case '8' -> { boton.setForeground(Color.DARK_GRAY); boton.setBackground(new Color(200, 200, 200)); }
            case '*' -> boton.setBackground(Color.RED);
            case 'F' -> boton.setBackground(Color.RED);
            case '#' -> boton.setBackground(Color.LIGHT_GRAY);
            default -> boton.setBackground(Color.WHITE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BuscaminasGUI::new);
    }
}
