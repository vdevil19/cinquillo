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
    private ArrayList<Jugador> jugadores;
    
    private int jugadorActual;

    public Juego(IU iu) {
        this.iu = iu;

    }

    public void jugar() {
        // Leemos datos de jugadores y los creamos
        Collection<String> datosJugadores = iu.pedirDatosJugadores();
        jugadores = new ArrayList<>();
        int numero = 1;
        for (String dj : datosJugadores) {
            jugadores.add(new Jugador(numero++, dj));
        }
        numeroJugadores = jugadores.size();
        
        // Creamos y barajamos las cartas
        baraja = new Baraja();
        
        // Repartimos las cartas
        jugadorActual = 0;
        while(!baraja.estaVacia()) {
            jugadores.get(jugadorActual)
                    .cogerCarta(baraja.siguienteCarta());
            jugadorActual = (jugadorActual + 1) % numeroJugadores;
        }
        
        // Mostramos jugadores y sus manos
        iu.mostrarMensaje("\n");
        iu.mostrarJugadores(jugadores);
        
        // Mostramos jugador actual
        Random rand = new Random();
        jugadorActual = rand.nextInt(numeroJugadores);
        iu.mostrarMensaje("\nTurno del jugador: " 
                + jugadores.get(jugadorActual).getNombre());
    }
}
