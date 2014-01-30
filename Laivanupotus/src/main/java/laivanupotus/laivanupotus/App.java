package laivanupotus.laivanupotus;

import laivanupotus.logiikka.Laiva;
import laivanupotus.logiikka.Pelaaja;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Pelaaja pelaaja = new Pelaaja("eka", 10);

        pelaaja.asetaLaiva(0, 0, 4, true);
        pelaaja.asetaLaiva(4, 5, 3, false);
        pelaaja.asetaLaiva(6, 4, 5, true);
        pelaaja.asetaLaiva(9, 9, 5, false);
        pelaaja.asetaLaiva(2, 4, 2, false);
        for (int[] alue : pelaaja.getAlue()) {
            for (int j = 0; j < pelaaja.getAlue().length; j++) {
                System.out.print(alue[j]);
            }
            System.out.println("");
        }

        for (Laiva laiva : pelaaja.getLaivasto()) {
                System.out.println(laiva);
            }
            System.out.println("");
        
        
        System.out.println("");
        
        Pelaaja r2d2 = new Pelaaja("r2d2", 10);

        r2d2.arpoLaiva(5);
        r2d2.arpoLaiva(4);
        r2d2.arpoLaiva(3);
        r2d2.arpoLaiva(2);
        r2d2.arpoLaiva(2);

        for (int[] alue : r2d2.getAlue()) {
            for (int j = 0; j < r2d2.getAlue().length; j++) {
                System.out.print(alue[j]);
            }
            System.out.println("");
        }
    }
}
