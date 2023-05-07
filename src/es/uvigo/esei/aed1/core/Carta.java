package es.uvigo.esei.aed1.core;

/**
 * Representa una carta, formada por un número y su palo correspondiente
 * 
 * @author Grupo 2Espadas
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
