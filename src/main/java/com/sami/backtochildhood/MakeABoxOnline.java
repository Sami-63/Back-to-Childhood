package com.sami.backtochildhood;

import javax.swing.JLabel;

import Server.NetworkConnection;

public class MakeABoxOnline extends MakeABox {
    String name, opponent;
    NetworkConnection nc;

    public MakeABoxOnline(String name, String opponent, int row, int column, int turn, NetworkConnection nc) {
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
    protected void updateNav() {
        if (turn == 1)
            navLabel.setText("Its your turn");
        else
            navLabel.setText("Opponents turn");
    }

    @Override
    protected void updateScore() {
        scoreLabel.setText(name + " = " + scoreA + "  " + opponent + " = " + scoreB);
    }

    @Override
    protected boolean isClickable(boolean clicked) {
        return clicked || turn == -1;
    }

    @Override
    protected void updateTurn(boolean scored, int lineType, int x, int y) {
        if (scored) {
            updateNav();
            if (turn == 0) {
                turn = -1;
                new Thread(new GetResponse()).start();
            } else {
                String response = lineType + "|" + x + "|" + y + "|1";
                if (nc.sendString(response))
                    ;
                else {
                    turn = -1;
                    // navLabel.setText("Server's offline");
                    new Thread(new GameOver("Server's offline")).start();
                }
            }
        } else {
            if (turn == 0)
                turn = 1;
            else {
                turn = -1;
                String response = lineType + "|" + x + "|" + y + "|0";
                if (nc.sendString(response))
                    new Thread(new GetResponse()).start();
                else {
                    // navLabel.setText("Server's offline");
                    new Thread(new GameOver("Server's offline")).start();
                }
            }
        }
        updateNav();

        totalLies--;

        if (totalLies == 0) {
            new Thread(new GameOver()).start();
        }
    }

    private class GetResponse implements Runnable {

        @Override
        public void run() {

            // keep receiving while opponent has move

            // System.out.println("waiting to receive...");
            String response = nc.recieveString();

            if (response.equals("")) {
                // navLabel.setText("Serve's offline");
                new Thread(new GameOver("Server's offline")).start();
                return;
            } else if (response.equals("win")) {
                navLabel.setText("opponent has left");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            navLabel.setText("You win.....");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                // navLabel.setText("Serve's offline");
                new Thread(new GameOver("opponent has left")).start();
                return;
            }

            String s[] = response.split("\\|");
            int t = Integer.parseInt(s[0]);
            int i = Integer.parseInt(s[1]);
            int j = Integer.parseInt(s[2]);

            // System.out.println("recived :" + response);

            turn = 0;
            if (t == 0)
                lineX[i][j].doClick();
            else
                lineY[i][j].doClick();
        }
    }

    private void setName() {
        p1 = name.substring(0, 1).toUpperCase();
        p2 = opponent.substring(0, 1).toUpperCase();
    }

    // private void setName() {
    // for (int i = 0; i < Math.min(name.length(), opponent.length()); i++) {
    // name = name.toUpperCase();
    // opponent = opponent.toUpperCase();
    // if (name.charAt(i) != opponent.charAt(i)) {
    // p1 = String.valueOf(name.charAt(i)).toUpperCase();
    // p2 = String.valueOf(opponent.charAt(i)).toUpperCase();
    // break;
    // }
    // }
    // }

    @Override
    protected void setWinner(JLabel label) {
        if (scoreA > scoreB)
            label.setText("You wins");
        else if (scoreA < scoreB)
            label.setText("You lost");
        else
            label.setText("It's  a  tie");
    }
}
