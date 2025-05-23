package consola.JuegoAjedrez.piezas;

import consola.JuegoAjedrez.util.Colores;

public class Alfil extends Pieza{
    public Alfil(boolean color, Posicion posicion) {
        super(color, posicion);
    }

    public String mostrarSimbolo() {
        if (color) {
            return Colores.AZUL_BRIGHT + "B";
        } else {
            return Colores.AMARILLO_BRIGHT + "B";
        }
    }

    /**
 * Verifica si el movimiento del alfil hacia la posición destino es válido.
 * Un movimiento es válido si es diagonal y no es la misma casilla.
 *
 * @param destino posición a la que se desea mover el alfil.
 * @return true si el movimiento es diagonal válido; false en caso contrario.
 */
    public boolean movimiento(Posicion destino) {
        Posicion origen = this.getPosicion();
        int diagonalX = Math.abs(destino.getX() - origen.getX());
        int diagonalY = Math.abs(destino.getY() - origen.getY());

        // (=============== || =============================== && ===============================)
        if (destino == null || origen.getX() == destino.getX() && origen.getY() == destino.getY()) {
            System.out.println("Movimiento invalido del alfil");
            return false;
        }

        // (======================)
        if (diagonalX == diagonalY) { // al igualar los valores absolutos de x e y determinamos que al ser los mismos valores estan en la diagonal, cosa que por ejemplo con el caballo va a ser molesta de gestionar
            System.out.println("Movimiento diagonal del alfil valido");
            return true;
        }

        System.out.println("Movimiento invalido del alfil");
        return false;
    }
}
