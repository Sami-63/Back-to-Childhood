package com.sami.backtochildhood;

import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

//master

public class HomePage extends JFrame {

    KGradientPanel background;
    Page startGame;
    NextPage gameChooser, gameMode, makeABoxGridChooser, snakeLudoGridChooser, online, searching;

    JLabel searchingLabel, title;

    String currentGame = "";
    JTextField usernameTextfield;
    Button start;

    HomePage() {
        title = new JLabel();
        title.setText("Back to Childhood");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(0, 0, 0, 0));
        title.setBounds(150, 35, 500, 94);
        title.setOpaque(true);
        title.setForeground(Color.black);

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(
                    Font.createFont(Font.TRUETYPE_FONT, new File("Best Valentina TTF.ttf")));
            title.setFont(new Font("Best Valentina", Font.PLAIN, 70));
            // System.out.println("font added");
        } catch (Exception e) {
            title.setFont(new Font("Constantia", Font.BOLD, 25));
            // System.out.println("failed");
        }
        // title.setFont(new Font("Constantia", Font.BOLD, 50));

        setTitle("Back to Childhood");
        background = new KGradientPanel();
        background.setPreferredSize(new Dimension(800, 500));
        background.setLayout(null);
        background.setkStartColor(new Color(189, 195, 199));
        background.setkEndColor(new Color(164, 162, 180));

        {
            Button playButton = new Button("Play");
            playButton.setBounds(150, 65, 200, 75);
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startGame.setVisible(false);
                    gameChooser.setVisible(true);
                }
            });

            Button exitButton = new Button("Exit");
            exitButton.setBounds(150, 160, 200, 75);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            startGame = new Page();
            startGame.add(playButton);
            startGame.add(exitButton);
        }
        // -----------------------------------------------------------

        {
            Button tictactoeButton = new Button("Tic-Tac-Toe");
            tictactoeButton.setBounds(175, 20, 150, 50);
            tictactoeButton.changeFontSize(14);
            tictactoeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentGame = "tic-tac-toe";
                    gameChooser.setVisible(false);
                    gameMode.setVisible(true);
                }
            });

            Button makeaboxButton = new Button("Make-a-Box");
            makeaboxButton.setBounds(175, 90, 150, 50);
            makeaboxButton.changeFontSize(14);
            makeaboxButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentGame = "make-a-box";
                    gameChooser.setVisible(false);
                    gameMode.setVisible(true);

                }
            });

            Button churPoliceButton = new Button("Chur-Police");
            churPoliceButton.setBounds(175, 160, 150, 50);
            churPoliceButton.changeFontSize(14);
            churPoliceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentGame = "chur-police";
                    gameChooser.setVisible(false);
                    online.setVisible(true);
                }
            });

            Button snakeLudoButton = new Button("Snake-Ludo");
            snakeLudoButton.setBounds(175, 230, 150, 50);
            snakeLudoButton.changeFontSize(14);
            snakeLudoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentGame = "snake-ludo";
                    gameChooser.setVisible(false);
                    gameMode.setVisible(true);
                }
            });

            gameChooser = new NextPage(startGame);
            gameChooser.add(tictactoeButton);
            gameChooser.add(makeaboxButton);
            gameChooser.add(churPoliceButton);
            gameChooser.add(snakeLudoButton);
        }
        // ------------------------------------------

        {
            Button onlineButton = new Button("Online");
            onlineButton.setBounds(150, 65, 200, 75);
            onlineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameMode.setVisible(false);
                    online.setVisible(true);
                }
            });

            Button offlineButton = new Button("Offline");
            offlineButton.setBounds(150, 160, 200, 75);
            offlineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameMode.setVisible(false);

                    if (currentGame.equals("tic-tac-toe")) {
                        dispose();
                        new TicTacToe();
                    } else if (currentGame.equals("make-a-box")) {
                        makeABoxGridChooser.setVisible(true);
                    } else if (currentGame.equals("snake-ludo")) {
                        snakeLudoGridChooser.setVisible(true);
                    }

                }
            });

            gameMode = new NextPage(gameChooser);
            gameMode.add(onlineButton);
            gameMode.add(offlineButton);
        }
        // --------------------------------------------------

        {
            Button type1 = new Button("4 X 6");
            type1.setBounds(150, 27, 200, 65);
            type1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new MakeABox(4, 6);
                }
            });

            Button type2 = new Button("6 X 8");
            type2.setBounds(150, 117, 200, 65);
            type2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new MakeABox(6, 8);
                }
            });

            Button type3 = new Button("8 X 10");
            type3.setBounds(150, 207, 200, 65);
            type3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new MakeABox(8, 10);
                }
            });

            makeABoxGridChooser = new NextPage(gameMode);
            makeABoxGridChooser.add(type1);
            makeABoxGridChooser.add(type2);
            makeABoxGridChooser.add(type3);
        }

        // ----------------------------------------------------
        {
            Button Player2 = new Button("2 players");
            Player2.setBounds(150, 27, 200, 65);
            Player2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new SnakeLudo(2);
                }
            });

            Button Player3 = new Button("3 players");
            Player3.setBounds(150, 117, 200, 65);
            Player3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new SnakeLudo(3);
                }
            });

            Button Player4 = new Button("4 players");
            Player4.setBounds(150, 207, 200, 65);
            Player4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new SnakeLudo(4);
                }
            });

            snakeLudoGridChooser = new NextPage(gameMode);
            snakeLudoGridChooser.add(Player2);
            snakeLudoGridChooser.add(Player3);
            snakeLudoGridChooser.add(Player4);
        }

        // ---------------------------------------------------

        online = new NextPage(null);
        for (ActionListener al : online.button.getActionListeners()) {
            online.button.removeActionListener(al);
        }
        online.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                online.setVisible(false);
                if (currentGame.equals("chur-police"))
                    gameChooser.setVisible(true);
                else
                    gameMode.setVisible(true);
            }
        });

        usernameTextfield = new JTextField();
        usernameTextfield.setBounds(150, 65, 200, 75);
        usernameTextfield.setFont(new Font("Constantia", Font.BOLD, 30));
        usernameTextfield.setBorder(null);
        usernameTextfield.setHorizontalAlignment(SwingConstants.CENTER);
        usernameTextfield.setBackground(new Color(255, 255, 255));

        usernameTextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start.doClick();
            }
        });

        start = new Button("Start");
        start.setBounds(150, 160, 200, 75);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(currentGame);
                online.setVisible(false);
                searching.setVisible(true);

                startOnline();
            }
        });

        online.add(usernameTextfield);
        online.add(start);

        // ---------------------------------------------

        searchingLabel = new JLabel();
        searchingLabel = new JLabel("Searching for players....");
        searchingLabel.setForeground(Color.white);
        searchingLabel.setBackground(new Color(255, 255, 255, 0));
        searchingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchingLabel.setBounds(50, 100, 400, 100);
        searchingLabel.setFont(new Font("Constantia", Font.BOLD, 30));
        searchingLabel.setOpaque(true);

        searching = new NextPage(null);
        for (ActionListener al : searching.button.getActionListeners()) {
            searching.button.removeActionListener(al);
        }
        searching.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameTextfield.setText("");
                searchingLabel.setText("Searching for players....");
                searching.setVisible(false);
                startGame.setVisible(true);
                repaint();
            }
        });
        searching.button.setVisible(false);
        searching.add(searchingLabel);

        background.add(title);
        background.add(startGame);
        background.add(gameChooser);
        background.add(gameMode);
        background.add(makeABoxGridChooser);
        background.add(snakeLudoGridChooser);
        background.add(online);
        background.add(searching);

        this.add(background);
        this.pack();
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        repaint();
    }

    void startOnline() {
        new Thread(new ConnectGame(currentGame, usernameTextfield.getText(), this,
                searchingLabel, searching.button)).start();
    }

    class Page extends KGradientPanel {
        Page() {
            this.setBounds(150, 145, 500, 300);
            this.setkBorderRadius(40);
            this.setBackground(new Color(0, 0, 0, 0));

            this.setkStartColor(new Color(40, 49, 59));
            this.setkEndColor(new Color(72, 84, 97));

            this.setLayout(null);

            // this.setkStartColor(new Color(255, 255, 255, 100));
            // this.setkEndColor(new Color(255, 255, 255, 100));
        }
    }

    class NextPage extends Page implements ActionListener {

        Page prevPage;
        Button button;

        public NextPage(Page prevPage) {
            this.prevPage = prevPage;

            // Button back = new Button("Back");
            // back.setPreferredSize(new Dimension(50, 50));
            // back.setkBorderRadius(50);
            this.setVisible(false);

            button = new Button("Back");
            button.addActionListener(this);
            button.setBounds(10, 10, 80, 40);
            button.setFont(new Font("Constantia", Font.BOLD, 15));
            button.setkBorderRadius(40);

            this.add(button);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.setVisible(false);
            prevPage.setVisible(true);

        }

    }

    class Button extends KButton {
        Button(String text) {
            this.setText(text);
            this.setPreferredSize(new Dimension(200, 75));
            this.setBackground(new Color(0, 0, 0, 0));
            this.setkBorderRadius(20);
            this.setkStartColor(Color.white);
            this.setkEndColor(Color.white);

            this.setkHoverStartColor(new Color(255, 255, 255, 100));
            this.setkHoverEndColor(new Color(255, 255, 255, 100));

            this.setFont(new Font("Constantia", Font.BOLD, 25));
            this.setkForeGround(Color.black);
            this.setBorder(null);

            this.setkHoverForeGround(Color.black);
            this.setkPressedColor(Color.white);
        }

        void changeFontSize(int size) {
            this.setFont(new Font("Constantia", Font.BOLD, size));
        }
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
