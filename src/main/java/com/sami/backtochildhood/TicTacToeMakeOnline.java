/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sami.backtochildhood;

import Server.NetworkConnection;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;

/**
 *
 * @author As-Sami
 */
public class TicTacToeMakeOnline implements Runnable {
    void run(String name, JFrame frame) throws IOException {
        Socket socket = new Socket("192.168.56.1", 12345);
        System.out.println("socket connecteds....");
        NetworkConnection nc = new NetworkConnection(socket);
        System.out.println("Network connected");

        System.out.println("Searching for players");
        frame.repaint();

        nc.sendString(name + "|tic-tac-toe");
        String response = "online?";// = nc.recieveString();

        while (response.equals("online?")) {
            response = nc.recieveString();
        }

        frame.dispose();
        boolean turn;
        if (response.equals("1")) {
            System.out.println("You got the first move");
            turn = true;
        } else {
            System.out.println("Opponent got the first move");
            turn = false;
        }
        System.out.println("turn = " + turn);
        System.out.println("----------------------------------------");

        new TicTacToeOnline(nc, turn);
    }

    String name;
    JFrame frame;

    TicTacToeMakeOnline(String name, JFrame frame) {
        this.name = name;
        this.frame = frame;
    }

    @Override
    public void run() {
        try {
            run(name, frame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
