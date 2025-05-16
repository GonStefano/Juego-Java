package juegoAventura.entidades;

import juegoAventura.gestores.GestorHabilidades;

import java.io.Serializable;

public class Jugador extends Entidad implements Serializable {
    private String clase;

    public Jugador(String nombre, String clase, int nivel, int experiencia) {
        super(nombre, nivel, experiencia);
        this.clase = clase;
        gestorHabilidades = new GestorHabilidades(clase);
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void imprimir(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", vidaMaxima=" + vidaMaxima +
                ", clase='" + clase + '\'' +
                ", mana=" + mana +
                ", defensa=" + defensa +
                ", nivel=" + nivel +
                ", experiencia=" + experiencia +
                '}';
    }
}
