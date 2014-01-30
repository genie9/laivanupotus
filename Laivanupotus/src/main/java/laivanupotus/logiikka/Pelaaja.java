package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.Random;

public class Pelaaja {

    private final Random r;
    private final String name;
    private int[][] alue;
    private ArrayList<Laiva> laivasto;              //toistaiseksi kaiken varalta

    public Pelaaja(String name, int sivu) {
        this.r = new Random();
        this.name = name;
        this.alue = new int[sivu][sivu];
        this.laivasto = new ArrayList<>();
    }

    public boolean arpoLaiva(int koko) {            //tekoälylle
        boolean suunta = r.nextBoolean();           

        if (suunta) {                                           //jos arvottu pystyyn
            int x = r.nextInt(10 - koko + 1);
            int y = r.nextInt(10);
            int xend = x + koko - 1;                    
            Laiva laiva = new Laiva(x, y, koko, suunta);
            if (xend < alue.length && x >= 0 && y >= 0 && y < alue.length) {
                if (merkkaaXlle(x, y, xend)) {
                    laivasto.add(laiva);
                    return true;
                }
            }
        } else {                                                //jos arvottu vaakaan
            int x = r.nextInt(10);
            int y = r.nextInt(10 - koko + 1);
            int yend = y + koko - 1;
            Laiva laiva = new Laiva(x, y, koko, suunta);
            if (x < alue.length && x >= 0 && y >= 0 && yend < alue.length) {
                if (merkkaaYlle(x, y, yend)) {
                    laivasto.add(laiva);
                    return true;
                }
            }
        }
        return false;
    }

    public void asetaLaiva(int x, int y, int koko, boolean suunta) {    //ihmispelaajalle, aika samanlainen kuin arpoLaiva
        Laiva laiva = new Laiva(x, y, koko, suunta);

        if (suunta) {
            int xend = x + koko - 1;
            if (xend < alue.length && x >= 0 && y >= 0 && y < alue.length) {
                if (merkkaaXlle(x, y, xend)) {
                    laivasto.add(laiva);
                }
            }
        } else {
            int yend = y + koko - 1;
            if (x < alue.length && x >= 0 && y >= 0 && yend < alue.length) {
                if (merkkaaYlle(x, y, yend)) {
                    laivasto.add(laiva);
                }
            }
        }
    }

    private boolean merkkaaXlle(int x, int y, int end) {        //apumetodi laivojen asettamiselle pystysuuntaan
        if (alue[x][y] == 0) {
            alue[x][y] = 1;
            if (end == x) {
                return true;
            }
            return merkkaaXlle(x + 1, y, end);
        }
        return false;
    }

    private boolean merkkaaYlle(int x, int y, int end) {         //apumetodi laivojen asettamiselle vaakasuuntaan
        if (alue[x][y] == 0) {
            alue[x][y] = 1;
            if (end == y) {
                return true;
            }
            return merkkaaYlle(x, y + 1, end);
        }
        return false;
    }

    //tämä ehkä käyttöliitymälle
    public boolean ammu(int x, int y) {
        for (Laiva laiva : laivasto) {
            if (x == laiva.getX() && y == laiva.getY()) {
                laiva.setKunto(laiva.getKunto() - 1);
                return true;
            }
        }
        this.alue[x][y] = -1;
        return false;
    }

    public int[][] getAlue() {
        return alue;
    }

    public String getName() {
        return name;
    }

}
