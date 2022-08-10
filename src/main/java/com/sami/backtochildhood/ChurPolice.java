package com.sami.backtochildhood;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.k33ptoo.components.KButton;

import Server.NetworkConnection;

public class ChurPolice extends JFrame {

    JPanel background, playerProfile[], cards[];
    JLabel cardPoint[], playerName[], navBar;
    Point handPosition[], initialPosition[], shufflePosition[], middlePoint;

    Boolean cardDisable[], profileDisable[];
    NetworkConnection nc;
    int turn, startingTurn;
    int playerCard[], playerScore[], moves, counter = 0;

    /*
     * 0 1
     * 2 3
     * * *
     * 0 = myturn
     * 1,2,3 = next player
     */

    ChurPolice(NetworkConnection nc, int turn, String players[], int moves) {

        this.setTitle("Chur Police");

        this.moves = moves;
        this.turn = 0;
        startingTurn = turn;
        this.nc = nc;

        playerCard = new int[4];

        // System.out.println("starting turn : " + startingTurn);

        playerScore = new int[4];

        {
            cardDisable = new Boolean[4];
            cardDisable[0] = true;
            cardDisable[1] = true;
            cardDisable[2] = true;
            cardDisable[3] = true;
        }

        {
            profileDisable = new Boolean[4];
            profileDisable[0] = true;
            profileDisable[1] = true;
            profileDisable[2] = true;
            profileDisable[3] = true;
        }

        background = new JPanel();
        background.setPreferredSize(new Dimension(900, 600));
        background.setBackground(new Color(25, 27, 75));

        background.setLayout(null);
        {
            this.handPosition = new Point[4];
            this.handPosition[0] = new Point(160, 70);
            this.handPosition[1] = new Point(660, 70);
            this.handPosition[2] = new Point(160, 450);
            this.handPosition[3] = new Point(660, 450);
        }

        {
            this.initialPosition = new Point[4];
            this.initialPosition[0] = new Point(360, 210);
            this.initialPosition[1] = new Point(460, 210);
            this.initialPosition[2] = new Point(360, 310);
            this.initialPosition[3] = new Point(460, 310);
        }

        {
            this.shufflePosition = new Point[4];
            this.shufflePosition[0] = new Point(410, 125);
            this.shufflePosition[1] = new Point(410, 215);
            this.shufflePosition[2] = new Point(410, 305);
            this.shufflePosition[3] = new Point(410, 395);
        }

        middlePoint = new Point(410, 260);

        cardPoint = new JLabel[4];
        cards = new JPanel[4];

        for (int i = 0; i < 4; i++) {
            cardPoint[i] = new JLabel();
            if (i == 0)
                cardPoint[i].setText("1200");
            else if (i == 1)
                cardPoint[i].setText("800");
            else if (i == 2)
                cardPoint[i].setText("500");
            else if (i == 3)
                cardPoint[i].setText("0X0");

            cardPoint[i].setHorizontalAlignment(JLabel.CENTER);
            cardPoint[i].setVerticalAlignment(JLabel.CENTER);
            cardPoint[i].setForeground(Color.black);
            cardPoint[i].setFont(new Font("Roboto", Font.BOLD, 25));
            cardPoint[i].setVisible(false);

            cards[i] = new JPanel();
            cards[i].setLayout(new BorderLayout(0, 0));
            cards[i].setSize(80, 80);
            cards[i].setBackground(new Color(214, 204, 203));
            cards[i].setLocation(handPosition[i]);
            cards[i].add(cardPoint[i], BorderLayout.CENTER);
            cards[i].addMouseListener(new CardListener());
        }
        playerProfile = new JPanel[4];
        playerName = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            playerName[i] = new JLabel();
            playerName[i].setText(players[i]);
            playerName[i].setHorizontalAlignment(JLabel.CENTER);
            playerName[i].setVerticalAlignment(JLabel.CENTER);
            playerName[i].setForeground(Color.black);
            playerName[i].setFont(new Font("Roboto", Font.BOLD, 25));

            playerProfile[i] = new JPanel();
        }

        playerProfile[0].setBounds(0, 0, 150, 150);
        playerProfile[1].setBounds(750, 0, 150, 150);
        playerProfile[2].setBounds(0, 450, 150, 150);
        playerProfile[3].setBounds(750, 450, 150, 150);

