package laivanupotus.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {

    Pelaaja pelaaja;

    public PelaajaTest() {
    }

    @Before
    public void setUp() {
        pelaaja = new Pelaaja("testaaja", 10);
    }

    @Test
    public void konstruktoriLuoPelaajanTest() {
        assertEquals("testaaja", pelaaja.getName());
    }

    @Test
    public void arpoLaivaPysyyAlueellaTest() {
        Laiva uusi = pelaaja.arvoLaiva(3);
        int xend = uusi.getKoko() - 1 + uusi.getX();
        int yend = uusi.getKoko() - 1 + uusi.getY();

        assertTrue(uusi.getX() >= 0 && xend < 10 && uusi.getY() >= 0 && yend < 10);
    }

        @Test
    public void arpoLaivaKoollaOnValiaTest() {
        assertNull( pelaaja.arvoLaiva(-3));
    }
    
            @Test
    public void asetaLaivaKoollaOnValiaTest() {
        assertNull( pelaaja.arvoLaiva(-3));
    }
    
    @Test
    public void asetaLaivaPalauttaaNullVäärilläArvoillaTest() {
        assertEquals(null, pelaaja.asetaLaiva(10, 10, 1, true));
    }

    @Test
    public void asetaLaivaPalauttaaNullVäärilläArvoilla2Test() {
         assertEquals(null, pelaaja.asetaLaiva(-1, 1, 1, true));
    }

    @Test
    public void asetaLaivaPalauttaaLaivanOikeillaArvoillaTest() {
        String res = pelaaja.asetaLaiva(3, 3, 3, true).toString();
        assertEquals("x = 3, y = 3, koko = 3, suunta = vertical", res);
    }

    @Test
    public void asetaLaivaPalauttaaLaivanOikeillaArvoilla2Test() {
        String res = pelaaja.asetaLaiva(3, 3, 3, false).toString();
        assertEquals("x = 3, y = 3, koko = 3, suunta = horisontal", res);
    }

    @Test
    public void asetaLaivaLisaaLaivastoonOikeinTest() {
        pelaaja.asetaLaiva(3, 3, 3, true).toString();
        //pelaaja.asetaLaiva(3, 3, 3, true).toString();
        //pelaaja.asetaLaiva(3, 3, 3, true).toString();
        pelaaja.asetaLaiva(4, 4, 3, true).toString();

        assertSame(2, pelaaja.getLaivasto().size());
    }
    
    @Test
    public void arpoLaivaLisaaLaivastoonOikeinTest() {
        pelaaja.arvoLaiva(4);
        pelaaja.arvoLaiva(3);
         assertSame(2, pelaaja.getLaivasto().size());
    }
}
