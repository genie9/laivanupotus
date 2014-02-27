package laivanupotus.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import laivanupotus.ui.GUI;

/**
 * Start-napin kuuntelija. Asettaa pelin canShoot-tilaaan, ottaa jatkopelin kannalta
 * tarpeettomat napit pois käytöstä ja ottaa käyttöön info-alueen. Yksinpelissä piilottaa 
 * AI:n laivat.
 * 
 * @author Genie
 */
public class StartListener implements ActionListener {

    private final JButton toDisable1;
    private final JButton toDisable2;
    private final JPanel toVisible;

    /**
     * Käyttäjän syöttämät alueet:
     * @param toDisable1
     * @param toDisable2
     * @param toVisible
     */
    public StartListener(JButton toDisable1, JButton toDisable2, JPanel toVisible) {
        this.toDisable1 = toDisable1;
        this.toDisable2 = toDisable2;
        this.toVisible = toVisible;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!GUI.pvp) {
            GUI.g2.hideShips(true);
        }
        toDisable1.setEnabled(false);
        toDisable2.setEnabled(false);
        toVisible.setVisible(true);
        GUI.canShoot = true;
    }
}
