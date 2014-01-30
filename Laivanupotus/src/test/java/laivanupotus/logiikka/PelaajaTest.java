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
        pelaaja=new Pelaaja("testaaja", 10);
    }
    
     @Test
     public void konstruktoriLuoPelaajanTest() {
         assertEquals("testaaja", pelaaja.getName());
     }
     
     @Test
     public void 
     
}
