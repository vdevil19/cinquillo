package es.uvigo.esei.aed1.core;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

// TODO javadocs clase y métodos
/**
 * Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos,
 * valores de las cartas de 1 a 12. 
 * Estructura: se utilizará un TAD adecuado
 * Funcionalidad: barajar las cartas, devolver la carta situada encima del
 * montón de cartas
 *
 * @author Grupo XXXXX
 */
public class Baraja {

    private final int PALOS = 4;
    private final int CARTAS = 12;
    private final int NUMCARTAS = (PALOS * CARTAS);
    // Atributos clase baraja
    private Stack<Carta> cartas;

    // Constructor
    public Baraja() {
        cartas = new Stack<>();
        // Ordenamos el mazo con todas las cartas
        for (int i = 0; i < PALOS; i++) {
            for (int j = 0; j < CARTAS; j++) {
                Carta c = new Carta(Carta.Palo.values()[i], j + 1);
                cartas.push(c);
            }
        }
    }

    //    Barajar cartas
    public void barajarCartas() {
        // Barajamos
        ArrayList<Carta> mazo = new ArrayList<>(NUMCARTAS);
        while(!cartas.empty()){
            mazo.add(cartas.pop());
        }
        Random r = new Random();
        while (!mazo.isEmpty()) {
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
