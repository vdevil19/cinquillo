package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Jugador;
import java.util.Scanner;

/**
 * Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una entrada/salida en modo texto 
 * Se recomienda una implementación modular.
 * 
 * @author Grupo 2Espadas
 */
public class IU {
    private final Scanner teclado;

    public IU() {
        teclado = new Scanner(System.in).useDelimiter("\r?\n");
    }

    /**
     * Lee un num. de teclado
     * 
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public int leeNum(String msg) {
        while (true) {
            String str = leeString(msg);
            
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException _e) {
                System.out.println("Entrada no válida. Debe ser un entero.");
            }
        }
    }

    /**
     * Lee un número en el intervalo [min, max_ex)
     * 
     * @param msg El mensaje a visualizar
     * @param min Valor mínimo del intervalo, inclusivo
     * @param max_ex Valor mínimo del intervalo, exclusivo
     * @return El num., como entero
     */
    public int leerIntRango(String msg, int min, int max_ex) {
        int x = min - 1;
        while (x < min || x >= max_ex) {
            x = leeNum(msg);
            if (x < min || x >= max_ex) { // Tener que comprobar dos veces la condición es malo (se podría usar un break pero bueno)
                System.err.println(String.format("Número incorrecto. El valor debe estar entre %d y %d.", min, max_ex - 1));
            }
        }
        return x;
    }

    /**
     * Lee una cadena
     * 
     * @param msg El mensaje a visualizar
     * @return Cadena leída
     */
    public String leeString(String msg) {
        System.out.print(msg);
        return teclado.next();
    }

    /**
     * Lee una cadena
     * 
     * @param args Mensajes a visualizar
     * @return Cadena leída
     */
    public String leeString(String msg, Object... args) {
        System.out.printf(msg, args);
        return teclado.next();
    }

    /**
     * Muestra un mensaje en pantalla
     * 
     * @param msg Mensaje a visualizar
     */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * Muestra varios mensaje en pantalla
     * 
     * @param args Mensajes a visualizar
     */
    public void mostrarMensaje(String msg, Object... args) {
        System.out.printf(msg, args);
    }

    /**
     * Lee el número jugadores participantes y sus nombres
     * 
     * @return Array con los nombres de los jugadores
     */
    public String[] pedirDatosJugadores(){
        int numJugadores = leerIntRango("Introduce el número de jugadores (3 / 4): ", 3, 5);
        
        String[] nombres = new String[numJugadores];
        for (int i = 0; i < numJugadores; i++) {
            nombres[i] = leeString("Nombre del jugador " + (i + 1) + ": ");
        }
        
        return nombres;
    }

    /**
     * Muestra información de un jugador
     * 
     * @param jugador Jugador que queremos mostrar
     * @param turno Posición del jugador
     */
    public void mostrarJugador(Jugador jugador, int turno){
        System.out.println(jugador.toString(turno));
    }

    /**
     * Muestra los datos de los jugadores
     * 
     * @param jugadores Array con los jugadores de la partida
     */
    public void mostrarJugadores(Jugador[] jugadores){
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println(jugadores[i].toString(i + 1));
        }
    }
    
    /**
     * Muestra un aviso indicando que el jugador no puede jugar y
     * espera a que se pulse intro
     */
    public void sinCartasJugables() {
        mostrarMensaje("No tienes ninguna carta para jugar.");
        leeString("Pulsa intro para terminar el turno.");
    }
    
    /**
     * Lee el número de carta que el jugador quiere jugar
     * 
     * @param j Jugador que jugará la carta
     * @return Número de orden de la carta
     */
    public int pedirCarta(Jugador j){
        int numCarta = leerIntRango("Introduce el número de carta a jugar: ", 
                1, j.getTamañoMano() + 1);
        
        return numCarta;
    }
}
