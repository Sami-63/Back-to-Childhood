/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToeOnline;

/**
 *
 * @author Hp
 */
import java.awt.Color;
import java.awt.event.ActionEvent;

public class TicTacToe extends com.sami.backtochildhood.TicTacToe {

    NetworkConnection nc;

    TicTacToe(NetworkConnection nc, boolean turn) {
        super();
        this.nc = nc;
        this.player1_turn = turn;
        StartGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("katakuti file....");
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {

                if (player1_turn == true) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("its your opponents turn");
                        check();

                        nc.sendString(Integer.toString(i));
                        new Thread(new GetResponse()).start();
                    }
                }
            }

        }
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

            System.out.println("Recieving string...");
            String s = nc.recieveString();
            System.out.println("String received... : 0 + s");
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
