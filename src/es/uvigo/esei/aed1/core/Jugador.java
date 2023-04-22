package es.uvigo.esei.aed1.core;

import java.util.ArrayList;

/**
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 * 
 * @author VíctorManuel
 */
public class Jugador {
    private String nombre;
    private ArrayList<Carta> mano;
    
    public String getNombre() {
        return nombre;
    }
    
    public ArrayList<Carta> getMano() {
        return mano;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<>();
    }
    
    public void cogerCarta(Carta c) {
        mano.add(c);
    }

    public String toString(int numero) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mano.size() - 1; i++) {
            sb.append(mano.get(i));
            sb.append(" | ");
        }
        if(!mano.isEmpty()) {
            sb.append(mano.get(mano.size() - 1));
        }
        
        return sb.toString();
    }
}
