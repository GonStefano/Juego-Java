package consola.juegoBuscaminas;

import java.util.Arrays;
import java.util.Random;


public class Buscaminas {
    private static final char MINA = '*';
    private static final char VACIO = ' ';
    private static final char BANDERA = 'F';


    char[][] tableroVisible;
    private char[][] tableroMinas;
    private boolean[][] descubierto;
    private boolean primeraJugada = true;
    private int filas, columnas, minas;


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


    private void calcularNumeros() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tableroMinas[i][j] == MINA) continue;
                int contador = contarMinas(i, j);
                if (contador > 0) tableroMinas[i][j] = (char) ('0' + contador);
            }
        }
    }


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


    public boolean haGanado() {
        int descubiertas = 0;
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                if (descubierto[i][j]) descubiertas++;
        return descubiertas == (filas * columnas - minas);
    }


}
