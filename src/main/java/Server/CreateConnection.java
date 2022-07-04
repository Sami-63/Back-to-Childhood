package Server;

public class CreateConnection implements Runnable{
    public NetworkConnection nc;
    public UserList userList;
    
    CreateConnection(NetworkConnection nc, UserList userList){
        this.nc = nc;
        this.userList = userList;
    }

    @Override
    public void run() {
        System.out.println("In crate connection...");

        String response = nc.recieveString();
        System.out.println(response);
        
        String responses[] = response.split("\\|"); // name|game

        String name = responses[0], game = responses[1];
        System.out.println("Name : " + name + " | game : " + game);
        switch (game) {
            case "tic-tac-toe":
                new Thread(new CreateTicTacToe(nc, name, userList)).start();
                break;
        
            case "make-a-box":
                new Thread(new CreateMakeABox(nc, name)).start();
                break;

            default:
                System.out.println("the end of file.....");
                break;
        }
    }
}
