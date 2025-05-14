package juegoAventura.Entidades;

public class Monstruo extends Entidad{

    public Monstruo(String nombre, int vida, int nivel, int experiencia) {
        super(nombre, nivel, experiencia);
        vidaMaxima = vida;
        vidaActual = vida;
    }



}
