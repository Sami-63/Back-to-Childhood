package Server;

import java.io.Serializable;

/**
 *
 * @author As-Sami
 */
public class Data implements Serializable, Cloneable{
    
    public String msg;
    
    @Override
    public Object clone()throws CloneNotSupportedException{  
        return super.clone();
    } 
}