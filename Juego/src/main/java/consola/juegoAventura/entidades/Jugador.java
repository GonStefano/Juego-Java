package consola.juegoAventura.entidades;

import consola.juegoAventura.gestores.GestorHabilidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Jugador extends Entidad implements Serializable {
    private String clase;
    private String ultimaVez;

    public Jugador(String nombre, String clase, int nivel, int experiencia) {
        super(nombre, nivel, experiencia);
        this.clase=clase;
        gestorHabilidades = new GestorHabilidades(clase);

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        ultimaVez = ahora.format(formato);
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
