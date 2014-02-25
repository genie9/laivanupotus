package laivanupotus.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Random;
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
    private static Random r;
    private static Player player1;
    private static Player player2;
    public static boolean whosTurn;
    private static JPanel info;
    public static Grid g1;
    public static Grid g2;
    public static boolean canShoot;

    public GUI() {
        r = new Random();
        player1 = new Player("Test", 10);
        player2 = new Player("tapettava", 10);
        whosTurn = true;
        info = new JPanel();
        g1 = new Grid(player1);
        g2 = new Grid(player2);
        g2.hideShips();
        canShoot = false;
    }

    @Override
    public void run() {
        frame = new JFrame("BATTLESHIPS");
        frame.setPreferredSize(new Dimension(800, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        /*mainLayout*/
        BoxLayout layout = new BoxLayout(container, BoxLayout.LINE_AXIS);
        container.setLayout(layout);

        /*left side - battleArea*/
        JPanel battleArea = new JPanel();
        battleArea.setLayout(new BoxLayout(battleArea, BoxLayout.PAGE_AXIS));

//        battleArea.setBorder(BorderFactory.createLineBorder(Color.black));
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
        randomize.addActionListener(new ShipRandomizerListener(player1, 5, g1));

        JButton start = new JButton("Start game!");
        start.addActionListener(new ShipRandomizerListener(player2, 5, g2));
        start.addActionListener(new StartListener(randomize, start, info));

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
        JLabel hit = (JLabel) info.getComponent(1);
        if (state == -3) {
            hit.setText("WIN!!");
            canShoot = !canShoot;
        } else if (state == -2) {
            hit.setText("ERROR! Shoot again");
        } else if (state == -1) {
            hit.setText("miss");
        } else if (state == 0) {
            hit.setText("destroyed");
        } else {
            hit.setText("hit");
        }
    }

    public static void setWhosTurn() {
        whosTurn = !whosTurn;
        JLabel turnInfo = (JLabel) info.getComponent(0);
        if (whosTurn) {
            if(!canShoot){
                turnInfo.setText("Player1");
                return;
            }
            turnInfo.setText("Player1 shoots!");
            System.out.println("Player2: " + player2.shots + " shots, fleetSize " + player2.getFleet().size() + ". Player1 shoots!");
        } else {
            if(!canShoot){
                turnInfo.setText("Player2");
                return;
            }
            turnInfo.setText("Player2 shoots!");
            System.out.println("Player1: " + player1.shots + " shots, fleetSize " + player1.getFleet().size() + ". Player2 shoots!");
            cylonShoots();
        }
    }

    public static void cylonShoots() {
        int result = 0;
        while (result > -1) {
            int x = r.nextInt(10);
            int y = r.nextInt(10);
            result = player2.shoot(player1, x, y);
            if (result == 0) {
                if (player1.getFleet().isEmpty()) {
                    infoState(-3);
                }
            }
            infoState(result);
            g1.update(x, y, player1);
//            Thread.sleep(1000);   
        }
        setWhosTurn();
    }

    public static boolean isWhosTurn() {
        return whosTurn;
    }

    public static Player getPlayer(boolean who) {
        if (true) {
            return player1;
        }
        return player2;
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
