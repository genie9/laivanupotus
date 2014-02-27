package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.graphics.Grid;
import laivanupotus.logiikka.Player;

/**
 * Randomize-napin kuuntelija
 *
 * @author Genie
 */
public class ShipRandomizerListener implements ActionListener {

    private Grid grid;
    private final Player p;
    private final int count;

    /**
     *
     * @param p Vuorossa oleva Player
     * @param count laivojen määrä
     * @param grid Vuorossa olevan pelaajan kenttä
     */
    public ShipRandomizerListener(Player p, int count, Grid grid) {
        this.p = p;
        this.count = count - 1;
        this.grid = grid;
    }

    /**
     * Tyhjentää kentän (Grid-luokka) laivoista
     *
     * @param p Vuorossa oleva Player
     * @param grid Vuorossa olevan pelaajan kenttä
     */
    private void emptyArea(Player p, Grid grid) {
        this.p.getFleet().removeAll(this.p.getFleet());
        for (int i = 0; i < p.getArea().length; i++) {
            for (int j = 0; j < p.getArea().length; j++) {
                p.getArea()[i][j] = '.';
                grid.update(i, j, p);
            }
        }
    }

    /**
     * Laskee laivojenn pituudet ja arpoo laivat kentälle. 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        emptyArea(p, grid);
        for (int i = count; i >= 0; i--) {
            int size = i;
            if (i < 3) {
                if (i == 0) {
                    size = 1;
                }
                size++;
            }
            p.randomizeShip(size);
        }
        grid.updateAll();

    }
}
