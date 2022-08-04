package com.sami.backtochildhood;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import Server.NetworkConnection;

public class SnakeLudoMakeOnline {
    static void run(String name, JFrame frame) throws IOException {
        Socket socket = new Socket("192.168.56.1", 12345);
        System.out.println("socket connecteds....");
        NetworkConnection nc = new NetworkConnection(socket);
        System.out.println("Network connected");

        nc.sendString(name + "|snake-ludo");

        String response = "online?";// = nc.recieveString();

        while (response.equals("online?")) {
            response = nc.recieveString();
        }

        String responses[] = response.split("\\|");

        frame.dispose();

        String players[] = new String[4];
        players[0] = name;
        players[1] = responses[1];
        players[2] = responses[2];
        players[3] = responses[3];

        // for (int i = 0; i < 4; i++)
        // System.out.println("Player :" + i + " -> " + players[i]);

        int turn = Integer.parseInt(responses[0]);
        new SnakeLudoOnline(nc, turn, players);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name = sc.next();
        try {
            run(name, new JFrame());
        } catch (HeadlessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
