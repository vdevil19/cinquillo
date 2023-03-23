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
    private int numero;
    private String nombre;
    private ArrayList<Carta> mano;

    public int getNumero() {
        return numero;
    }
    
    public String getNombre() {
        return nombre;
    }

    public Jugador(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
        mano = new ArrayList<>();
    }
    
    public void cogerCarta(Carta c) {
        mano.add(c);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Jugador " + numero + ": " + nombre);
        sb.append(" - ");
        for (int i = 0; i < mano.size(); i++) {
            Carta c = mano.get(i);
            sb.append(c.getNumero());            
            sb.append(c.getPalo().toString());
            if(i < mano.size() - 1) {
                sb.append(" | ");
            }
        }
        
        return sb.toString();
    }
}
