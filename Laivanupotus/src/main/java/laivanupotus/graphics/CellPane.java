package laivanupotus.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import laivanupotus.logiikka.Player;
import laivanupotus.ui.GUI;

/**
 * Piirtää yksittäiset ruudut kenttään(Gridiin). Ruuduilla on mouselistener.
 *
 * @author Genie
 */
public class CellPane extends JPanel {

    private Color background;
    private Color defbg;
    private int x, y;
    private Player p;
    /**
     * Ruudun väriä määrittelevä tila char:ina
     */
    private char state;
    /*  
     * Ruudun näkyvyyttä määrittelevä tila
     */
    private boolean hidden;

    /**
     *
     * @param x Ruudun pystysuunnan koordinaatti
     * @param y Ruudun vaakasuunnan koordinaatti
     * @param p Ruudun omistava pelaaja
     */
    public CellPane(int x, int y, Player p) {
        this();
        this.x = x;
        this.y = y;
        this.p = p;
        this.state = p.getArea()[x][y];
        updateColour(state);
        this.hidden = false;
    }

    /**
     * Luodan hiiren kuuntelijat
     */
    public CellPane() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defbg);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (GUI.canShoot) {
                    shoot();
                }
            }
        });
    }

    /**
     * Hiirenklikkauksella ajaa Playerin shoot() metodia kyseiselle ruudulle.
     * Jos ruutu on ampujan oma, ei tee mitään
     *
     */
    private void shoot() {
        boolean cur = GUI.whosTurn;
        if (this.p == GUI.getPlayer(cur)) {    //ei voi ampua omalle alueelle
            return;
        }
        int result;

        /*yritys optimoida allaolevaa metodin osiota. ei toimi vielä*/
//        result = GUI.getPlayer(cur).shoot(GUI.getPlayer(!cur), x, y);
//        if (GUI.getPlayer(!cur).getFleet().isEmpty()) {
//            result = -3;
//            GUI.win = true;
//            GUI.setWhosTurn();
//        } else if (result == -1) {
//            if (!GUI.pvp) {
//                GUI.setWhosTurn();
//            }
//        }
        if (GUI.whosTurn) {
            result = GUI.getPlayer1().shoot(GUI.getPlayer2(), x, y);
            if (GUI.getPlayer2().getFleet().isEmpty()) {
                result = -3;
                GUI.win = true;
                GUI.setWhosTurn();
            } else if (result == -1) {
                if (!GUI.pvp) {
                    GUI.setWhosTurn();
                }

            }
        } else {
            result = GUI.getPlayer2().shoot(GUI.getPlayer1(), x, y);
            if (GUI.getPlayer1().getFleet().isEmpty()) {
                result = -3;
                GUI.win = true;
                GUI.setWhosTurn();
            }
            if (result == -1) {
                if (!GUI.pvp) {
                    GUI.setWhosTurn();
                }
            }

        }
        GUI.infoState(result);
        updateColour(state);
        GUI.g1.updateAll();
        GUI.g2.updateAll();
    }

    /**
     * Asettaa ruudun halutun väriseksi.
     *
     * @param state
     */
    private void updateColour(char state) {
        if (state == 'x') {
            background = Color.PINK;
        } else if (state == 'S') {
            if (!hidden) {
                background = Color.BLACK;
            }
        } else if (state == 'X') {
            background = Color.ORANGE;
        } else if (state == 'D') {
            background = Color.RED;
        } else {
            background = Color.getHSBColor(145.0f / 255.0f, 165.0f / 255.0f, 125.0f / 255.0f);
        }
        defbg = background;
        repaint();
    }

    public void setState(char state) {
        updateColour(state);
    }

    @Override
    public Color getBackground() {
        return background;
    }

    @Override
    public void setBackground(Color bg) {
        this.background = bg;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }

    /**
     * Piilottaa ruudun.
     *
     * @param state True - piilottaa ruudun, false laittaa näkyväksi.
     */
    public void setHidden(boolean state) {
        this.hidden = state;
    }
}
