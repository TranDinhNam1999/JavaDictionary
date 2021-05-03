package pkg20424055;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Nam Dinh
 */
public class HandlerDictionary {
    HashMap<String, String> listDict;
    String history = "";
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public HandlerDictionary() {
        this.listDict = new HashMap<String, String>();
    }

    public HashMap<String, String> getListDict() {
        return listDict;
    }
    public void setListDict(HashMap<String, String> listEmoji) {
        this.listDict = listEmoji;
    }
    
    @Override
    public String toString() {
        varObject ref = new varObject();
        listDict.entrySet().forEach(entry -> {
            ref.result += entry.getKey() + '`' + entry.getValue() + '\n';
        });
        return ref.result;
    }

    public void searchByWord() throws IOException {
        while (true) {
            System.out.print("\nVui long nhap slang word key can tim (Nhap 0 de dung tim kiem): ");
            String key = reader.readLine();

            if (key.equals("0")) {
                System.out.printf("Dang chuyen huong ve menu\n");
                break;
            }

            String wordKey = key;
            String wordValue = listDict.get(key);

            if (wordValue == null) {
                System.out.printf("Khong tim thay ket qua cho key: %s\n", wordKey);
            }
            else {
                System.out.printf("Ket qua: %s - %s\n", wordKey,  wordValue);
            }

            saveWordHistory(wordKey, wordValue);
            saveSlangWordHistoryToFile();
        }
    }


