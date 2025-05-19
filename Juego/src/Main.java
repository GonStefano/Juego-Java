import juegoAventura.Juego;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("***********************************");
            System.out.println("*        MENÚ DE VIDEOJUEGOS      *");
            System.out.println("***********************************");
            System.out.println("1. Ajedrez");
            System.out.println("2. Buscaminas");
            System.out.println("3. Batalla por turno");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = teclado.nextInt();
                System.out.println();

                switch (opcion) {
                    case 1:
                        System.out.println("Ajedrez");

                        break;
                    case 2:
                        System.out.println("Buscaminas");
                        break;
                    case 3:
                        Juego j = new Juego();
                        j.iniciar(teclado);
                        break;
                    case 4:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                        break;
                }

                System.out.println();
            } catch (Exception e) {
                System.out.println("\n¡Cuidado! Debes introducir un número.");
                teclado.nextLine();
            }

        } while (opcion != 4);

        teclado.close();
    }
}