package Server;

import java.util.Vector;

public class UserList{

    static private Vector<User> q = new Vector<>();
    boolean isWorking = false;

    private void refresh(){
        Vector<Integer> to_remove = new Vector<>();
        for(int i=q.size()-1 ; i>-1 ; i--)
            if( q.elementAt(i).isOnline()==false )
                to_remove.add(i);

        for(int i : to_remove)
            q.remove(i);
    }

    synchronized protected Vector<User> getUsers(User user, int n){

        while(isWorking){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isWorking = true;
        q.add(user);
        Vector<User> users = new Vector<>();
        refresh();

        if(q.size()==n){
            for(User u : q)
                users.add(u);
        }
        isWorking = false;
        notifyAll();
        return users;
    }
}