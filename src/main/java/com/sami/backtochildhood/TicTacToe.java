/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sami.backtochildhood;

/**
 *
 * @author Mahmud
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.k33ptoo.components.KButton;

public class TicTacToe implements ActionListener {

    Random random = new Random();

    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    public JLabel navLabel = new JLabel();
    public JButton[] buttons = new JButton[9];
    public boolean player1Turn;

    public TicTacToe() {

        frame.setTitle("Tic tac toe");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        navLabel.setBackground(new Color(25, 25, 25));
        navLabel.setBackground(new Color(25, 250, 0));
        navLabel.setFont(new Font("Ink Free", Font.BOLD, 50));
        navLabel.setHorizontalAlignment(JLabel.CENTER);
        navLabel.setText("Tic-Tac-Toe");
        navLabel.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 100));
            buttons[i].setBackground(Color.black);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(navLabel);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        firstTurn();
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
                        navLabel.setText("O turn");
                        check();
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1Turn = true;
                        navLabel.setText("X turn");
                        check();
                    }
                }
            }

        }

    }

    public void firstTurn() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1Turn = true;
            navLabel.setText("X turn");
        } else {
            player1Turn = false;
            navLabel.setText("O turn");
        }
    }

    public void check() {

        if ((buttons[0].getText() == "X")
                && (buttons[1].getText() == "X")
                && (buttons[2].getText() == "X")) {

            XWins(0, 1, 2);
        } else if ((buttons[3].getText() == "X")
                && (buttons[4].getText() == "X")
                && (buttons[5].getText() == "X")) {

            XWins(3, 4, 5);
        } else if ((buttons[6].getText() == "X")
                && (buttons[7].getText() == "X")
                && (buttons[8].getText() == "X")) {

            XWins(6, 7, 8);
        } else if ((buttons[0].getText() == "X")
                && (buttons[3].getText() == "X")
                && (buttons[6].getText() == "X")) {

            XWins(0, 3, 6);
        } else if ((buttons[1].getText() == "X")
                && (buttons[4].getText() == "X")
                && (buttons[7].getText() == "X")) {

            XWins(1, 4, 7);
        } else if ((buttons[2].getText() == "X")
                && (buttons[5].getText() == "X")
                && (buttons[8].getText() == "X")) {

            XWins(2, 5, 8);
        } else if ((buttons[0].getText() == "X")
                && (buttons[4].getText() == "X")
                && (buttons[8].getText() == "X")) {

            XWins(0, 4, 8);
        } else if ((buttons[2].getText() == "X")
                && (buttons[4].getText() == "X")
                && (buttons[6].getText() == "X")) {

            XWins(2, 4, 6);
        }

        // check O win conditions
        else if ((buttons[0].getText() == "O")
                && (buttons[1].getText() == "O")
                && (buttons[2].getText() == "O")) {

            OWins(0, 1, 2);
        } else if ((buttons[3].getText() == "O")
                && (buttons[4].getText() == "O")
                && (buttons[5].getText() == "O")) {

            OWins(3, 4, 5);
        } else if ((buttons[6].getText() == "O")
                && (buttons[7].getText() == "O")
                && (buttons[8].getText() == "O")) {

            OWins(6, 7, 8);
        } else if ((buttons[0].getText() == "O")
                && (buttons[3].getText() == "O")
                && (buttons[6].getText() == "O")) {

            OWins(0, 3, 6);
        } else if ((buttons[1].getText() == "O")
                && (buttons[4].getText() == "O")
                && (buttons[7].getText() == "O")) {

            OWins(1, 4, 7);
        } else if ((buttons[2].getText() == "O")
                && (buttons[5].getText() == "O")
                && (buttons[8].getText() == "O")) {

            OWins(2, 5, 8);
        } else if ((buttons[0].getText() == "O")
                && (buttons[4].getText() == "O")
                && (buttons[8].getText() == "O")) {

            OWins(0, 4, 8);
        } else if ((buttons[2].getText() == "O")
                && (buttons[4].getText() == "O")
                && (buttons[6].getText() == "O")) {

            OWins(2, 4, 6);
        }

        else if ((buttons[0].getText() == "O" || buttons[0].getText() == "X")
                && (buttons[1].getText() == "O" || buttons[1].getText() == "X")
                && (buttons[2].getText() == "O" || buttons[2].getText() == "X")
                && (buttons[3].getText() == "O" || buttons[3].getText() == "X")
                && (buttons[4].getText() == "O" || buttons[4].getText() == "X")
                && (buttons[5].getText() == "O" || buttons[5].getText() == "X")
                && (buttons[6].getText() == "O" || buttons[6].getText() == "X")
                && (buttons[7].getText() == "O" || buttons[7].getText() == "X")
                && (buttons[8].getText() == "O" || buttons[8].getText() == "X")) {

            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }

            // textfield.setText("No One Wins!");
            Tie();
            new Thread(new GameOver()).start();
        }
    }

    public void Tie() {
        navLabel.setText("Nobody wins");
    }

    public void XWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        navLabel.setText("X wins!");
        new Thread(new GameOver()).start();

    }

    public void OWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        navLabel.setText("O wins!");
        new Thread(new GameOver()).start();
    }

    class GameOver implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SLabel score = new SLabel();
            score.setText("Game Over");

            buttonPanel.removeAll();
            buttonPanel.revalidate();
            buttonPanel.repaint();
            score.setBounds(50, 50, 400, 200);

            buttonPanel.setPreferredSize(new Dimension(400, 400));
            buttonPanel.setLayout(null);
            buttonPanel.setBackground(Color.black);

            SButton exit = new SButton("Exit");
            SButton retry = new SButton("Retry");
            exit.setBounds(100, 300, 100, 50);
            retry.setBounds(100, 250, 100, 50);

            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    new HomePage();
                }
            });

            retry.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    new TicTacToe();
                }
            });

            Dimension d = buttonPanel.getSize();
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.setBounds((int) d.getWidth() / 2 - 150, 100, 300, 400);

            panel.setBackground(Color.black);
            panel.add(score);
            panel.add(retry);
            panel.add(exit);

            buttonPanel.add(panel);

            buttonPanel.revalidate();
            buttonPanel.repaint();
            frame.repaint();
        }

        private class SLabel extends JLabel {
            SLabel() {
                super();
                setBackground(new Color(0, 0, 0, 0));
                setForeground(new Color(0, 255, 0));
                setPreferredSize(new Dimension(400, 70));
                setHorizontalAlignment(SwingConstants.CENTER);
                setVerticalAlignment(SwingConstants.CENTER);
                setFont(new Font("LCDMono2", Font.PLAIN, 50));
            }
        }

        private class SButton extends KButton {
            SButton(String t) {
                super();

                setFont(new Font("Arial", Font.BOLD, 30));
                setForeground(Color.black);
                setBorder(null);
                setText(t);
                setPreferredSize(new Dimension(200, 80));
                setkStartColor(new Color(241, 39, 17));
                setkEndColor(new Color(245, 175, 25));

                setkHoverStartColor(new Color(245, 175, 25));
                setkHoverEndColor(new Color(241, 39, 17));
                setkHoverForeGround(Color.black);

                setkPressedColor(Color.white);

                setkBorderRadius(20);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
