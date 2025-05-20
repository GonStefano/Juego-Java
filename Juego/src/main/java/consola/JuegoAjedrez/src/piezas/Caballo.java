package consola.JuegoAjedrez.src.piezas;
import consola.JuegoAjedrez.src.util.Colores;

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

    public boolean movimiento(Posicion destino) {
        Posicion origen = this.getPosicion();

        if (destino == null || (origen.getX() == destino.getX() && origen.getY() == destino.getY())) {
            System.out.println("Movimiento inválido del caballo");
            return false;
        }

        int dx = Math.abs(origen.getX() - destino.getX());
        int dy = Math.abs(origen.getY() - destino.getY());

        // movimiento en forma de L 2x1 o 1x2
        if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)) {
            return true;
        }

        System.out.println("Movimiento inválido del caballo");
        return false;
    }
}
