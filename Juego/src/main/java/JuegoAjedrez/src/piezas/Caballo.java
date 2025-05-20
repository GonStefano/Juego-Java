package JuegoAjedrez.src.piezas;
import JuegoAjedrez.src.util.Colores;

public class Caballo extends Pieza {
    
    public Caballo(boolean color, Posicion posicion) {
        super(color, posicion);
    }

    public String mostrarSimbolo() {
        if (color) {
            return Colores.AZUL_BRIGHT + "C";
        } else {
            return Colores.AMARILLO_BRIGHT + "C";
        }
    }
}
