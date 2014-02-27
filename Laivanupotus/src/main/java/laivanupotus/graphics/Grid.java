package laivanupotus.graphics;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import laivanupotus.logiikka.Player;

/**
 * 
 * @author Genie
 */
public class Grid extends JPanel {
    private final Player player;
    private final char[][] area;

    /**
     * Luodaan alueelle layout, johon sijoitetaan ruudut(CellPane:it).
     * Alustetaan area pelaajan pelialuella. 
     * 
     * @param p Gridin omistava pelaaja
     */
    public Grid(Player p) {
        this.player = p;
        this.area = p.getArea();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                
                CellPane cellPane = new CellPane(col, row, this.player);
                Border border;
                if (row < 9) {
                    if (col < 9) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < 9) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cellPane.setBorder(border);
                add(cellPane, gbc);
            }
        }
    }

    /**
     * Päivittää ruudun tilan annetuissa koordinaateissa.
     * 
     * @param x pystysuuntainen koordinaatti
     * @param y vaakasuuntainen koordinaatti
     * @param p Kentän omistava pelaaja
     */
    public void update(int x, int y, Player p) {
        if(getPlayer()== p)
                getCell(y * 10 + x).setState(area[x][y]);
            }
    
    /**
     * Päivittää kaikki gridin ruudut
     */
    public void updateAll() {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                getCell(j * 10 + i).setState(area[i][j]);
            }
        }
    }

    public CellPane getCell(int coord) {
        return (CellPane) getComponent(coord);
    }
    
    /**
     * Päivittää kaikkien gridin ruutujen hidden tilaa.
     * @param state Käyttäjän antama syöte, true/false
     */
    public void hideShips(boolean state){
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                getCell(j * 10 + i).setHidden(state);
            }
        }
        updateAll();
    }

    public Player getPlayer() {
        return player;
    }
    
}
