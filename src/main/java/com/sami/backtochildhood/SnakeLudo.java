/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author As-Sami
 */
package com.sami.backtochildhood;

import com.k33ptoo.components.KButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeLudo extends JFrame {

    ImageIcon icon, dice1, dice2, dice3, dice4, dice5, dice6;
    JPanel gamePanel, winnerPanel, initialPanel, sidePanel, dicePanel, titlePanel;
    JLabel LudoPaper = new JLabel();
    JLabel diceLabel = new JLabel();
    Random random = new Random();

    Guti guti[];
    int turn = 0; // 0, 1, 2, 3 - th player's move
    int dice = 0;
    int player, win = 0;

    private int t = 0;
    Timer diceTimer = new Timer(10, new ActionListener() {
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
            String s = "src\\main\\java\\Image\\dice"
                    + Integer.toString(dice) + ".jpg";
            ImageIcon icon = new ImageIcon(s);

            diceLabel.setIcon(icon);
            diceLabel.revalidate();
        }
    });

    SnakeLudo(int player) {

        this.player = player;
        guti = new Guti[4];
        for (int i = 0; i < 4; i++)
            guti[i] = new Guti(i);

        icon = new ImageIcon("src\\main\\java\\Image\\SnakeLudo.png");

        dice1 = new ImageIcon("src\\main\\java\\Image\\dice1.jpg");
        dice2 = new ImageIcon("src\\main\\java\\Image\\dice2.jpg");
        dice3 = new ImageIcon("src\\main\\java\\Image\\dice3.jpg");
        dice4 = new ImageIcon("src\\main\\java\\Image\\dice4.jpg");
        dice5 = new ImageIcon("src\\main\\java\\Image\\dice5.jpg");
        dice6 = new ImageIcon("src\\main\\java\\Image\\dice6.jpg");

        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(700, 700));

        LudoPaper.setIcon(icon);
        LudoPaper.setOpaque(true);
        gamePanel.add(LudoPaper);
        gamePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        winnerPanel = new JPanel();
        winnerPanel.setPreferredSize(new Dimension(200, 150));
        winnerPanel.setBackground(new Color(229, 228, 226));

        dicePanel = new JPanel();
        dicePanel.setPreferredSize(new Dimension(200, 400));
        dicePanel.setBackground(Color.white);

        diceLabel.setIcon(dice4);
        diceLabel.setOpaque(true);
        LudoPaper.setBackground(new Color(0, 0, 25));
        dicePanel.add(diceLabel);

        KButton roll = new KButton();
        roll.setkStartColor(new Color(31, 28, 44));
        roll.setkEndColor(new Color(146, 141, 171));
        roll.setkHoverStartColor(new Color(146, 141, 171));
        roll.setkHoverEndColor(new Color(31, 28, 44));
        roll.setkHoverForeGround(Color.white);

        dicePanel.add(roll);
        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceTimer.start();
            }
        });
        roll.setBorder(null);
        roll.setkBorderRadius(40);
        roll.setText("Role");

        KButton exitButton = new KButton();
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

        initialPanel = new JPanel();
        initialPanel.setPreferredSize(new Dimension(200, 150));
        initialPanel.setBackground(new Color(229, 228, 226));

        sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        sidePanel.add(winnerPanel);
        sidePanel.add(dicePanel);
        sidePanel.add(initialPanel);
        sidePanel.setPreferredSize(new Dimension(200, 700));

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.red);
        titlePanel.setPreferredSize(new Dimension(900, 80));

        this.setLayout(new BorderLayout(0, 0));
        this.add(sidePanel, BorderLayout.WEST);
        this.add(gamePanel);
        this.add(titlePanel, BorderLayout.SOUTH);

        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paintComponents(g);

        for (int i = 0; i < player; i++)
            guti[i].draw(g);

    }

    private class Guti {

        private int x, y;
        private final int radius = 30;
        Color color;
        public int position = 0;
        Point cell[];

        public void move(int n) {
            if (position == 0) {
                if (n == 1)
                    position = 1;
            } else if (position + n < 101)
                position += n;
            else if (position + n == 101) {
                position += n;
                win++;
            }
        }

        public Guti(int type) {

            cell = new Point[102];
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    int n = 10 - i;
                    n *= 10;
                    for (int j = 0; j < 10; j++) {
                        cell[n--] = new Point(208 + 35 + 60 * j, 30 + 35 + 60 * i - (i / 4));
                    }
                } else {
                    int n = 10 - i - 1;
                    n *= 10;
                    for (int j = 0; j < 10; j++) {
                        cell[++n] = new Point(208 + 35 + 60 * j, 30 + 35 + 60 * i - (i / 4));
                    }
                }
            }

            if (type == 0) {
                cell[0] = new Point(150, 695);
                cell[101] = new Point(150, 70);
                color = new Color(26, 105, 133);
            } else if (type == 1) {
                cell[0] = new Point(75, 695);
                cell[101] = new Point(75, 70);
                color = new Color(220, 24, 24);
            } else if (type == 2) {
                cell[0] = new Point(150, 625);
                cell[101] = new Point(150, 140);
                color = new Color(255, 103, 0);
            } else if (type == 3) {
                cell[0] = new Point(75, 625);
                cell[101] = new Point(75, 140);
                color = new Color(34, 139, 34);
                ;
            }

        }

        public void draw(Graphics g) { // darw at center
            int r = radius;

            x = cell[position].x;
            y = cell[position].y;

            g.setColor(color);
            g.fillOval(x - r, y - r, r * 2, r * 2);

            r = 28;
            g.setColor(Color.white);
            g.fillOval(x - r, y - r, r * 2, r * 2);

            r = 25;
            g.setColor(color);
            g.fillOval(x - r, y - r, r * 2, r * 2);

            r = 11;
            g.setColor(Color.white);
            g.fillOval(x - r, y - r, r * 2, r * 2);
        }
    }

    private void makeMove() {
        guti[turn].move(dice);
        repaint();

        if (win == player)
            return;

        do {
            turn++;
            turn %= player;
        } while (guti[turn].position == 101);

        System.out.println(turn);
    }

    public static void main(String[] args) {
        new SnakeLudo(2);
    }
}
