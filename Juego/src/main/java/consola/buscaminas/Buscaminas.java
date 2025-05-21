package consola.buscaminas;

import java.util.*;

public class Buscaminas {
    protected final char MINA = '*';
    protected final char VACIO = ' ';
    protected final char BANDERA = 'F';

    protected char[][] tableroVisible;
    protected char[][] tableroMinas;
    protected boolean[][] descubierto;

    protected boolean primeraJugada = true;
    protected int filas, columnas, minas;

    /**
     * Constructor que inicializa el juego según el nivel de dificultad.
     * @param nivel Nivel de dificultad (1 = fácil, 2 = medio, 3 = difícil)
     */
    public Buscaminas(int nivel) {
        switch (nivel) {
            case 1 -> { filas = 8; columnas = 8; minas = 10; }
            case 2 -> { filas = 16; columnas = 16; minas = 40; }
            case 3 -> { filas = 16; columnas = 30; minas = 99; }
            default -> throw new IllegalArgumentException("Nivel inválido");
        }
        tableroVisible = new char[filas][columnas];
        tableroMinas = new char[filas][columnas];
        descubierto = new boolean[filas][columnas];
        for (char[] row : tableroVisible) Arrays.fill(row, '#');
        for (char[] row : tableroMinas) Arrays.fill(row, VACIO);
    }

    /**
     * Coloca minas aleatoriamente en el tablero, asegurando que la primera jugada sea segura.
     * @param x Fila de la primera jugada
     * @param y Columna de la primera jugada
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
     * Calcula y asigna los números de minas vecinas a cada celda vacía.
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
     * Cuenta el número de minas alrededor de una celda.
     * @param x Fila
     * @param y Columna
     * @return Número de minas adyacentes
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
     * Revela una celda y, si está vacía, revela recursivamente las celdas vecinas.
     * @param x Fila
     * @param y Columna
     */
    private void revelar(int x, int y) {
        if (x < 0 || y < 0 || x >= filas || y >= columnas || descubierto[x][y])
            return;

        descubierto[x][y] = true;
        tableroVisible[x][y] = tableroMinas[x][y];

        if (tableroMinas[x][y] == VACIO) {
            for (int dx = -1; dx <= 1; dx++)
                for (int dy = -1; dy <= 1; dy++)
                    revelar(x + dx, y + dy);
        }
    }

    /**
     * Procesa una jugada del usuario.
     * @param x Fila seleccionada
     * @param y Columna seleccionada
     * @param ponerBandera True si se desea colocar/quitar una bandera
     * @return True si el juego continúa, False si se ha perdido
     */
    public boolean jugar(int x, int y, boolean ponerBandera) {
        if (x < 0 || y < 0 || x >= filas || y >= columnas) {
            System.out.println("Movimiento fuera de rango.");
            return true;
        }

        if (ponerBandera) {
            if (tableroVisible[x][y] == BANDERA) tableroVisible[x][y] = '#';
            else tableroVisible[x][y] = BANDERA;
            return true;
        }

        if (primeraJugada) {
            colocarMinas(x, y);
            primeraJugada = false;
        }

        if (tableroMinas[x][y] == MINA) {
            tableroVisible[x][y] = MINA;
            mostrarTablero(tableroMinas);
            System.out.println("¡Has perdido!");
            return false;
        }

        revelar(x, y);
        return true;
    }

    /**
     * Muestra un tablero en la consola.
     * @param tablero Matriz que representa el tablero a mostrar
     */
    public void mostrarTablero(char[][] tablero) {
        System.out.print("   ");
        for (int j = 0; j < columnas; j++) System.out.print(j + " ");
        System.out.println();
        for (int i = 0; i < filas; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < columnas; j++) System.out.print(tablero[i][j] + " ");
            System.out.println();
        }
    }

    /**
     * Verifica si el jugador ha ganado (todas las celdas no-mina descubiertas).
     * @return True si ha ganado, False en caso contrario
     */
    public boolean haGanado() {
        int descubiertas = 0;
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                if (descubierto[i][j]) descubiertas++;
        return descubiertas == (filas * columnas - minas);
    }

    public char[][] getTableroVisible() {
        return tableroVisible;
    }

    public char[][] getTableroMinas() {
        return tableroMinas;
    }
}
