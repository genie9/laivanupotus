package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.ui.GUI;

/**
 * endGame menuItemin kuuntelija. Lopettaa pelin.
 * 
 * @author evly@cs
 */
public class EndGameListener implements ActionListener {

    public EndGameListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GUI.frame.dispose();
    }
    
}
