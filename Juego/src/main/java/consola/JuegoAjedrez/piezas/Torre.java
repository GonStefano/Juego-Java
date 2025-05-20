package consola.JuegoAjedrez.piezas;

import consola.JuegoAjedrez.util.Colores;

public class Torre extends Pieza {
    public Torre(boolean color, Posicion posicion) {
        super(color, posicion);
    }

    public boolean movimiento(Posicion destino) {
        Posicion origen = this.getPosicion();

        int filaActual = origen.getY();
        int filaDestino = destino.getY();

        int columnaActual = origen.getX() - 'A';
        int columnaDestino = destino.getX() - 'A';
        // (========================= && ===============================)
        if (filaActual == filaDestino && columnaActual == columnaDestino) { // con esto estamos diciendo que la torre no puede moverse en diagonal
            return false;
        }

        // Solo puede moverse en línea recta
        // (========================= || ===============================)
        if (filaActual == filaDestino || columnaActual == columnaDestino) { // parriba o pabajo, si fuera un && en lugar de un || sería la logica del alfil
            return true;
        }

        return false;
    }

    public String mostrarSimbolo() {
        if (color) {
            return Colores.AZUL_BRIGHT + "T";
        } else {
            return Colores.AMARILLO_BRIGHT + "T";
        }
    }
}
