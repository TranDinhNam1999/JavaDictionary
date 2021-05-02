/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424055;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Nam Dinh
 */
public class AlthorymeDictionary {
    Stack<String>[] slangWord;
    Stack<String>[] definition;
    Stack<String> history;
    static final int MaxIndex=209953;
    AlthorymeDictionary(){
    	history=new Stack();
    	slangWord=new Stack[MaxIndex];
    	definition=new Stack[MaxIndex];
        //List=new String[MaxIndex];
        try {
          File myObj = new File("slang.txt");
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] tmp=data.split("`");
            if(tmp.length!=2) continue;
            try {
            	slangWord[HashFunction(tmp[0],MaxIndex)]=new Stack<String>(); 
            	slangWord[HashFunction(tmp[0],MaxIndex)].push(tmp[1]);
            	definition[HashFunction(tmp[1],MaxIndex)]=new Stack<String>(); 
            	definition[HashFunction(tmp[1],MaxIndex)].push(tmp[0]);
            }
            catch(Exception e) {
            	e.printStackTrace();
            }
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
    }
    public String searchBySlangWord(String s){
    	history.push(s);
    	try {
    		return slangWord[HashFunction(s,MaxIndex)].peek();
    	}
        catch(Exception e) {
        	return "NONE";
        }
    }
    public String searchByDefinition(String s){
    	try {
            return definition[HashFunction(s,MaxIndex)].peek();
    	}
        catch(Exception e) {
        	return "NONE";
        }
    }
    public String[] getHistory() {
    	return (String[]) history.toArray();
    }
    public void addSlangWord(String s,String d) {
    	slangWord[HashFunction(s,MaxIndex)]=new Stack<String>(); 
    	slangWord[HashFunction(s,MaxIndex)].push(d);
    	definition[HashFunction(d,MaxIndex)]=new Stack<String>(); 
    	definition[HashFunction(d,MaxIndex)].push(s);
    }
    public void Overwrite(String s,String d) {
    	slangWord[HashFunction(s,MaxIndex)].pop();
    	slangWord[HashFunction(s,MaxIndex)].push(d);
    	definition[HashFunction(d,MaxIndex)].pop();
    	definition[HashFunction(d,MaxIndex)].push(s);
    }
    public void Duplicate(String s,String d) {
    	slangWord[HashFunction(s,MaxIndex)].push(d);
    	definition[HashFunction(d,MaxIndex)]=new Stack<String>(); 
    	definition[HashFunction(d,MaxIndex)].push(d);
    }
    private int HashFunction(String x, int M){
	    char ch[];
	    ch = x.toCharArray();
	    int i, sum;
	    for (sum=0, i=0; i < x.length(); i++)
	      sum += ch[i]*(i+1);
	    return sum % M;
    }
}
