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

    public boolean introducirMovimiento() {
        if (!hayReyVivo(true)) {
                System.out.println("Fin del juego: ¡El rey blanco ha sido capturado!");
                System.out.println("Capturas realizadas por blancas: " + capturasBlancas);
                System.out.println("Capturas realizadas por negras: " + capturasNegras);

                Estadistica.guardarEnArchivo("Negras", capturasBlancas, capturasNegras);
                System.exit(0);

            }
            if (!hayReyVivo(false)) {
                System.out.println("Fin del juego: ¡El rey negro ha sido capturado!");
                System.out.println("Capturas realizadas por blancas: " + capturasBlancas);
                System.out.println("Capturas realizadas por negras: " + capturasNegras);

                Estadistica.guardarEnArchivo("Blancas", capturasBlancas, capturasNegras);
                System.exit(0);

            }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el movimiento (ej: A1 A3): ");
        String input = scanner.nextLine().toUpperCase();
        Terminal.clearConsole(false);

        try {
            String[] partes = input.split(" ");
            if (partes.length != 2) {
                throw new MovimientoInvalidoExcepcion("Formato inválido. Usa el formato: A1 A3");
            }

            String movimientoOrigen = partes[0];
            String movimientoDestino = partes[1];

            if (movimientoOrigen.length() != 2 || movimientoDestino.length() != 2) {
                throw new MovimientoInvalidoExcepcion("Coordenadas inválidas. Usa letras A-H y números 1-8.");
            }

            char letraOrigen = movimientoOrigen.charAt(0);
            int numeroOrigen = Character.getNumericValue(movimientoOrigen.charAt(1));

            char letraDestino = movimientoDestino.charAt(0);
            int numeroDestino = Character.getNumericValue(movimientoDestino.charAt(1));

            Posicion origen = new Posicion(letraOrigen, numeroOrigen);
            Posicion destino = new Posicion(letraDestino, numeroDestino);

            // para las estadisticas
            int filaOrigen = 8 - numeroOrigen;
            int colOrigen = letraOrigen - 'A';
            int filaDestino = 8 - numeroDestino;
            int colDestino = letraDestino - 'A';

            Pieza piezaOrigen = tablero[filaOrigen][colOrigen];
            Pieza piezaDestino = tablero[filaDestino][colDestino];

            if (piezaDestino != null && piezaOrigen != null && piezaOrigen.getColor() != piezaDestino.getColor()) {
                if (piezaOrigen.getColor()) { // si es blanca
                    capturasBlancas++;
                } else {
                    capturasNegras++;
                }
            }            
            return true;
        } catch (MovimientoInvalidoExcepcion | CapturaAliadaExcepcion e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Por favor intenta otro movimiento.");
            return false;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return false;
        }
    }

    public boolean moverPieza(Posicion origen, Posicion destino) throws MovimientoInvalidoExcepcion, CapturaAliadaExcepcion {
        int filaOrigen = 8 - origen.getY();
        int columnaOrigen = origen.getX() - 'A';
    
        int filaDestino = 8 - destino.getY();
        int columnaDestino = destino.getX() - 'A';
    
        Pieza pieza = tablero[filaOrigen][columnaOrigen];

        if (pieza.getColor() != turnoBlancas) {
            if (turnoBlancas) {
                System.out.println("Es el turno de las blancas.");
            } else {
                System.out.println("Es el turno de las negras.");
            }
            return false;
        }

        // Si no hay pieza en origen = no se puede mover, logicamente
        // (=============)
        if (pieza == null) {
            System.out.println("No hay pieza en la casilla de origen.");
            throw new MovimientoInvalidoExcepcion("Movimiento invalido: no hay pieza en la casilla de origen.");
        }
    
        // validar el movimiento usando su l´ogica
        // (==========================)
        if (!pieza.movimiento(destino)) {
            System.out.println("Movimiento no válido para esta pieza.");
            throw new MovimientoInvalidoExcepcion("Movimiento invalido: movimiento no valido para esta pieza.");
        }
    
        // si hay una pieza nuestra en la casilla destino
        Pieza objetivo = tablero[filaDestino][columnaDestino];
        // (================ && =======================================)
        if (objetivo != null && objetivo.getColor() == pieza.getColor()) {
            System.out.println("No puedes capturar una pieza aliada.");
            throw new CapturaAliadaExcepcion("Movimiento invalido: no puedes capturar una pieza aliada.");
        }

        // (====================== || ====================== || ======================))
        if (pieza instanceof Torre || pieza instanceof Alfil || pieza instanceof Reina) {
            if (hayPiezasEntre(origen, destino)) {
                System.out.println("Hay piezas bloqueando el camino.");
                throw new MovimientoInvalidoExcepcion("Movimiento invalido: hay piezas bloqueando el camino.");
            }
        }
    
        tablero[filaDestino][columnaDestino] = pieza; // mover la pieza al destino
        pieza.setPosicion(destino);
        tablero[filaOrigen][columnaOrigen] = null; // quitar la pieza del origen para no duplicarla
    
        System.out.println("Movimiento realizado.");
        turnoBlancas = !turnoBlancas;
        mostrarTablero();
        return true;
    }

    /* Metodo para verificar si hay piezas en medio de los movimientos y decidir si se mueve el objeto de la pieza o no */
    private boolean hayPiezasEntre(Posicion origen, Posicion destino) {
        int filaOrigen = 8 - origen.getY();
        int filaDestino = 8 - destino.getY();
        
        int columnaOrigen = origen.getX() - 'A'; // A - A, como A vale 65 en valor unicode y se le resta 65 el resultado es 0 que es la posicion del array. Como siempre restamos A, en el momento en
        int columnaDestino = destino.getX() - 'A'; // el que ponemos B sería 1, la segunda posicion del array, y así sucesivamente
    
        if (columnaOrigen == columnaDestino) { // movimiento vertical
            int inicio = Math.min(filaOrigen, filaDestino) + 1; // +1 poruqe necesitamos que se mueva al menos una posicion para que cuente el movimiento
            int fin = Math.max(filaOrigen, filaDestino);
    
            for (int i = inicio; i < fin; i++) {
                if (tablero[i][columnaOrigen] != null) {
                    return true;
                }
            }
        } else if (filaOrigen == filaDestino) { // movimiento horizontal
            int inicio = Math.min(columnaOrigen, columnaDestino) + 1; 
            int fin = Math.max(columnaOrigen, columnaDestino);
    
            for (int i = inicio; i < fin; i++) {
                if (tablero[filaOrigen][i] != null) {
                    return true;
                }
            }
        } else if (Math.abs(filaDestino - filaOrigen) == Math.abs(columnaDestino - columnaOrigen)) { // movimiento diagonal
            int pasos = Math.abs(filaDestino - filaOrigen);
    
            int direccionFila;
            if (filaDestino > filaOrigen) {
                direccionFila = 1;
            } else {
                direccionFila = -1;
            }
    
            int direccionColumna;
            if (columnaDestino > columnaOrigen) {
                direccionColumna = 1;
            } else {
                direccionColumna = -1;
            }
    
            int f = filaOrigen + direccionFila;
            int c = columnaOrigen + direccionColumna;
    
            for (int i = 1; i < pasos; i++) {
                if (tablero[f][c] != null) {
                    return true;
                }
                f = f + direccionFila;
                c = c + direccionColumna;
            }
        }
        return false;
    }	

    public boolean hayReyVivo(boolean color) {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza p = tablero[fila][col];
                if (p != null && p instanceof Rey && p.getColor() == color) {
                    return true;
                }
            }
        }
        return false;
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
        introducirMovimiento();
    }


    public Pieza[][] getTablero() {
        return tablero;
    }
    public void setTablero(Pieza[][] tablero) {
        this.tablero = tablero;
    }
}
