package laivanupotus.listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.logiikka.Player;

public class ShipRandomizerListener implements ActionListener {

    private Component component;
    private final Player p;
    private final int count;

    public ShipRandomizerListener(Player p, int count, Component c) {
        component = c;
        this.p = p;
        this.count = count;
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
        }
        component.repaint();
    }
}
