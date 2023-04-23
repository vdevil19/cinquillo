/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;

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
            // no puede colocar ninguna carta en la mesa, se informa y pasa el turno 
            iu.sinCartasJugables();
        }
        else { 
            int carta = -1;
            // Preguntar qué carta colocar. Si es posible, se coloca en la mesa.
            do {
                carta = iu.pedirCarta(jugadores[jugadorActual]);
                
                if(mesa.esColocable(jugadores[jugadorActual].getCarta(carta - 1))) {
                    mesa.colocarCarta(jugadores[jugadorActual].jugarCarta(carta - 1));
                }
                // Si no es posible, se le informa y se le pide que escoja otra carta. 
                else {
                    iu.mostrarMensaje("Lo siento, no se puede jugar esa carta.");
                    carta = -1;
                }   
            } while(carta == -1);
        }
    }
    
    /** 
     * Muestra las cartas en la mesa y la mano del jugador, para el turno actual
     */
    private void infoTurno() {
        // Mostramos cartas en la mesa
        iu.mostrarMensaje("\n" + mesa.toString());

        // Mostramos jugador actual
        iu.mostrarMensaje("\nTurno del jugador: " + jugadores[jugadorActual].getNombre());
        iu.mostrarJugador(jugadores[jugadorActual], jugadorActual + 1);
    }
    
    /** 
     * Muestra el ganador de la partida
     */
    private void finPartida() {
        // Mostramos el nombre del ganador.
        iu.mostrarMensaje("\n\n");
        iu.mostrarMensaje(jugadores[ganador].getNombre() + " ha ganado la partida!\n");
    }
}