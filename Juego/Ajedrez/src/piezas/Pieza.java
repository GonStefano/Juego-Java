package piezas;

public abstract class Pieza {
    protected boolean color; // las negras serán false y las blancas true
    protected Posicion posicion;

    public Pieza(boolean color, Posicion posicion) {
        this.color = color;
        this.posicion = posicion;
    }

    public abstract String mostrarSimbolo();

    public boolean getColor() {
        return color;
    }
    public Posicion getPosicion() {
        return posicion;
    }
    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
}
