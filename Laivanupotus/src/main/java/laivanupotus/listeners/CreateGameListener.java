package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.ui.GUI;

/**
 *
 * @author evly
 */
public class CreateGameListener implements ActionListener {

    public CreateGameListener() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        GUI.changeScreen = true;
        GUI.update();
    }
    
}
