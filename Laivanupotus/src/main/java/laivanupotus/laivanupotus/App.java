package laivanupotus.laivanupotus;

import laivanupotus.logiikka.Pelikentta;
import laivanupotus.ui.Kayttoliittyma;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Kayttoliittyma ui = new Kayttoliittyma();
        Pelikentta kentta = new Pelikentta(13);
        kentta.sijoitaLaivatRandomilla(5);
        kentta.tulostaLaivat();
    }
}
