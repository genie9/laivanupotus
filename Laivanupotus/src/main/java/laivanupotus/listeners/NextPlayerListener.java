package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import laivanupotus.ui.GUI;

/**
 *
 * @author evly
 */
public class NextPlayerListener implements ActionListener {

    private JLabel labelToChange;

    public NextPlayerListener(JLabel placeShips) {
        labelToChange = placeShips;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        GUI.setWhosTurn(false);

        if (GUI.players) {
            labelToChange.setText("Player2, place your ships!");
        } else {
            labelToChange.setText("Player2 is ready. Press START");
        }
    }

}
