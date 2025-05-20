package consola.juegoBuscaminas;


import java.util.Scanner;


public class JugarBuscamina {

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Selecciona nivel: 1-Fácil 2-Medio 3-Difícil");
        int nivel = sc.nextInt();
        Buscaminas juego = new Buscaminas(nivel);


        boolean continuar = true;
        while (continuar) {
            juego.mostrarTablero(juego.tableroVisible);
            System.out.print("Introduce fila, columna y 'b' si es bandera (ej: 3 4 b o 3 4): ");
            int x = sc.nextInt(), y = sc.nextInt();
            String tipo = sc.nextLine().trim();
            boolean ponerBandera = tipo.equalsIgnoreCase("b");


            continuar = juego.jugar(x, y, ponerBandera);
            if (juego.haGanado()) {
                juego.mostrarTablero(juego.tableroVisible);
                System.out.println("¡Felicidades, ganaste!");
                break;
            }
        }
    }
}
