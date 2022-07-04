package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static UserList userList = new UserList();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started...");
        System.out.println(InetAddress.getLocalHost());

        while (true) {
            Socket socket = serverSocket.accept();
            NetworkConnection nc = new NetworkConnection(socket);

            System.out.println("Found a user... ");
            new Thread(new CreateConnection(nc, userList)).start();;
        }
    }
}