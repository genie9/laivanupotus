package laivanupotus.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaivaTest {
    
    Laiva laiva;

    @Before
    public void setUp() {
        laiva = new Laiva(4, 5, 4, true);
    }
    
     @Test
     public void konstruktoriAsettaaArvotOikein() {
         assertEquals("x = 4, y = 5, koko = 4, suunta = vertical", laiva.toString());
     }
}
