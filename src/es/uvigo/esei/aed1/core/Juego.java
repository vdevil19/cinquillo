/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Juego {

    private final IU iu;
    private Baraja baraja;
    private Mesa mesa;
    private int numeroJugadores;
    private Jugador[] jugadores;
    
    private int jugadorActual;
    private int ganador = -1;

    public Juego(IU iu) {
        this.iu = iu;
    }
    
    public void comenzarJuego() {
        // Leemos datos de jugadores y los creamos
        iniciarJugadores();
        
        // crear mesa y repartir cartas
        iniciarMesaYManos();
        
        // jugar partida
        jugar();
    }

    private void iniciarJugadores() {
        String[] datosJugadores = iu.pedirDatosJugadores();
        numeroJugadores = datosJugadores.length;
        
        jugadores = new Jugador[numeroJugadores];
        for (int i = 0; i < numeroJugadores; i++) {
            jugadores[i] = new Jugador(datosJugadores[i]);
        }
    }
    
    private void iniciarMesaYManos() {
        // Creamos mesa
        mesa = new Mesa();
        
        // Creamos y barajamos las cartas
        baraja = new Baraja();
        
        // Repartimos las cartas
        jugadorActual = 0;
        while(!baraja.estaVacia()) {
            jugadores[jugadorActual].cogerCarta(baraja.siguienteCarta());
            jugadorActual = (jugadorActual + 1) % numeroJugadores;
        }
    }
    
    private void jugar() {
        // iniciamos con jugador 1
        jugadorActual = 0;
        boolean partidaTerminada = false;
        
        // bucle principal del juego
        do {
            // Mostramos mesa y mano del jugador actual
            infoTurno();
            
            // juega el jugador actual
            turnoJugador();
            
            // si después de jugar no le quedan cartas, ha ganado
            if(jugadores[jugadorActual].cartasColocadas()) {
                partidaTerminada = true;
                ganador = jugadorActual;
            }
            
            // pasamos el turno al siguiente jugador
            jugadorActual = (jugadorActual + 1) % numeroJugadores;
        } while (!partidaTerminada);
        
        finPartida();
    }
    
    private void turnoJugador() {
        // el jugador no tiene cartas para jugar
        if(!jugadores[jugadorActual].puedeJugar(mesa)) {
            // Si el/la jugador/a activo/a no puede colocar ninguna carta en la mesa, se informa
            // y pasa el turno al siguiente jugador/a.
        }
        else {
            // Si el/la jugador/a activo/a puede colocar alguna carta en la mesa, se le debe
            // preguntar qué carta quiere colocar. Si es posible, se coloca en la mesa. Si no es
            // posible, se le informa y se le pide que escoja otra carta. Una vez colocada pasa el
            // turno al siguiente jugador/a.
        }
    }
    
    /** 
     * Muestra las cartas en la mesa y la mano del jugador, para el turno actual
     */
    private void infoTurno() {
        // Mostramos cartas en la mesa
        iu.mostrarMensaje("\n" + mesa.toString());

        // Mostramos jugador actual
        Random rand = new Random();
        jugadorActual = rand.nextInt(numeroJugadores);
        iu.mostrarMensaje("\nTurno del jugador: " + jugadores[jugadorActual].getNombre());
        iu.mostrarJugador(jugadores[jugadorActual], jugadorActual + 1);
    }
    
    /** 
     * Muestra el ganador de la partida
     */
    private void finPartida() {
        // Mostramos el nombre del ganador
    }
}
