package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.graphics.Grid2;
import laivanupotus.logiikka.Player;

public class ShipRandomizerListener implements ActionListener {

    private Grid2 grid;
    private final Player p;
    private final int count;

    public ShipRandomizerListener(Player p, int count, Grid2 grid) {
        this.grid = grid;
        this.p = p;
        this.count = count;
    }

    private void emtyArea(Player p, Grid2 grid) {
        this.p.getFleet().removeAll(this.p.getFleet());
        for (int i = 0; i < p.getArea().length; i++) {
            for (int j = 0; j < p.getArea().length; j++) {
                p.getArea()[i][j] = '.';
            }
            grid.update();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        emtyArea(p, grid);
        for (int i = count; i >= 0; i--) {
            int size = i;
            if (i < 3) {
                if (i == 0) {
                    size = 1;
                }
                size++;
            }
            p.arvoLaiva(size);
            grid.update();
        }

    }
}
