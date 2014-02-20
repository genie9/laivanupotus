package laivanupotus.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import laivanupotus.graphics.Grid;
import laivanupotus.listeners.ShipRandomizerListener;
import laivanupotus.listeners.StartListener;
import laivanupotus.logiikka.Player;

/**
 *
 * @author Genie
 */
public class GUI implements Runnable {

    private JFrame frame;
    private static Player player1;
    private static Player player2;
    private static boolean whosTurn;
    private static JPanel info;
    public static Grid g1;
    public static Grid g2;

    public GUI() {
        player1 = new Player("Test", 10);
        player2 = new Player("tapettava", 10);
        whosTurn = true;
        info = new JPanel();
        g1 = new Grid(player1);
        g2 = new Grid(player2);
    }

    @Override
    public void run() {
        frame = new JFrame("BATTLESHIPS");
        frame.setPreferredSize(new Dimension(800, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        /*mainLayout*/
        BoxLayout layout = new BoxLayout(container, BoxLayout.LINE_AXIS);
        container.setLayout(layout);

        /*left side - battleArea*/
        JPanel battleArea = new JPanel();
        battleArea.setLayout(new BoxLayout(battleArea, BoxLayout.PAGE_AXIS));

        battleArea.add(g1);
        battleArea.add(g2);
        JPanel controls = informationArea();

        /*mainLayout container*/
        container.add(battleArea);
        container.add(controls);
    }

    private JPanel informationArea() {
        /*right side - informationArea*/
        JPanel controls = new JPanel();
        
        controls.setLayout(new BoxLayout(controls, BoxLayout.PAGE_AXIS));
        controls.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 50));
        controls.add(Box.createHorizontalGlue());
        
        JLabel welcome = new JLabel("Welcome to fight for your glory! \n");
        
        
        
        JButton randomize = new JButton("Create yar fleet");
        ShipRandomizerListener randomizer1 = new ShipRandomizerListener(player1, 5, g1);
        randomize.addActionListener(randomizer1);
        JButton start = new JButton("Start game!");
        StartListener starter = new StartListener(randomize, start, info);
        ShipRandomizerListener randomizer2 = new ShipRandomizerListener(player2, 5, g2);
        start.addActionListener(randomizer2);
        start.addActionListener(starter);
        info.setLayout(new BoxLayout(info, BoxLayout.PAGE_AXIS));
        JLabel turnInfo = new JLabel("Player1 shoots! \n");
        JLabel state = new JLabel();
        info.add(turnInfo);
        info.add(state);
        controls.add(welcome);
        controls.add(Box.createRigidArea(new Dimension(0, 100)));
        controls.add(randomize);
        controls.add(Box.createRigidArea(new Dimension(0, 20)));
        controls.add(start);
        controls.add(Box.createRigidArea(new Dimension(0, 20)));
        info.setVisible(false);
        controls.add(info);
        return controls;
    }

    public static void infoState(int state) {
        if (state == -1) {
            JLabel hit = (JLabel) info.getComponent(1);
            hit.setText("miss");
        } else if (state == 0) {
            JLabel destroyed = (JLabel) info.getComponent(1);
            destroyed.setText("destroyed");
        } else {
            JLabel hit = (JLabel) info.getComponent(1);
            hit.setText("hit");
        }
    }

    public static void setWhosTurn(boolean muutos) {
        GUI.whosTurn = muutos;
        if (muutos) {
            JLabel turnInfo = (JLabel) info.getComponent(0);
            turnInfo.setText("Player1 shoots!");
        } else {
            JLabel turnInfo = (JLabel) info.getComponent(0);
            turnInfo.setText("Player2 shoots!");
        }
    }

    public static boolean isWhosTurn() {
        return whosTurn;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public JFrame getFrame() {
        return frame;
    }
}
