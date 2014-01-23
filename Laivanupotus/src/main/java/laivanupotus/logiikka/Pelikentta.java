package laivanupotus.logiikka;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pelikentta {

    private int[][] size;
    private Map<Laiva, Status> laivat;
    private Map ammukset;
    private Random sijoittaja;

    public Pelikentta(int ruutuja) {
        size = new int[ruutuja][ruutuja];
        this.laivat = new HashMap<Laiva, Status>();
        this.ammukset = new HashMap();
        this.sijoittaja = new Random();
    }

    public void sijoitaLaivatRandomilla(int laivoja) {
        for (int i = laivoja; i > 0; i--) {
            int koko = i;
            if (i < 3) {
                laivat.put(new Laiva(sijoittaja.nextInt(13) + 1, sijoittaja.nextInt(13) + 1, koko + 1,
                        sijoittaja.nextBoolean()), Status.EHJÄ);
            } else {
                laivat.put(new Laiva(sijoittaja.nextInt(13) + 1, sijoittaja.nextInt(13) + 1, koko,
                        sijoittaja.nextBoolean()), Status.EHJÄ);
            }
        }
    }

//    public void asetaLaivat(int laivoja) {
//    
//    }
//
//    public void tulostaKentta() {
//
//    }
    

    public void tulostaLaivat() {
        System.out.println(laivat.keySet());
    }
}
