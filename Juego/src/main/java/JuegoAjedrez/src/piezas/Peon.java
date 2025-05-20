package JuegoAjedrez.src.piezas;
import JuegoAjedrez.src.util.Colores;

public class Peon extends Pieza {
    public Peon(boolean color, Posicion posicion) {
        super(color, posicion);
    }

    public boolean movimiento(Posicion destino) {
        Posicion origen = this.getPosicion();

        int filaOrigen = origen.getY();
        int filaDestino = destino.getY();

        int columnaOrigen = origen.getX() - 'A';
        int columnaDestino = destino.getX() - 'A';

        int dx = columnaDestino - columnaOrigen;
        int dy = filaDestino - filaOrigen;

        // direccion blancas suben 1 negras bajan -1
        int direccion;

        if (this.color == true) {
            direccion = 1;
        } else {
            direccion = -1;
        }

        if (dx == 0 && dy == direccion) {
            return true;
        }

        if (dx == 0) {
            if (this.color == true && filaOrigen == 1 && dy == 2) {
                return true;
            }

            if (this.color == false && filaOrigen == 6 && dy == -2) {
                return true;
            }
        }

        // Movimiento diagonal para captura (por ahora sin verificar si hay enemigo)
        if ((dx == 1 || dx == -1) && dy == direccion) {
            return true;
        }

        System.out.println("Movimiento inválido del peón");
        return false;
    }

    public String mostrarSimbolo() {
        if (color) {
            return Colores.AZUL_BRIGHT + "P";
        } else {
            return Colores.AMARILLO_BRIGHT + "P";
        }
    }
}
