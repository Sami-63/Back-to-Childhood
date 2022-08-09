/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sami.backtochildhood;

/**
 *
 * @author As-Sami
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MakeABox extends JFrame {

    protected String p1 = "A", p2 = "B";
    int turn;

    JPanel mainPanel, scorePanel, navigationPanel,whoWins;
    JLabel scoreLabel, navLabel;

    protected int row;
    protected int column;
    public Dot dots[][];
    public LineX lineX[][];
    public LineY lineY[][];
    public Box boxes[][];

    protected int scoreA = 0, scoreB = 0;

    public MakeABox(int row, int column) {

        this.setTitle("Make a box");

        this.row = row;
        this.column = column;

        turn = 1;// true -> player 1 turn, false -> player 2 turn

        mainPanel = new JPanel();

        {
            dots = new Dot[30][30];
            lineX = new LineX[30][30];
            lineY = new LineY[30][30];
            boxes = new Box[30][30];
        }

        {
            scorePanel = new JPanel();
            scorePanel.setPreferredSize(new Dimension(70 * column - 50, 70));
            scorePanel.setBackground(new Color(0, 0, 0));

            scoreLabel = new JLabel();
            scoreLabel.setFont(new Font("LCDMono2", Font.PLAIN, 30));
            scoreLabel.setHorizontalAlignment(JLabel.CENTER);
            scoreLabel.setVerticalAlignment(JLabel.CENTER);
            scoreLabel.setForeground(new Color(0, 255, 0));
            scorePanel.add(scoreLabel);
            updateScore();
        }

        {
            navigationPanel = new JPanel();
            navigationPanel.setPreferredSize(new Dimension(70 * column - 50, 40));
            navigationPanel.setBackground(new Color(0, 0, 0));

            navLabel = new JLabel();
            navLabel.setFont(new Font("LCDMono2", Font.PLAIN, 30));
            navLabel.setForeground(new Color(0, 255, 0));
            navLabel.setHorizontalAlignment(JLabel.CENTER);
            navigationPanel.add(navLabel);
            updateNav();
        }

        mainPanel.setPreferredSize(new Dimension(70 * column - 50, 70 * row - 50));
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        for (int i = 0; i < 2 * row - 1; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < column * 2 - 1; j++) {
                    if (j % 2 == 0) {
                        mainPanel.add(dots[i / 2][j / 2] = new Dot(i / 2, j / 2));
                    } else {
                        mainPanel.add(lineX[i / 2][j / 2] = new LineX(i / 2, j / 2));
                    }
                }
            } else {
                for (int j = 0; j < column * 2 - 1; j++) {
                    if (j % 2 == 0) {
                        mainPanel.add(lineY[i / 2][j / 2] = new LineY(i / 2, j / 2));
                    } else {
                        mainPanel.add(boxes[i / 2][j / 2] = new Box(i / 2, j / 2));
                    }
                }
            }
        }
        {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLayout(new BorderLayout(0, 10));
            this.add(scorePanel, BorderLayout.NORTH);
            this.add(mainPanel, BorderLayout.CENTER);
            this.add(navigationPanel, BorderLayout.SOUTH);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            this.setResizable(false);
            this.setVisible(true);
        }
    }

    public void updateNav() {
        if (turn == 1)
            navLabel.setText("A's turn");
        else
            navLabel.setText("B's turn");
    }

    public void updateScore() {
        scoreLabel.setText("A = " + scoreA + " | B = " + scoreB);
        if(scoreA+scoreB == (row-1)*(column-1)){
            whoWins = new JPanel();
            if(scoreA>scoreB){
                navLabel.setText("A wins!");
            }
            else if(scoreA<scoreB){
                navLabel.setText("B wins!");
            }
            else if(scoreA==scoreB){
                navLabel.setText("Nobody wins! Match Tied!");
            }
        }
    }

    void updateTurn(boolean scored, int lineType, int x, int y) {
        if (scored == false) {
            turn = (turn == 1 ? 0 : 1);
            updateNav();
        }
    }

    boolean isClickable(boolean clicked) {
        return clicked;
    }

    protected class Dot extends JButton {

        private int x, y;

        private int height = 20, width = 20;
        private Color bg = Color.BLACK;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;

            this.setEnabled(false);
            this.setBorderPainted(false);
            this.setBackground(bg);
            this.setSize(width, height);
            setMinimumSize(getSize());
            setMaximumSize(getSize());
            setPreferredSize(getSize());
        }
    }

    protected class LineX extends JButton {

        private int x, y;

        private int height = 20, width = 50;
        private Color bg = Color.white, activate = new Color(45, 45, 46);
        private boolean clicked;

        boolean isClicked() {
            return clicked;
        }

        public LineX(int x, int y) {
            this.x = x;
            this.y = y;

            this.setBorderPainted(false);
            this.clicked = false;
            this.setBackground(bg);
            this.setSize(width, height);
            setMinimumSize(getSize());
            setMaximumSize(getSize());
            setPreferredSize(getSize());

            this.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isClickable(clicked)) {
                        return;
                    }

                    setBackground(activate);
                    clicked = true;

                    int nx, ny;
                    boolean scored = false;

                    nx = x - 1;
                    ny = y;

                    if (nx > -1 && nx < row
                            && lineX[nx][ny].isClicked()
                            && lineY[nx][ny].isClicked()
                            && lineY[nx][ny + 1].isClicked()) {
                        boxes[nx][ny].setOwner((turn == 1 ? p1 : p2));
                        scored = true;
                    }

                    nx = x + 1;
                    ny = y;

                    if (nx > -1 && nx < row
                            && lineX[nx][ny].isClicked()
                            && lineY[x][y].isClicked()
                            && lineY[x][y + 1].isClicked()) {
                        boxes[x][y].setOwner((turn == 1 ? p1 : p2));
                        scored = true;
                    }

                    updateTurn(scored, 0, x, y);

                    // if (scored == false) {
                    // turn = (turn == 1 ? 0 : 1);
                    // updateNav();
                    // }

                    // System.out.println(x + " " + y);

                }

            });

            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (clicked) {
                        return;
                    }
                    LineX xx = (LineX) e.getSource();
                    xx.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (clicked) {
                        return;
                    }
                    LineX xx = (LineX) e.getSource();
                    xx.setBackground(Color.white);
                }
            });
        }
    }

    protected class LineY extends JButton {

        private int x, y;

        private int height = 50, width = 20;
        private Color bg = Color.white, activate = new Color(45, 45, 46);
        private boolean clicked;

        boolean isClicked() {
            return clicked;
        }

        public LineY(int x, int y) {
            this.x = x;
            this.y = y;

            this.setBorderPainted(false);
            this.clicked = false;
            this.setBackground(bg);
            this.setSize(width, height);
            setMinimumSize(getSize());
            setMaximumSize(getSize());
            setPreferredSize(getSize());

            this.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isClickable(clicked)) {
                        return;
                    }

                    LineY xx = (LineY) e.getSource();
                    xx.setBackground(activate);
                    clicked = true;

                    int nx, ny;
                    boolean scored = false;

                    nx = x;
                    ny = y - 1;

                    if (ny > -1 && ny < column
                            && lineY[nx][ny].isClicked()
                            && lineX[nx][ny].isClicked()
                            && lineX[nx + 1][ny].isClicked()) {
                        boxes[nx][ny].setOwner((turn == 1 ? p1 : p2));
                        scored = true;
                    }

                    nx = x;
                    ny = y + 1;

                    if (ny > -1 && ny < column
                            && lineY[nx][ny].isClicked()
                            && lineX[x][y].isClicked()
                            && lineX[x + 1][y].isClicked()) {
                        boxes[x][y].setOwner((turn == 1 ? p1 : p2));
                        scored = true;
                    }

                    updateTurn(scored, 1, x, y);

                    // System.out.println(x + " " + y);

                }

            });

            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (clicked) {
                        return;
                    }
                    LineY xx = (LineY) e.getSource();
                    xx.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (clicked) {
                        return;
                    }
                    LineY xx = (LineY) e.getSource();
                    xx.setBackground(Color.white);
                }
            });
        }

    }

    protected class Box extends JButton {

        private int x, y;

        private int height = 50, width = 50;
        private Color bg = Color.white;
        private String owner;

        public Box(int x, int y) {

            this.setFont(new Font("LCDMono2", Font.PLAIN, 20));

            this.x = x;
            this.y = y;

            owner = "";

            this.setBackground(bg);
            this.setBorderPainted(false);
            this.setEnabled(false);
            this.setSize(width, height);
            setMinimumSize(getSize());
            setMaximumSize(getSize());
            setPreferredSize(getSize());
        }

        void setOwner(String s) {
            if (turn == 1)
                scoreA++;
            else
                scoreB++;

            updateScore();

            owner = s;
            this.setText(s);
        }
    }

    public static void main(String[] args) {
        new MakeABox(4, 4);
    }
}
