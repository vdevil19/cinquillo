package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega).
 * Se recomienda una implementación modular.
 * 
 * @author Grupo 2Espadas
 */
public class Juego {
    // constantes con la puntuación del juego
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
        asOros = false;
    }
    
    /**
     * Comienza el juego
     */
    public void comenzarJuego() {
        // Leemos datos de jugadores y los creamos
        iniciarJugadores();
        
        // comenzamos una partida
        nuevaPartida();
    }

    /**
     * Crea los jugadores a partir de los datos introducidos por el usuario
     */
    private void iniciarJugadores() {
        String[] datosJugadores = iu.pedirDatosJugadores();
        
        jugadores = new Jugador[datosJugadores.length];
        for (int i = 0; i < datosJugadores.length; i++) {
            jugadores[i] = new Jugador(datosJugadores[i]);
        }
        
        // sorteamos jugador que comienza la partida
        jugadorActual = (new Random()).nextInt(jugadores.length);
    }
    
    /**
     * Comienza una nueva partida
     */
    public void nuevaPartida() {
        partidas++;
        
        // crear mesa y repartir cartas
        iniciarMesaYManos();
        
        // jugar partida
        jugar();
    }
    
    /**
     * Vacía la mesa y reparte las cartas de la baraja entre los jugadores
     */
    private void iniciarMesaYManos() {
        // Creamos mesa
        mesa = new Mesa();
        
        // Vaciamos las manos de los jugadores
        for (Jugador j : jugadores) {
            j.vaciarMano();
        }
        
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
    
    /**
     * Bucle principal de juego, jugador por jugador muestra la información de 
     * la partida, permite que el jugador juegue una carta si tiene la opción, 
     * y comprueba si la partida ha terminado
     */
    private void jugar() {
        // iniciamos la partida con el jugador actual
        boolean partidaTerminada = false;
        int ganador = 0;
        
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
                ganador = jugadorActual;
            }
            
            // pasamos el turno al siguiente jugador
            jugadorActual = (jugadorActual + 1) % jugadores.length;
        } while (!partidaTerminada);
        
        finPartida(ganador);
    }
    
    /**
     * Permite jugar un turno a un jugador
     * 
     * @param jugadorActual Jugador al que toca jugar en el turno actual
     */
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
     * Muestra las cartas en la mesa y la mano del jugador
     * 
     * @param jugadorActual Jugador al que toca jugar en el turno actual 
     */
    private void infoTurno(int jugadorActual) {
        // Mostramos info de partida
        iu.mostrarMensaje("\nPartida " + partidas);
        
        // Mostramos cartas en la mesa
        iu.mostrarMensaje(mesa.toString());

        // Mostramos jugador actual
        iu.mostrarMensaje("\nTurno del jugador: " 
                + jugadores[jugadorActual].getNombre() + " (" 
                + jugadores[jugadorActual].getPuntos() + " puntos)");
        iu.mostrarJugador(jugadores[jugadorActual], jugadorActual + 1);
    }
    
    /** 
     * Finaliza la partida, si se ha jugado el as de oros muestra los ganadores,
     * si no, comienza una nueva partida
     */
    private void finPartida(int ganador) {
        // Comenzamos nueva partida
        if(!asOros) {
            iu.mostrarMensaje("\n\n" +"-".repeat(50));
            iu.mostrarMensaje(jugadores[ganador].getNombre()
                    + " ha ganado la partida.");
            iu.leeString("Pulsa intro para continuar.");
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
        // comprobamos ganadores
        List<String> ganadores = new ArrayList();
        int puntuacionMaxima = 0;
        for (Jugador j : jugadores) {
            if(j.getPuntos() >= puntuacionMaxima) {
                if(j.getPuntos() > puntuacionMaxima) {
                    puntuacionMaxima = j.getPuntos();
                    ganadores.clear();
                }
                ganadores.add(j.getNombre() + ": " + puntuacionMaxima + " puntos");
            }
        }
        
        System.out.println("\n\n");
        iu.mostrarMensaje("Ganadores\n---------");
        for (String g : ganadores) {
            iu.mostrarMensaje(g);
        }
    }
}