package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.graphics.Grid;
import laivanupotus.logiikka.Player;

/**
 *
 * @author Genie
 */
public class ShipRandomizerListener implements ActionListener {

    private Grid grid;
    private final Player p;
    private final int count;

    public ShipRandomizerListener(Player p, int count, Grid grid) {
        this.grid = grid;
        this.p = p;
        this.count = count;
    }

    private void emptyArea(Player p, Grid grid) {
        this.p.getFleet().removeAll(this.p.getFleet());
        for (int i = 0; i < p.getArea().length; i++) {
            for (int j = 0; j < p.getArea().length; j++) {
                p.getArea()[i][j] = '.';
                grid.update(i,j, p);
            }
//            grid.update();
        }
    }

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
            p.arvoLaiva(size);
            grid.updateAll();
        }

    }
}
