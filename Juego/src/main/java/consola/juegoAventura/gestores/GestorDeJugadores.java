package consola.juegoAventura.gestores;

import consola.juegoAventura.entidades.Jugador;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GestorDeJugadores {
    public  ArrayList<Jugador> jugadores;

    public GestorDeJugadores(){
        jugadores = new ArrayList<>();
    }

    /**
     * Metodo que crea o carga la ArrayList que se guarda en el archivo jugadores.dat y lo almacena en el atributo jugadores(ArrayList)
     * @return La ArrayList que se ha guardado con el metodo guardarJugador.
     */
    public ArrayList<Jugador> cargarJugadores(){
        try {
            File archivo = new File("jugadores.dat");
            if (archivo.exists()){
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo));
                jugadores = (ArrayList<Jugador>) in.readObject();
                in.close();
                ordenarJugadores();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jugadores;
    }

    /**
     * Metodo que guarda los jugadores que estan en el atributo jugadores(ArrayList)
     */
    public void guardarJugador(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("jugadores.dat"));
            out.writeObject(jugadores);
            out.close();
        } catch (Exception e){
            System.out.println("Problema en el metodo de guardarJugador");
            e.printStackTrace();
        }
    }

    /**
     * Metodo que busca el jugador por el nombre.
     * @param nombre Nombre del jugador
     * @return Devuelve el jugador
     */
    public Jugador buscarJugadorPorNombre(String nombre){
        for (Jugador j : jugadores){
            if (j.getNombre().equalsIgnoreCase(nombre)){
                return j;
            }
        }
        return null;
    }

    /**
     * Metodo que verifica si un jugador esta o no en el ArrayList
     * @param nombre Nombre del jugador
     * @return Devuelve true si ha encontra al jugador y lo contrario si es false.
     */
    public boolean verificarJugador(String nombre){
            for (Jugador j : jugadores){
                if (j.getNombre().equalsIgnoreCase(nombre)){
                    return true;
                }
            }
        return false;
    }

    /**
     * Metodo que actualiza las estadisticas del jugador que juega y actualiza en el ArrayList y en el archivo.dat
     * @param jugador Jugador que esta jugando
     */
    public void actualizarJugador(Jugador jugador){
        boolean cerrar = false;
        for (int i=0 ; i<jugadores.size() && !cerrar ; i++){
            Jugador j = jugadores.get(i);
            if (j.getNombre().equalsIgnoreCase(jugador.getNombre())){
                jugadores.set(i,jugador);
                guardarJugador();
                cerrar = true;
            }
        }
    }

    /**
     * Metodo que puede ordenar los jugadores de mayor a menor por nivel y experiencia
     */
    public void ordenarJugadores(){
        jugadores.sort(Comparator.comparingInt(Jugador::getNivel).reversed().thenComparing(Comparator.comparingInt(Jugador::getExperiencia).reversed()));
    }
}
