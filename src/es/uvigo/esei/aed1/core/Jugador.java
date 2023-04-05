package es.uvigo.esei.aed1.core;

import java.util.ArrayDeque;

/**
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 * 
 * @author VíctorManuel
 */
public class Jugador {
    private String nombre;
    private ArrayDeque<Carta> mano;
    
    public String getNombre() {
        return nombre;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayDeque<>();
    }
    
    public void cogerCarta(Carta c) {
        mano.add(c);
    }

    public String toString(int numero) {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador ").append(numero).append(": ").append(nombre);
        sb.append(" - ");
        for (Carta c : mano) {
            sb.append(c);
            if(!c.equals(mano.getLast())) {
                sb.append(" | ");
            }
        }
        
        return sb.toString();
    }
}
