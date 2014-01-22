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
        this.pelaaja1Kentta = new Pelikentta();
        this.pelaaja2Kentta = new Pelikentta();
        lukija = new Scanner(System.in);
        pelaaja1 = new Pelaaja();
        pelaaja2 = new Pelaaja();

    }

    public void pelivalmistelut() {
        
        System.out.println("Valitse väri: \n punainen = 1 \n musta = 2");
        
        if (lukija.nextInt() == 1) {
            pelaajaPunainen();
        }
        if (lukija.nextInt() == 2) {
            pelaajaMusta();
        }
        
        
    }

    private void pelaajaPunainen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void pelaajaMusta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
