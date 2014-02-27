package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import laivanupotus.ui.GUI;

/**
 * Ready-napin kuuntelija. Ottaa start-nappin käyttöön. Enemmän toimintoja kaksinpelia varten, mm. 
 * piilotetaan vastapelaajan laivat ja muutetaan randonize napin toimintaa.
 * Koska PvP ei ole toiminnassa, koodi kommentoituna alla.
 * 
 * @author evly
 */
public class HideShipsListener implements ActionListener {

    private static int count;

    public HideShipsListener() {
        count = 0;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        count++;
        if (!GUI.pvp) {
            if (count == 1) {
                GUI.start.setEnabled(true);
                return;
            }
        }

        /*pvp peliin*/
//        if (count == 1) {
//            GUI.g1.hideShips(true);
//            GUI.g2.hideShips(true);
//            return;
//        }
//        if(count == 2){
//            if (GUI.whosTurn) {
//              GUI.g1.hideShips(false);
//            } else {
//              GUI.g2.hideShips(false);
//            }
//            if (!GUI.canShoot) {
//              JButton b = GUI.ranomize;
//              b.removeAll();
//              b.setText("Player2, Create yar fleet");;
//              b.addActionListener(new ShipRandomizerListener(GUI.getPlayer2(), 5, GUI.g2));
//              GUI.start.setEnabled(true);
//            }
//            count=0;
//            if (GUI.canShoot) {
//              GUI.setWhosTurn();
//            }
//        }      
    }
}
