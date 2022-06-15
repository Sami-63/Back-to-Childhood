/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MakeABoxOnline;

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
                user.setOpponent(opponent);
                userList.get(opponent).setOpponent(name);

                new Thread(new CreateConnection(name, opponent)).start();
            }
        }
    }

    public static class CreateConnection implements Runnable {

        User player1, player2;
        Random rand = new Random();
        
        public CreateConnection(String p1, String p2) {
            this.player1 = userList.get(p1);
            this.player2 = userList.get(p2);
        }

        @Override
        public void run() {
            
            int type = rand.nextInt() % 4;

            if(type<0) type += 4;

            int row=-1, column=-1;
            switch(type){
                case 0:
                    row=5; column=7;
                    break;
                
                case 1:
                    row=7; column=9;
                    break;
                
                    case 2:
                    row=9; column=9;
                    break;
            
                case 3:
                    row=13; column=13;
                    break;
            }
            
            

            boolean turn = rand.nextBoolean();
            Data data = new Data();

            // turn = true -> player 1 moves first

            data.msg = (turn ? 1 : 0) + "$" + player2.username + "$" + row + "$" + column;
            player1.nc.write(data);

            data.msg = (turn ? 0 : 1) + "$" + player1.username + "$" + row + "$" + column;;
            player2.nc.write(data);
                            
            System.out.println("\n\n\n");

            boolean running = true;
            while(running){

                if(turn){

                    String res = player1.nc.recieveString();
                    player2.nc.sendString(res);

                    if(res.charAt(res.length()-1) == '0')
                        turn = false;

                }else{

                    String res = player2.nc.recieveString();
                    player1.nc.sendString(res);

                    if(res.charAt(res.length()-1) == '0')
                        turn = true;
                }

            }

            
        }
    }
}