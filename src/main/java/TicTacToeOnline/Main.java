/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TicTacToeOnline;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author As-Sami
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.56.1", 12345);
        System.out.println("socket connecteds....");
        NetworkConnection nc = new NetworkConnection(socket);
        System.out.println("Network connected");

        System.out.print("Enter username : ");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        nc.write(username);

        System.out.println("\nSearching for players...");

        Data data = (Data) nc.read();
        String msg = data.msg;
        System.out.println(msg);
        
        boolean turn;
        if (msg.equals("1")) {
            System.out.println("You got the first move");
            turn = true;
        } else {
            System.out.println("Opponent got the first move");
            turn = false;
        }
        System.out.println("turn = " + turn);
        System.out.println("----------------------------------------");
        // new KataKuti(nc, turn);
        new TicTacToe(nc, turn);
    }
}
