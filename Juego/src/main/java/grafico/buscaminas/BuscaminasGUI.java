package grafico.buscaminas;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class BuscaminasGUI extends JFrame {
    private static final char MINA = '*';
    private static final char VACIO = ' ';
    private static final char BANDERA = 'F';

    private JButton[][] botones;
    private char[][] tableroMinas;
    private boolean[][] descubierto;
    private boolean[][] banderas;
    private boolean primeraJugada = true;
    private boolean modoBandera = false;
    private int filas, columnas, minas;

    private JPanel panelTablero;
    private JLabel estadoLabel;
    private JButton modoBanderaBtn;

    /**
     * Constructor que inicializa la ventana y los componentes gráficos.
     * Crea botones para seleccionar dificultad y para activar modo bandera.
     */
    public BuscaminasGUI() {
        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelControl = new JPanel();
        panelControl.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JButton facil = new JButton("Fácil");
        JButton medio = new JButton("Medio");
        JButton dificil = new JButton("Difícil");

        // Estilo de los botones de nivel
        Font nivelFont = new Font("Arial", Font.BOLD, 20);
        Dimension botonTamano = new Dimension(150, 50);
        for (JButton b : new JButton[]{facil, medio, dificil}) {
            b.setFont(nivelFont);
            b.setPreferredSize(botonTamano);
        }

        modoBanderaBtn = new JButton("Modo Bandera: OFF");
        estadoLabel = new JLabel("Selecciona nivel para comenzar");

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
        panelTablero.setBackground(Color.decode("#FFCCE5")); // Marco rosa claro
        add(panelTablero, BorderLayout.CENTER);

        setSize(900, 900);
        setVisible(true);
    }

    /**
     * Cambia el estado del modo bandera (ON/OFF) y actualiza el texto del botón correspondiente.
     */
    private void cambiarModoBandera() {
        modoBandera = !modoBandera;
        modoBanderaBtn.setText("Modo Bandera: " + (modoBandera ? "ON" : "OFF"));
    }

    /**
     * Inicializa el juego con un nivel de dificultad dado.
     * Configura el tamaño del tablero y número de minas según el nivel,
     * crea y configura los botones que representan las casillas.
     * @param nivel 1 para fácil, 2 para medio, 3 para difícil
     */
    private void iniciarJuego(int nivel) {
        switch (nivel) {
            case 1 -> {
                filas = 8;
                columnas = 8;
                minas = 10;
            }
            case 2 -> {
                filas = 16;
                columnas = 16;
                minas = 40;
            }
            case 3 -> {
                filas = 16;
                columnas = 30;
                minas = 99;
            }
        }

        botones = new JButton[filas][columnas];
        tableroMinas = new char[filas][columnas];
        descubierto = new boolean[filas][columnas];
        banderas = new boolean[filas][columnas];
        primeraJugada = true;

        for (char[] row : tableroMinas) Arrays.fill(row, VACIO);

        panelTablero.removeAll();
        panelTablero.setLayout(new GridLayout(filas, columnas, 2, 2));
        panelTablero.setBackground(Color.decode("#FFCCE5"));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton btn = new JButton();
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setFont(new Font("Arial", Font.BOLD, 16));
                final int x = i, y = j;
                btn.addActionListener(e -> manejarClick(x, y));
                botones[i][j] = btn;
                panelTablero.add(btn);
            }
        }

        panelTablero.revalidate();
        panelTablero.repaint();
    }

    /**
     * Coloca minas aleatoriamente en el tablero, evitando la casilla de la primera jugada.
     * @param x fila de la primera jugada para evitar colocar mina
     * @param y columna de la primera jugada para evitar colocar mina
     */
    private void colocarMinas(int x, int y) {
        Random r = new Random();
        int colocadas = 0;
        while (colocadas < minas) {
            int i = r.nextInt(filas);
            int j = r.nextInt(columnas);
            if (tableroMinas[i][j] != MINA && (i != x || j != y)) {
                tableroMinas[i][j] = MINA;
                colocadas++;
            }
        }
        calcularNumeros();
    }

    /**
     * Calcula el número de minas alrededor de cada casilla vacía y actualiza el tablero.
     */
    private void calcularNumeros() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tableroMinas[i][j] == MINA) continue;
                int contador = contarMinas(i, j);
                if (contador > 0) tableroMinas[i][j] = (char) ('0' + contador);
            }
        }
    }

    /**
     * Cuenta cuántas minas hay alrededor de una casilla dada.
     * @param x fila de la casilla
     * @param y columna de la casilla
     * @return número de minas adyacentes
     */
    private int contarMinas(int x, int y) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++)
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && ny >= 0 && nx < filas && ny < columnas && tableroMinas[nx][ny] == MINA)
                    count++;
            }
        return count;
    }

    /**
     * Maneja la acción de click en una casilla.
     * Dependiendo del modo bandera y estado de la casilla, coloca o quita bandera,
     * descubre la casilla o finaliza el juego si se pisa una mina.
     * @param x fila de la casilla clickeada
     * @param y columna de la casilla clickeada
     */
    private void manejarClick(int x, int y) {
        if (descubierto[x][y]) return;

        if (modoBandera) {
            if (!banderas[x][y]) {
                botones[x][y].setText("<3");
                banderas[x][y] = true;
            } else {
                botones[x][y].setText("");
                banderas[x][y] = false;
            }
            return;
        }

        if (banderas[x][y]) return;

        if (primeraJugada) {
            colocarMinas(x, y);
            primeraJugada = false;
        }

        if (tableroMinas[x][y] == MINA) {
            botones[x][y].setText("BOOM");
            botones[x][y].setBackground(Color.RED);
            descubrirTodas();
            estadoLabel.setText("¡Has perdido!");
            JOptionPane.showMessageDialog(this, "¡Has perdido!");
            return;
        }

        revelar(x, y);

        if (haGanado()) {
            estadoLabel.setText("¡Has ganado!");
            JOptionPane.showMessageDialog(this, "¡Felicidades, has ganado!");
        }
    }

    /**
     * Revela la casilla indicada y, si está vacía, revela recursivamente las casillas adyacentes.
     * @param x fila de la casilla a revelar
     * @param y columna de la casilla a revelar
     */
    private void revelar(int x, int y) {
        if (x < 0 || y < 0 || x >= filas || y >= columnas || descubierto[x][y]) return;

        descubierto[x][y] = true;
        char valor = tableroMinas[x][y];
        JButton boton = botones[x][y];
        boton.setEnabled(false);
        boton.setText(valor == VACIO ? "" : String.valueOf(valor));

        // Color de fondo dependiendo del número
        switch (valor) {
            case '1' -> {
                boton.setForeground(Color.BLUE);
                boton.setBackground(new Color(220, 235, 255));
            }
            case '2' -> {
                boton.setForeground(new Color(0, 128, 0));
                boton.setBackground(new Color(210, 255, 210));
            }
            case '3' -> {
                boton.setForeground(Color.RED);
                boton.setBackground(new Color(255, 210, 210));
            }
            case '4' -> {
                boton.setForeground(new Color(128, 0, 128));
                boton.setBackground(new Color(240, 220, 255));
            }
            case '5' -> {
                boton.setForeground(Color.MAGENTA);
                boton.setBackground(new Color(255, 220, 240));
            }
            case '6' -> {
                boton.setForeground(Color.CYAN);
                boton.setBackground(new Color(210, 255, 255));
            }
            case '7' -> {
                boton.setForeground(Color.BLACK);
                boton.setBackground(new Color(230, 230, 230));
            }
            case '8' -> {
                boton.setForeground(Color.DARK_GRAY);
                boton.setBackground(new Color(200, 200, 200));
            }
            default -> boton.setBackground(Color.WHITE);
        }

        if (valor == VACIO) {
            boton.setBackground(Color.WHITE);
            for (int dx = -1; dx <= 1; dx++)
                for (int dy = -1; dy <= 1; dy++)
                    revelar(x + dx, y + dy);
        }
    }

    /**
     * Descubre todas las casillas, mostrando minas y números, y deshabilita los botones.
     */
    private void descubrirTodas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tableroMinas[i][j] == MINA)
                    botones[i][j].setText("💣");
                botones[i][j].setEnabled(false);
            }
        }
    }

    /**
     * Comprueba si el jugador ha ganado el juego.
     * El jugador gana si todas las casillas sin minas están descubiertas.
     * @return true si ha ganado, false en caso contrario
     */
    private boolean haGanado() {
        int descubiertas = 0;
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                if (descubierto[i][j]) descubiertas++;
        return descubiertas == (filas * columnas - minas);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BuscaminasGUI::new);
    }
}
