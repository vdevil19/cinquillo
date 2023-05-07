package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;

// TODO javadocs clase y métodos
/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 * 
 * @author Grupo XXXX
 */
public class Juego {
    private static final int PUNTOS_PARTIDA = 4;
    private static final int PUNTOS_AS = 2;


    // Atributos
    private final IU iu;
    private Baraja baraja;
    private Mesa mesa;
    private Jugador[] jugadores;
    
    private int jugadorActual;
    private int partidas;
    private boolean asOros;

    // Constructor
    public Juego(IU iu) {
        this.iu = iu;
        partidas = 0;
        asOros = true;
    }
    
    public void comenzarJuego() {
        // Leemos datos de jugadores y los creamos
        iniciarJugadores();
        
        // comenzamos una partida
        nuevaPartida();
    }

    private void iniciarJugadores() {
        String[] datosJugadores = iu.pedirDatosJugadores();
        
        jugadores = new Jugador[datosJugadores.length];
        for (int i = 0; i < datosJugadores.length; i++) {
            jugadores[i] = new Jugador(datosJugadores[i]);
        }
        
        // TODO: sortear jugador inicial
        jugadorActual = 0;
    }
    
    public void nuevaPartida() {
        partidas++;
        
        // crear mesa y repartir cartas
        iniciarMesaYManos();
        
        // jugar partida
        jugar();
    }
    
    private void iniciarMesaYManos() {
        // Creamos mesa
        mesa = new Mesa();
        
        // Creamos y barajamos las cartas
        baraja = new Baraja();
        baraja.barajarCartas();
        // Repartimos las cartas
        int jugador = 0;
        while(!baraja.estaVacia()) {
            jugadores[jugador].cogerCarta(baraja.siguienteCarta());
            jugador = (jugador + 1) % jugadores.length;
        }
    }
    
    private void jugar() {
        // iniciamos la partida con el jugador actual
        boolean partidaTerminada = false;
        
        // bucle principal del juego
        do {
            // Mostramos mesa y mano del jugador actual
            infoTurno(jugadorActual);
            
            // juega el jugador actual
            turnoJugador(jugadorActual);
            
            // si después de jugar no le quedan cartas, ha ganado
            if(jugadores[jugadorActual].cartasColocadas()) {
                partidaTerminada = true;
                jugadores[jugadorActual].sumarPuntos(PUNTOS_PARTIDA);
            }
            
            // pasamos el turno al siguiente jugador
            jugadorActual = (jugadorActual + 1) % jugadores.length;
        } while (!partidaTerminada);
        
        finPartida();
    }
    
    private void turnoJugador(int jugadorActual) {
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
                    Carta c = jugadores[jugadorActual].jugarCarta(carta - 1);
                    mesa.colocarCarta(c);
                    // Si hemos jugado el as de oros lo anotamos para terminar el juego
                    if(c.getNumero() == 1 && c.getPalo() == Carta.Palo.OROS) {
                        asOros = true;
                        jugadores[jugadorActual].sumarPuntos(PUNTOS_AS * partidas);
                    }
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
    private void infoTurno(int jugadorActual) {
        // Mostramos info de partida
        iu.mostrarMensaje("\nPartida " + partidas);
        
        // Mostramos cartas en la mesa
        iu.mostrarMensaje(mesa.toString());

        // Mostramos jugador actual
        iu.mostrarMensaje("\nTurno del jugador: " 
                + jugadores[jugadorActual].getNombre() + " (" 
                + jugadores[jugadorActual].getPuntos() + "puntos)");
        // TODO: mostrar puntos del jugador
        iu.mostrarJugador(jugadores[jugadorActual], jugadorActual + 1);
    }
    
    /** 
     * Muestra el ganador de la partida
     */
    private void finPartida() {
        // Comenzamos nueva partida
        if(!asOros) {
            nuevaPartida();
        }
        // El juego ha terminado, mostramos los ganadores
        else { 
            infoGanadores();
        }
    }
    
    /** 
     * Muestra los ganadores del juego
     */
    private void infoGanadores() {
        // TODO: mostrar ganadores
        iu.mostrarMensaje("Ganadores");
        for (Jugador jugadore : jugadores) {
            System.out.println("\n\n");
            System.out.println(jugadore.getNombre() + ": " + jugadore.getPuntos());
        }
    }
}