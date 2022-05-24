/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author As-Sami
 */
public class TypingInJpanel extends JPanel implements KeyListener{

    public TypingInJpanel() {
        setLayout(null);
        setBounds(50,50,200,200);
        setBackground(Color.red);
        setFocusable(true);
        this.addKeyListener(this);
//        this.requestFocus();
    }
    
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        
        TypingInJpanel panel = new TypingInJpanel();
        frame.add(panel);
        
//        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("released");
    }
}
