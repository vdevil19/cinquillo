/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package es.uvigo.esei.aed1.core;

// TODO javadocs clase y métodos
/**
 * Representa una carta de la baraja
 * 
 * @author VíctorManuel
 */
public class Carta {
     // Enumerado con los posibles valores de los palos
    public enum Palo {
        OROS,
        COPAS,
        ESPADAS,
        BASTOS;

        @Override
        public String toString() {
            return this.name().charAt(0)
                    + this.name().substring(1).toLowerCase();
        }
    }
    // Atributos
    private Palo palo;
    private int numero;

    // Constructor
    public Carta(Palo palo, int numero) {
        this.palo = palo;
        this.numero = numero;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return numero + palo.toString();
    }
}
