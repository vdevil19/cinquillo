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
    private int numeroJugadores;
    private Jugador[] jugadores;
    
    private int jugadorActual;

    public Juego(IU iu) {
        this.iu = iu;
    }

    public void jugar() {
        // Leemos datos de jugadores y los creamos
        String[] datosJugadores = iu.pedirDatosJugadores();
        numeroJugadores = datosJugadores.length;
        
        jugadores = new Jugador[numeroJugadores];
        for (int i = 0; i < numeroJugadores; i++) {
            jugadores[i] = new Jugador(datosJugadores[i]);
        }
        
        // Creamos y barajamos las cartas
        baraja = new Baraja();
        
        // Repartimos las cartas
        jugadorActual = 0;
        while(!baraja.estaVacia()) {
            jugadores[jugadorActual].cogerCarta(baraja.siguienteCarta());
            jugadorActual = (jugadorActual + 1) % numeroJugadores;
        }
        
        infoTurno();
    }
    
    /** 
     * Muestra las cartas en la mesa y la mano del jugador, para el turno actual
     */
    private void infoTurno() {
        // Mostramos cartas en la mesa
        iu.mostrarMensaje("\n");
        iu.mostrarMensaje("-".repeat(80));        
        iu.mostrarMensaje("| Oros:    ");
        iu.mostrarMensaje("|");
        iu.mostrarMensaje("| Copas:   ");
        iu.mostrarMensaje("|");
        iu.mostrarMensaje("| Espadas: ");
        iu.mostrarMensaje("|");
        iu.mostrarMensaje("| Bastos:  ");
        iu.mostrarMensaje("-".repeat(80));

        // Mostramos jugador actual
        Random rand = new Random();
        jugadorActual = rand.nextInt(numeroJugadores);
        iu.mostrarMensaje("\nTurno del jugador: " + jugadores[jugadorActual].getNombre());
        iu.mostrarJugador(jugadores[jugadorActual], jugadorActual + 1);
    }
}
