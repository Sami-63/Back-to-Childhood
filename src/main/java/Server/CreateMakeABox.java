package Server;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class CreateMakeABox implements Runnable {

    public User user;
    public UserList userList;

    CreateMakeABox(NetworkConnection nc, String name, UserList userList) {
        user = new User(nc, name);
        this.userList = userList;
    }

    @Override
    public void run() {
        System.out.println("in make a box... creating connection");
        System.out.println("Player name " + user.username);
        System.out.println("-------------------------------------------------\n");

        Vector<User> users = userList.getUsers(user, 2, "make-a-box");

        if (users.size() == 0)
            return;

        User player1 = users.elementAt(0);
        User player2 = users.elementAt(1);

        Random rand = new Random();
        int type = rand.nextInt() % 3;

        if (type < 0)
            type += 3;

        int row = -1, column = -1;
        switch (type) {
            case 0:
                row = 5;
                column = 7;
                break;

            case 1:
                row = 7;
                column = 9;
                break;

            case 2:
                row = 9;
                column = 9;
                break;
        }

        boolean turn = rand.nextBoolean();

        player1.nc.sendString((turn ? 1 : 0) + "$" + player2.username + "$" + row + "$" + column);
        player2.nc.sendString((turn ? 0 : 1) + "$" + player1.username + "$" + row + "$" + column);

        boolean running = true;
        while (running) {

            if (turn) {

                String res = player1.nc.recieveString();

                if (res.equals("")) {
                    break;
                }

                running = player2.nc.sendString(res);

                // if (!running || res.equals(""))
                // break;

                if (res.charAt(res.length() - 1) == '0')
                    turn = false;

            } else {

                String res = player2.nc.recieveString();

                if (res.equals("")) {
                    break;
                }

                running = player1.nc.sendString(res);

                // if (!running || res.equals(""))
                // break;

                if (res.charAt(res.length() - 1) == '0')
                    turn = true;
            }

        }

        try {
            try {
                player1.nc.sendString("win");
            } catch (Exception e) {
            }

            try {
                player2.nc.sendString("win");
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
