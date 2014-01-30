package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.Random;

public class Pelaaja {
    private Random sijoittaja;
    private String name;
    private int[][] alue;
    private ArrayList<Laiva> laivasto;
    
//    private Pelikentta omaKentta;
//    private Pelikentta kohdeKentta;

    public Pelaaja(String name, int sivu) {
        this.sijoittaja = new Random();
        this.name = name;
        this.alue =  new int[sivu][sivu];
        this.laivasto = new ArrayList<Laiva>();
//        this.omaKentta = new Pelikentta(13);
//        this.kohdeKentta = new Pelikentta(13);
    }
    
    public void arpoLaiva(){
        
    }
    
    public void asetaLaiva(int x, int y, int koko, boolean suunta){
        for(Laiva laiva : laivasto){
            for()
            if(laiva.getX()+i != x && laiva.getY() != y ){
                if()
                laivasto.add(new Laiva(x, y, koko, suunta));
            }
        }
    }
    
    public boolean ammu(int x, int y){
        for(Laiva laiva : laivasto){
        if(x==laiva.getX() && y == laiva.getY()){
            laiva.setKunto(laiva.getKunto()-1);
            return true;
        }
        }
        this.alue[x][y]=1;
        return false;
    }
    
}
