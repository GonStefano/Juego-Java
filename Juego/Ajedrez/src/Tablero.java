// Ajedrez
import java.util.Scanner;
import util.*;
import piezas.*;
import Excepciones.*;

public class Tablero {
    private static final int DIMENSIONES = 8;
    private Pieza[][] tablero;
    private boolean turnoBlancas = true;
    private int capturasBlancas = 0;
    private int capturasNegras = 0;

    public Tablero() {
        tablero = new Pieza[DIMENSIONES][DIMENSIONES];
        colocarPiezas();
    }

    private void colocarPiezas() {
    // PIEZAS NEGRAS
    tablero[0][0] = new Torre(false, new Posicion('A', 8));
    tablero[0][1] = new Caballo(false, new Posicion('B', 8));
    tablero[0][2] = new Alfil(false, new Posicion('C', 8));
    tablero[0][3] = new Reina(false, new Posicion('D', 8));
    tablero[0][4] = new Rey(false, new Posicion('E', 8));
    tablero[0][5] = new Alfil(false, new Posicion('F', 8));
    tablero[0][6] = new Caballo(false, new Posicion('G', 8));
    tablero[0][7] = new Torre(false, new Posicion('H', 8));
        for (int i = 0; i < 8; i++) {
            tablero[1][i] = new Peon(false, new Posicion((char) ('A' + i), 7));
        }

    // PIEZAS BLANCAS
    tablero[7][0] = new Torre(true, new Posicion('A', 1));
    tablero[7][1] = new Caballo(true, new Posicion('B', 1));
    tablero[7][2] = new Alfil(true, new Posicion('C', 1));
    tablero[7][3] = new Reina(true, new Posicion('D', 1));
    tablero[7][4] = new Rey(true, new Posicion('E', 1));
    tablero[7][5] = new Alfil(true, new Posicion('F', 1));
    tablero[7][6] = new Caballo(true, new Posicion('G', 1));
    tablero[7][7] = new Torre(true, new Posicion('H', 1));
        for (int i = 0; i < 8; i++) {
            tablero[6][i] = new Peon(true, new Posicion((char) ('A' + i), 2));
        }
    }

    public void mostrarTablero() {
        System.out.println(Colores.AZUL_BRIGHT + "BLANCAS: Q, K, B, C, R, P" + Colores.RESET);
        System.out.println(Colores.AMARILLO_BRIGHT + "NEGRAS: Q, K, B, C, R, P" + Colores.RESET);
        System.out.println();
        System.out.println(Colores.ROJO_BRIGHT + "     A     B     C     D     E     F     G     H" + Colores.RESET);
        System.out.println(Colores.NEGRO_BRIGHT + "  ╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗" + Colores.RESET);
        for (int i = 0; i < DIMENSIONES; i++) {
            System.out.print(Colores.ROJO_BRIGHT + (8 - i) + Colores.RESET + Colores.NEGRO_BRIGHT + " ║" + Colores.RESET);
            for (int j = 0; j < DIMENSIONES; j++) {
                Pieza p = tablero[i][j];
            
                String fondo;
                if ((i + j) % 2 == 0) {
                    fondo = Colores.FONDO_BLANCO;
                } else {
                    fondo = Colores.FONDO_NEGRO;
                }
            
                String contenido = " ";
                if (p != null) {
                    contenido = p.mostrarSimbolo();
                }
            
                System.out.print(fondo + "  " + contenido + "  " + Colores.RESET);
                System.out.print(Colores.NEGRO_BRIGHT + "║" + Colores.RESET);
            }
            System.out.println(" " + Colores.ROJO_BRIGHT + (8 - i) + Colores.RESET);
            if (i < DIMENSIONES - 1) {
                System.out.println(Colores.NEGRO_BRIGHT + "  ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣" + Colores.RESET);
            }
        }
        System.out.println(Colores.NEGRO_BRIGHT + "  ╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝" + Colores.RESET);
        System.out.println(Colores.ROJO_BRIGHT + "     A     B     C     D     E     F     G     H" + Colores.RESET);
        System.out.println();
        // llamadda al metodo de leer el movimiento
    }


    public Pieza[][] getTablero() {
        return tablero;
    }
    public void setTablero(Pieza[][] tablero) {
        this.tablero = tablero;
    }
}
