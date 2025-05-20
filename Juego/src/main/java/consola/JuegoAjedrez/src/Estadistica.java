
package consola.JuegoAjedrez.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;

public class Estadistica {
    public static void guardarEnArchivo(String ganador, int capturasBlancas, int capturasNegras) {
        try {
            FileWriter fw = new FileWriter("estadisticas.txt", true); // true para no destruir el archivo
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String ahora = LocalDateTime.now().format(dtf);

            fw.write("Fecha: " + ahora + " | Ganador: " + ganador + " | Capturas Blancas: " + capturasBlancas + " | Capturas Negras: " + capturasNegras + "\n");

            fw.close();
        } catch (Exception e) {
            System.out.println("Error al guardar estadísticas: " + e.getMessage());
        }
    }
}

