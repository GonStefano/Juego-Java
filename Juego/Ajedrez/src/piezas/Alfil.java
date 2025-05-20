package piezas;

import util.Colores;

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
}