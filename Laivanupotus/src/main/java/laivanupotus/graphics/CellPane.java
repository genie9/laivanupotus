package laivanupotus.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import laivanupotus.logiikka.Player;
import laivanupotus.ui.GUI;

/**
 *
 * @author Genie
 */
public class CellPane extends JPanel {

    private Color background;
    private Color defbg;
    private int x, y;
    private Player p;
    private char state;
    private boolean hidden;

    public CellPane(int x, int y, Player p) {
        this();
        this.x = x;
        this.y = y;
        this.p = p;
        this.state = p.getArea()[x][y];
        updateColour(state);
        this.hidden = false;
    }
//char state

    public CellPane() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //   background = getBackground();
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

    private void shoot() {
        boolean cur = GUI.isWhosTurn();
        if (this.p == GUI.getPlayer(cur)) {    //ei voi ampua omalle alueelle
            return;
        }
        int result;

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

    public void setHidden() {
        this.hidden = true;
    }
}