        for (int i = 0; i < 4; i++) {
            playerProfile[i].setLayout(new BorderLayout(0, 0));
            playerProfile[i].add(playerName[i], BorderLayout.CENTER);
            playerProfile[i].addMouseListener(new ProfileListener());
        }

        BackToNormalProfileColor();

        for (int i = 0; i < 4; i++) {
            background.add(playerProfile[i]);
            background.add(cards[i]);
        }

        navBar = new JLabel();
        navBar.setText("Chur Police");
        navBar.setBounds(275, 0, 350, 80);
        navBar.setHorizontalAlignment(JLabel.CENTER);
        navBar.setVerticalAlignment(JLabel.CENTER);
        navBar.setForeground(new Color(247, 164, 0));
        navBar.setFont(new Font("Roboto", Font.BOLD, 25));
        background.add(navBar);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.add(background);
        this.pack();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        new Thread(new GameThread()).start();
    }

    void BackToNormalProfileColor() {
        playerProfile[0].setBackground(Color.red);
        playerProfile[1].setBackground(Color.blue);
        playerProfile[2].setBackground(Color.green);
        playerProfile[3].setBackground(Color.yellow);
    }

    void getStarted() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                navBar.setText("Chur-Police");
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 4; i++)
            cards[i].setLocation(initialPosition[i]);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 4; i++)
            cards[i].setLocation(middlePoint);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 4; i++)
            cards[i].setLocation(shufflePosition[i]);
    }

    private class GameThread implements Runnable {

        @Override
        public void run() {

            if (moves == counter) {

                new Thread(new GameOver()).start();

                return;
            }

            System.out.println("now = " + (turn + startingTurn) % 4);
            // for (int i = 0; i < 4; i++)
            // System.out.println("disable[" + i + "] = " + disable[i]);

            if (turn == 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                // reciving card from server
                String res = nc.recieveString();
                String r[] = res.split("\\|");
                // System.out.println("Cards : " + res);
                for (int i = 0; i < 4; i++) {
                    cardPoint[i].setText(r[i]);
                }

                System.out.println("got the cards");

                for (int j = 0; j < 4; j++)
                    cardPoint[j].setVisible(false);
                BackToNormalProfileColor();

                // animating the shuffle
                getStarted();
            }

            if ((turn + startingTurn) % 4 == 0) {

                navBar.setText("it's your turn");
                System.out.println("its your turn");
                for (int i = 0; i < 4; i++) {
                    if (cards[i].getLocation().equals(shufflePosition[i]))
                        cardDisable[i] = false;
                }
            } else {

                navBar.setText("it's " + playerName[(turn + startingTurn) % 4].getText() + "'s turn");
                System.out.println("waitinng for opponents");
                String response = nc.recieveString();
                System.out.println("response -> " + response);

                int selectedCard = Integer.parseInt(response);
                cards[selectedCard].setLocation(handPosition[(turn + startingTurn) % 4]);
                playerCard[(turn + startingTurn) % 4] = Integer
                        .parseInt(cardPoint[selectedCard].getText());
                turn++;

                check();
            }

        }
    }

    private class SecondPhase implements Runnable {

        @Override
        public void run() {

            if (playerCard[0] == 500) {
                counter++;
                navBar.setText("you are the police... identify the theif");
                System.out.println("you need to identify the chur");
                repaint();
                for (int i = 1; i < 4; i++) {
                    if (playerCard[i] == 0 || playerCard[i] == 800) {
                        playerProfile[i].setBackground(new Color(137, 139, 143));
                        profileDisable[i] = false;
                    }
                }
            } else {
                navBar.setText("waiting for the police's turn");
                repaint();
                System.out.println("waiting for police's turn");
                String response = nc.recieveString();
                System.out.println("response -> " + response);
                if (response.equals("0")) {

                    Thread t = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            navBar.setText("the police got the wrong person");
                        }
                    });

                    t.start();

                    // navBar.setText("the police got the wrong person");
                    // System.out.println("changing points");

                    for (int i = 0; i < 4; i++) {
                        if (playerCard[i] == 500) {
                            playerCard[i] = 0;
                        } else if (playerCard[i] == 0) {
                            playerCard[i] = 500;
                        }
                    }
                } else {
                    // navBar.setText("the police got the chur");
                    Thread t = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            navBar.setText("the police got the chur");
                        }
                    });

                    t.start();
                }
                for (int i = 0; i < 4; i++) {
                    // cardPoint[i].setText(Integer.toString(playerCard[i]));
                    cardPoint[i].setVisible(true);
                }

                System.out.println("score: ");
                for (int i = 0; i < 4; i++) {
                    // System.out.println(playerName[i].getText() + " -> " + playerCard[i]);
                    playerScore[i] += playerCard[i];
                }
                // System.out.println("repainting");
                repaint();
                // System.out.println("repainting done");

                // for (int i = 0; i < 4; i++) {
                // System.out.println(playerName[i].getText() + " -> " + playerScore[i]);
                // }

                counter++;
                new Thread(new GameThread()).start();
            }
        }

    }

    void check() {
        if (turn == 4) {

            // System.out.println("in check function");

            turn = 0;
            startingTurn--;
            if (startingTurn == -1)
                startingTurn = 3;

            // System.out.println("changing cards visibility");
            for (int i = 0; i < 4; i++) {
                if (cardPoint[i].getText().equals("500") || cardPoint[i].getText().equals("1200")) {
                    cardPoint[i].setVisible(true);
                }
            }
            // System.out.println("visibility changed");

            // System.out.println("\n");
            // for (int i = 0; i < 4; i++)
            // System.out.println(playerName[i].getText() + "'s score = " + playerCard[i] +
            // "-");
            // System.out.println("\n");

            new Thread(new SecondPhase()).start();
        } else {
            new Thread(new GameThread()).start();
        }
    }

    private class CardListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == cards[i] && cardDisable[i] == false) {
                    cards[i].setLocation(handPosition[0]);
                    cardPoint[i].setVisible(true);

                    nc.sendString(Integer.toString(i));
                    playerCard[0] = Integer.parseInt(cardPoint[i].getText());
                    // System.out.println("you got : " + playerCard[0]);

                    for (int j = 0; j < 4; j++)
                        cardDisable[j] = true;
                    turn++;
                    check();
                }
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
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == cards[i] && cardDisable[i] == false)
                    cards[i].setBackground(new Color(125, 122, 121));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == cards[i])
                    cards[i].setBackground(new Color(214, 204, 203));
            }
        }

    }

    private class ProfileListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == playerProfile[i] && profileDisable[i] == false) {
                    nc.sendString(Integer.toString(playerCard[i] == 0 ? 1 : 0));

                    if (playerCard[i] == 0) {
                        // System.out.println("you got the chur");

                        Thread t = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                navBar.setText("you got the chur");
                            }
                        });

                        t.start();

                    } else {
                        for (int j = 0; j < 4; j++) {
                            if (playerCard[j] == 500)
                                playerCard[j] = 0;
                            else if (playerCard[j] == 0)
                                playerCard[j] = 500;
                        }
                        // System.out.println("u are the chur");
                        Thread t = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                navBar.setText("you choose the wrong person");
                            }
                        });

                        t.start();
                    }
                    repaint();
                    for (int j = 0; j < 4; j++)
                        profileDisable[j] = true;

                    for (int j = 0; j < 4; j++)
                        cardPoint[j].setVisible(true);
                    BackToNormalProfileColor();

                    System.out.println("score: ");
                    for (int j = 0; j < 4; j++) {
                        System.out.println(playerName[j].getText() + " -> " + playerCard[j]);
                        playerScore[j] += playerCard[j];
                    }

                    // System.out.println("score: ");
                    // for (int j = 0; j < 4; j++)
                    // System.out.println(playerName[j].getText() + " -> " + playerCard[j]);

                    repaint();

                    System.out.println("counter -> " + counter + " | moves -> " + moves);

                    if (moves == counter)
                        new Thread(new GameOver()).start();
                    else
                        new Thread(new GameThread()).start();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == playerProfile[i] && profileDisable[i] == false) {
                    playerProfile[i].setBackground(new Color(64, 68, 74));
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == playerProfile[i] && profileDisable[i] == false) {
                    playerProfile[i].setBackground(new Color(137, 139, 143));
                }
            }

        }

    }

    private class GameOver implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                cards[i].setVisible(false);
            }

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }

            navBar.setText("");

            // ---------------------------------------------------------------
            JPanel scoreBoard = new JPanel();
            scoreBoard.setBounds(200, 100, 500, 400);
            scoreBoard.setBackground(Color.white);
            scoreBoard.setLayout(null);

            for (int i = 0; i < 4; i++)
                for (int j = i + 1; j < 4; j++)
                    if (playerScore[i] < playerScore[j]) {
                        int temp = playerScore[i];
                        playerScore[i] = playerScore[j];
                        playerScore[j] = temp;

                        String stemp = playerName[i].getText();
                        playerName[i].setText(playerName[j].getText());
                        playerName[j].setText(stemp);
                    }

            try {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(
                        Font.createFont(Font.TRUETYPE_FONT, new File("Lighthouse_PersonalUse.ttf")));
            } catch (Exception e) {
            }

            SLabel label = new SLabel("Leader Board");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Lighthouse Personal Use", Font.PLAIN, 40));
            label.setBounds(50, 0, 400, 80);

            SPanel panels[] = new SPanel[3];
            for (int i = 0; i < 3; i++) {
                panels[i] = new SPanel();
                panels[i].setBounds(25 + 150 * i, 90, 150, 300);
                panels[i].setLayout(new FlowLayout());
            }

            panels[0].add(new SLabel("King"));
            panels[0].add(new SLabel("Minister"));
            panels[0].add(new SLabel("Police"));
            panels[0].add(new SLabel("Thief"));

            for (int i = 0; i < 4; i++) {
                panels[1].add(new SLabel(playerName[i].getText()));
            }

            for (int i = 0; i < 4; i++) {
                panels[2].add(new SLabel("" + playerScore[i]));
            }

            // SLabel p1 = new SLabel("<p>King &emsp;- " + playerName[0].getText() + " - " +
            // playerScore[0] + "</p>");
            // SLabel p2 = new SLabel("<p>Minister &emsp;- " + playerName[1].getText() + " -
            // " + playerScore[1] + "</p>");
            // SLabel p3 = new SLabel("<p>Police &emsp;- " + playerName[2].getText() + " - "
            // + playerScore[2] + "</p>");
            // SLabel p4 = new SLabel("<p>Theif &emsp;- " + playerName[3].getText() + " - "
            // + playerScore[3] + "</p>");

            KButton exit = new KButton();
            exit.setText("Exit");
            exit.setFont(new Font("Arial", Font.PLAIN, 25));
            exit.setkStartColor(new Color(0, 0, 70));
            exit.setkEndColor(new Color(28, 181, 224));
            exit.setkForeGround(new Color(250, 250, 205));
            exit.setkHoverStartColor(new Color(28, 181, 224));
            exit.setkHoverEndColor(new Color(0, 0, 70));
            exit.setBounds(350, 510, 200, 80);
            exit.setBorder(null);
            exit.setkBorderRadius(20);
            exit.setkPressedColor(Color.gray);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    termnate();
                    new HomePage();
                }
            });

            scoreBoard.add(label);
            for (int i = 0; i < 3; i++)
                scoreBoard.add(panels[i]);

            background.add(scoreBoard);
            background.add(exit);

            new Thread(new Runnable() {

                @Override
                public void run() {
                    restart();
                    background.revalidate();
                }

            }).start();
            // ---------------------------------------------------------------
        }

        private class SPanel extends JPanel {
            SPanel() {
                super();
                setPreferredSize(new Dimension(150, 300));
            }
        }

        private class SLabel extends JLabel {
            SLabel(String text) {
                super(text);

                setVerticalAlignment(SwingConstants.CENTER);
                setPreferredSize(new Dimension(100, 70));
                setForeground(Color.black);
                setBackground(new Color(0, 0, 0, 0));

                setFont(new Font("Arial", Font.BOLD, 20));
                // setFont(new Font("LightHouse", Font.BOLD, 20));
            }
        }
    }

    private void termnate() {
        this.dispose();
    }

    private void restart() {
        this.repaint();
    }

    public static void main(String[] args) {
        // new ChurPolice();
    }
}
