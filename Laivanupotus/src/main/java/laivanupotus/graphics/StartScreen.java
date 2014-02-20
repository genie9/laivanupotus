package laivanupotus.graphics;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author sipsihiiri
 */
public class StartScreen extends JPanel{

    public StartScreen() {
        setLayout(layout);
    }
    
    GridLayout layout = new GridLayout(3, 2, 30, 30);
    Border outer = new EtchedBorder(3, Color.BLUE, Color.yellow);
    
    JButton rusRules = new JButton("Soviet Union Rules");
}
