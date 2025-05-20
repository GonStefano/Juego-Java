package consola.aventura;

import consola.aventura.entidades.Jugador;
import consola.aventura.gestores.GestorDeJugadores;

import java.util.Scanner;

public class Juego {
    private GestorDeJugadores gestor;
    private Jugador jugador;

    public Juego(){
        gestor = new GestorDeJugadores();
        gestor.cargarJugadores();
    }

    /**
     * Metodo donde muestra el menu donde el jugador puede crear un heroe o iniciar sesion.
     * @param teclado Scanner solicitado.
     */
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
            } catch (Exception e) {
                teclado.nextLine();
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

    /**
     *Metodo que muestra el menu principal donde el jugador ya ha iniciado sesion, donde llama los metodos que estan dentro.
     * @param teclado Scanner solicitado.
     */
    private void mostrarMenuPrincipal(Scanner teclado){
        int op1;
        do {

            mostrarTitulo();
            mostrarMenu();

            System.out.print("▶ Elige una opción: ");
            try {
                op1 = teclado.nextInt();
            } catch (Exception e) {
                teclado.nextLine();
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

    /**
     * Muestra el tituto del juegoo.
     */
    private void mostrarTitulo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       [=] LEYENDAS DEL DESTINO [=]     ║");
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    /**
     * Muestra el menu despues de iniciar.
     */
    private void mostrarMenu() {
        System.out.println("🧭 MENÚ PRINCIPAL");
        System.out.println("1️⃣  Jugar");
        System.out.println("2️⃣  Habilidades");
        System.out.println("3️⃣  Estadisticas");
        System.out.println("4️⃣  Salir del reino");
    }

    /**
     * Muestra habilidades del jugador.
     */
    private void mostrarHabilidades(){

        System.out.println("      == HABILIDADES DE " + jugador.getNombre().toUpperCase() + " ==");
        System.out.println("----------------------------------------");
        jugador.getGestorHabilidades().mostrarHabilidades();
    }

    /**
     * Metodo que permite acceder a los datos del jugador mediante el atributo gestor.
     * @param teclado Scanner solicitado.
     */
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

    /**
     * Metodo que permite crear un jugador y almacenarlo en el archivo con el atributo gestor.
     * @param teclado Scanner solicitado.
     */
    private void crearNuevoJugador(Scanner teclado) {
        teclado.nextLine();
        int num=0;
        String nombre;
        String clase="";
        do {
            System.out.print("\n📝 Ingresa el nombre de tu héroe: ");
            nombre = teclado.nextLine();

            System.out.print("\n📝 Clases: \n");
            System.out.println("1️⃣  Guerrero");
            System.out.println("2️⃣  Mago");
            System.out.println("3️⃣  Ladron");
            System.out.println("4️⃣  Arquero");
            System.out.print("\n📝 Elige la clase de tu heroe: ");
            try {
                num = teclado.nextInt();
                teclado.nextLine();
            } catch (Exception e) {
                teclado.nextLine();
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
                case 4:
                    clase = "Arquero";
                    break;
                default:
                    System.out.println("Introduce un numero entre el 1-4");
                    break;
            }
        } while ( nombre.trim().isEmpty() || (num > 4 || num < 1));

        if (!gestor.verificarJugador(nombre)){
            if (clase.equalsIgnoreCase("Guerrero")){
                gestor.jugadores.add(new Jugador(nombre, clase, 100, 1, 40, 0));
                System.out.println("guerrero");
            } else if (clase.equalsIgnoreCase("Mago")) {
                gestor.jugadores.add(new Jugador(nombre, clase, 60, 1, 80, 0));
                System.out.println("mago");
            } else if (clase.equalsIgnoreCase("Ladron")) {
                gestor.jugadores.add(new Jugador(nombre, clase, 80, 1, 50, 0));
                System.out.println("ladron");
            } else if (clase.equalsIgnoreCase("Arquero")) {
                gestor.jugadores.add(new Jugador(nombre, clase, 75, 1, 60, 0));
                System.out.println("arquero");
            }
            gestor.guardarJugador();
            System.out.println("🎉 Héroe creado exitosamente. ¡Prepárate para la aventura!\n");

            jugador = gestor.buscarJugadorPorNombre(nombre);
        }
        else {
            System.out.println("\"❌ Héroe encontrado. El nombre ya fue usado\n\"");
        }
    }

    /**
     * Metodo que usa el gestor de jugadores para leer y mostrar las estadisticas.
     */
    private void mostrarEstadisticas() {
        System.out.println("\n📜 LEYENDAS DEL REINO:");
        gestor.ordenarJugadores();
        for (Jugador j : gestor.jugadores) {
            System.out.println("🧝 Nombre: " + j.getNombre() + " | Clase: " + j.getClase() + " | Nivel: " + j.getNivel() + " | EXP: " + j.getExperiencia() + " | Ultima vez: " + jugador.getUltimaVez());
        }
        System.out.println();
    }

    /**
     * Metodo que se encargga del sistema del juego.
     * @param teclado Scanner solicitado.
     */
    private void jugar(Scanner teclado) {
        System.out.println("juego");
    }

    private void salir() {
        System.out.println("\n🏰 El reino espera tu regreso, héroe.");
        System.out.println("Gracias por jugar ❤️\n");
    }
}
