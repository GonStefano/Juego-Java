package grafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import grafico.buscaminas.BuscaminasGUI;
import grafico.space.vista.ViewManager;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class ConsolaGrafica extends JFrame {

    public ConsolaGrafica() {
        setTitle("Consola de Juegos");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel con imagen de fondo personalizada
        JPanel panelFondo = new JPanel() {
            Image fondo = new ImageIcon(getClass().getResource("/fondo_consola.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelFondo.setLayout(new GridBagLayout());

        // Botones con colores personalizados
        JButton btnBuscaminas = crearBotonRedondeado("Buscaminas", new Color(63, 101, 237));
        JButton btnShooter = crearBotonRedondeado("Space Runner", new Color(63, 101, 237));

        // Manejador de eventos
        ActionListener juegoListener = e -> {
            String juego = ((JButton) e.getSource()).getText();
            if (juego.equals("Buscaminas")) {
                SwingUtilities.invokeLater(() -> new BuscaminasGUI());
            } else if (juego.equals("Space Runner")) {
                lanzarSpaceShooterFX();
            } else {
                JOptionPane.showMessageDialog(this, "Lanzando " + juego + "...");
            }
        };

        btnBuscaminas.addActionListener(juegoListener);
        btnShooter.addActionListener(juegoListener);

        // Añadir botones al panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridy = 0;
        panelFondo.add(btnBuscaminas, gbc);
        gbc.gridy = 1;
        panelFondo.add(btnShooter, gbc);

        setContentPane(panelFondo);
    }

    // Botón redondeado personalizado
    private JButton crearBotonRedondeado(String texto, Color colorFondo) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(colorFondo.darker());
                } else {
                    g.setColor(colorFondo);
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(Color.BLACK);
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            }
        };

        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setForeground(Color.WHITE);
        boton.setPreferredSize(new Dimension(220, 60));
        boton.setOpaque(false);
        boton.setBorderPainted(false);
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Inicializa JavaFX (obligatorio para usar Stage en Swing)
            new JFXPanel();

            ConsolaGrafica consola = new ConsolaGrafica();
            consola.setVisible(true);
        });
    }

    private void lanzarSpaceShooterFX() {
        Platform.runLater(() -> {
            ViewManager v = new ViewManager();
            v.getMainStage().show();
        });
    }
}
