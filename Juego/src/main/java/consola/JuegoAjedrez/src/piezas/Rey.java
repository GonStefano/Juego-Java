package consola.JuegoAjedrez.src.piezas;

import consola.JuegoAjedrez.src.util.Colores;

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
}
