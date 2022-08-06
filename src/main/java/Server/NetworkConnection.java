package Server;

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

    public boolean write(Object obj) {
        try {
            oos.writeObject(obj);
            return true;
        } catch (IOException ex) {
            System.out.println("Failed to write");
            return false;
        }
    }

    public Object read() {
        Object obj;
        try {
            obj = ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Failed to read");
            return null;
        }
        return obj;
    }

    public boolean sendString(String s) {
        Data data = new Data();
        data.msg = s;
        return write(data);
    }

    public String recieveString() {
        Data data = (Data) read();
        if (data == null) {
            System.out.println("couldn't recieve string");
            return "";
        }
        return data.msg;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("couldn't close the socket");
            e.printStackTrace();
        }
    }

    boolean isConnected() {
        return socket.isConnected();
    }
}
