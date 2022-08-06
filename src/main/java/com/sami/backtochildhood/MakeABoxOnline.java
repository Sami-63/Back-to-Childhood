package com.sami.backtochildhood;

import Server.NetworkConnection;

public class MakeABoxOnlineV2 extends MakeABox {
    String name, opponent;
    NetworkConnection nc;

    public MakeABoxOnlineV2(String name, String opponent, int row, int column, int turn, NetworkConnection nc) {
        super(row, column);
        this.name = name;
        this.opponent = opponent;

        setName();
        turn = (turn == 0 ? -1 : 1);
        this.turn = turn;
        this.nc = nc;

        updateNav();
        updateScore();
        if (turn == -1) {
            new Thread(new GetResponse()).start();
        }
    }

    @Override
    public void updateNav() {
        if (turn == 1)
            navLabel.setText("Its your turn");
        else
            navLabel.setText("Opponents turn");
    }

    @Override
    public void updateScore() {
        scoreLabel.setText(name + " = " + scoreA + "  " + opponent + " = " + scoreB);
    }

    @Override
    boolean isClickable(boolean clicked) {
        return clicked || turn == -1;
    }

    @Override
    void updateTurn(boolean scored, int lineType, int x, int y) {
        if (scored) {
            updateNav();
            if (turn == 0)
                new Thread(new GetResponse()).start();
            else {
                String response = lineType + "|" + x + "|" + y + "|1";
                nc.sendString(response);
            }
        } else {
            if (turn == 0)
                turn = 1;
            else {
                turn = -1;
                String response = lineType + "|" + x + "|" + y + "|0";
                nc.sendString(response);
                new Thread(new GetResponse()).start();
            }
        }
        updateNav();
    }

    private class GetResponse implements Runnable {

        @Override
        public void run() {

            // keep receiving while opponent has move

            System.out.println("waiting to receive...");
            String response = nc.recieveString();
            String s[] = response.split("\\|");
            int t = Integer.parseInt(s[0]);
            int i = Integer.parseInt(s[1]);
            int j = Integer.parseInt(s[2]);

            System.out.println("recived :" + response);

            turn = 0;
            if (t == 0)
                lineX[i][j].doClick();
            else
                lineY[i][j].doClick();
        }
    }

    private void setName() {
        name = name.substring(0, 1).toUpperCase();
        opponent = opponent.substring(0, 1).toUpperCase();
    }
}
