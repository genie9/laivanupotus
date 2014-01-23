package laivanupotus.ui;

import java.util.Scanner;
import laivanupotus.logiikka.Pelaaja;
import laivanupotus.logiikka.Pelikentta;

public class Kayttoliittyma {

    private Pelikentta pelaaja1Kentta;
    private Pelikentta pelaaja2Kentta;
    public Scanner lukija;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;

    public Kayttoliittyma() {
        lukija = new Scanner(System.in);
    }

    public void pelivalmistelut() {

        System.out.println("Valitse väri: \n punainen = 1 \n musta = 2");

        if (lukija.nextInt() == 1) {
            
        }
        if (lukija.nextInt() == 2) {

        }

    }
}
