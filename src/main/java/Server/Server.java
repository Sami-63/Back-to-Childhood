package Server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static UserList userList = new UserList();

    public static void main(String[] args) {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server started...");
            System.out.println(InetAddress.getLocalHost());

            System.out.println("-------------------------------------------------\n");
            while (true) {
                Socket socket = serverSocket.accept();
                NetworkConnection nc = new NetworkConnection(socket);

                System.out.println("Found a user... ");
                new Thread(new CreateConnection(nc, userList)).start();
            }
        } catch (Exception e) {
            System.out.println("there is problem with the connection");
            e.printStackTrace();
        }

    }
}
