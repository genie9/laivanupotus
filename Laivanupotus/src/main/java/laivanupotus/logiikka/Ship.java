package laivanupotus.logiikka;

/**
 *
 * @author Genie
 */
public class Ship {

    private final int x;
    private final int y;
    private final int size;
    private final boolean position; //true=vertical, false=horisontal
    private int[][] turvaAlue;
    private int health;

    public Ship(int x, int y, int size, boolean position) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.position = position;
        this.health = size;

        /*turva-alue ei ole vielä implementoitu*/

//        if (this.position) {
//            this.turvaAlue = new int[3][size + 2];
//        } else {
//            this.turvaAlue = new int[size + 2][3];
//        }
//        for(int i = 0; i<this.turvaAlue.length; i++){
//            for (int j = 0; j < this.turvaAlue[i].length; j++) {
//                if(i < 10 && i >= 0 && j >= 0 && j < 10){
//                    
//                }
//            }
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

    public boolean isPosition() {
        return position;
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
            if (this.position == true) {
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
        return "ship(" + this.x + "," + this.y + ")";
    }

}
