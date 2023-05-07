package es.uvigo.esei.aed1.core;

import java.util.ArrayList;

/**
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado Funcionalidad: Añadir carta a
 * la mano, convertir a String el objeto Jugador (toString)
 *
 * @author VíctorManuel
 */
public class Jugador {

    // Atributos
    private String nombre;
    private int puntos;
    private ArrayList<Carta> mano;

    // Constructor
    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getPuntos() {
        return puntos;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }
    
    /**
     * Vacía la mano del jugador
     */
    public void vaciarMano() {
        mano.clear();
    }

    /**
     * Añade una carta a la mano del jugador
     * 
     * @param c Carta que se añadirá
     */
    public void cogerCarta(Carta c) {
        mano.add(c);
    }

    /**
     * Se quita una carta de la mano del jugador
     * 
     * @param num Número de orden de la carta
     * @return Carta que saldrá de la mano del jugador
     */
    public Carta getCarta(int num) {
        return mano.get(num);
    }

    /**
     * Juega una carta de la mano del jugador
     * 
     * @param num Número de orden de la carta
     * @return Carta que se jugará
     */
    public Carta jugarCarta(int num) {
        Carta c = getCarta(num);
        mano.remove(c);

        return c;
    }

    /**
     * Comprueba si el jugador tiene alguna carta que pueda ser jugada
     * 
     * @param mesa Mesa del juego actual
     * @return true si puede jugar alguna carta 
     */
    public boolean puedeJugar(Mesa mesa) {
        for (Carta carta : mano) {
            if (mesa.esColocable(carta)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si un jugador ha jugado todas sus cartas
     *
     * @return true si ha jugado todas las cartas
     */
    public boolean cartasColocadas() {
        return mano.isEmpty();
    }
    
    public void sumarPuntos(int p) {
        puntos += p;
    }

    public String toString(int numero) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mano.size(); i++) {
            sb.append("[" + (i + 1) + "]");
            sb.append(mano.get(i) + " ");
        }

        return sb.toString();
    }
}
