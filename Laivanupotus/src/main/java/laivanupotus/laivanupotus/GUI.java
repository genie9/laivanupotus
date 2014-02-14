package laivanupotus.laivanupotus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import laivanupotus.graphics.Grid2;
import laivanupotus.listeners.ShipRandomizerListener;
import laivanupotus.logiikka.Player;
//import laivanupotus.logiikka.Ship;
//import javax.swing.BoxLayout;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.WindowConstants;

public class GUI implements Runnable {

    private JFrame frame;
    private Player player1;
    private Player player2;

    public GUI() {
        player1 = new Player("Test", 10);
        player2 = new Player("tapettava", 10);
    }

    @Override
    public void run() {
        frame = new JFrame("BATTLESHIPS");
        frame.setPreferredSize(new Dimension(400, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        container.add(new JLabel("Welcome to fight for your glory! \n"));
//        container.add(new JLabel("Choose your colour."));
//        
//        JButton red = new JButton("RED");
//        JButton black = new JButton("BLACK");

//        ButtonGroup buttonGroup = new ButtonGroup();
//        buttonGroup.add(red);
//        buttonGroup.add(black);
        Grid2 p1 = new Grid2(player1.getArea());
        Grid2 p2 = new Grid2(player2.getArea());

        JButton randomize = new JButton("Create yor fleet!");
        ShipRandomizerListener randomListener = new ShipRandomizerListener(player1, 5, p1);
            
            randomize.addActionListener(randomListener);

        

//        container.add(red);
//        container.add(black);
        container.add(p1);
        container.add(p2);
        container.add(randomize);
    }

    public JFrame getFrame() {
        return frame;
    }
}
