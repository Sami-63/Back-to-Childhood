package com.sami.backtochildhood;

import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.k33ptoo.components.KButton;

import Server.NetworkConnection;

public class ConnectGame implements Runnable {

    String game, name;
    JFrame frame;
    JLabel label;
    NetworkConnection nc;
    KButton button;

    ConnectGame(String game, String name, JFrame frame, JLabel label, KButton button) {
        this.game = game;
        this.name = name;
        this.frame = frame;
        this.label = label;
        this.button = button;
    }

    void makeABox(String matchInfo) {

        frame.dispose();

        String list[] = matchInfo.split("\\$");

        int turn = Integer.parseInt(list[0]);
        String opponent = list[1];
        int row = Integer.parseInt(list[2]);
        int column = Integer.parseInt(list[3]);

        if (turn == 1) {
            System.out.println("You got the first move");
        } else {
            System.out.println("Opponent got the first move");
        }

        new MakeABoxOnline(name, opponent, row, column, turn, nc);
    }

    void snakeLudo(String response) {
        String responses[] = response.split("\\|");
        String players[] = new String[4];
        players[0] = name;
        players[1] = responses[1];
        players[2] = responses[2];
        players[3] = responses[3];

        int turn = Integer.parseInt(responses[0]);
        new SnakeLudoOnline(nc, turn, players);
    }

    void churPolice(String response) {
        String responses[] = response.split("\\|");

        String players[] = new String[4];
        players[0] = name;
        players[1] = responses[1];
        players[2] = responses[2];
        players[3] = responses[3];

        int turn = Integer.parseInt(responses[0]);
        new ChurPolice(nc, turn, players);
    }

    void tictactoe(String response) {
        boolean turn;
        if (response.equals("1")) {
            System.out.println("You got the first move");
            turn = true;
        } else {
            System.out.println("Opponent got the first move");
            turn = false;
        }
        System.out.println("turn = " + turn);
        System.out.println("----------------------------------------");

        new TicTacToeOnline(nc, turn);
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("192.168.56.1", 12345);
            System.out.println("socket connecteds....");
            nc = new NetworkConnection(socket);
            System.out.println("Network connected");

            System.out.println("\nSearching for players...");

            nc.sendString(name + "|" + game);

            String response = "online?";
            while (response.equals("online?")) {
                response = nc.recieveString();
            }

            frame.dispose();

            if (game == "tic-tac-toe")
                tictactoe(response);
            else if (game == "make-a-box")
                makeABox(response);
            else if (game == "snake-ludo")
                snakeLudo(response);
            else if (game == "chur-police")
                churPolice(response);

        } catch (Exception e) {
            System.out.println("server offline");
            label.setText("server offline");
            button.setVisible(true);
            frame.repaint();
        }
    }

}
