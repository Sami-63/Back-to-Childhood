/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Drawing;

/**
 *
 * @author As-Sami
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Template extends JPanel{
    Template(){
        this.setBackground(Color.gray);
    }

    public void paint(Graphics g){
        super.paintComponent(g);

        InitialBox b1 = new InitialBox(1);
        InitialBox b2 = new InitialBox(2);
        InitialBox b3 = new InitialBox(3);
        InitialBox b4 = new InitialBox(4);
        b1.draw(g);
        b2.draw(g);
        b3.draw(g);
        b4.draw(g);

        RoadOfGuti r1 = new RoadOfGuti(1);
        RoadOfGuti r2 = new RoadOfGuti(2);
        RoadOfGuti r3 = new RoadOfGuti(3);
        RoadOfGuti r4 = new RoadOfGuti(4);
        r1.draw(g);
        r2.draw(g);
        r3.draw(g);
        r4.draw(g);
    }

    

    private class InitialBox{
        private int x,y, boxNumber;
        private final int width = 200, height = 200;
        Color color;

        public InitialBox(int boxNumber) {
            this.boxNumber = boxNumber;
            switch (boxNumber) {
                case 1:
                    x = 0;
                    y = 0;
                    color = Color.blue;
                    break;
            
                case 2:
                    x = 320;
                    y = 0;
                    color = Color.red;
                    break;
            
                case 3:
                    x = 0;
                    y = 320;
                    color = Color.green;
                    break;
                
                case 4:
                    x = 320;
                    y = 320;
                    color = Color.yellow;
                    break;

                default:
                    break;
            }
        }

        public void draw(Graphics g){

            g.setColor(color);
            g.fillRect(x, y, width, height);
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);

            g.setColor(Color.cyan);
            g.fillRect(x+width/2-40, y+height/2-40, 80, 80);


            g.setColor(Color.black);
            g.drawRect(x+width/2-40, y+height/2-40, 40, 40);
            g.drawRect(x+width/2-40, y+height/2, 40, 40);
            g.drawRect(x+width/2, y+height/2-40, 40, 40);
            g.drawRect(x+width/2, y+height/2, 40, 40);

        }
        
        public void insertGuti(){
            
        }
    }

    public class RoadOfGuti{

        private int row,column, index, x, y;
        private final int height=40, width=40;
        
        public RoadOfGuti(int index) {
            this.index = index;

            switch (index) {
                case 1:
                    row = 5;
                    column = 3;
                    x = 200;
                    y = 0;
                    break;
                
                case 2:
                    row = 3;
                    column = 5;
                    x = 0;
                    y = 200;
                    break;
            
                case 3:
                    row = 3;
                    column = 5;
                    x = 320;
                    y = 200;
                    break;
                
                case 4:
                    row = 5;
                    column = 3;
                    x = 200;
                    y = 320;
                    break;
            
                default:
                    break;
            }
        }

        public void draw(Graphics g){

            g.setColor(Color.black);
            for(int i=0 ; i<column ; i++)
                for(int j=0 ; j<row ; j++ )
                    g.drawRect(x + i*height, y + j*width, width, height);
        }


    }

    private class Guti{
        private int x,y;
        private final int radius=15;
        Color color;

        public Guti(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public void draw(Graphics g){
            g.setColor(color);
            g.fillOval(x, y, radius*2, radius*2);
        }
    }
}
