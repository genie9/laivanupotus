package laivanupotus.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player red;
    Player black;

    public PlayerTest() {
    }

    @Before
    public void setUp() {
        red = new Player("testaaja", 10);
        black = new Player("ammuttava", 10);
    }

    @Test
    public void konstruktoriLuoPelaajanTest() {
        assertEquals("testaaja", red.getName());
    }

    @Test
    public void arvoLaivaPysyyAlueellaTest() {
        Ship uusi = red.randomizeShip(3);
        if (uusi.isPosition()) {
            int xend = uusi.getSize() - 1 + uusi.getX();
            assertTrue((uusi.getX() >= 0) && (xend < 10) && (uusi.getY() >= 0) && (uusi.getY() < 10));
        } else {
            int yend = uusi.getSize() - 1 + uusi.getY();
            assertTrue((uusi.getX() >= 0) && (uusi.getX() < 10) && (uusi.getY() >= 0) && (yend < 10));
        }
    }

    @Test
    public void arvoLaivaEiLisaaLaivastoaNegatiivisillaTest() {
        Ship uusi = red.randomizeShip(-1);
        int laivastonKoko = red.getFleet().size();
        assertEquals(0, laivastonKoko);
    }

    @Test
    public void arvoLaivaEiLisaaLaivastoaYliAlueenArvoillaTest() {
        Ship uusi = red.randomizeShip(11);
        int laivastonKoko = red.getFleet().size();
        assertEquals(0, laivastonKoko);
    }

    @Test
    public void arvoLaivaKoollaOnValiaTest1() {
        assertEquals(null, red.randomizeShip(-3));
    }

    @Test
    public void arvoLaivaKoollaOnValiaTest2() {
        assertEquals(null, red.randomizeShip(10));
    }
    

    
    @Test
    public void asetaLaivaKoollaOnValiaTest1() {
        assertNull(red.placeShip(1, 1, 10, true));
    }

    @Test
    public void asetaLaivaKoollaOnValiaTest2() {
        assertEquals(null, red.placeShip(1, 1, -3, true));
    }
    @Test
    public void asetaLaivaKoollaOnValiaTest3() {
        String res = red.placeShip(0, 0, 4, true).toString();
        assertEquals("ship(0,0)", res);
    }

    @Test
    public void asetaLaivaPalauttaaNullVaarillaArvoillaTest() {
        assertEquals(null, red.placeShip(10, 10, 1, false));
    }

    @Test
    public void asetaLaivaPalauttaaNullVaarillaArvoilla2Test() {
        assertEquals(null, red.placeShip(-1, -1, 1, true));
    }

    @Test
    public void asetaLaivaPalauttaaLaivanOikeillaArvoillaTest() {
        String res = red.placeShip(3, 3, 3, true).toString();
        assertEquals("ship(3,3)", res);
    }

    @Test
    public void asetaLaivaPalauttaaLaivanOikeillaArvoilla2Test() {
        String res = red.placeShip(3, 3, 3, false).toString();
        assertEquals("ship(3,3)", res);
    }

    @Test
    public void asetaLaivaLisaaLaivastoonOikeinTest() {
        red.placeShip(0, 0, 3, true);
        red.placeShip(0, 0, 3, false);
        red.placeShip(0, 3, 3, true);
        red.placeShip(2, 2, 3, false);

        assertEquals(2, red.getFleet().size());
    }

    @Test
    public void arvoLaivaLisaaLaivastoonOikeinTest() {
        red.randomizeShip(4);
        red.randomizeShip(3);
        red.randomizeShip(-3);
        assertSame(2, red.getFleet().size());
    }

    @Test
    public void shootOsuessaanPalauttaaOkeinTest() {
        black.placeShip(0, 0, 3, true);
        assertEquals(2, red.shoot(black, 0, 0));
    }

    @Test
    public void shootEiOsuessaanPalauttaaOikeinTest() {
        black.placeShip(0, 0, 1, true);
        assertEquals(-1, red.shoot(black, 0, 1));
        assertEquals(1, red.shots);
    }

    @Test
    public void shootTappaessaanPoistaaLaivanLaivastostaTest() {
        black.placeShip(0, 0, 1, true);
        red.shoot(black, 0, 0);
        assertEquals(0, black.getFleet().size());
        assertEquals(1, red.shots);
    }
    
    @Test
    public void shootAmpuessaanSamaanPaikkaanPalauttaaOikeinTest() {
        black.placeShip(0, 0, 1, true);
        red.shoot(black, 0, 0);
        assertEquals(-2, red.shoot(black, 0, 0));
        assertEquals(1, red.shots);
    }

}
