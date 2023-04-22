/*
 * Representa una carta, formada por un número y su palo correspondiente
 */

package es.uvigo.esei.aed1.core;

public class Carta {
    public enum Palo {
        OROS,
        COPAS,
        ESPADAS,
        BASTOS;
        
        @Override
        public String toString() {
            return this.name().charAt(0) +
                this.name().substring(1).toLowerCase();
        }
    }

    public Palo getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    private Palo palo;
    private int numero;

    public Carta(Palo palo, int numero) {
        this.palo = palo;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return numero + palo.toString();
    }
}
