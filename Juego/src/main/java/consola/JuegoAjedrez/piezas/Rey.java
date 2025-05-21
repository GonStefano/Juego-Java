package consola.JuegoAjedrez.piezas;

import consola.JuegoAjedrez.util.Colores;

public class Rey extends Pieza {
    public Rey(boolean color, Posicion posicion) {
        super(color, posicion);
    }

    public String mostrarSimbolo() {
        if (color) {
            return Colores.AZUL_BRIGHT + "K";
        } else {
            return Colores.AMARILLO_BRIGHT + "K";
        }
    }

    /**
 * Verifica si el movimiento del rey hacia la posición destino es válido.
 * El rey no puede permanecer en la misma casilla.
 *
 * @param destino posición a la que se desea mover el rey.
 * @return true si el movimiento es a una casilla distinta; false en caso contrario.
 */
    public boolean movimiento(Posicion destino) {
        Posicion origen = this.getPosicion();
        
        // (=============== || =============================== && ===============================)
        if (destino == null || origen.getX() == destino.getX() && origen.getY() == destino.getY()) {
            System.out.println("Movimiento invalido del Rey");
            return false;
        }
        return true;
    }
}
