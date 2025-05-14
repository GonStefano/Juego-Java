package juegoAventura;

import java.io.Serializable;

public class Jugador extends Entidad implements Serializable {
    private String clase;

    public Jugador(String nombre, String clase, int nivel, int experiencia) {
        super(nombre, nivel, experiencia);
        this.clase = clase;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
}
