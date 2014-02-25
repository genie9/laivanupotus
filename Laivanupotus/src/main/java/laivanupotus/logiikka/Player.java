package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Genie
 */
public class Player {

    private final Random r;
    private final String name;
    private final char[][] area;
    private final List<Ship> fleet;
    public int shots;

    /**
     * Konstruktori luo pelialueen ko. pelaajalle sekä laivasto(fleet)
     *
     * @param name Käyttäjän pelinimi
     * @param side Kentän koko
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
        this.fleet = new ArrayList<>();
        this.shots = 0;
    }

    /**
     * Metodi arpoo tietyn kokoisen laivan sijainnin. Käyttää avukseen metodia
 placeShip().
     *
     * @param size Syöte laivan koolle
     *
     * @return Ship Palauttaa Laivan onnistuessaan; palauttaa itsensä, jos
     * sijainti ei täytä vaatimuksia.
     */
    public Ship randomizeShip(int size) {            //tekoälylle
        Ship s;
        boolean orientation = r.nextBoolean();
        if (orientation) {  //jos arvottu pystyyn
            s = placeShip(r.nextInt(area.length - size + 1), r.nextInt(area.length), size, orientation);
            if (s == null) {
                randomizeShip(size);
            }
        } else {
            s = placeShip(r.nextInt(area.length), r.nextInt(area.length - size + 1), size, orientation);
            if (s == null) {
                randomizeShip(size);
            }
        }
        return s;
    }

    /**
     * Metodi asettaa laivan haluttuun sijaintiin.
     *
     * @param x Käyttäjän antama koordinaatti
     * @param y Käyttäjän antama koordinaatti
     * @param size Käyttäjän antama laivan koko
     * @param orientation Käyttäjän antama laivan suunta
     * @return Laiva Palauttaa laivan onnistuessaan, muuten null
     *
     */
    public Ship placeShip(int x, int y, int size, boolean orientation) {    //ihmispelaajalle
        if (size >= 1 && size < area.length) {
            if (orientation) {
                int xend = x + size - 1;
                if (xend < area.length && x >= 0 && y >= 0 && y < area.length) {
                    if (markOnX(x, y, xend)) {
                        Ship ship = new Ship(x, y, size, orientation);
                        fleet.add(ship);
                        return ship;
                    }
                }
            } else {
                int yend = y + size - 1;
                if (x < area.length && x >= 0 && y >= 0 && yend < area.length) {
                    if (markOnY(x, y, yend)) {
                        Ship ship = new Ship(x, y, size, orientation);
                        fleet.add(ship);
                        return ship;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Apumetodi laivojen asettamiselle pystysuuntaan. Metodi päivittää
     * pelialueetta, kun laivoja asetetaan. Samalla tarkistaa ettei laiva joudu
     * varattuun paikkaan.
     *
     * @param x
     * @param y
     * @param end
     * @return true Onnistuessaan ja false, jos paikka on varattu
     */
    private boolean markOnX(int x, int y, int end) {
        for (int i = x; i <= end; i++) {
            if (area[i][y] != '.') {
                return false;
            }
        }
        for (int j = x; j <= end; j++) {
            area[j][y] = 'S';
        }
        return true;
    }

    /**
     * Apumetodi laivojen asettamiselle vaakasuuntaan. Metodi päivittää
     * pelialueetta, kun laivoja asetetaan/arvotaan. Samalla tarkistaa ettei
     * laiva joudu varattuun paikkaan.
     *
     * @param x
     * @param y
     * @param end
     * @return true Onnistuessaan ja false, jos paikka on varattu
     */
    private boolean markOnY(int x, int y, int end) {
        for (int i = y; i <= end; i++) {
            if (area[x][i] != '.') {
                return false;
            }
        }
        for (int j = y; j <= end; j++) {
            area[x][j] = 'S';
        }
        return true;
    }

    /**
     * Metodi kutsuu laivaston(fleet) jokaiselle alukselle metodia isHit(), joka
     * kertoo ampumisen onnistumisen tai epäonnistumisen. Onnistuessa, aluksen
     * health päivitetään. Laivan tuhoutuessa laivastosta postetaan kyseinen
     * alus.
     *
     * @param p Vastapelaaja
     * @param x Käyttäjän antama x-coord
     * @param y Käyttäjän antama y-coord
     * @return ship.health Onnistuessa eli 0 - ship.size, muuten -1
     */
    public int shoot(Player p, int x, int y) {
        if (p.getArea()[x][y] == '.') {
            p.getArea()[x][y] = 'x';
            this.shots++;
            return -1;
        }
        if (p.getArea()[x][y] == 'S') {
            this.shots++;
            for (Ship ship : p.getFleet()) {
                if (ship.isHit(x, y)) {
                    ship.setHealth(ship.getHealth() - 1);
                    if (ship.getHealth() == 0) {
                        p.getFleet().remove(ship);
                        p.getArea()[x][y] = 'D';
                        return ship.getHealth();
                    }
                    p.getArea()[x][y] = 'X';
                    return ship.getHealth();
                }
            }
        }
        return -2;
    }

    public List<Ship> getFleet() {
        return fleet;
    }

    /**
     * Testausta varten tulostaa laivaston kaikki alukset
     *
     * @param p Pelaaja
     */
    public void printFleat(Player p) {
        for (Ship ship : p.getFleet()) {
            System.out.println(ship);
        }
    }

    public char[][] getArea() {
        return area;
    }

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
