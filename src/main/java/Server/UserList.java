package Server;

import java.util.Vector;

public class UserList{

    static private Vector<Pair> q;
    boolean isWorking;

    public UserList(){
        q = new Vector<>();
        isWorking = false;
    }

    // private void refresh(){
    //     Vector<Integer> to_remove = new Vector<>();
    //     for(int i=q.size()-1 ; i>-1 ; i--)
    //         if( q.elementAt(i).isOnline()==false )
    //             to_remove.add(i);

    //     for(int i : to_remove)
    //         q.remove(i);

    //     System.out.println("=========================");
    //     System.out.println(this);
    //     for(User u : q)
    //         System.out.println("usename :" + u.username);
    //     System.out.println("=========================");
    // }

    synchronized protected Vector<User> getUsers(User user, int n, String game){

        while(isWorking){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isWorking = true;
        q.add(new Pair(user, game));
        // refresh(); // is online doesn't work
        Vector<Pair> ret = new Vector<>();
        
        for(Pair p : q){
            if( p.gameName.equals(game) ){
                ret.add(p);
            }
        }
        
        Vector<User> users = new Vector<>();
        if(ret.size()==n){
            for(Pair p : ret){
                users.add(p.user);
                q.remove(p);
            }
        }
        
        isWorking = false;
        notifyAll();
        return users;
    }

    // public void print() {
    //     while(isWorking){
    //         try {
    //             wait();
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }

    //     isWorking = true;
        
    //     isWorking = false;
    //     notifyAll();
    // }

    private class Pair{
        User user;
        String gameName;
        
        public Pair(User user, String gameName) {
            this.user = user;
            this.gameName = gameName;
        }
    }
}