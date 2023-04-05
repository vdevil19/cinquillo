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
    private Stack<Carta> cartas;

    public Baraja() {
        cartas = new Stack<>();
        barajar();
    }
    
    public void barajar() {
        cartas.clear();
        
        // Ordenamos el mazo con todas las cartas
        ArrayList<Carta> mazo = new ArrayList<>(4 * 12);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                Carta c = new Carta(Carta.Palo.values()[i], j + 1);
                mazo.add(c);
            }
        }
        
        // Barajamos
        Random r = new Random();
        while(!mazo.isEmpty()) {
            int pos = r.nextInt(mazo.size());
            Carta c = mazo.remove(pos);
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
