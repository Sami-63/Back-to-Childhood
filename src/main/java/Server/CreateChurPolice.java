package Server;

import java.util.Random;
import java.util.Vector;

public class CreateChurPolice implements Runnable {

    public User user;
    public UserList userList;

    CreateChurPolice(NetworkConnection nc, String name, UserList userList) {
        user = new User(nc, name);
        this.userList = userList;
    }

    int[] getCard() {
        int cards[] = { 1200, 800, 500, 0 };

        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            int pos = rand.nextInt(4);
            int temp = cards[i];
            cards[i] = cards[pos];
            cards[pos] = temp;
        }

        return cards;
    }

    @Override
    public void run() {
        System.out.println("in chur police");

        Vector<User> player = userList.getUsers(user, 4, "chur-police");

        if (player.size() == 0)
            return;

        for (int i = 0; i < 4; i++)
            player.elementAt(i).nc.sendString(Integer.toString(i));

        System.out.println("chur-police started");
        for (int m = 0; m < 10; m++) {

            // getting random card
            int[] cards = getCard();
            String toSend = Integer.toString(cards[0]);
            for (int i = 1; i < 4; i++)
                toSend += "|" + Integer.toString(cards[i]);
            // for (int i = 0; i < 4; i++)

            // sending cards to each player
            System.out.println("cards--------");
            for (int i = 0; i < 4; i++) {
                player.elementAt(i).nc.sendString(toSend);
                System.out.print(cards[i] + " ");
            }
            System.out.println("\n-----------");
            int pickedCard[] = new int[4]; // 0 = first player

            // determine who's move first
            int move = m % 4, police = -1;
            for (int i = 0; i < 4; i++) {

                // receiving each player's move
                String response = player.elementAt(move).nc.recieveString();
                pickedCard[move] = cards[Integer.parseInt(response)];
                if (pickedCard[move] == 500)
                    police = move;

                // sending other players, the selected moves
                for (int j = 0; j < 4; j++)
                    if (move != j)
                        player.elementAt(j).nc.sendString(response);
                move++;
                move %= 4;
            }

            // // receiving from police
            // String response = player.elementAt(police).nc.recieveString();

            // // sending the other
            // for (int i = 0; i < 4; i++)
            // if (i != police)
            // player.elementAt(i).nc.sendString(response);
        }
    }

}
