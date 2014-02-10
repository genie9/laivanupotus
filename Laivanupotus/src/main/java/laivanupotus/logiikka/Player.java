package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    private final Random r;
    private final String name;
    private char[][] area;
    private ArrayList<Ship> fleat;

    /**
     *
     * @param name
     * @param side
     */
    public Player(String name, int side) {
        this.r = new Random();
        this.name = name;
        this.area = new char[side][side];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                area[i][j] = '.';
            }
        }
        this.fleat = new ArrayList<>();
    }

    /**
     * Metodi arpoo tietyn kokoisen laivan sijainnin.
     * 
     * @param size Syöte laivan koolle
     * 
     * @return Ship Palauttaa Laivan onnistuessaan; palauttaa itsensä jos 
     *              sijainti ei täytä vaatimuksia; väärillä syötteillä
     *              palautetaan null.
     */
    public Ship arvoLaiva(int size) {            //tekoälylle
        if (size >= 0 && size < area.length) {
            boolean orientation = r.nextBoolean();
            if (orientation) {                                           //jos arvottu pystyyn
                int x = r.nextInt(area.length - size + 1);
                int y = r.nextInt(area.length);
                int xend = x + size - 1;

                if (xend < area.length && x >= 0 && y >= 0 && y < area.length) {
                    if (merkkaaXlle(x, y, xend)) {
                        Ship laiva = new Ship(x, y, size, orientation);
                        fleat.add(laiva);
                        return laiva;
                    }
                    return arvoLaiva(size);
                }
            } else {                                                //jos arvottu vaakaan
                int x = r.nextInt(area.length);
                int y = r.nextInt(area.length - size + 1);
                int yend = y + size - 1;

                if (x < area.length && x >= 0 && y >= 0 && yend < area.length) {
                    if (merkkaaYlle(x, y, yend)) {
                        Ship laiva = new Ship(x, y, size, orientation);
                        fleat.add(laiva);
                        return laiva;
                    }
                    return arvoLaiva(size);
                }
            }
        }
        return null;
    }

    /**
     *Metodi asettaa laivan haluttuun sijaintiin.
     * 
     * @param x Käyttäjän antama koordinaatti
     * @param y Käyttäjän antama koordinaatti
     * @param size Käyttäjän antama laivan koko
     * @param orientation Käyttäjän antama laivan suunta
     * @return  Laiva Palauttaa laivan onnistuessaan, muuten null
     * 
     */
    public Ship asetaLaiva(int x, int y, int size, boolean orientation) {    //ihmispelaajalle, aika samanlainen kuin arpoLaiva
        if (size >= 0 && size < area.length) {
            Ship laiva = new Ship(x, y, size, orientation);

            if (orientation) {
                int xend = x + size - 1;
                if (xend < area.length && x >= 0 && y >= 0 && y < area.length) {
                    if (merkkaaXlle(x, y, xend)) {
                        fleat.add(laiva);
                        return laiva;
                    }
                }
            } else {
                int yend = y + size - 1;
                if (x < area.length && x >= 0 && y >= 0 && yend < area.length) {
                    if (merkkaaYlle(x, y, yend)) {
                        fleat.add(laiva);
                        return laiva;
                    }
                }
            }
        }
        return null;
    }

    private boolean merkkaaXlle(int x, int y, int end) {        //apumetodi laivojen asettamiselle pystysuuntaan
        if (area[x][y] == '.') {                                 //&& area[x][y - 1] == 0 && area[x][y + 1] == 0 && area[x + 1][y] == 0
            area[x][y] = 'S';
            if (end == x) {
                return true;
            }
            return merkkaaXlle(x + 1, y, end);
        }
        return false;
    }

    private boolean merkkaaYlle(int x, int y, int end) {         //apumetodi laivojen asettamiselle vaakasuuntaan
        if (area[x][y] == '.') {
            area[x][y] = 'S';
            if (end == y) {
                return true;
            }
            return merkkaaYlle(x, y + 1, end);
        }
        return false;
    }

    /**
     *
     * @param p
     * @param x
     * @param y
     * @return
     */
    public boolean shoot(Player p, int x, int y) {
        for (Ship ship : p.getFleat()) {
            if (ship.isHit(x, y)) {
                ship.setHealth(ship.getHealth() - 1);
                if (ship.getHealth() == 0) {
                    p.getFleat().remove(ship);
                    System.out.println("The ship is destroyed!");
                }
                p.getArea()[x][y] = 'X';
                return true;
            }
        }
        p.getArea()[x][y] = 'x';
        return false;
    }

    /**
     *
     * @return
     */
    public ArrayList<Ship> getFleat() {
        return fleat;
    }

    /**
     *
     * @param p
     */
    public void printFleat(Player p) {
        for (Ship ship : p.getFleat()) {
            System.out.println(ship);
        }
    }

    /**
     *
     * @return
     */
    public char[][] getArea() {
        return area;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
}

//    private void turvaAlue(){
//        for (int i = 0; i< area.length; i++ ) {
//            for (int j = 0; j < area.length; j++) {
//                
//                if(area[i][j]== 1 && area[i-1][j-1]== 0){
//                    area[i-1][j-1]= 2;
//                }
//                else if(area[i][j]== 1 && area[i-1][j]== 0){
//                    area[i-1][j]= 2;
//                }
//                else if(area[i][j]== 1 && area[i-1][j+1]== 0){
//                    area[i-1][j+1]= 2;
//                }
//                else if(area[i][j]== 1 && area[i][j+1]== 0){
//                    area[i][j+1]= 2;
//                }
//                else if(area[i][j]== 1 && area[i+1][j+1]== 0){
//                    area[i+1][j+1]= 2;
//                }
//                else if(area[i][j]== 1 && area[i+1][j]== 0){
//                    area[i-1][j+1]= 2;
//                }
//                else if(area[i][j]== 1 && area[i+1][j-1]== 0){
//                    area[i][j+1]= 2;
//                }
//                else if(area[i][j]== 1 && area[i+1][j-1]== 0){
//                    area[i+1][j-1]= 2;
//                }
//            }
//        }
//    }
