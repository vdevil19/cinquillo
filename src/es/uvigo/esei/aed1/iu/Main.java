
package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Juego;

/**
 * Clase principal de la aplicación.
 * 
 * @author Grupo XXXX
 */
public class Main {
    // TODO javadocs clase y métodos
    public static void main(String[] args) {
        IU iu = new IU();
        Juego cinquillo = new Juego(iu);
        cinquillo.comenzarJuego();
    }   
}

