package com.sami.backtochildhood;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import Server.NetworkConnection;

public class ChurPoliceMakeOnline {
    static void run(String name) throws IOException {
        Socket socket = new Socket("192.168.56.1", 12345);
        System.out.println("socket connecteds....");
        NetworkConnection nc = new NetworkConnection(socket);
        System.out.println("Network connected");

        Scanner sc = new Scanner(System.in);
        name = sc.next();
        sc.close();
        nc.sendString(name + "|chur-police");
        String responses[] = nc.recieveString().split("\\|");

        String players[] = new String[4];
        players[0] = name;
        players[1] = responses[1];
        players[2] = responses[2];
        players[3] = responses[3];

        // for (int i = 0; i < 4; i++)
        // System.out.println("Player :" + i + " -> " + players[i]);

        int turn = Integer.parseInt(responses[0]);
        new ChurPolice(nc, turn, players);
    }

    public static void main(String[] args) {
        try {
            run("sami");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
