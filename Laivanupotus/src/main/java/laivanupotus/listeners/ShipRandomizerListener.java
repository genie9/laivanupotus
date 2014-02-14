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
        this.p.getFleet().removeAll(this.p.getFleet());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
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
