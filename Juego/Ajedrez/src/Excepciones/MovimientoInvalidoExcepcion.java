package Excepciones;

public class MovimientoInvalidoExcepcion extends Exception {
    public MovimientoInvalidoExcepcion(String mensaje) {
        super(mensaje);
    }
    
    public MovimientoInvalidoExcepcion() {
        super("Movimiento inválido.");
    }
}