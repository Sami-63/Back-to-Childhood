package com.sami.backtochildhood;

import Server.NetworkConnection;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

public class MakeABoxMakeOnline {
    static void run(String name, JFrame frame) throws IOException {
        Socket socket = new Socket("192.168.56.1", 12345);
        System.out.println("socket connecteds....");
        NetworkConnection nc = new NetworkConnection(socket);
        System.out.println("Network connected");

        System.out.println("\nSearching for players...");

        nc.sendString(name + "|make-a-box");

        String matchInfo = nc.recieveString();
        System.out.println(matchInfo);

        frame.dispose();

        String list[] = matchInfo.split("\\$");

        int turn = Integer.parseInt(list[0]);
        String opponent = list[1];
        int row = Integer.parseInt(list[2]);
        int column = Integer.parseInt(list[3]);

        // System.out.println("#" + list[0] + "#");
        // System.out.println(opponent);
        // System.out.println(row + " " + column);

        if (turn == 1) {
            System.out.println("You got the first move");
        } else {
            System.out.println("Opponent got the first move");
        }

        new MakeABoxOnline(name, opponent, row, column, turn, nc);

    }
}
