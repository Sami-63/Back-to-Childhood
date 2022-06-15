package MakeABoxOnline;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
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
        
        String list[] = msg.split("\\$");

        int turn = Integer.parseInt(list[0]);
        String opponent = list[1];
        int row = Integer.parseInt(list[2]);
        int column = Integer.parseInt(list[3]);


        // System.out.println("#" + list[0] + "#");
        // System.out.println(opponent);
        // System.out.println(row + " " + column);

        if (turn==1) {
            System.out.println("You got the first move");
        } else {
            System.out.println("Opponent got the first move");
        }
        
        new MakeABox(username, opponent, 5, 5, turn, nc);
        
    }
}
