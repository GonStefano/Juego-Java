package juegoAventura;

import juegoAventura.entidades.Jugador;
import juegoAventura.gestores.GestorDeJugadores;

import java.util.Scanner;

public class Juego {
    private GestorDeJugadores gestor;
    private Jugador jugador;

    public Juego(){
        gestor = new GestorDeJugadores();
        gestor.cargarJugadores();
    }

    public void iniciar(Scanner teclado) {
        int op1;
        do {
            System.out.println("\n¡Bienvenido a Leyendas del Destino!");

            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("              * Elige una opción *              ");
            System.out.println("                                                ");
            System.out.println("      1. 🆕 Crear un nuevo héroe                 ");
            System.out.println("      2. 🔄 Iniciar o Reanudar partida           ");
            System.out.println("      3. ❌ Salir                                ");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println();
            System.out.print("▶ Elige una opción: ");
            try {
                op1 = teclado.nextInt();
            } catch (NumberFormatException e) {
                op1 = -1;
            }
            switch (op1)
            {
                case 1:
                    crearNuevoJugador(teclado);
                    break;
                case 2:
                    iniciarSesion(teclado);
                    break;
                case 3:
                    System.out.println("Saliendo del juego");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intenta de nuevo.\n");
                    break;
            }
        }while (op1 != 3 && jugador == null);
        if (jugador != null){
            mostrarMenuPrincipal(teclado);
        }
    }

    private void mostrarMenuPrincipal(Scanner teclado){
        int op1;
        do {

            mostrarTitulo();
            mostrarMenu();

            System.out.print("▶ Elige una opción: ");
            try {
                op1 = teclado.nextInt();
            } catch (NumberFormatException e) {
                op1 = -1;
            }

            switch (op1) {
                case 1:
                    jugar(teclado);
                    break;
                case 2:
                    mostrarHabilidades();
                    break;
                case 3:
                    mostrarEstadisticas();
                    break;
                case 4:
                    salir();
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intenta de nuevo.\n");
            }

        } while (op1 != 4);
    }

    private void mostrarTitulo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       [=] LEYENDAS DEL DESTINO [=]     ║");
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    private void mostrarMenu() {
        System.out.println("🧭 MENÚ PRINCIPAL");
        System.out.println("1️⃣  Jugar");
        System.out.println("2️⃣  Ver las Habilidades");
        System.out.println("3️⃣  Ver leyendas guardadas");
        System.out.println("4️⃣  Salir del reino");
    }

    private void mostrarHabilidades(){

        System.out.println("      == HABILIDADES DE " + jugador.getNombre().toUpperCase() + " ==");
        System.out.println("----------------------------------------");
        jugador.getGestorHabilidades().mostrarHabilidades();
    }

    private void iniciarSesion(Scanner teclado) {
        String nombre ="";
        try {
            System.out.print("\n🔑 Nombre del héroe: ");
            teclado.nextLine();
            nombre = teclado.nextLine();
        }catch (Exception e){
            nombre = "";
        }

        if (!nombre.equals("")){
            jugador = gestor.buscarJugadorPorNombre(nombre);
        }

        if (jugador != null) {
            System.out.println("✨ Bienvenido de nuevo, valiente " + jugador.getNombre() + " (Nivel " + jugador.getNivel() + ")!");
        } else if (!nombre.isEmpty()){
            System.out.println("❌ Héroe no encontrado. ¿Quizás deberías crearlo primero?\n");
        }
    }

    private void crearNuevoJugador(Scanner teclado) {
        int num;
        System.out.print("\n📝 Ingresa el nombre de tu héroe: ");
        teclado.nextLine();
        String nombre = teclado.nextLine();
        String clase = "";
        do {
            System.out.print("\n📝 Clases: \n");
            System.out.println("1️⃣  Guerrero");
            System.out.println("2️⃣  Mago");
            System.out.println("3️⃣  Ladron");
            System.out.print("\n📝 Elige la clase de tu heroe: ");
            try {
                num = teclado.nextInt();
            } catch (Exception e) {
                num = -1;
            }

            switch (num) {
                case 1:
                    clase = "Guerrero";
                    break;
                case 2:
                    clase = "Mago";
                    break;
                case 3:
                    clase = "Ladron";
                    break;
                default:
                    System.out.println("Introduce un numero entre el 1-3");
                    break;
            }
        } while (clase.equalsIgnoreCase("") && num != -1);

        if (!gestor.verificarJugador(nombre)){
            gestor.jugadores.add(new Jugador(nombre, clase, 1, 0));
            gestor.guardarJugador();
            System.out.println("🎉 Héroe creado exitosamente. ¡Prepárate para la aventura!\n");

            jugador = gestor.buscarJugadorPorNombre(nombre);
        }
        else {
            System.out.println("\"❌ Héroe encontrado. El nombre ya fue usado\n\"");
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("\n📜 LEYENDAS DEL REINO:");
        gestor.ordenarJugadores();
        for (Jugador j : gestor.jugadores) {
            System.out.println("🧝 " + j.getNombre() + " | Clase: " + j.getClase() + " | Nivel: " + j.getNivel() + " | EXP: " + j.getExperiencia());
        }
        System.out.println();
    }

    private void jugar(Scanner teclado) {
        System.out.println("juego");
    }

    private void salir() {
        System.out.println("\n🏰 El reino espera tu regreso, héroe.");
        System.out.println("Gracias por jugar ❤️\n");
    }
}
