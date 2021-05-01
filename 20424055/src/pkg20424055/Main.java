/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424055;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Nam Dinh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Initializing a Dictionary 
        Dictionary edu = new Hashtable(); 
       // put() method 
        edu.put("1000", "Edureka"); 
        edu.put("2000", "Platfrom"); 
       // elements() method : 
        for (Enumeration i = edu.elements(); i.hasMoreElements();) 
        { 
            System.out.println("Value in Dictionary : " + i.nextElement()); 
        } 
        // get() method : 
        System.out.println("nValue at key = 3000 : " + edu.get("2000")); 
        System.out.println("Value at key = 1000 : " + edu.get("2000")); 
        // isEmpty() method : 
        System.out.println("nThere is no key-value pair : " + edu.isEmpty() + "n"); 
        // keys() method : 
        for (Enumeration k = edu.keys(); k.hasMoreElements();) 
        { 
            System.out.println("Keys in Dictionary : " + k.nextElement()); 
        } 
       // remove() method : 
        System.out.println("nRemove : " + edu.remove("1000")); 
        System.out.println("Check the value of removed key : " + edu.get("1000")); 
        System.out.println("nSize of Dictionary : " + edu.size()); 
    }
    
}
