package juegoAventura.entidades;

import juegoAventura.gestores.GestorHabilidades;

import java.io.Serializable;

public class Entidad implements Serializable {

    protected String nombre;
    protected int vidaMaxima;
    protected int vidaActual;
    protected int mana;
    protected int defensa;
    protected int nivel;
    protected int experiencia;
    protected GestorHabilidades gestorHabilidades;

    public Entidad(String nombre, int nivel, int experiencia) {
        this.nombre = nombre;
        this.vidaMaxima = 100;
        this.vidaActual = vidaMaxima;
        this.mana = 100;
        this.defensa = 0;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.gestorHabilidades = new GestorHabilidades();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public GestorHabilidades getGestorHabilidades() {
        return gestorHabilidades;
    }


    public void imprimir(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Entidad{" +
                "nombre='" + nombre + '\'' +
                ", vidaMaxima=" + vidaMaxima +
                ", vidaActual=" + vidaActual +
                ", mana=" + mana +
                ", defensa=" + defensa +
                ", nivel=" + nivel +
                ", experiencia=" + experiencia +
                '}';
    }
}
