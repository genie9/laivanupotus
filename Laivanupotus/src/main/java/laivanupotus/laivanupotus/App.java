package laivanupotus.laivanupotus;

import laivanupotus.ui.GUI;
import javax.swing.SwingUtilities;

/**
 * Peliä käynnistävä pääluokka
 * 
 * @author evly
 */
public class App {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        start();
    }

    /**
     * aloittaa pelin
     */
    public static void start() {
        SwingUtilities.invokeLater(new GUI());
    }
}
