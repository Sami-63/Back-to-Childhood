/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author As-Sami
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
    
        
        
        frame.add(new TicTacToe());
        
        frame.setVisible(true);
    }
    
}
