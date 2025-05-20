package consola.aventura.entidades;

import consola.aventura.gestores.GestorHabilidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Jugador extends Entidad implements Serializable {
    private String clase;
    private String ultimaVez;

    public Jugador(String nombre, String clase, int vida, int nivel, int mana, int experiencia) {
        super(nombre, nivel, mana, experiencia);
        this.clase=clase;
        gestorHabilidades = new GestorHabilidades(clase);
        super.vidaMaxima=vida;
        super.vidaActual=vida;
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        ultimaVez = ahora.format(formato);
    }

    public String getClase() {
        return clase;
    }

    public String getUltimaVez() {
        return ultimaVez;
    }

    public void setUltimaVez(String ultimaVez) {
        this.ultimaVez = ultimaVez;
    }

    public void imprimir(){
        System.out.println(toString());
    }

    /**
     * Encargado de subir el nivel del jugador.
     * @param experienciaGanada experiencia que gana mientras mata monstruos.
     * @return retorna un booleano si es verdadero
     */
    public boolean subirNivel(int experienciaGanada){
        experiencia+=experienciaGanada;
        boolean verificar = false;
        while (nivel < 10 && experiencia >= requerirExperiencia(nivel)) {
            experiencia = experiencia - requerirExperiencia(nivel);
            nivel++;
            System.out.println("Has subido al nivel " + nivel + "!");
            verificar = true;
        }
        return verificar;
    }

    /**
     * Devuelve la experiencia que se necesita para subir de nivel
     * @param nivel Nivel para ver la experiencia necesaria para subir de nivel.
     * @return La experiencia que se requiere.
     */
    public int requerirExperiencia(int nivel){
        int[] niveles = {0,100,200,350,500,700,950,1250,1600,2000};
        if (nivel >= 0 && nivel < niveles.length) {
            return niveles[nivel];
        } else {
            return 1000000000;
        }
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
