package laivanupotus.laivanupotus;

import laivanupotus.ui.GUI;
import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        
        GUI w = new GUI();

        SwingUtilities.invokeLater(w);
    }
}
