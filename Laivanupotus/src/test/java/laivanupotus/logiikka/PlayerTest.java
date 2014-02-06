package laivanupotus.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player pelaaja;

    public PlayerTest() {
    }

    @Before
    public void setUp() {
        pelaaja = new Player("testaaja", 10);
    }

    @Test
    public void konstruktoriLuoPelaajanTest() {
        assertEquals("testaaja", pelaaja.getName());
    }

    @Test
    public void arvoLaivaPysyyAlueellaTest() {
        Ship uusi = pelaaja.arvoLaiva(3);
        if(uusi.isOrientation()){
        int xend = uusi.getSize() - 1 + uusi.getX();
        assertTrue((uusi.getX() >= 0) && (xend < 10) && (uusi.getY() >= 0) && (uusi.getY() < 10));
        }else{
        int yend = uusi.getSize() - 1 + uusi.getY();
        assertTrue((uusi.getX() >= 0) && (uusi.getX() < 10) && (uusi.getY() >= 0) && (yend < 10));
        }
    }

    @Test
    public void arvoLaivaEiLisaaLaivastoaNegatiivisillaTest() {
        Ship uusi = pelaaja.arvoLaiva(-1);
        int laivastonKoko = pelaaja.getFleat().size();
        assertEquals(0, laivastonKoko);
    }

    @Test
    public void arvoLaivaEiLisaaLaivastoaYliAlueenArvoillaTest() {
        Ship uusi = pelaaja.arvoLaiva(11);
        int laivastonKoko = pelaaja.getFleat().size();
        assertEquals(0, laivastonKoko);
    }
    
    @Test
    public void arpoLaivaKoollaOnValiaTest() {
        assertNull(pelaaja.arvoLaiva(-3));
    }

    @Test
    public void asetaLaivaKoollaOnValiaTest() {
        assertNull(pelaaja.arvoLaiva(-3));
    }

    @Test
    public void asetaLaivaPalauttaaNullVaarillaArvoillaTest() {
        assertEquals(null, pelaaja.asetaLaiva(10, 10, 1, true));
    }

    @Test
    public void asetaLaivaPalauttaaNullVaarillaArvoilla2Test() {
        assertEquals(null, pelaaja.asetaLaiva(-1, 1, 1, true));
    }

    @Test
    public void asetaLaivaPalauttaaLaivanOikeillaArvoillaTest() {
        String res = pelaaja.asetaLaiva(3, 3, 3, true).toString();
        assertEquals("x = 3, y = 3, koko = 3, suunta = vertical, health = 3", res);
    }

    @Test
    public void asetaLaivaPalauttaaLaivanOikeillaArvoilla2Test() {
        String res = pelaaja.asetaLaiva(3, 3, 3, false).toString();
        assertEquals("x = 3, y = 3, koko = 3, suunta = horisontal, health = 3", res);
    }

    @Test
    public void asetaLaivaLisaaLaivastoonOikeinTest() {
        pelaaja.asetaLaiva(0, 0, 3, true).toString();
//        pelaaja.asetaLaiva(0, 0, 3, false).toString();
//        pelaaja.asetaLaiva(0, 3, 3, true).toString();
        pelaaja.asetaLaiva(2, 2, 3, false).toString();

        assertSame(2, pelaaja.getFleat().size());
    }

    @Test
    public void arvoLaivaLisaaLaivastoonOikeinTest() {
        pelaaja.arvoLaiva(4);
        pelaaja.arvoLaiva(3);
        pelaaja.arvoLaiva(-3);
        assertSame(2, pelaaja.getFleat().size());
    }
}
