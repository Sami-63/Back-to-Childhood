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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SnakeLudo extends JFrame{
    
    ImageIcon icon, dice1, dice2, dice3, dice4, dice5, dice6;
    JPanel gamePanel, winnerPanel, initialPanel, sidePanel, dicePanel;
    Random random = new Random();
    
    SnakeLudo(){
        icon = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\SnakeLudo.png");
//        dice = new ImageIcon[6];
        
//        dice[0] = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice1.png");
//        dice[1] = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice2.png");
//        dice[2] = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice3.png");
//        dice[3] = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice4.png");
//        dice[4] = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice5.png");
//        dice[5] = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice6.png");

        dice1 = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice1.jpg");
        dice2 = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice2.jpg");
        dice3 = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice3.jpg");
        dice4 = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice4.jpg");
        dice5 = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice5.jpg");
        dice6 = new ImageIcon("C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice6.jpg");
        
                
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(700,700));
        
        JLabel label = new JLabel();
        label.setIcon(icon);
        label.setOpaque(true);          
        gamePanel.add(label);
        gamePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        
        winnerPanel = new JPanel();
        winnerPanel.setPreferredSize(new Dimension(200, 150));
        winnerPanel.setBackground(new Color(229,228,226));
        
        dicePanel = new JPanel();
        dicePanel.setPreferredSize(new Dimension(200, 400));
        dicePanel.setBackground(Color.white);
        JLabel dice = new JLabel();
        dice.setIcon(dice4);
        dice.setOpaque(true);
        label.setBackground(new Color(0,0,25));
        dicePanel.add(dice);
        KButton roll = new KButton();
        dicePanel.add(roll);
        roll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                roleDice(dice);
            }
        });
        
        initialPanel = new JPanel();
        initialPanel.setPreferredSize(new Dimension(200, 150));
        initialPanel.setBackground(new Color(229,228,226));
        
        sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        sidePanel.add(winnerPanel);
        sidePanel.add(dicePanel);
        sidePanel.add(initialPanel);
        sidePanel.setPreferredSize(new Dimension(200, 700));
        
        this.setLayout(new BorderLayout(0,0));
        this.add(sidePanel, BorderLayout.WEST);
        this.add(gamePanel);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private int roleDice(JLabel label){
        
        int i = Math.abs(random.nextInt()) % 6 + 1;
        String s = "C:\\Users\\As-Sami\\Documents\\NetBeansProjects\\BackToChildHood\\src\\main\\java\\Image\\dice" + Integer.toString(i) + ".jpg";
        ImageIcon icon = new ImageIcon(s);

        System.out.println(s);
        
        label.setIcon(icon);
        label.revalidate();

     
        
        
        
        return 0;
    }
    
    private class Guti{
        
        Color color;
        int position;
            
        Guti(Color color){
            this.color = color;
            position = -1;
        }
    }
    
    public static void main(String[] args) {
        new SnakeLudo();
    }
}
