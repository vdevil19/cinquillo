/*

 */

package es.uvigo.esei.aed1.core;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
 * Estructura: se utilizará un TAD adecuado
 * Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 * 
 * @author Grupo XXXXX
 */
public class Baraja {
    private final int PALOS = 4;
    private final int CARTAS = 12;
    
    private Stack<Carta> cartas;

    public Baraja() {
        cartas = new Stack<>();
        for (int i = 0; i < PALOS; i++) {
            for (int j = 0; j < CARTAS; j++) {
                Carta c = new Carta(Carta.Palo.values()[i], j + 1);
                cartas.add(c);
            }
        }
    }
    
    public void barajar() {        
        // Creamos un mazo temporal con todas las cartas
        ArrayList<Carta> temp = new ArrayList<>(CARTAS * PALOS);
        for (int i = 0; i < PALOS; i++) {
            for (int j = 0; j < CARTAS; j++) {
                Carta c = new Carta(Carta.Palo.values()[i], j + 1);
                temp.add(c);
            }
        }
        
        cartas.clear();
        // Barajamos
        Random r = new Random();
        while(!temp.isEmpty()) {
            int pos = r.nextInt(temp.size());
            Carta c = temp.remove(pos);
            cartas.push(c);
        }
    }
    
    public boolean estaVacia() {
        return cartas.empty();
    }
        
    public Carta siguienteCarta() {
        return cartas.pop();
    }
}
