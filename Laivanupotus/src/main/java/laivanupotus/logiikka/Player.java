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
    private char[][] area;
    private List<Ship> fleet;
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
     * Metodi arpoo tietyn kokoisen laivan sijainnin.
     *
     * @param size Syöte laivan koolle
     *
     * @return Ship Palauttaa Laivan onnistuessaan; palauttaa itsensä jos
     * sijainti ei täytä vaatimuksia; väärillä syötteillä palautetaan null.
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
                        Ship ship = new Ship(x, y, size, orientation);
                        fleet.add(ship);
                        return ship;
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
                        fleet.add(laiva);
                        return laiva;
                    }
                    return arvoLaiva(size);
                }
            }
        }
        return null;
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
    public Ship asetaLaiva(int x, int y, int size, boolean orientation) {    //ihmispelaajalle, aika samanlainen kuin arpoLaiva
        if (size >= 0 && size < area.length) {
            Ship laiva = new Ship(x, y, size, orientation);

            if (orientation) {
                int xend = x + size - 1;
                if (xend < area.length && x >= 0 && y >= 0 && y < area.length) {
                    if (merkkaaXlle(x, y, xend)) {
                        fleet.add(laiva);
                        return laiva;
                    }
                }
            } else {
                int yend = y + size - 1;
                if (x < area.length && x >= 0 && y >= 0 && yend < area.length) {
                    if (merkkaaYlle(x, y, yend)) {
                        fleet.add(laiva);
                        return laiva;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Apumetodi laivojen asettamiselle pystysuuntaan. Metodi päivittää
     * pelialueetta, kun laivoja asetetaan/arvotaan. Samalla tarkistaa ettei
     * laiva joudu varattuun paikkaan.
     *
     * @param x
     * @param y
     * @param end
     * @return true Onnistuessaan ja false, jos paikka on varattu
     */
    private boolean merkkaaXlle(int x, int y, int end) {
        if (area[x][y] == '.') {
            area[x][y] = 'S';
            if (end == x) {
                return true;
            }
            return merkkaaXlle(x + 1, y, end);
        }
        return false;
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
    private boolean merkkaaYlle(int x, int y, int end) {
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
            this.shots ++;
            return -1;
        }
        if (p.getArea()[x][y] == 'S') {
            this.shots ++;
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
