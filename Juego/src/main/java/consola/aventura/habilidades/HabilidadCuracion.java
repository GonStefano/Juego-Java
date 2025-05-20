package consola.aventura.habilidades;

import java.io.Serializable;

public class HabilidadCuracion extends Habilidad implements Serializable {
    private int cura;

    public HabilidadCuracion(String nombre, String descripcion, int mana, int cura) {
        super(nombre, descripcion, mana);
        this.cura=cura;
    }

    public int getCura() {
        return cura;
    }

    public void setCura(int cura) {
        this.cura = cura;
    }

    @Override
    public void imprimir() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "• " + nombre +
                "\n  → Cura: " + cura +
                "\n  → Maná: " + mana +
                "\n  → " + descripcion +
                "\n----------------------------------------";
    }
}
