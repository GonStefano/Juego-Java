package piezas;
import util.Colores;

public class Peon extends Pieza {
    public Peon(boolean color, Posicion posicion) {
        super(color, posicion);
    }

    public String mostrarSimbolo() {
        if (color) {
            return Colores.AZUL_BRIGHT + "P";
        } else {
            return Colores.AMARILLO_BRIGHT + "P";
        }
    }
}
