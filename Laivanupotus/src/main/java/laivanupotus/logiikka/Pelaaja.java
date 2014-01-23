package laivanupotus.logiikka;

public class Pelaaja {
    private String name;
    private Pelikentta omaKentta;
    private Pelikentta kohdeKentta;

    public Pelaaja(String name) {
        this.name = name;
        this.omaKentta = new Pelikentta(13);
        this.kohdeKentta = new Pelikentta(13);
    }
    
    public boolean ammu(int x, int y){
        
        return false;
    }
}
