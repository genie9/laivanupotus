package laivanupotus.logiikka;

public class Laiva {

    private int x;
    private int y;
    private int koko;
    private boolean suunta;
    private int[][] turvaAlue;

    public Laiva(int x, int y, int koko, boolean suunta) {
        this.x = x;
        this.y = y;
        this.koko = koko;
        this.suunta = suunta;
    
        /*tämä vielä kesken,
        pitäisi olla koordinaatteina ja
        kentän reunat otetaan huomioon
        */
//        if (this.suunta) {                          
//            this.turvaAlue = new int[3][koko + 2];  
//        } else {                                    
//            this.turvaAlue = new int[koko + 2][3];  
//        }
    }
    
    public int getX(){
        return this.x;
    }

    public int getY() {
        return y;
    }

    public int getKoko() {
        return koko;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setKoko(int koko) {
        this.koko = koko;
    }

    @Override
    public String toString() {
        return "x = "+ this.x+", y = "+this.y+", koko = "+this.koko+", suunta = "+this.suunta;
    }

    
    
}
