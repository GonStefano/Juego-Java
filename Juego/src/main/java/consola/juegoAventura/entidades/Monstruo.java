package consola.juegoAventura.entidades;

import consola.juegoAventura.gestores.GestorHabilidades;
import consola.juegoAventura.habilidades.*;

import java.util.ArrayList;

public class Monstruo extends Entidad{

    public Monstruo(String nombre, int vida, int nivel, int mana, int experiencia) {
        super(nombre, nivel, mana, experiencia);
        vidaMaxima = vida;
        vidaActual = vida;
        this.gestorHabilidades = new GestorHabilidades();;
    }

    /**
     * Metodo que devuelve la habilidad del monstruo de forma aleatoria.
     * @return una habilidad de forma aleatoria.
     */
    public Habilidad obtenerHabilidad(){
        double random = Math.random();
        ArrayList<Habilidad> hab = gestorHabilidades.obtenerHabilidades();
        for (Habilidad h : hab){
            if (random<=0.6){
                if (h instanceof HabilidadAtaque){
                    return h;
                }
            } else if (random > 0.6 && random<=0.8) {
                if (h instanceof HabilidadDefensa){
                    return h;
                }
            }else if (random > 0.8){
                if ((h instanceof HabilidadCuracion)){
                    return h;
                }
            }
        }
        return null;
    }

    @Override
    public void imprimir() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Monstruo{" +
                "nombre='" + nombre + '\'' +
                ", vidaMaxima=" + vidaMaxima +
                ", vidaActual=" + vidaActual +
                ", mana=" + mana +
                ", habilidades=" + gestorHabilidades +
                ", defensa=" + defensa +
                ", nivel=" + nivel +
                ", experiencia=" + experiencia +
                '}';
    }

}
