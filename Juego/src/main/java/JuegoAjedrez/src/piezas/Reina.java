package JuegoAjedrez.src.piezas;

import JuegoAjedrez.src.util.Colores;

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
}