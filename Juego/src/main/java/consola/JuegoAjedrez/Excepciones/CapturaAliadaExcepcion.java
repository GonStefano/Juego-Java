package consola.JuegoAjedrez.Excepciones;

public class CapturaAliadaExcepcion extends Exception {
    public CapturaAliadaExcepcion(String mensaje) {
        super(mensaje);
    }

    public CapturaAliadaExcepcion() {
        super("No puedes capturar a tu propia pieza");
    }
}
