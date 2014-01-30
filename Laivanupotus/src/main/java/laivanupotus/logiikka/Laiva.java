package laivanupotus.logiikka;

public class Laiva {

    private final int x;
    private final int y;
    private final int koko;
    private final boolean suunta; //true=vertical, false=horisontal
    //private int[][] turvaAlue;
    private int kunto;

    public Laiva(int x, int y, int koko, boolean suunta) {
        this.x = x;
        this.y = y;
        this.koko = koko;
        this.suunta = suunta;
        this.kunto = koko;

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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public int getKoko() {
        return koko;
    }

    public int getKunto() {
        return kunto;
    }

//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
//
//    public void setKoko(int koko) {
//        this.koko = koko;
//    }
    public void setKunto(int kunto) {
        this.kunto = kunto;
    }

    @Override
    public String toString() {
        String pos = "";
        if (suunta) {
            pos = "vertical";
        } else {
            pos = "horisontal";
        }
        return "x = " + this.x + ", y = " + this.y + ", koko = " + this.koko + ", suunta = " + pos;
    }

}
