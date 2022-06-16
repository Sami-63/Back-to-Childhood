/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author As-Sami
 */
public class Server {

    static HashMap<String, User> userList = new HashMap<>(); // list of all players
    static Queue<String> q = new LinkedList<>(); // waiting players

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started...");
        System.out.println(InetAddress.getLocalHost());

        while (true) {
            Socket socket = serverSocket.accept();
            NetworkConnection nc = new NetworkConnection(socket);

            System.out.println("Found a user... ");
            new Thread(new GetUsername(nc)).start();
        }

    }

    public static class GetUsername implements Runnable {

        NetworkConnection nc;
        String name;

        public GetUsername(NetworkConnection nc) {
            this.nc = nc;
        }

        @Override
        public void run() {
            Object nameObj = nc.read();
            name = (String) nameObj;

            User user = new User(nc, name);
            userList.put(name, user);

            System.out.println("Got user #" + name);
            
            if (q.isEmpty()) {
                q.add(name);
            } else if (q.size() == 1) {
                String opponent = q.remove();
                
                if( userList.get(name).nc.isConnected() && userList.get(opponent).nc.isConnected() )
                    new Thread(new CreateConnection(name, opponent)).start();
            }
        }
    }

    static class CreateConnection implements Runnable {

        User player1, player2;
        Random rand = new Random();
        
        public CreateConnection(String p1, String p2) {
            this.player1 = userList.get(p1);
            this.player2 = userList.get(p2);

            // maybe these line will be deleted
            userList.remove(p1);
            userList.remove(p2);
        }

        @Override
        public void run() {
            boolean turn = rand.nextBoolean();
            
            if (turn) {

                Data data = new Data();
                data.msg = "1";
                player1.nc.write(data);

                data.msg = "0";
                player2.nc.write(data);
                                
                for(int i=0 ; i<9 ; i++){
                    if(i%2==0){
                        data = (Data) player1.nc.read();
                        player2.nc.write(data);
                    }else{
                        data = (Data) player2.nc.read();
                        player1.nc.write(data);
                    }
                }

            } else {

                Data data = new Data();
                data.msg = "1";
                player2.nc.write(data);

                data.msg = "0";
                player1.nc.write(data);
                
                for(int i=0 ; i<9 ; i++){
                    if(i%2==1){
                        data = (Data) player1.nc.read();
                        player2.nc.write(data);
                    }else{
                        data = (Data) player2.nc.read();
                        player1.nc.write(data);
                    }
                }
            }
        }
    }
}