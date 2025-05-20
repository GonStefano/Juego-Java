package consola.aventura.gestores;

import consola.aventura.habilidades.Habilidad;
import consola.aventura.habilidades.HabilidadAtaque;
import consola.aventura.habilidades.HabilidadCuracion;
import consola.aventura.habilidades.HabilidadDefensa;

import java.io.Serializable;
import java.util.ArrayList;

public class GestorHabilidades implements Serializable {
    private ArrayList<Habilidad> habilidades;


    public GestorHabilidades() {
        this.habilidades = new ArrayList<>();
    }

    public GestorHabilidades(String clase) {
        this.habilidades = new ArrayList<>();

        configurar(clase);
    }

    private void configurar(String clase){
        if (clase.equalsIgnoreCase("Guerrero")){
            HabilidadAtaque corteImparable = new HabilidadAtaque("Corte Imparable", "Un ataque devastador que atraviesa la defensa del enemigo.", 10, 30);
            HabilidadCuracion grimorioGuerrero = new HabilidadCuracion("Grimorio del Guerrero", "Recupera vida al usar un libro antiguo de batalla.", 12, 20);
            HabilidadDefensa escudoHierro = new HabilidadDefensa("Escudo de Hierro", "Aumenta la defensa del guerrero durante el turno.", 8, 15);
            habilidades.add(corteImparable);
            habilidades.add(escudoHierro);
            habilidades.add(grimorioGuerrero);
        } else if (clase.equalsIgnoreCase("Mago")) {
            HabilidadAtaque bolaDeFuego = new HabilidadAtaque("Bola de Fuego", "Lanza una explosión de fuego que causa daño en área.", 15, 40);
            HabilidadCuracion curacionArcanica = new HabilidadCuracion("Curación Arcanica", "Un hechizo que sana a todos los aliados cercanos.", 20, 25);
            HabilidadDefensa escudoMistico = new HabilidadDefensa("Escudo Místico", "Crea una barrera mágica que reduce el daño recibido.", 18, 20);
            habilidades.add(bolaDeFuego);
            habilidades.add(escudoMistico);
            habilidades.add(curacionArcanica);

        } else if (clase.equalsIgnoreCase("Ladron")) {
            HabilidadAtaque golpeSutil = new HabilidadAtaque("Golpe Sutil", "Un golpe rápido y preciso que causa daño elevado.", 10, 25);
            HabilidadCuracion roboVida = new HabilidadCuracion("Robo de Vida", "Roba vida a un enemigo y la transfiere al ladrón.", 12, 20);
            HabilidadDefensa esquivaSombria = new HabilidadDefensa("Esquiva Sombría", "Aumenta las probabilidades de esquivar el próximo ataque.", 8, 30);
            habilidades.add(golpeSutil);
            habilidades.add(esquivaSombria);
            habilidades.add(roboVida);
        } else if (clase.equalsIgnoreCase("Arquero")) {
            HabilidadAtaque flechaEnvenenada = new HabilidadAtaque("Flecha Envenenada", "Dispara una flecha que envenena al enemigo y le causa daño por veneno.", 12, 25);
            HabilidadCuracion balsamoCurativo = new HabilidadCuracion("Bálsamo Curativo", "Aplica un bálsamo curativo que sana a un aliado.", 10, 15);
            HabilidadDefensa reflejoRapido = new HabilidadDefensa("Reflejo Rápido", "Aumenta la velocidad del arquero y permite esquivar mejor los ataques.", 12, 20);
            habilidades.add(flechaEnvenenada);
            habilidades.add(reflejoRapido);
            habilidades.add(balsamoCurativo);

        }
    }

    public  ArrayList<Habilidad> obtenerHabilidades(){
        ArrayList<Habilidad> hab = new ArrayList<>();
        for (Habilidad h : habilidades){
            hab.add(h);
        }
        return hab;
    }

    public void añadirHabilidad(Habilidad habilidad){
        habilidades.add(habilidad);
    }

    public void eliminarHabilidad(Habilidad habilidad){
        habilidades.remove(habilidad);
    }

    public void mostrarHabilidades(){
        for (Habilidad h : habilidades){
            h.imprimir();
        }
    }
}
