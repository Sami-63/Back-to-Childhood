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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MakeABox extends JFrame {

    private String p1 = "S", p2 = "M";
    boolean turn;

    JPanel mainPanel, scorePanel, navigationPanel;
    JTextField textField;
    private int row;
    private int column;
    private Dot dots[][];
    private LineX lineX[][];
    private LineY lineY[][];
    private Box boxes[][];

    public MakeABox(int row, int column) {

        this.row = row;
        this.column = column;
        
        turn = true;// true -> player 1 turn, false -> player 2 turn

        scorePanel = new JPanel();
        mainPanel = new JPanel();
        navigationPanel = new JPanel();
        dots = new Dot[30][30];
        lineX = new LineX[30][30];
        lineY = new LineY[30][30];
        boxes = new Box[30][30];
        
        scorePanel.setPreferredSize(new Dimension(70 * column - 50, 70));
        navigationPanel.setPreferredSize(new Dimension(70 * column - 50, 70));
        
        textField = new JTextField();
        textField.setBackground(new Color(0,0,0,125));
        textField.setFont(new Font("MV Boli", Font.BOLD, 50));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Make-A-Box");
        textField.setOpaque(true);
        textField.setSize(70 * column - 50, 70);
        
        scorePanel.setBackground(new Color(25, 250, 0));
//        scorePanel.add(textField);
        
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
        
        navigationPanel.setBackground(Color.blue);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(new BorderLayout(0,10));
        this.add(scorePanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(navigationPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setResizable(false);
        this.setVisible(true);
    }

    private class Dot extends JButton {

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

    private class LineX extends JButton {

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

            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (clicked) {
                        return;
                    }

                    LineX xx = (LineX) e.getSource();
                    xx.setBackground(activate);
                    clicked = true;

                    int nx, ny;
                    boolean scored = false;

                    nx = x - 1;
                    ny = y;

                    if (nx > -1 && nx < row
                            && lineX[nx][ny].isClicked()
                            && lineY[nx][ny].isClicked()
                            && lineY[nx][ny + 1].isClicked()) {
                        boxes[nx][ny].setOwner((turn ? p1 : p2));
                        scored = true;
                    }

                    nx = x + 1;
                    ny = y;

                    if (nx > -1 && nx < row
                            && lineX[nx][ny].isClicked()
                            && lineY[x][y].isClicked()
                            && lineY[x][y + 1].isClicked()) {
                        boxes[x][y].setOwner((turn ? p1 : p2));
                        scored = true;
                    }

                    if (scored == false) {
                        turn = (turn ? false : true);
                    }

                    // System.out.println(x + " " + y);
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
            }
            );
        }
    }

    private class LineY extends JButton {

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

            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
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
                        boxes[nx][ny].setOwner((turn ? p1 : p2));
                        scored = true;
                    }

                    nx = x;
                    ny = y + 1;

                    if (ny > -1 && ny < column
                            && lineY[nx][ny].isClicked()
                            && lineX[x][y].isClicked()
                            && lineX[x + 1][y].isClicked()) {
                        boxes[x][y].setOwner((turn ? p1 : p2));
                        scored = true;
                    }

                    if (scored == false) {
                        turn = (turn ? false : true);
                    }

                    // System.out.println(x + " " + y);
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
            }
            );
        }

    }

    private class Box extends JButton {

        private int x, y;

        private int height = 50, width = 50;
        private Color bg = Color.white;
        private String owner;

        public Box(int x, int y) {
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
            owner = s;
            this.setText(s);
        }
    }
    
    public static void main(String[] args) {
        new MakeABox(4,6);    
    }
}
