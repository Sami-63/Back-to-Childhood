/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MakeABoxOnline;

/**
 *
 * @author As-Sami
 */
public class User {
    String username, opponent;
    NetworkConnection nc;

    public User(NetworkConnection nc, String username) {
        this.nc = nc;
        this.username = username;
    }
    
    public User(NetworkConnection nc, String username, String opponent) {
        this.nc = nc;
        this.username = username;
        this.opponent = opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getOpponent() {
        return opponent;
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
}
