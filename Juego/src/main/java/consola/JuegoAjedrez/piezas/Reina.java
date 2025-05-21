package consola.JuegoAjedrez.piezas;

import consola.JuegoAjedrez.util.Colores;

public class Reina extends Pieza {
    public Reina(boolean color, Posicion posicion) {
        super(color, posicion);
    }

    public String mostrarSimbolo() {
        if (color) {
            return Colores.AZUL_BRIGHT + "Q";
        } else {
            return Colores.AMARILLO_BRIGHT + "Q";
        }
    }

    /**
 * Verifica si el movimiento de la reina hacia la posición destino es válido.
 * El movimiento es válido si es horizontal, vertical o diagonal.
 *
 * @param destino posición a la que se desea mover la reina.
 * @return true si el movimiento es válido; false en caso contrario.
 */

     public boolean movimiento(Posicion destino) { // juntando la logica de la torre y el alfil sacamos la reina
        Posicion origen = this.getPosicion();

        int filaActual = origen.getY();
        int filaDestino = destino.getY();

        int columnaActual = origen.getX() - 'A';
        int columnaDestino = destino.getX() - 'A';

        int diagonalX = Math.abs(destino.getX() - origen.getX());
        int diagonalY = Math.abs(destino.getY() - origen.getY());

        // (=============== || =============================== && ===============================)
        if (destino == null || origen.getX() == destino.getX() && origen.getY() == destino.getY()) {
            System.out.println("Movimiento invalido de la reina");
            return false;
        }

        // (========================= && ===============================)
        if (filaActual == filaDestino && columnaActual == columnaDestino) {
            return false;
        }

        // (========================= || ===============================)
        if (filaActual == filaDestino || columnaActual == columnaDestino) {
            return true;
        }

        // (======================)
        if (diagonalX == diagonalY) {
            // System.out.println("Movimiento diagonal de la reina valido");
            return true;
        }

        System.out.println("Movimiento invalido de la reina");
        return false;
    }
}
