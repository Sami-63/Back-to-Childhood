/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sami.backtochildhood;

/**
*
* @author As-Sami
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

public class SnakeLudo extends JFrame {

    ImageIcon icon;
    JPanel gamePanel, winnerPanel, initialPanel, sidePanel, dicePanel, navPanel;
    JLabel LudoPaper = new JLabel(), navLabel;
    JLabel diceLabel = new JLabel();
    Random random = new Random();
    Color playerColor[];

    int turn = 0; // 0, 1, 2, 3 - th player's move
    int dice = 0;
    int player, playerPosition[];
    Vector<Integer> winner;

    GutiBox grid[], initialGuti[], winnerGuti[];
    boolean disable;

    int t = 0;
    Timer diceTimer = new Timer(10, new DiceListener());

    SnakeLudo(int player) {

        this.setTitle("Snake Ludo");
        disable = false;
        this.player = player;
        playerPosition = new int[4];

        winner = new Vector<>();
        grid = new GutiBox[101];
        int n = 100;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 10; j++) {
                    grid[n] = new GutiBox();
                    grid[n].setBounds(150 + j * 60, i * 60, 60, 60);
                    n--;
                }
            } else {
                for (int j = 9; j > -1; j--) {
                    grid[n] = new GutiBox();
                    grid[n].setBounds(150 + j * 60, i * 60, 60, 60);
                    n--;
                }
            }
        }

        playerColor = new Color[4];
        playerColor[0] = new Color(225, 0, 0, 140); // red
        playerColor[1] = new Color(0, 0, 255, 140); // blue
        playerColor[2] = new Color(255, 255, 0, 140); // yello
        playerColor[3] = new Color(0, 255, 0, 140); // green

        icon = new ImageIcon("src\\main\\java\\Image\\SnakeLudo.png");

        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(750, 678));
        gamePanel.setLayout(null);

        LudoPaper.setIcon(icon);
        LudoPaper.setOpaque(true);
        LudoPaper.setBounds(150, 0, 600, 600);

        {
            initialPanel = new JPanel();
            initialPanel.setLayout(null);
            initialPanel.setBounds(0, 480, 150, 120);
            initialPanel.setBackground(new Color(12, 23, 34));

            initialGuti = new GutiBox[4];
            initialGuti[0] = new GutiBox();
            initialGuti[0].setBounds(10, 0, 60, 60);

            initialGuti[1] = new GutiBox();
            initialGuti[1].setBounds(10, 60, 60, 60);

            initialGuti[2] = new GutiBox();
            initialGuti[2].setBounds(80, 0, 60, 60);

            initialGuti[3] = new GutiBox();
            initialGuti[3].setBounds(80, 60, 60, 60);

            for (int i = 0; i < player; i++)
                initialPanel.add(initialGuti[i]);
        }

        for (int i = 0; i < player; i++)
            initialGuti[i].addGuti(playerColor[i]);

        // playerPosition[0] = 99;
        // playerPosition[1] = 99;

        // grid[99].addGuti(playerColor[0]);
        // grid[99].addGuti(playerColor[1]);

        {
            winnerPanel = new JPanel();
            winnerPanel.setLayout(null);
            winnerPanel.setBounds(0, 0, 150, 120);
            winnerPanel.setBackground(new Color(12, 56, 34));

            winnerGuti = new GutiBox[4];
            winnerGuti[0] = new GutiBox();
            winnerGuti[0].setBounds(10, 0, 60, 60);

            winnerGuti[1] = new GutiBox();
            winnerGuti[1].setBounds(10, 60, 60, 60);

            winnerGuti[2] = new GutiBox();
            winnerGuti[2].setBounds(80, 0, 60, 60);

            winnerGuti[3] = new GutiBox();
            winnerGuti[3].setBounds(80, 60, 60, 60);

            for (int i = 0; i < 4; i++)
                winnerPanel.add(winnerGuti[i]);
        }

        {
            dicePanel = new JPanel();
            dicePanel.setBounds(0, 120, 150, 360);
            dicePanel.setBackground(Color.white);

            diceLabel.setIcon(new ImageIcon("src\\main\\java\\Image\\dice1.jpg"));
            diceLabel.setOpaque(true);
            dicePanel.add(diceLabel);
        }

        {
            KButton roll = new KButton();
            roll.setPreferredSize(new Dimension(100, 40));
            roll.setkStartColor(new Color(31, 28, 44));
            roll.setkEndColor(new Color(146, 141, 171));
            roll.setkHoverStartColor(new Color(146, 141, 171));
            roll.setkHoverEndColor(new Color(31, 28, 44));
            roll.setkHoverForeGround(Color.white);

            dicePanel.add(roll);
            roll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (disable)
                        return;
                    diceTimer.start();
                }
            });
            roll.setBorder(null);
            roll.setkBorderRadius(40);
            roll.setText("Roll");

            KButton exitButton = new KButton();
            exitButton.setPreferredSize(new Dimension(100, 40));
            exitButton.setkStartColor(new Color(31, 28, 44));
            exitButton.setkEndColor(new Color(146, 141, 171));
            exitButton.setkHoverStartColor(new Color(146, 141, 171));
            exitButton.setkHoverEndColor(new Color(31, 28, 44));
            exitButton.setkHoverForeGround(Color.white);

            dicePanel.add(exitButton);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new FirstPage().run();
                }
            });
            exitButton.setBorder(null);
            exitButton.setkBorderRadius(40);
            exitButton.setText("Quit game");

        }

        navPanel = new JPanel();
        navPanel.setBackground(new Color(47, 79, 79));
        navPanel.setBounds(0, 600, 750, 90);

        for (int i = 1; i <= 100; i++)
            gamePanel.add(grid[i]);

        gamePanel.add(initialPanel);
        gamePanel.add(winnerPanel);
        gamePanel.add(dicePanel);
        gamePanel.add(LudoPaper);
        gamePanel.add(navPanel);

        navLabel = new JLabel();
        navLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        navLabel.setForeground(Color.white);
        updateNav();
        navPanel.add(navLabel);

        grid[96].makeSnake(44);
        grid[92].makeSnake(67);
        grid[75].makeSnake(48);
        grid[65].makeSnake(9);
        grid[39].makeSnake(3);

        grid[4].makeLadder(56);
        grid[28].makeLadder(47);
        grid[12].makeLadder(50);
        grid[41].makeLadder(82);
        grid[68].makeLadder(89);

        this.add(gamePanel);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class GutiBox extends JPanel {

        Guti gutis[];
        Vector<Color> gutiColor;

        boolean isLadder, isSnake;
        int end;

        GutiBox() {
            isLadder = isSnake = false;
            end = -1;

            // this.setPreferredSize(new Dimension(60, 60));
            gutis = new Guti[5];
            for (int i = 0; i < 5; i++) {
                gutis[i] = new Guti(Color.black);
                gutis[i].setVisible(false);
            }
            gutiColor = new Vector<>();
            this.setBackground(new Color(0, 0, 0, 0));
            this.setLayout(null);

            gutis[0].setBounds(10, 10, 40, 40);

            gutis[1].setkBorderRadius(25);
            gutis[1].setBounds(3, 3, 25, 25);

            gutis[2].setkBorderRadius(25);
            gutis[2].setBounds(33, 33, 25, 25);

            gutis[3].setkBorderRadius(25);
            gutis[3].setBounds(3, 33, 25, 25);

            gutis[4].setkBorderRadius(25);
            gutis[4].setBounds(33, 3, 25, 25);

            for (int i = 0; i < 5; i++)
                this.add(gutis[i]);
        }

        void makeLadder(int x) {
            isLadder = true;
            this.end = x;
        }

        void makeSnake(int x) {
            isSnake = true;
            this.end = x;
        }

        void addGuti(Color c) {
            gutiColor.add(c);
            draw();
        }

        void removeGuti(Color c) {
            int pos = -1;
            for (int i = 0; i < gutiColor.size(); i++)
                if (gutiColor.elementAt(i).equals(c))
                    pos = i;

            gutiColor.remove(pos);
            draw();
        }

        void draw() {

            if (gutiColor.size() == 1) {
                gutis[0].setColor(gutiColor.elementAt(0));
                gutis[0].setVisible(true);
                for (int i = 1; i < 5; i++)
                    gutis[i].setVisible(false);
            } else {
                for (int i = 0; i < 5; i++)
                    gutis[i].setVisible(false);

                for (int i = 0; i < gutiColor.size(); i++) {
                    gutis[1 + i].setColor(gutiColor.elementAt(i));
                    gutis[1 + i].setVisible(true);
                }
            }
            new Thread(new Runnable() {

                @Override
                public void run() {
                    restart();
                }

            }).start();
        }

        private class Guti extends KGradientPanel {

            Guti(Color c) {
                this.setkStartColor(c);
                this.setkEndColor(c);
                this.setkBorderRadius(50);
                this.setBackground(new Color(0, 0, 0, 0));
            }

            void setColor(Color c) {
                this.setkStartColor(c);
                this.setkEndColor(c);
            }
        }
    }

    void makeMove() {

        System.out.println("turn -> " + turn);

        if (playerPosition[turn] == 0) {
            if (dice == 1) {
                initialGuti[turn].removeGuti(playerColor[turn]);
                grid[1].addGuti(playerColor[turn]);
                playerPosition[turn] = 1;
                return;
            }
        } else {

            if (playerPosition[turn] + dice > 101)
                ;
            else if (playerPosition[turn] + dice == 101) {
                // System.out.println("winning");
                // System.out.println("player position: ");

                grid[playerPosition[turn]].removeGuti(playerColor[turn]);
                playerPosition[turn] += dice;
                winnerGuti[turn].addGuti(playerColor[turn]);

                winner.add(turn);

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        navLabel.setText("Player " + ((turn + 3) % 4 + 1 + "wins"));
                    }
                }).start();

                if (winner.size() == player - 1) {
                    disable = true;

                    KButton retryButton = new KButton();
                    retryButton.setPreferredSize(new Dimension(100, 40));
                    retryButton.setkStartColor(new Color(31, 28, 44));
                    retryButton.setkEndColor(new Color(146, 141, 171));
                    retryButton.setkHoverStartColor(new Color(146, 141, 171));
                    retryButton.setkHoverEndColor(new Color(31, 28, 44));
                    retryButton.setkHoverForeGround(Color.white);

                    dicePanel.add(retryButton);
                    retryButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                            new SnakeLudo(player);
                        }
                    });
                    retryButton.setBorder(null);
                    retryButton.setkBorderRadius(40);
                    retryButton.setText("Play Again");
                    dicePanel.add(retryButton);

                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            navLabel.setText("Game Over");
                        }

                    }).start();
                }

            } else {
                grid[playerPosition[turn]].removeGuti(playerColor[turn]);
                playerPosition[turn] += dice;
                grid[playerPosition[turn]].addGuti(playerColor[turn]);

                if (grid[playerPosition[turn]].isLadder || grid[playerPosition[turn]].isSnake) {
                    disable = true;
                    new Thread(new Jump(turn)).start();
                }
            }
        }

        increamentTurn();

        updateNav();
        // System.out.println(turn);
    }

    class Jump implements Runnable {
        int x;

        Jump(int x) {
            this.x = x;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int tt = this.x;

            // System.out.println("end = " + playerPosition[turn]);

            // System.out.println("Turn = " + turn);
            // System.out.println("Player position = " + playerPosition[turn]);
            // System.out.println("end = " + grid[playerPosition[turn]].end);

            grid[playerPosition[tt]].removeGuti(playerColor[tt]);
            playerPosition[tt] = grid[playerPosition[tt]].end;
            grid[playerPosition[tt]].addGuti(playerColor[tt]);

            disable = false;
        }

    }

    class DiceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t == 20) {
                diceTimer.stop();
                t = 0;

                makeMove();
                return;
            }

            t++;
            dice = Math.abs(random.nextInt()) % 6 + 1;

            // if (t == 20)
            // dice = 1;

            String s = "src\\main\\java\\Image\\dice" + Integer.toString(dice) + ".jpg";
            ImageIcon icon = new ImageIcon(s);

            diceLabel.setIcon(icon);
            diceLabel.revalidate();
        }

    }

    void increamentTurn() {

        do {
            turn++;
            turn %= player;
        } while (winner.contains(turn));

    }

    void restart() {
        repaint();
    }

    void updateNav() {
        navLabel.setText("Player " + (turn + 1) + "'s turn..");
    }

    public static void main(String[] args) {
        new SnakeLudo(3);
    }
}
