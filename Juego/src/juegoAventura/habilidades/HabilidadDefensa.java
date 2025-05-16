package juegoAventura.habilidades;

import java.io.Serializable;

public class HabilidadDefensa extends Habilidad implements Serializable {
    protected int defensa;

    public HabilidadDefensa(String nombre, String descripcion,int mana, int  defensa) {
        super(nombre, descripcion, mana);
        this.defensa=defensa;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    @Override
    public void imprimir() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "• " + nombre +
                "\n  → Defensa: " + defensa +
                "\n  → Maná: " + mana +
                "\n  → " + descripcion +
                "\n----------------------------------------";
    }
}
