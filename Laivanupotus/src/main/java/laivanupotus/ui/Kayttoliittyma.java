package laivanupotus.ui;

import java.util.Scanner;
import laivanupotus.logiikka.Pelaaja;

public class Kayttoliittyma {

    public Scanner lukija;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;

    public Kayttoliittyma() {
        lukija = new Scanner(System.in);
    }

    public void pelivalmistelut() {
        System.out.println("Welcome to fight for your glory!");

        System.out.println("Choose your colour: \n red = 1 \n black = 2");
        int colour = lukija.nextInt();
        System.out.println("Choose the number of ships for your fleat: 5, 7, 9.");
        int area = lukija.nextInt();
        
        if (colour == 1) {
            System.out.println("How shall we call you amiral?");
            String name = lukija.nextLine();
            pelaaja1= new Pelaaja(name, area);
        }
        if (lukija.nextInt() == 2) {
        }

    }
}
