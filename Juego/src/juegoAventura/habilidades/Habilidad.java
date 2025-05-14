package juegoAventura.habilidades;

public class Habilidad {
    protected String nombre;
    protected int mana;
    protected String descripcion;

    public Habilidad(String nombre, String descripcion, int mana) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.mana=mana;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void imprimir(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Habilidad{" +
                "nombre='" + nombre + '\'' +
                ", mana=" + mana +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
