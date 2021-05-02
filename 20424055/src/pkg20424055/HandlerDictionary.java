/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424055;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 *
 * @author Nam Dinh
 */
public class HandlerDictionary {
    
    ClassDictionary dic;
        
    public void readFileSlang(){
        try {
            String filePath = "G:\\Project\\20424055\\slang.txt";
            RandomAccessFile file = null;
            file = new RandomAccessFile(filePath,"r");
            String str;
            while ((str = file.readLine()) != null) {
               String[] stringSplit = str.split("`");
               if (stringSplit.length < 2) {
                   continue;
               }
                dic.listDic.put(stringSplit[0], stringSplit[1]);
            }
            file.close();
        } catch (IOException e) {
        }
    }
}
