
package com.sami.backtochildhood;

/**
 *
 * @author As-Sami
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Server.NetworkConnection;

public class SnakeLudoOnline extends SnakeLudo {

    NetworkConnection nc;
    String players[];

    SnakeLudoOnline(NetworkConnection nc, int turn, String players[]) {
        super(4);

        this.setTitle(players[0]);
        this.players = players;
        this.nc = nc;
        this.turn = turn;

        System.out.println("\turn = " + turn);
        for (int i = 0; i < 4; i++)
            System.out.println(i + " -> " + players[i]);
        System.out.println("---------------------------");

        diceTimer = new Timer(100, new DiceListener());

        if (turn != 0)
            disable = true;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(600 - 150 * turn, dim.height / 2 - this.getSize().height / 2);

        new Thread((new GameThread())).start();
    }

    class setDice implements Runnable {

        int i;

        setDice(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            String s = "src\\main\\java\\Image\\dice" + Integer.toString(dice) + ".jpg";
            ImageIcon icon = new ImageIcon(s);

            diceLabel.setIcon(icon);
            diceLabel.revalidate();
        }
    }

    class GameThread implements Runnable {

        @Override
        public void run() {

            System.out.println("in game thread");

            System.out.println("--> its " + players[turn] + "'s turn now | turn -> " + turn);

            if (turn % 4 == 0) {
                System.out.println("its your turn");
                navLabel.setText("it's your turn");
                disable = false;
            } else {

                System.out.println("waiting for oppontnts......");
                navLabel.setText("it's " + players[turn % 4] + "'s turn");

                for (int i = 0; i < 5; i++) {
                    String response = nc.recieveString();
                    // System.out.println("dice ->" + response);
                    new Thread(new setDice(Integer.parseInt(response))).start();

                    dice = Integer.parseInt(response);

                }

                makeMove();
                new Thread(new GameThread()).start();
            }

        }
    }

    class DiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (t == 5) {
                diceTimer.stop();
                t = 0;

                boolean flag = false;
                if (playerPosition[0] == 0 && dice == 1) {
                    System.out.println("again triggered");
                    nc.sendString("again");
                    flag = true;
                }

                disable = true;
                makeMove();

                if (flag == false) {

                    if (playerPosition[0] == 101) {
                        playerPosition[0] = -1;
                        nc.sendString("win");
                        disable = true;
                    } else
                        nc.sendString("continue");
                }

                new Thread(new GameThread()).start();
                return;

            }

            t++;
            dice = Math.abs(random.nextInt()) % 6 + 1;

            // if (t == 5)
            // dice = 1;

            nc.sendString(Integer.toString(dice));

            // System.out.println("-> " + dice);

            String s = "src\\main\\java\\Image\\dice" + Integer.toString(dice) + ".jpg";
            ImageIcon icon = new ImageIcon(s);

            diceLabel.setIcon(icon);
            diceLabel.revalidate();
        }
    }

}
