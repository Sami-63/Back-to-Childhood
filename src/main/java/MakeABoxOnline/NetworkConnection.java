/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MakeABoxOnline;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author As-Sami
 */
public class NetworkConnection {
    Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public NetworkConnection(Socket socket) throws IOException {
        this.socket = socket;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }
    
    public void write(Object obj){
        try {
            oos.writeObject(obj);
        } catch (IOException ex) {
            System.out.println("Failed to write");
        }
    }

    public Object read(){
        Object obj;
        try {
            obj = ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Failed to read");
            return null;
        }
        return obj;
    }

    public void sendString(String s){
        Data data = new Data();
        data.msg = s;
        write(data);
    }

    public String recieveString(){
        Data data = (Data) read();
        return data.msg;
    }
}
