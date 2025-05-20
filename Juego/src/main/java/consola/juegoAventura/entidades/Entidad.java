package consola.juegoAventura.entidades;

import consola.juegoAventura.gestores.GestorHabilidades;
import consola.juegoAventura.habilidades.*;

import java.io.Serializable;

public class Entidad implements Serializable {

    protected String nombre;
    protected int vidaMaxima;
    protected int vidaActual;
    protected int mana;
    protected int manaMax;
    protected int defensa;
    protected int nivel;
    protected int experiencia;
    protected GestorHabilidades gestorHabilidades;

    public Entidad(String nombre, int nivel, int mana, int experiencia) {
        this.nombre = nombre;
        this.mana = mana;
        manaMax = mana;
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

    public int getManaMax() {
        return manaMax;
    }

    public GestorHabilidades getGestorHabilidades() {
        return gestorHabilidades;
    }

    /**
     * Se encarga de atacar a la entidad que se solicita.
     * @param enemigo Monstruo al que queremos atacar.
     * @param ataque Habilidad que vamos a usar conta el enemigo
     * @return
     */
    public boolean atacar(Entidad enemigo, HabilidadAtaque ataque){
        if (getMana()<ataque.getMana()){
            System.out.println("No tienes suficiente mana para esta habilidad");
            return false;
        }
        if (enemigo.getDefensa() > 0){
            if (enemigo.getDefensa() > ataque.getDaño()){
                setMana(getMana()- ataque.getMana());
                enemigo.setDefensa(enemigo.getDefensa() - ataque.getDaño());
            }
            else if (enemigo.getDefensa() <= ataque.getMana()){
                setMana(getMana() - ataque.getMana());
                enemigo.setVidaActual(enemigo.getVidaActual() - (ataque.getDaño() - enemigo.getDefensa()));
                enemigo.setDefensa(0);
            }
        }else {
            enemigo.setVidaActual(enemigo.getVidaActual() - ataque.getDaño());
            setMana(getMana()-ataque.getMana());
        }

        if (enemigo.getVidaActual()<0){
            enemigo.setVidaActual(0);
        }
        System.out.println(nombre + " ha atacado con " + ataque.getNombre() + " y le ha causado " + ataque.getDaño() + " de daño");
        return true;
    }

    /**
     * Se encarga de poner defensa en el jugador
     * @param defensa Habilidad que se aplica en el jugador
     * @return retorna un booleano, verdadero si se ha completadom y falso si no se ha podido usar la habilidad.
     */
    public boolean defender(HabilidadDefensa defensa){
        if (getMana() < defensa.getMana()){
            return false;
        }
        setDefensa(defensa.getDefensa());
        setMana(getMana() - defensa.getMana());
        System.out.println(nombre + " ha usado " + defensa.getNombre());
        return true;
    }

    /**
     * Se encarga de curar al jugador
     * @param curar Habilidad que se aplica en el jugador
     * @return retorna un booleano, verdadero si se ha completadom y falso si no se ha podido curar.
     */
    public boolean curar(HabilidadCuracion curar){
        System.out.println(nombre + " ha usado " + curar.getNombre());
        setVidaActual(vidaActual + curar.getCura());

        if (getMana() >= curar.getMana()){
            if (vidaActual > vidaMaxima){

                setVidaActual(vidaMaxima);
                System.out.println(nombre + " ha recuperado toda la vida!");

            }else {

                System.out.println(nombre + " tiene " + vidaActual + " de salud");

            }
            return  true;
        }
        System.out.println("No tienes suficiente mana para esta habilidad");
        return false;
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
