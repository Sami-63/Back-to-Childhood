package Server;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class CreateTicTacToe implements Runnable{

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


        if(users.size()==0)return;

        // game starts from here.....
        Random rand = new Random();
        boolean turn = rand.nextBoolean();
        
        User player1 = users.elementAt(0);
        User player2 = users.elementAt(1);

        System.out.println("Found 2 players....");
        System.out.println("Player 1 : " + player1.username + " | Status : " + (player1.isOnline() ? "Online" : "Offline"));
        System.out.println("Player 2 : " + player2.username + " | Status : " + (player2.isOnline() ? "Online" : "Offline"));

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

        try {
            player1.nc.socket.close();
            player2.nc.socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
