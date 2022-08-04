package Server;

import java.util.Vector;

public class CreateSnakeLudo implements Runnable {

    public User user;
    public UserList userList;

    CreateSnakeLudo(NetworkConnection nc, String name, UserList userList) {
        user = new User(nc, name);
        this.userList = userList;
    }

    @Override
    public void run() {
        System.out.println("in snake ludo");

        Vector<User> player = userList.getUsers(user, 4, "snake-ludo");

        if (player.size() == 0)
            return;

        for (int i = 0; i < 4; i++) {
            String moveAndOtherPlayerInfo = Integer.toString((4 - i) % 4);
            for (int j = 1; j <= 3; j++)
                moveAndOtherPlayerInfo += "|" + player.elementAt((i + j) % 4).username;

            System.out.println("-> " + moveAndOtherPlayerInfo);
            player.elementAt(i).nc.sendString(moveAndOtherPlayerInfo);
        }

        System.out.println("snake ludo started");

        boolean f = true;
        Vector<Integer> winner = new Vector<>();

        for (int m = 0; true; m++) {

            int curPlayer = m % 4;
            System.out.println("now -> " + player.elementAt(curPlayer).username);

            if (winner.contains(curPlayer)) {
                System.out.println("skipped");
                continue;
            } else {
                System.out.println("still going");
            }

            for (int i = 0; i < 5; i++) {
                String response = player.elementAt(curPlayer).nc.recieveString();
                f = response.equals(null);
                for (int j = 1; j < 4; j++) {
                    f = player.elementAt((curPlayer + j) % 4).nc.sendString(response);
                }
            }
            System.out.println("f = " + f);

            String response = player.elementAt(curPlayer).nc.recieveString();
            f = !response.equals("");

            System.out.println("response -> " + response);

            System.out.println("f = " + f);

            if (response.equals("again"))
                m--;
            else if (response.equals("win"))
                winner.add(curPlayer);
            if (!f || winner.size() == 3)
                break;
        }

        try {
            System.out.println("complete");
            for (int i = 0; i < 4; i++)
                player.elementAt(i).nc.socket.close();
            System.out.println("socket closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
