package consola.aventura.habilidades;

import java.io.Serializable;

public class HabilidadAtaque extends Habilidad implements Serializable {
    private  int daño;

    public HabilidadAtaque(String nombre, String descripcion, int mana, int daño) {
        super(nombre, descripcion, mana);
        this.daño=daño;
    }

    public int getDaño() {
        return daño;
    }

    public void imprimir(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "• " + nombre +
                "\n  → Daño: " + daño +
                "\n  → Maná: " + mana +
                "\n  → " + descripcion +
                "\n----------------------------------------";
    }
}
