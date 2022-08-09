package com.sami.backtochildhood;

import Server.NetworkConnection;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class TicTacToeOnline extends TicTacToe {

    NetworkConnection nc;
    boolean gameover;

    TicTacToeOnline(NetworkConnection nc, boolean turn) {
        super();
        this.nc = nc;
        this.player1Turn = turn;
        gameover = false;
        StartGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {

                if (player1Turn == true) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1Turn = false;
                        navLabel.setText("its your opponents turn");

                        if (nc.sendString(Integer.toString(i)))
                            check();
                        else {
                            gameover = true;
                            navLabel.setText("Server's offline");
                        }

                        if (!gameover)
                            new Thread(new GetResponse()).start();
                    }
                }
            }

        }
    }

    @Override
    public void XWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        navLabel.setText("You win!");
        nc.close();
        gameover = true;
        new Thread(new GameOver()).start();

    }

    @Override
    public void OWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        navLabel.setText("You lost!");
        nc.close();
        gameover = true;
        new Thread(new GameOver()).start();
    }

    @Override
    public void Tie() {
        gameover = true;
        navLabel.setText("Nobody wins");
        nc.close();
    }

    @Override
    public void firstTurn() {
    }

    void StartGame() {

        if (player1Turn) {
            navLabel.setText("its your turn");
        } else {
            navLabel.setText("its your opponents turn");
            new Thread(new GetResponse()).start();
        }
    }

    class GetResponse implements Runnable {

        @Override
        public void run() {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String s = nc.recieveString();
            if (s.equals("win")) {
                navLabel.setText("opponent has left");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            navLabel.setText("You win.....");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                gameover = true;
                new Thread(new GameOver()).start();
                return;
            } else if (s.equals("")) {
                navLabel.setText("Server's offline");
                new Thread(new GameOver()).start();
                return;
            }

            int n = Integer.parseInt(s);
            buttons[n].doClick();

            buttons[n].setForeground(new Color(0, 0, 255));
            buttons[n].setText("O");
            player1Turn = true;
            navLabel.setText("its your turn");
            check();

            return;
        }
    }
}
