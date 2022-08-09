package Server;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class CreateTicTacToe implements Runnable {

    public User user;
    public UserList userList;

    public CreateTicTacToe(NetworkConnection nc, String name, UserList userList) {
        user = new User(nc, name);
        this.userList = userList;
    }

    @Override
    public void run() {
        System.out.println("in create tic tac toe");

        Vector<User> users = userList.getUsers(user, 2, "tic-tac-toe");
        System.out.println("-------------------------------------------------\n");

        if (users.size() == 0)
            return;

        // game starts from here.....
        Random rand = new Random();
        int turn = rand.nextInt() % 2;

        User player1 = users.elementAt(0);
        User player2 = users.elementAt(1);

        System.out.println("Found 2 players....");

        player1.nc.sendString(Integer.toString(turn));
        player2.nc.sendString(Integer.toString((turn + 1) % 2));

        boolean flag = true;
        for (int i = 0; i < 9; i++) {
            if (i % 2 == turn) {
                String response = player2.nc.recieveString();
                if (response.equals(""))
                    break;
                flag = player1.nc.sendString(response);
            } else {
                String response = player1.nc.recieveString();
                if (response.equals(""))
                    break;
                flag = player2.nc.sendString(response);
            }

            if (!flag)
                break;
        }

        try {

            try {
                player1.nc.sendString("win");
                System.out.println("send to p1");
            } catch (Exception e) {
            }

            try {
                player2.nc.sendString("win");
                System.out.println("send to p2");
            } catch (Exception e) {
            }

            System.out.println("complete");
            player1.nc.socket.close();
            player2.nc.socket.close();
            System.out.println("socket closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
