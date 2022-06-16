/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

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
