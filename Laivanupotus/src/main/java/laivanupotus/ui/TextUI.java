package laivanupotus.ui;

import java.util.Scanner;
//import laivanupotus.logiikka.Ship;
import laivanupotus.logiikka.Player;
import laivanupotus.logiikka.Ship;

public class TextUI {

    public Scanner lukija;
    private Player player1;
    private Player player2;
    private int areaSize;

    public TextUI(int areaSize) {
        lukija = new Scanner(System.in);
        this.areaSize = areaSize;
    }

    public void preparation() {
        System.out.println("Welcome to fight for your glory!");

        System.out.println("Choose your colour: \n red = 1 \n black = 2");
        int colour = Integer.parseInt(lukija.nextLine());
//        System.out.println("Choose the number of ships maximum 10 for your fleats maximum : ");
//        int ships = lukija.nextInt();

        if (colour == 1) {
            System.out.print("How shall we call you admiral? ");
            String name = lukija.nextLine();
            player1 = new Player(name, areaSize);
            System.out.println("Admiral " + player1.getName() + " place your ships.");
            placeShips(player1);
            createCylon(2);
        } else if (colour == 2) {
            System.out.print("How shall we call you admiral? ");
            String name = lukija.nextLine();
            player2 = new Player(name, areaSize);
            System.out.println("Admiral " + player2.getName() + " place your ships.");
            placeShips(player2);
            createCylon(1);
        }
        printSituation(player1);
        System.out.println("");
        printSituation(player2);
        play();
    }

    private void placeShips(Player p) {
        for (int i = 1; i >= 0; i--) {
            int size = i;
            if (i < 3) {
                if (i == 0) {
                    size = 1;
                }
                size++;
            }

//            System.out.println(size + " long ship.");
//
//            System.out.print("x: ");
//            int x = Integer.parseInt(lukija.nextLine());
//
//            System.out.print("y: ");
//            int y = Integer.parseInt(lukija.nextLine());
//
//            System.out.print("suunta (1 = vertical, 2 = horisontal): ");
//            boolean pos = false;
//            int suunta = lukija.nextInt();
//            if (suunta == 1 || suunta == 2) {
//                if (lukija.nextInt() == 1) {
//                    pos = true;
//                } else {
//                    pos = false;at laivanupotus.ui.TextUI.play(TextUI.java:84)
//                }
//            }
//            p.asetaLaiva(x, y, size, pos);
            p.arvoLaiva(size);
//            printSituation(p);
//            System.out.println("");
        }
    }

    private void play() {
        boolean theEnd = false;
        mainLoop:
        while (!theEnd) {

            /* player 1 shoots */
            System.out.println("Give your coordinates, admiral " + player1.getName());
            p1Loop:
            while (true) {
                System.out.print("x: ");
                int x = Integer.parseInt(lukija.nextLine());
                System.out.print("y: ");
                int y = Integer.parseInt(lukija.nextLine());

                if (x >= player2.getArea().length || x < 0 || y < 0 || y >= player2.getArea().length) {
                    System.out.println("Out of range. Choose new coordinates.");
                    continue;
                }
                if (player1.shoot(player2, x, y)) {
                    /* checking for victory */
                    if (player2.getFleet().isEmpty()) {
                        System.out.println("The victory is your admiral " + player1.getName() + " !!!");
                        theEnd = true;
                        printSituation(player2);
                        break mainLoop;
                    }
                    System.out.println("It's a hit! You may shoot again.");
                    printSituation(player2);
                    continue;
                }
                printSituation(player2);
                break;
            }

            /* player 2 shoots */
            System.out.println("Give your coordinates, admiral " + player2.getName());
            p2Loop:
            while (true) {
                System.out.print("x: ");
                int x = Integer.parseInt(lukija.nextLine());
                System.out.print("y: ");
                int y = Integer.parseInt(lukija.nextLine());
                if (x >= player2.getArea().length || x < 0 || y < 0 || y >= player2.getArea().length) {
                    System.out.println("Out of range. Choose new coordinates.");
                    continue;
                }
                if (player2.shoot(player1, x, y)) {
                    /* checking for victory */
                    if (player1.getFleet().isEmpty()) {
                        System.out.println("The victory is your admiral " + player2.getName() + " !!!");
                        theEnd = true;
                        printSituation(player1);
                        break mainLoop;
                    }
                    System.out.println("It's a hit! You may shoot again.");
                    printSituation(player1);
                    continue;
                }
                printSituation(player1);
                break;
            }
        }
    }

    private void printSituation(Player p) {
        for (int i = 0; i < p.getArea().length; i++) {
            for (int j = 0; j < p.getArea().length; j++) {
                System.out.print(p.getArea()[i][j]);
            }
            System.out.println("");
        }
        System.out.println(player1.getFleet().size());
        System.out.println(player2.getFleet().size());
    }

    /* creating AI player */
    private void createCylon(int playerNum) {
        if (playerNum == 1) {
            player1 = new Player("Cylon", areaSize);
            placeShips(player1);
        } else {
            player2 = new Player("Cylon", areaSize);
            placeShips(player2);
        }
    }
}
