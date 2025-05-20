package piezas;

import util.Colores;

public class Torre extends Pieza {
    public Torre(boolean color, Posicion posicion) {
        super(color, posicion);
    }

    public String mostrarSimbolo() {
        if (color) {
            return Colores.AZUL_BRIGHT + "T";
        } else {
            return Colores.AMARILLO_BRIGHT + "T";
        }
    }
}
