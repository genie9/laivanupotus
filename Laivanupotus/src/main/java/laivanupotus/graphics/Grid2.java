package laivanupotus.graphics;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Grid2 extends JPanel {

    private char[][] area;

    public Grid2(char[][] area) {
        this.area = area;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                CellPane cellPane = new CellPane(col, row, this.area[col][row]);
                Border border = null;
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

    public void update() {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                getCell(j * 10 + i).setState(area[i][j]);
            }
        }
    }

    public CellPane getCell(int coord) {
        return (CellPane) getComponent(coord);
    }
//    @Override
//    public Component getComponentAt(int x, int y) {
//        return super.getComponentAt(x, y); //To update body of generated methods, choose Tools | Templates.
//    }

}
