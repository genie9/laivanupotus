package laivanupotus.logiikka;

/**
 *
 * @author Genie
 */
public class Ship {

    private final int x;
    private final int y;
    private final int size;
    private final boolean orientation; //true=vertical, false=horisontal
//    private int[][] turvaAlue;
    private int health;

    public Ship(int x, int y, int size, boolean orientation) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.orientation = orientation;
        this.health = size;

        /*turva-alue ei ole vielä implementoitu
         */
//        for(int i = 0; i<koko; i++){
//              if (this.suunta) {                          
//            this.turvaAlue = new int[3][size + 2];
//            this.turvaAlue[x-1][y-1]=2;
//        } else {                                    
//            this.turvaAlue = new int[size + 2][3];  
//        }
//        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean isOrientation() {
        return orientation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Metodi tarkistaa onko laivaan osuttu käymällä läpi sen kaikkia
     * koordinaatteja
     *
     * @param xcomp Shoot() metodilta saatu
     * @param ycomp Shoot() metodilta saatu
     * @return true Osuessaan, muuten false
     */
    public boolean isHit(int xcomp, int ycomp) {
        for (int i = 0; i < this.size; i++) {
            if (this.orientation == true) {
                if (this.x + i == xcomp && this.y == ycomp) {
                    return true;
                }
            } else {
                if (this.x == xcomp && this.y + i == ycomp) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String pos = "";
        if (orientation) {
            pos = "vertical";
        } else {
            pos = "horisontal";
        }
        return "x = " + this.x + ", y = " + this.y + ", koko = " + this.size + ", suunta = " + pos + ", health = " + this.health;
    }

}
