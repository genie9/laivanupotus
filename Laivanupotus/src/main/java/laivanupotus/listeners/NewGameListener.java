package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.laivanupotus.App;
import laivanupotus.ui.GUI;

/**
 * newGame menuItemin kuuntelija. Tekee uuden pelin.
 * @author evly@cs
 */
public class NewGameListener implements ActionListener {

    public NewGameListener() {
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {    
        App.start();
        GUI.frame.dispose();
    }
    
}
