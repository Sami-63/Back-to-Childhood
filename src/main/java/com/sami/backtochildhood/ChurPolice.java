package com.sami.backtochildhood;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Server.NetworkConnection;

public class ChurPolice extends JFrame{
    
    JPanel background, playerProfile[], cards[];
    JLabel cardPoint[];
    Point handPosition[], initialPosition[], shufflePoint[], middlePoint;

    Boolean disable;
    NetworkConnection nc;
    int playerTurn;

    ChurPolice(NetworkConnection nc, int turn){

        playerTurn = turn;
        this.nc = nc;

        disable = true;

        background = new JPanel();
        background.setPreferredSize(new Dimension(900, 600));
        background.setBackground(new Color(12,34,56));

        background.setLayout(null);
        {
            this.handPosition = new Point[4];
            this.handPosition[0]  = new Point(160,70);
            this.handPosition[1]  = new Point(660,70);
            this.handPosition[2]  = new Point(160,450);
            this.handPosition[3]  = new Point(660,450);
        }
        
        {
            this.initialPosition = new Point[4];
            this.initialPosition[0]  = new Point(360, 210);
            this.initialPosition[1]  = new Point(460, 210);
            this.initialPosition[2]  = new Point(360, 310);
            this.initialPosition[3]  = new Point(460, 310);
        }

        {
            this.shufflePoint = new Point[4];
            this.shufflePoint[0]  = new Point(410, 125);
            this.shufflePoint[1]  = new Point(410, 215);
            this.shufflePoint[2]  = new Point(410, 305);
            this.shufflePoint[3]  = new Point(410, 395);
        }

        middlePoint = new Point(410,260);

        cardPoint = new JLabel[4];
        cards = new JPanel[4];

        for(int i=0 ; i<4 ; i++){
            cardPoint[i] = new JLabel();
            if(i==0) cardPoint[i].setText("1200");
            else if(i==1) cardPoint[i].setText("800");
            else if(i==2) cardPoint[i].setText("500");
            else if(i==3) cardPoint[i].setText("0X0");

            // cardPoint[i].setBounds(10, 20, 60, 40);
            cardPoint[i].setHorizontalAlignment(JLabel.CENTER);
            cardPoint[i].setVerticalAlignment(JLabel.CENTER);
            cardPoint[i].setForeground(Color.black);
            cardPoint[i].setFont(new Font("Roboto", Font.BOLD, 25));
            cardPoint[i].setVisible(false);

            cards[i] = new JPanel();
            cards[i].setLayout(new BorderLayout(0,0));
            cards[i].setSize(80, 80);
            cards[i].setLocation(initialPosition[i]);
            cards[i].add(cardPoint[i], BorderLayout.CENTER);
        }

        {
            playerProfile = new JPanel[4];
            playerProfile[0] = new JPanel();
            playerProfile[0].setBounds(0, 0, 150, 150);
            playerProfile[0].setBackground(Color.red);
            

            playerProfile[1] = new JPanel();
            playerProfile[1].setBounds(750, 0, 150, 150);
            playerProfile[1].setBackground(Color.blue);

            playerProfile[2] = new JPanel();
            playerProfile[2].setBounds(0, 450, 150, 150);
            playerProfile[2].setBackground(Color.green);
            
            playerProfile[3] = new JPanel();
            playerProfile[3].setBounds(750, 450, 150, 150);
            playerProfile[3].setBackground(Color.yellow);
        }

        for(int i=0 ; i<4 ; i++){
            background.add(playerProfile[i]);
            background.add(cards[i]);
        }

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.add(background);
        this.pack();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        // while(true){
        //     try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        //     for(int i=0 ; i<4 ; i++) cards[i].setLocation(middlePoint);

        //     try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        //     for(int i=0 ; i<4 ; i++) cards[i].setLocation(shufflePoint[i]);

        //     try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        //     for(int i=0 ; i<4 ; i++) cards[i].setLocation(handPosition[i]);
        //     for(int i=0 ; i<4 ; i++) cardPoint[i].setVisible(true);

        //     try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        //     for(int i=0 ; i<4 ; i++) cards[i].setLocation(initialPosition[i]);
        //     for(int i=0 ; i<4 ; i++) cardPoint[i].setVisible(false);
        // }

        startGame();
    }

    void startGame(){

        /*
         * all card in initial point
         * wait some time
         * gather at middle
         * wait some time
         * cards get shuffled
         * receive card's value
         * each player picks a card
         *    check move if move=0, you pick card
         *    if move=1, you wait 1 move
         *    if move=2, you wait 2 move
         *    if move=3, you wait 3 move
         * if you are police then you pick the theif in 10s
         *    if don't pick then you will get zero
         *    then you send your answer
         * else you wait 10s. or wait to receive answer
         * after recieving answer / giving answer wait 2 sec
         * then gather all card at initial point
         */

        

    }

    private class CardListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            // hover effect
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // hover effect
        }

    }

    public static void main(String[] args) {
        // new ChurPolice();
    }
}