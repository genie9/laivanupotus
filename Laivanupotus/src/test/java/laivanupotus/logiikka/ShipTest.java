package laivanupotus.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShipTest {
    
    Ship laiva;

    @Before
    public void setUp() {
        
    }
    
     @Test
     public void konstruktoriAsettaaArvotOikeinTest() {
         laiva = new Ship(4, 5, 4, true);
         assertEquals("ship(4,5)", laiva.toString());
     }
     
     @Test
     public void isHitTrueOsuessaHorisantalLaivaanTest(){
         laiva = new Ship(4, 5, 4, false);
         assertEquals(true, laiva.isHit(4, 6));
     }
     
     @Test
     public void isHitFalseEiOsuessaHorisantalLaivaanTest(){
         laiva = new Ship(4, 5, 4, false);
         assertEquals(false, laiva.isHit(0, 0));
     }
      @Test
     public void isHitTrueOsuessaVerticalLaivaanTest(){
         laiva = new Ship(4, 5, 4, true);
         assertEquals(true, laiva.isHit(5, 5));
     }
      @Test
     public void isHitFalseEiOsuessaVerticalLaivaanTest(){
         laiva = new Ship(4, 5, 4, true);
         assertEquals(false, laiva.isHit(0, 0));
     }
}
