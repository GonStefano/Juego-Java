package JuegoAjedrez.src.piezas;

import JuegoAjedrez.src.util.Colores;

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