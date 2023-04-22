/*
* Representa la Mesa de juego. 
* Estructura: se utilizará un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo más adecuado). La DobleCola se comentó en clase de teoría
* Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */
package es.uvigo.esei.aed1.core;

import java.util.ArrayDeque;
import java.util.Deque;

public class Mesa {
    private final Deque<Carta>[] mesa;    
    
    //constructor
    public Mesa() {
        mesa = new Deque[4];
        
        vaciarMesa();
    }
    
    public void vaciarMesa() {
        for (int i = 0; i < mesa.length; i++) {
            mesa[i] = new ArrayDeque<>();
        }
    }
    
    public boolean esColocable(Carta c) {
        // los cincos se colocan siempre
        if(c.getNumero() == 5) {
            return true;
        }
        else {
            int palo = c.getPalo().ordinal();
            return !mesa[palo].isEmpty() && 
                (c.getNumero() == mesa[palo].getFirst().getNumero() - 1 ||
                c.getNumero() == mesa[palo].getLast().getNumero() + 1);
        }
    }
    
    public void colocarCarta(Carta c) {
        if(esColocable(c)) {
            //colocamos la mesa en la mano de su palo correspondiente
            int palo = c.getPalo().ordinal();
            
            // cinco
            if(c.getNumero() == 5) {
                mesa[palo].addFirst(c);
            }
            else{
                // principio
                if(c.getNumero() == mesa[palo].getFirst().getNumero() - 1){
                    mesa[palo].addFirst(c);
                }
                // final
                else{
                    mesa[palo].add(c);
                }
            }
        }
    }

    //a�adir m�s funcionalidades
    // mostrar el estado de la mesa
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("-".repeat(80));        
        sb.append("\n| Oros:    ");
        for (Carta c : mesa[0]) {
            sb.append(c.getNumero());
            if(!c.equals(mesa[0].getLast())) {
                sb.append(" - ");
            }
        }
        sb.append("\n|");
        sb.append("\n| Copas:   ");
        for (Carta c : mesa[1]) {
            sb.append(c.getNumero());
            if(!c.equals(mesa[1].getLast())) {
                sb.append(" - ");
            }
        }
        sb.append("\n|");
        sb.append("\n| Espadas: ");
        for (Carta c : mesa[2]) {
            sb.append(c.getNumero());
            if(!c.equals(mesa[2].getLast())) {
                sb.append(" - ");
            }
        }
        sb.append("\n|");
        sb.append("\n| Bastos:  ");
        for (Carta c : mesa[3]) {
            sb.append(c.getNumero());
            if(!c.equals(mesa[3].getLast())) {
                sb.append(" - ");
            }
        }
        sb.append("\n");
        sb.append("-".repeat(80));   
        
        return sb.toString();
    }
}
