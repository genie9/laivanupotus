package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import laivanupotus.ui.GUI;

/**
 * Tämä luokka ei ole käytössä. Luokan tarkoitus on luoda napin kuuntelija
 * kaksinpelia varten jolla mm. piilotetaan vastapelaajan laivat ja muutetaan
 * randonize napin toimintaa.
 *
 * @author evly
 */
public class HideShipsListener implements ActionListener {

    public HideShipsListener() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("toimiiks tää buiogggggev");
        if (GUI.canShoot) {
            GUI.setWhosTurn();
        }
        if (!GUI.canShoot) {
            JButton b = (JButton) GUI.informationArea().getComponent(5);

            b.removeAll();
            b.setText("Player2, Create yar fleet");
            b.addActionListener(new ShipRandomizerListener(GUI.getPlayer2(), 5, GUI.g2));
        }
//        GUI.changePlayer();
        if (GUI.whosTurn) {
            GUI.g1.hideShips();
        } else {
            GUI.g2.hideShips();
        }
    }
}
