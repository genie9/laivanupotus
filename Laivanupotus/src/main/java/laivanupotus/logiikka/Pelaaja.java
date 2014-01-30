package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.Random;

public class Pelaaja {

    private final Random r;
    private final String name;
    private int[][] alue;
    private ArrayList<Laiva> laivasto;

    public Pelaaja(String name, int sivu) {
        this.r = new Random();
        this.name = name;
        this.alue = new int[sivu][sivu];
        this.laivasto = new ArrayList<>();
    }

    public Laiva arpoLaiva(int koko) {            //tekoälylle
        if (koko >= 0 && koko < alue.length) {
            boolean suunta = r.nextBoolean();
            if (suunta) {                                           //jos arvottu pystyyn
                int x = r.nextInt(10 - koko + 1);
                int y = r.nextInt(10);
                int xend = x + koko - 1;
                Laiva laiva = new Laiva(x, y, koko, suunta);
                if (xend < alue.length && x >= 0 && y >= 0 && y < alue.length) {
                    if (merkkaaXlle(x, y, xend)) {
                        laivasto.add(laiva);
                        return laiva;
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
                        return laiva;
                    }
                }
            }
        }
        return null;
    }

    public Laiva asetaLaiva(int x, int y, int koko, boolean suunta) {    //ihmispelaajalle, aika samanlainen kuin arpoLaiva
        if (koko >= 0 && koko < alue.length) {
            Laiva laiva = new Laiva(x, y, koko, suunta);

            if (suunta) {
                int xend = x + koko - 1;
                if (xend < alue.length && x >= 0 && y >= 0 && y < alue.length) {
                    if (merkkaaXlle(x, y, xend)) {
                        laivasto.add(laiva);
                        return laiva;
                    }
                }
            } else {
                int yend = y + koko - 1;
                if (x < alue.length && x >= 0 && y >= 0 && yend < alue.length) {
                    if (merkkaaYlle(x, y, yend)) {
                        laivasto.add(laiva);
                        return laiva;
                    }
                }
            }
        }
        return null;
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

    public ArrayList<Laiva> getLaivasto() {
        return laivasto;
    }

    public int[][] getAlue() {
        return alue;
    }

    public String getName() {
        return name;
    }

}
