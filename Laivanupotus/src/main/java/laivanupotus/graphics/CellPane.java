package laivanupotus.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class CellPane extends JPanel {

    private Color background;
    private Color defbg;
    private int x, y;
    private char state;

    public CellPane(int x, int y, char state) {
        this();
        this.x = x;
        this.y = y;
        this.state = state;
        updateColour(state);

    }

    private void updateColour(char state) {
        if (state == 'x') {
            background = Color.PINK;
        } else if (state == 'S') {
            background = Color.BLACK;
        } else if (state == 'X') {
            background = Color.ORANGE;
        } else {
            background = Color.getHSBColor(145.0f / 255.0f, 175.0f / 255.0f, 125.0f / 255.0f);
        }
        defbg = background;
        repaint();
    }

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
        });
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
}
