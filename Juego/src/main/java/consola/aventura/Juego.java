package consola.aventura;

import consola.aventura.entidades.Jugador;
import consola.aventura.entidades.Monstruo;
import consola.aventura.gestores.GestorDeJugadores;
import consola.aventura.habilidades.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            } else if (clase.equalsIgnoreCase("Mago")) {
                gestor.jugadores.add(new Jugador(nombre, clase, 60, 1, 80, 0));
            } else if (clase.equalsIgnoreCase("Ladron")) {
                gestor.jugadores.add(new Jugador(nombre, clase, 80, 1, 50, 0));
            } else if (clase.equalsIgnoreCase("Arquero")) {
                gestor.jugadores.add(new Jugador(nombre, clase, 75, 1, 60, 0));
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
        int op1;
        boolean suficienteMana = true;
        System.out.println("\n🌄 Te adentras en tierras peligrosas... ¡Prepárate para luchar!");
        Monstruo monstruo = obtenerMonstruo();

        System.out.println("\nHa aparecido " + monstruo.getNombre() + "!\n");
        reestablecerJugador();
        do {
            suficienteMana = true;
            System.out.println("                                               " + jugador.getNombre() + ": " + " ❤️: " + jugador.getVidaActual() + " 🛡️: " + jugador.getDefensa() + " 🌀: " + jugador.getMana() + "   " + monstruo.getNombre() + ": " + " ❤️: " + monstruo.getVidaActual() + " 🛡️: " + monstruo.getDefensa() + " 🌀: " + monstruo.getMana());
            System.out.println("Turno de " + jugador.getNombre() + ":\n");

            System.out.println("1." + jugador.getGestorHabilidades().habilidades.get(0).getNombre() + "  (Daño)" + jugador.getGestorHabilidades().habilidades.get(0).getMana());
            System.out.println("2." + jugador.getGestorHabilidades().habilidades.get(1).getNombre() + " (Defensa)" + jugador.getGestorHabilidades().habilidades.get(1).getMana());
            System.out.println("3." + jugador.getGestorHabilidades().habilidades.get(2).getNombre() + " (Curación)" + jugador.getGestorHabilidades().habilidades.get(2).getMana());

            System.out.print("\nElige una habilidad:");
            try {
                op1 = teclado.nextInt();
            }catch (Exception e){
                op1 = 4;
            }
            switch (op1)
            {
                case 1:

                    HabilidadAtaque ataque = (HabilidadAtaque) jugador.getGestorHabilidades().habilidades.get(0);
                    suficienteMana = jugador.atacar(monstruo, ataque);

                    break;

                case 2:

                    HabilidadDefensa defensa = (HabilidadDefensa) jugador.getGestorHabilidades().habilidades.get(1);
                    suficienteMana = jugador.defender(defensa);
                    break;

                case 3:

                    HabilidadCuracion curacion = (HabilidadCuracion) jugador.getGestorHabilidades().habilidades.get(2);
                    suficienteMana = jugador.curar(curacion);
                    break;
                default:

                    System.out.println("Introduce un numero entre 1-3");
                    break;
            }

            if (monstruo.getVidaActual() != 0 && suficienteMana){

                Habilidad habilidad = monstruo.obtenerHabilidad();

                if (habilidad instanceof HabilidadAtaque){

                    HabilidadAtaque ataque = (HabilidadAtaque) habilidad;
                    monstruo.atacar(jugador, ataque);

                } else if (habilidad instanceof  HabilidadDefensa) {

                    HabilidadDefensa defensa = (HabilidadDefensa) habilidad;
                    monstruo.defender(defensa);

                } else if (habilidad instanceof  HabilidadCuracion) {

                    HabilidadCuracion curacion = (HabilidadCuracion) habilidad;
                    monstruo.curar(curacion);
                }
            }
            recuperarMana();
            monstruo.setMana(monstruo.getMana()+6);
        }while (monstruo.getVidaActual() > 0  && jugador.getVidaActual() > 0);

        if (jugador.getVidaActual() == 0){

            System.out.println(monstruo.getNombre() + " ha derrotado a " + jugador.getNombre());

        } else if (monstruo.getVidaActual() == 0){

            System.out.println(jugador.getNombre() + " ha derrotado al " + monstruo.getNombre() + " de nivel " + monstruo.getNivel());
            jugador.subirNivel(monstruo.getExperiencia());
            LocalDateTime ahora = LocalDateTime.now();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String fechaFormateada = ahora.format(formato);
            jugador.setUltimaVez(fechaFormateada);
            gestor.actualizarJugador(jugador);

        }
    }


    /**
     * Metodo que no recibe nada y devuelve un monstruo que esta dentro de la array.
     * @return un objeto monstruo.
     */
    private Monstruo obtenerMonstruo(){
        ArrayList<Monstruo> monstruos = new ArrayList<>();
        Monstruo duende = new Monstruo("Duende", 45, 3, 30, 25);
        duende.getGestorHabilidades().añadirHabilidad(
                new HabilidadAtaque("Puñalada Sorpresa", "Un ataque rápido desde las sombras.", 5, 10));
        duende.getGestorHabilidades().añadirHabilidad(
                new HabilidadDefensa("Evasión Ágil", "Se mueve velozmente para evitar el próximo ataque.", 4, 8));
        duende.getGestorHabilidades().añadirHabilidad(
                new HabilidadCuracion("Hierbas Salvajes", "Utiliza hierbas del bosque para curar heridas leves.", 6, 12));

        Monstruo esqueleto = new Monstruo("Esqueleto", 35, 2, 25, 20);
        esqueleto.getGestorHabilidades().añadirHabilidad(
                new HabilidadAtaque("Flecha Ósea", "Dispara un hueso afilado como proyectil.", 6, 12));
        esqueleto.getGestorHabilidades().añadirHabilidad(
                new HabilidadDefensa("Esqueleto Reforzado", "Sus huesos se compactan para resistir más daño.", 5, 10));
        esqueleto.getGestorHabilidades().añadirHabilidad(
                new HabilidadCuracion("Rearme", "Se recompone ensamblando piezas sueltas.", 5, 10));

        Monstruo zombi = new Monstruo("Zombi", 60, 2, 20, 18);
        zombi.getGestorHabilidades().añadirHabilidad(
                new HabilidadAtaque("Golpe Lento", "Un ataque torpe pero potente.", 4, 11));
        zombi.getGestorHabilidades().añadirHabilidad(
                new HabilidadDefensa("Carne Putrefacta", "Su cuerpo descompuesto absorbe algo del daño.", 3, 6));
        zombi.getGestorHabilidades().añadirHabilidad(
                new HabilidadCuracion("Consumir Restos", "Se alimenta para recuperar vida.", 5, 10));

        Monstruo slime = new Monstruo("Slime", 25, 1, 15, 12);
        slime.getGestorHabilidades().añadirHabilidad(
                new HabilidadAtaque("Salpicadura Ácida", "Lanza ácido que daña lentamente al enemigo.", 3, 7));
        slime.getGestorHabilidades().añadirHabilidad(
                new HabilidadDefensa("Gel Reforzado", "Endurece su cuerpo gelatinoso para resistir daño.", 3, 6));
        slime.getGestorHabilidades().añadirHabilidad(
                new HabilidadCuracion("Regeneración Viscosa", "Su masa viscosa se regenera lentamente.", 4, 8));

        Monstruo fantasma = new Monstruo("Fantasma", 50, 4, 40, 35);
        fantasma.getGestorHabilidades().añadirHabilidad(
                new HabilidadAtaque("Toque Espectral", "Drena la energía vital del enemigo.", 8, 18));
        fantasma.getGestorHabilidades().añadirHabilidad(
                new HabilidadDefensa("Forma Etérea", "Se vuelve parcialmente intangible.", 7, 15));
        fantasma.getGestorHabilidades().añadirHabilidad(
                new HabilidadCuracion("Absorber Almas", "Recupera vida robando energía de su entorno.", 9, 18));

        Monstruo dragon = new Monstruo("Dragón", 120, 6, 60, 60);
        dragon.getGestorHabilidades().añadirHabilidad(
                new HabilidadAtaque("Aliento de Fuego", "Lanza una gran llamarada.", 12, 30));
        dragon.getGestorHabilidades().añadirHabilidad(
                new HabilidadDefensa("Escamas de Diamante", "Endurece sus escamas para volverse casi impenetrable.", 10, 25));
        dragon.getGestorHabilidades().añadirHabilidad(
                new HabilidadCuracion("Furia de Vida", "Activa su poder interno para regenerar grandes heridas.", 14, 28));

        monstruos.add(slime);
        monstruos.add(duende);
        monstruos.add(esqueleto);
        monstruos.add(zombi);
        monstruos.add(fantasma);
        monstruos.add(dragon);

        return monstruos.get((int)(Math.random() * monstruos.size()));
    }

    /**
     * Permite recuperar mana dependiendo de la clase del jugador
     */
    private void recuperarMana(){
        if (jugador.getClase().equalsIgnoreCase("Guerrero")){
            jugador.setMana(jugador.getMana()+8);
        }else if (jugador.getClase().equalsIgnoreCase("Mago")){
            jugador.setMana(jugador.getMana()+15);
        }else if (jugador.getClase().equalsIgnoreCase("Ladron")){
            jugador.setMana(jugador.getMana()+8);
        }else if (jugador.getClase().equalsIgnoreCase("Arquero")){
            jugador.setMana(jugador.getMana()+9);
        }
    }

    /**
     * Metodo que restablece la vida, mana y defensa del juegador.
     */
    private void reestablecerJugador(){
        jugador.setVidaActual(jugador.getVidaMaxima());
        jugador.setMana(jugador.getManaMax());
        jugador.setDefensa(0);
    }


    private void salir() {
        System.out.println("\n🏰 El reino espera tu regreso, héroe.");
        System.out.println("Gracias por jugar ❤️\n");
    }
}
