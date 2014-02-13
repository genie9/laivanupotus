package laivanupotus.laivanupotus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import laivanupotus.graphics.Grid2;
//import javax.swing.BoxLayout;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.WindowConstants;

public class GUI implements Runnable {

    private JFrame frame;

    public GUI() {
    }

    @Override
    public void run() {
        frame = new JFrame("BATTLESHIPS");
        frame.setPreferredSize(new Dimension(400, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setLayout(new BorderLayout());
        frame.add(new Grid2());
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        container.add(new JLabel("Welcome to fight for your glory! \n"));
        container.add(new JLabel("Choose your colour."));
        
        JButton red = new JButton("RED");
        JButton black = new JButton("BLACK");
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(red);
        buttonGroup.add(black);

        container.add(red);
        container.add(black);
    }

    public JFrame getFrame() {
        return frame;
    }
}