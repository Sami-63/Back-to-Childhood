package Server;

/**
 *
 * @author As-Sami
 */
public class User {
    public String username;
    NetworkConnection nc;

    public User(NetworkConnection nc, String username) {
        this.nc = nc;
        this.username = username;
    }
    
    public void send(String s){
        Data data = new Data();
        data.msg = s;

        nc.write(data);
    }

    public Data recieve() {
        Data data = (Data) nc.read();

        return data;
    }

    public boolean isOnline(){
        return nc.isConnected();
    }
}