    void saveWordHistory(String key, String value) throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String newItem = key + " _____ " + value + " _____ " + formatter.format(date) + "\n";
        history += newItem;
    }

    void saveSlangWordHistoryToFile() throws FileNotFoundException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dataPath() + "history.txt"), "utf-8"))) {
            writer.write(history);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readSlangWordHistory() {
        try {
            RandomAccessFile file = new RandomAccessFile(dataPath() + "history.txt", "r");
            String str;
            while ((str = file.readLine()) != null) {
                String[] stringSplit = str.split(" _____ ");
                if (stringSplit.length < 3) {
                    continue;
                }

                history += stringSplit[0] + " _____ " + stringSplit[1] + " _____ " + stringSplit[2] + "\n";
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    void definitionSlangWordQuestion() throws IOException {
        Object[] keyList = listDict.keySet().toArray();
        Object rKey1 = keyList[new Random().nextInt(keyList.length)];
        Object rKey2 = keyList[new Random().nextInt(keyList.length)];
        Object rKey3 = keyList[new Random().nextInt(keyList.length)];
        Object rKey4 = keyList[new Random().nextInt(keyList.length)];

        String[] listKeys = new String[]{rKey1.toString(), rKey2.toString(), rKey3.toString(), rKey4.toString()};
        int rtemp = new Random().nextInt(listKeys.length);
        System.out.println("Chon gia tri cho slang word: " + listKeys[rtemp]);

        System.out.println("1, " + listDict.get(rKey1));
        System.out.println("2, " + listDict.get(rKey2));
        System.out.println("3, " + listDict.get(rKey3));
        System.out.println("4, " + listDict.get(rKey4));

        System.out.print("Nhap lua chon cua ban: ");
        String answer = reader.readLine();

        switch (answer) {
            case "1":
                answer = rKey1.toString();
                break;
            case "2":
                answer = rKey2.toString();
                break;
            case "3":
                answer = rKey3.toString();
                break;
            case "4":
                answer = rKey4.toString();
                break;
        }

        if (answer == listKeys[rtemp]) {
            System.out.println("Chinh xac!");
        } else {
            System.out.println("Sai roi!");
        }
    }

    void showHistory() {
        history = "";
        readSlangWordHistory();
        System.out.print(history);
    }

    void addSlangWords() throws IOException {
        while (true) {
            System.out.print("Nhap slang word moi (nhap (0) de dung lai): ");
            String key = reader.readLine();

            if (key.equals("0")) break;

            if (listDict.containsKey(key)) {
                System.out.println("\nSlang word da ton tai voi definition _ " + listDict.get(key) + " \n - nhap (0) de dung \n - nhap (1) de duplicate \n - nhap (2) de overwrite \n");
                
                System.out.print(" Nhap lua chon cua ban: ");
                String chose = reader.readLine();
                
                if (chose.equals("0")) {
                    System.out.println("\nThoat...");
                }
                else if (chose.equals("1")) {
                    System.out.print(" Nhap definition cho slang word _ " + key + ": ");
                    String value = reader.readLine();
                    listDict.put(key + "_duplicated", value);
                    System.out.println("Da duplicate slangword");
                }
                else if (chose.equals("2")) {
                    System.out.print(" Nhap definition cho slang word _ " + key + ": ");
                    String value = reader.readLine();
                    listDict.put(key, value);
                    System.out.println("Da overwrite slangword");
                }
            }
            else {
                System.out.print("\n Nhap definition cho slang word _ " + key + ": ");
                String value = reader.readLine();
                listDict.put(key, value);
                System.out.println("Da nhap thanh cong\n");
            }

            saveNewListSlangWord();
        }
    }

    void saveNewListSlangWord() {
        String newList = this.toString();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dataPath() + "slang.txt"), "utf-8"))) {
            writer.write(newList);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void clearFile(String fileUrl) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileUrl), "utf-8"))) {
            writer.write("");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void deleteSlangWord () throws IOException {
        while (true) {
            System.out.print("Nhap slang word muon delete (nhap 0 de dung): ");
            
            String key = reader.readLine();

            if (key.equals("0")) break;

            if (!listDict.containsKey(key)) {
                System.out.println("Khong tim thay slang word, vui long nhap lai");
                continue;
            }

            System.out.print("Ban co chac muon xoa (y/n): ");
            String isConfirm = reader.readLine();
            if (isConfirm.equals("y")) {
                listDict.remove(key);
                saveNewListSlangWord();
                System.out.println("Xoa thanh cong");
                continue;
            }
        }
    }

    
    void editSlangWord() throws IOException {
        while (true) {
            System.out.print("Nhap slang word muon edit (nhap 0 de dung): ");
            
            String key = reader.readLine();

            if (key.equals("0")) break;

            if (!listDict.containsKey(key)) {
                System.out.println("Khong tim thay slang word, vui long nhap lai");
                continue;
            }

            System.out.print("Nhap definition: ");
            String newValue = reader.readLine();
            listDict.put(key, newValue);
            saveNewListSlangWord();
            System.out.println("Edit thanh cong !!");
            continue;
        }
    }

    void randomSlang() {
        Object[] keyList = listDict.keySet().toArray();
        Object randomKey = keyList[new Random().nextInt(keyList.length)];
        System.out.println(randomKey + " _____ " + listDict.get(randomKey));
    }


    void slangwordQuestion() throws IOException {
        Object[] keyList = listDict.keySet().toArray();
        Object rKey1 = keyList[new Random().nextInt(keyList.length)];
        Object rKey2 = keyList[new Random().nextInt(keyList.length)];
        Object rKey3 = keyList[new Random().nextInt(keyList.length)];
        Object rKey4 = keyList[new Random().nextInt(keyList.length)];

        String[] listKeys = new String[]{rKey1.toString(), rKey2.toString(), rKey3.toString(), rKey4.toString()};
        int rnd = new Random().nextInt(listKeys.length);
        System.out.println("Chon slang word cua definition: " + listDict.get(listKeys[rnd]));

        System.out.println("a, " + rKey1);
        System.out.println("b, " + rKey2);
        System.out.println("c, " + rKey3);
        System.out.println("d, " + rKey4);

        System.out.print("Nhap lua chon cua ban: ");
        String answer = reader.readLine();

        switch (answer) {
            case "a":
                answer = rKey1.toString();
                break;
            case "b":
                answer = rKey2.toString();
                break;
            case "c":
                answer = rKey3.toString();
                break;
            case "d":
                answer = rKey4.toString();
                break;
        }

        if (answer == listKeys[rnd]) {
            System.out.println("Chinh xac !!!");
        } else {
            System.out.println("Sai roi :(((");
        }
    }
    
    public String dataPath() {
        String dataPath = "G:\\Project\\20424055\\";
        return  dataPath;
    }

    public void readSlangFile() {
        try {
            RandomAccessFile file = new RandomAccessFile("G:\\Project\\20424055\\slang.txt", "r");
            String str;
            while ((str = file.readLine()) != null) {
               String[] stringSplit = str.split("`");
               if (stringSplit.length < 2) {
                   continue;
               }
                listDict.put(stringSplit[0], stringSplit[1]);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        readSlangWordHistory();
    }

    void reset() {
        listDict.clear();
        clearFile(dataPath() + "slang.txt");

        try {
            RandomAccessFile file = new RandomAccessFile(dataPath() + "slang_backup.txt", "r");
            String str;
            while ((str = file.readLine()) != null) {
                String[] stringSplit = str.split("`");
                if (stringSplit.length < 2) {
                    continue;
                }
                listDict.put(stringSplit[0], stringSplit[1]);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveNewListSlangWord();
        System.out.println("Da reset thanh cong !");
    }
}
