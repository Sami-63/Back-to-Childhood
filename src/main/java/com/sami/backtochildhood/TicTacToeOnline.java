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
        this.player1_turn = turn;
        gameover = false;
        StartGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {

                if (player1_turn == true) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("its your opponents turn");

                        nc.sendString(Integer.toString(i));
                        check();

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

        textfield.setText("You win!");
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

        textfield.setText("You lost!");
        nc.close();
        gameover = true;
        new Thread(new GameOver()).start();
    }

    @Override
    public void Tie() {
        gameover = true;
        textfield.setText("Nobody wins");
        nc.close();
    }

    @Override
    public void firstTurn() {
    }

    void StartGame() {

        if (player1_turn) {
            textfield.setText("its your turn");
        } else {
            textfield.setText("its your opponents turn");
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
            int n = Integer.parseInt(s);
            buttons[n].doClick();

            buttons[n].setForeground(new Color(0, 0, 255));
            buttons[n].setText("O");
            player1_turn = true;
            textfield.setText("its your turn");
            check();

            return;
        }
    }
}
