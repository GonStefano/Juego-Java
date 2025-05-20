package grafico.space.entidades;

public class Nave extends Entidad{
    protected  String imagenVida;
    protected int vida = 2;

    public Nave(String imagen, String imagenVida) {
        super(imagen);
        this.imagenVida = imagenVida;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagenVida() {
        return imagenVida;
    }

    public void setImagenVida(String imagenVida) {
        this.imagenVida = imagenVida;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void quitarVida(){
        vida--;
    }
}
