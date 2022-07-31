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
import java.awt.Toolkit;
import java.util.Random;
import java.util.Vector;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.k33ptoo.components.KGradientPanel;

public class Ludo extends JFrame {

    JPanel background, initialPanel[], winnerPanel, gutiPanel[];
    JLabel diceLabel[];

    GutiBox initialGuti[][], grid[];

    int GutiPosition[][], t, labelNum;
    Color playerColor[];
    Random random = new Random();
    Timer diceTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (t == 20) {
                diceTimer.stop();
                t = 0;

                // makeMove();
                return;
            }

            t++;
            int dice = Math.abs(random.nextInt()) % 6 + 1;
            String s = "src\\main\\java\\Image\\dice" + Integer.toString(dice) + "-small.png";
            ImageIcon icon = new ImageIcon(s);

            diceLabel[labelNum].setIcon(icon);
            diceLabel[labelNum].revalidate();
        }
    });

    Ludo() {

        background = new JPanel();
        background.setPreferredSize(new Dimension(600, 600));
        background.setLayout(null);

        playerColor = new Color[4];
        playerColor[0] = Color.red;
        playerColor[1] = Color.green;
        playerColor[2] = Color.cyan;
        playerColor[3] = Color.yellow;

        // adding grid
        {
            grid = new GutiBox[72];

            // 0-4
            for (int i = 0; i < 5; i++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(40 * (i + 1), 240, 40, 40);
                grid[i].setBackground(i == 0 ? new Color(255, 87, 34) : Color.blue);
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            // 44-48
            for (int i = 44, cnt = 0; i < 49; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(200 - 40 * cnt, 320, 40, 40);
                grid[i].setBackground(i == 48 ? new Color(255, 87, 34) : Color.blue);
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            // 49-51
            for (int i = 49, cnt = 0; i <= 51; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(0, 240 + 40 * cnt, 40, 40);
                grid[i].setBackground(Color.blue);
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            // 52-56
            for (int i = 52, cnt = 0; i <= 56; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(40 * (cnt + 1), 280, 40, 40);
                grid[i].setBackground(new Color(80, 58, 101));
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            // 5-10
            for (int i = 5, cnt = 0; i <= 10; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(240, 200 - cnt * 40, 40, 40);
                grid[i].setBackground(i == 9 ? new Color(255, 87, 34) : Color.blue);
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            // 12-17
            for (int i = 12, cnt = 0; i <= 17; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(320, cnt * 40, 40, 40);
                grid[i].setBackground(i == 13 ? new Color(255, 87, 34) : Color.blue);
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            grid[11] = new GutiBox();
            grid[11].setBounds(280, 0, 40, 40);
            grid[11].setBackground(Color.blue);
            grid[11].setBorder(BorderFactory.createLineBorder(Color.black));
            background.add(grid[11]);

            // 57-58
            for (int i = 52, cnt = 0; i <= 56; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(280, 40 * (cnt + 1), 40, 40);
                grid[i].setBackground(new Color(80, 58, 101));
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            // 18-23
            for (int i = 18, cnt = 0; i <= 23; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(360 + 40 * cnt, 240, 40, 40);
                grid[i].setBackground(i == 22 ? new Color(255, 87, 34) : Color.blue);
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            // 25-30
            for (int i = 25, cnt = 0; i <= 30; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(560 - 40 * cnt, 320, 40, 40);
                grid[i].setBackground(i == 26 ? new Color(255, 87, 34) : Color.blue);
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            grid[24] = new GutiBox();
            grid[24].setBounds(560, 280, 40, 40);
            grid[24].setBackground(Color.blue);
            grid[24].setBorder(BorderFactory.createLineBorder(Color.black));
            background.add(grid[24]);

            // 62-66
            for (int i = 62, cnt = 0; i <= 66; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(520 - 40 * cnt, 280, 40, 40);
                grid[i].setBackground(new Color(80, 58, 101));
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            // 31-36
            for (int i = 31, cnt = 0; i <= 36; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(320, 360 + 40 * cnt, 40, 40);
                grid[i].setBackground(i == 35 ? new Color(255, 87, 34) : Color.blue);
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            // 38-43
            for (int i = 38, cnt = 0; i <= 43; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(240, 560 - 40 * cnt, 40, 40);
                grid[i].setBackground(i == 39 ? new Color(255, 87, 34) : Color.blue);
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }

            grid[37] = new GutiBox();
            grid[37].setBounds(280, 560, 40, 40);
            grid[37].setBackground(Color.blue);
            grid[37].setBorder(BorderFactory.createLineBorder(Color.black));
            background.add(grid[37]);

            // 67-71
            for (int i = 67, cnt = 0; i <= 71; i++, cnt++) {
                grid[i] = new GutiBox();
                grid[i].setBounds(280, 520 - 40 * cnt, 40, 40);
                grid[i].setBackground(new Color(80, 58, 101));
                grid[i].setBorder(BorderFactory.createLineBorder(Color.black));
                background.add(grid[i]);
            }
        }

        initialPanel = new JPanel[4];
        diceLabel = new JLabel[4];
        initialGuti = new GutiBox[4][4];

        for (int i = 0; i < 4; i++) {

            // initial panel create
            initialPanel[i] = new JPanel();
            initialPanel[i].setBackground(new Color(12, 34, 56));
            initialPanel[i].setLayout(null);

            if (i % 2 == 0)
                initialPanel[i].setBounds(0, 360 * (i != 0 ? 1 : 0), 240, 240);
            else
                initialPanel[i].setBounds(360, 360 * (i != 1 ? 1 : 0), 240, 240);

            // dice label creating
            diceLabel[i] = new JLabel();
            diceLabel[i].setIcon(new ImageIcon("src\\main\\java\\Image\\dice1-small.png"));
            diceLabel[i].setBackground(new Color(0, 0, 0, 0));
            diceLabel[i].setOpaque(true);
            diceLabel[i].addMouseListener(new DiceListener());

            if (i % 2 == 1)
                diceLabel[i].setBounds(10, 95, 50, 50);
            else
                diceLabel[i].setBounds(180, 95, 50, 50);

            // panel creating
            KGradientPanel panel = new KGradientPanel();
            panel.setBounds(70, 70, 100, 100);
            panel.setkStartColor(Color.gray);
            panel.setkEndColor(Color.gray);
            panel.setBackground(new Color(0, 0, 0, 0));
            panel.setkBorderRadius(40);
            panel.setLayout(null);

            // gutibox
            initialGuti[i] = new GutiBox[4];
            initialGuti[i][0] = new GutiBox();
            initialGuti[i][0].setBounds(5, 5, 40, 40);

            initialGuti[i][1] = new GutiBox();
            initialGuti[i][1].setBounds(55, 5, 40, 40);

            initialGuti[i][2] = new GutiBox();
            initialGuti[i][2].setBounds(5, 55, 40, 40);

            initialGuti[i][3] = new GutiBox();
            initialGuti[i][3].setBounds(55, 55, 40, 40);

            for (int j = 0; j < 4; j++) {
                panel.add(initialGuti[i][j]);
            }

            // adding componenet
            initialPanel[i].add(diceLabel[i]);
            initialPanel[i].add(panel);
        }

        for (int i = 0; i < 4; i++)
            background.add(initialPanel[i]);

        this.add(background);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class DiceListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < 4; i++)
                if (e.getSource() == diceLabel[i]) {
                    t = 0;
                    labelNum = i;
                    diceTimer.start();
                }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    class GutiBox extends JPanel {

        Guti gutis[];
        Vector<Color> gutiColor;

        GutiBox() {

            gutis = new Guti[5];
            for (int i = 0; i < 5; i++) {
                gutis[i] = new Guti(Color.black);
                gutis[i].setVisible(false);
                this.add(gutis[i]);
            }
            gutiColor = new Vector<>();
            this.setPreferredSize(new Dimension(40, 40));
            this.setBackground(new Color(0, 255, 0, 0));
            this.setLayout(null);

            gutis[0].setBounds(5, 5, 30, 30);
            gutis[0].setkBorderRadius(30);

            gutis[1].setBounds(0, 0, 20, 20);
            gutis[1].setkBorderRadius(20);

            gutis[2].setBounds(20, 20, 20, 20);
            gutis[2].setkBorderRadius(20);

            gutis[3].setBounds(5, 20, 20, 20);
            gutis[3].setkBorderRadius(20);

            gutis[4].setBounds(20, 5, 20, 20);
            gutis[4].setkBorderRadius(20);

        }

        void addGuti(Color c) {
            // System.out.println("adding guti");
            gutiColor.add(c);
            draw();
        }

        void removeGuti(Color c) {
            int pos = -1;
            for (int i = 0; i < gutiColor.size(); i++)
                if (gutiColor.elementAt(i).equals(c))
                    pos = i;

            // System.out.println("To be removed color pos :" + pos);
            gutiColor.remove(pos);
            draw();
        }

        void draw() {
            // System.out.println("Drawing ..... | size : " + gutiColor.size());

            if (gutiColor.size() == 1) {
                gutis[0].setVisible(true);
                gutis[0].setColor(gutiColor.elementAt(0));

                for (int i = 1; i < 5; i++)
                    gutis[i].setVisible(false);
            }

            else {
                for (int i = 0; i < 5; i++)
                    gutis[i].setVisible(false);

                for (int i = 0; i < gutiColor.size(); i++) {
                    gutis[i + 1].setColor(gutiColor.elementAt(i));
                    gutis[i + 1].setVisible(true);
                }
            }

        }

        private class Guti extends KGradientPanel {
            Guti(Color c) {
                this.setkStartColor(c);
                this.setkEndColor(c);
                this.setBackground(new Color(0, 0, 0, 0));
            }

            void setColor(Color c) {
                this.setkStartColor(c);
                this.setkEndColor(c);
            }
        }
    }

    void restart() {
        repaint();
    }

    public static void main(String[] args) {
        new Ludo();
    }
}
