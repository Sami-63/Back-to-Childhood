/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MakeABox;

/**
 *
 * @author As-Sami
 */

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    GameFrame() {
        MakeABox panel = new MakeABox();
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);

    }
}
