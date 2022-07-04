package Server;

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
        
        Vector<User> users = userList.getUsers(user, 2);
        
        if(users.size()==0)return;

        // game starts from here.....
        Random rand = new Random();
        boolean turn = rand.nextBoolean();
        
        User player1 = users.elementAt(0);
        User player2 = users.elementAt(1);

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
