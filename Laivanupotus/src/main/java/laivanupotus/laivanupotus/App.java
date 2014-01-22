package laivanupotus.laivanupotus;

import laivanupotus.ui.Kayttoliittyma;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Kayttoliittyma ui = new Kayttoliittyma();
        ui.pelivalmistelut();
    }
}
