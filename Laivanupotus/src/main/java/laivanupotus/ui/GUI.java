package laivanupotus.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.*;
import laivanupotus.graphics.Grid;
import laivanupotus.listeners.HideShipsListener;
import laivanupotus.listeners.ShipRandomizerListener;
import laivanupotus.listeners.StartListener;
import laivanupotus.listeners.EndGameListener;
import laivanupotus.listeners.NewGameListener;
import laivanupotus.logiikka.Player;

/**
 *
 * @author Genie
 */
public class GUI implements Runnable {

    public static JFrame frame;

    private JMenuBar menuBar;
    private JMenu actions;
    private JMenuItem newGame;
    private JMenuItem exitGame;
    private static Random r;
    private static Player player1;
    private static Player player2;
    public static Grid g1;
    public static Grid g2;
    private static JPanel info;
    public static JButton start;
    public static JButton randomize;
    public static boolean canShoot;
    public static boolean whosTurn;
    public static boolean pvp;
    public static boolean win;

    public GUI() {
        r = new Random();
        player1 = new Player("Test", 10);
        player2 = new Player("tapettava", 10);
        g1 = new Grid(player1);
        g2 = new Grid(player2);
        canShoot = false;
        whosTurn = true;
        pvp = false;
        win = false;
    }

    @Override
    public void run() {
        frame = new JFrame("BATTLESHIPS");
        frame.setPreferredSize(new Dimension(800, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar());

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private JMenuBar menuBar() {
        menuBar = new JMenuBar();

        actions = new JMenu("Action");
        menuBar.add(actions);

        newGame = new JMenuItem("New game");
        newGame.addActionListener(new NewGameListener());
        exitGame = new JMenuItem("Exit game");
        exitGame.addActionListener(new EndGameListener());

        actions.add(newGame);
        actions.add(exitGame);

        return menuBar;
    }

    private void createComponents(Container container) {
        /*mainLayout*/
        BoxLayout layout = new BoxLayout(container, BoxLayout.LINE_AXIS);
        container.setLayout(layout);

        /*left side - battleArea*/
        JPanel battleArea = new JPanel();
        battleArea.setLayout(new BoxLayout(battleArea, BoxLayout.PAGE_AXIS));

        battleArea.add(g1);
        battleArea.add(g2);

        /*mainLayout container*/
        container.add(battleArea);
        container.add(informationArea());
    }

    /**
     * Peli-ikkunan oikea puoli. Sisältää informaation ja pelin ohjauksen.
     *
     * @return JPanel controls
     */
    public JPanel informationArea() {
        JPanel controls = new JPanel();

        controls.setLayout(new BoxLayout(controls, BoxLayout.PAGE_AXIS));
        controls.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 50));
        controls.add(Box.createHorizontalGlue());

        JButton ready = new JButton("Ready");           //ei voi ampua ennen kun laivat on kentällä
        ready.addActionListener(new HideShipsListener());

        controls.add(new JLabel("Welcome to fight for your glory! \n"));
        controls.add(Box.createRigidArea(new Dimension(0, 100)));
        controls.add(randomize());
        controls.add(Box.createRigidArea(new Dimension(0, 20)));
        controls.add(ready);
        controls.add(Box.createRigidArea(new Dimension(0, 20)));
        controls.add(info());
        controls.add(Box.createRigidArea(new Dimension(0, 100)));
        controls.add(start());
        controls.add(Box.createRigidArea(new Dimension(0, 20)));

        return controls;
    }

    /**
     * informatioArean nappi
     */
    private JButton randomize() {
        randomize = new JButton("Player1, Create yar fleet");
        randomize.addActionListener(new ShipRandomizerListener(player1, 6, g1));
        return randomize;
    }

    /**
     * informatioArean alue
     */
    private JPanel info() {
        info = new JPanel();
        info.setVisible(false);
        info.setLayout(new BoxLayout(info, BoxLayout.PAGE_AXIS));

        JLabel turnInfo = new JLabel("Player1 shoots! \n");
        JLabel state = new JLabel();

        info.add(turnInfo);
        info.add(state);
        return info;
    }

    /**
     * informatioArean nappi
     */
    private JButton start() {
        start = new JButton("Start game!");
        start.setEnabled(false);
        if (!pvp) {
            start.addActionListener(new ShipRandomizerListener(player2, 6, g2));
        }
        start.addActionListener(new StartListener(randomize, start, info));
        return start;
    }

    /**
     * Metodi saa tietoa ampumisen tuloksesta ja päivittää info():n
     *
     * @param state
     */
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

    /**
     * Metodi päivittää booleania whosTurn ja infon() esitystä pelivuorosta.
     * Metodi myös laittaa kutsuu AI:n shoot() metodia.
     */
    public static void setWhosTurn() {
        whosTurn = !whosTurn;
        if (canShoot) {
            JLabel turnInfo = (JLabel) info.getComponent(0);
            if (whosTurn) {
                if (win) {
                    turnInfo.setText("Player2");
                    return;
                }
                turnInfo.setText("Player1 shoots!");
                System.out.println("Player2: " + player2.shots + " shots, fleetSize " + player2.getFleet().size() + ". Player1 shoots!");
            } else {
                if (win) {
                    turnInfo.setText("Player1");
                    return;
                }
                turnInfo.setText("Player2 shoots!");
                System.out.println("Player1: " + player1.shots + " shots, fleetSize " + player1.getFleet().size() + ". Player2 shoots!");
                if (!pvp) {
                    cylonShoots();
                }
            }
        }
    }

    /**
     * AI:n ampuminen. Täysin randomoitu toiminnallisuus. Käyttää Playerin
     * shoot() metodia. Vaihtaa pelivuoroa ja päivittää infoStatea().
     */
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
        }
        setWhosTurn();
    }

    /**
     * Metodin tarkoitus ole korvata getPlayer1 ja getPlayer2 metodeja. Toimii
     * vaihtelevasti syystä tuntematon.
     *
     * @param who
     * @return player1 Jos true, player2, jos false
     */
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