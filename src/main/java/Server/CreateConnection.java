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
        
        String responses[] = response.split("\\|"); // name|game

        String name = responses[0], gameName = responses[1];
        System.out.println("Name : " + name + " | game : " + gameName);
        System.out.println("-------------------------------------------------\n");

        if(gameName.equals("tic-tac-toe"))
            new Thread(new CreateTicTacToe(nc, name, userList)).start();
        else if(gameName.equals("make-a-box"))
            new Thread(new CreateMakeABox(nc, name, userList)).start();
        else if(gameName.equals("chur-police"))
            new Thread(new CreateChurPolice(nc, name, userList)).start();
        else
            System.out.println("-> problem <-");
        
    }
}
