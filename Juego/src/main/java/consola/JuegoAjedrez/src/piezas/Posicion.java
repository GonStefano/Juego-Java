package consola.JuegoAjedrez.src.piezas;

public class Posicion {
    private char x; // Horizontal o columnas (A-H)
    private int y; // Vertical o filas (1-8)

    public Posicion(char x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
