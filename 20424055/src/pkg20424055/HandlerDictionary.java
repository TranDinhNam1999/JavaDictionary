/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    HashMap<String, String> listEmoji = new HashMap<String, String>();
    String history = "";
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public HashMap<String, String> getListEmoji() {
        return listEmoji;
    }
    public void setListEmoji(HashMap<String, String> listEmoji) {
        this.listEmoji = listEmoji;
    }
    
    @Override
    public String toString() {
        var ref = new var();
        listEmoji.entrySet().forEach(entry -> {
            ref.result += entry.getKey() + '`' + entry.getValue() + '\n';
        });
        return ref.result;
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
                listEmoji.put(stringSplit[0], stringSplit[1]);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        readHistory();
    }

    public void searchSlangWord() throws IOException {
        while (true) {
            System.out.print("\nVui long nhap slang word key can tim (Nhap 0 de dung tim kiem): ");
            String key = reader.readLine();

            if (key.equals("0")) {
                System.out.printf("Dang chuyen huong ve menu\n");
                break;
            }

            String slangWordKey = key;
            String slangWordValue = listEmoji.get(key);

            if (slangWordValue == null) {
                System.out.printf("Khong tim thay ket qua cho key: %s\n", slangWordKey);
            }
            else {
                System.out.printf("Ket qua: %s - %s\n", slangWordKey,  slangWordValue);
            }

            saveHistory(slangWordKey, slangWordValue);
            saveHistoryToFile();
        }
    }


    public void searchKey() throws IOException {
        while (true) {
            System.out.print("\nVui long nhap defination can tim (Nhap 0 de dung tim kiem): ");
            String definition = reader.readLine();

            if (definition.equals("0")) {
                System.out.printf("Dang chuyen huong ve menu\n");
                break;
            }

            String key = null;
            for(Map.Entry<String, String> entry: listEmoji.entrySet()) {
                if(entry.getValue().contains(definition)) {
                    System.out.printf("Ket qua: %s - %s\n", definition,  entry.getKey());
                    key = "";
                }
            }

            if (key == null) {
                System.out.printf("Khong tim thay ket qua cho definition: %s\n", definition);
            }
        }
    }

    void saveHistory(String key, String value) throws IOException {
        // save new word to history:
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String newItem = key + " _____ " + value + " _____ " + formatter.format(date) + "\n";
        history += newItem;
    }

    void saveHistoryToFile() throws FileNotFoundException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dataPath() + "history.txt"), "utf-8"))) {
            writer.write(history);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readHistory() {
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

    void showHistory() {
        history = "";
        readHistory();
        System.out.print(history);
    }

    void addSlangWord() throws IOException {
        while (true) {
            System.out.print("Nhap slang word moi (nhap (0) de dung): ");
            String key = reader.readLine();

            if (key.equals("0")) break;

            if (listEmoji.containsKey(key)) {
                System.out.println("\nSlang word da ton tai voi definition _ " + listEmoji.get(key) + " \n - nhap (0) de dung \n - nhap (1) de duplicate \n - nhap (2) de overwrite \n");
                System.out.print(" Nhap lua chon cua ban: ");
                String chose = reader.readLine();
                if (chose.equals("0")) {
                    System.out.println("\nThoat...");
                }
                else if (chose.equals("1")) {
                    System.out.print(" Nhap definition cho slang word _ " + key + ": ");
                    String value = reader.readLine();
                    listEmoji.put(key + "_duplicated", value);
                    System.out.println("Da duplicate slangword");
                }
                else if (chose.equals("2")) {
                    System.out.print(" Nhap definition cho slang word _ " + key + ": ");
                    String value = reader.readLine();
                    listEmoji.put(key, value);
                    System.out.println("Da overwrite slangword");
                }
            }
            else {
                System.out.print("\n Nhap definition cho slang word _ " + key + ": ");
                String value = reader.readLine();
                listEmoji.put(key, value);
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

            if (!listEmoji.containsKey(key)) {
                System.out.println("Khong tim thay slang word, vui long nhap lai");
                continue;
            }

            System.out.print("Ban co chac muon xoa (y/n): ");
            String isConfirm = reader.readLine();
            if (isConfirm.equals("y")) {
                listEmoji.remove(key);
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

            if (!listEmoji.containsKey(key)) {
                System.out.println("Khong tim thay slang word, vui long nhap lai");
                continue;
            }

            System.out.print("Nhap definition: ");
            String newValue = reader.readLine();
            listEmoji.put(key, newValue);
            saveNewListSlangWord();
            System.out.println("Edit thanh cong !!");
            continue;
        }
    }

    void reset() {
        listEmoji.clear();
        clearFile(dataPath() + "slang.txt");

        try {
            RandomAccessFile file = new RandomAccessFile(dataPath() + "slang_backup.txt", "r");
            String str;
            while ((str = file.readLine()) != null) {
                String[] stringSplit = str.split("`");
                if (stringSplit.length < 2) {
                    continue;
                }
                listEmoji.put(stringSplit[0], stringSplit[1]);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveNewListSlangWord();
        System.out.println("Da reset thanh cong !");
    }

    void randomSlang() {
        Object[] keyList = listEmoji.keySet().toArray();
        Object randomKey = keyList[new Random().nextInt(keyList.length)];
        System.out.println(randomKey + " _____ " + listEmoji.get(randomKey));
    }

    void definitionQuestion() throws IOException {
        Object[] keyList = listEmoji.keySet().toArray();
        Object randomKey1 = keyList[new Random().nextInt(keyList.length)];
        Object randomKey2 = keyList[new Random().nextInt(keyList.length)];
        Object randomKey3 = keyList[new Random().nextInt(keyList.length)];
        Object randomKey4 = keyList[new Random().nextInt(keyList.length)];

        String[] listKeys = new String[]{randomKey1.toString(), randomKey2.toString(), randomKey3.toString(), randomKey4.toString()};
        int rnd = new Random().nextInt(listKeys.length);
        System.out.println("Chon gia tri cho slang word: " + listKeys[rnd]);

        System.out.println("a, " + listEmoji.get(randomKey1));
        System.out.println("b, " + listEmoji.get(randomKey2));
        System.out.println("c, " + listEmoji.get(randomKey3));
        System.out.println("d, " + listEmoji.get(randomKey4));

        System.out.print("Nhap lua chon cua ban: ");
        String answer = reader.readLine();

        switch (answer) {
            case "a":
                answer = randomKey1.toString();
                break;
            case "b":
                answer = randomKey2.toString();
                break;
            case "c":
                answer = randomKey3.toString();
                break;
            case "d":
                answer = randomKey4.toString();
                break;
        }

        if (answer == listKeys[rnd]) {
            System.out.println("Chinh xac !!!");
        } else {
            System.out.println("Sai roi :(((");
        }
    }

    void slangwordQuestion() throws IOException {
        Object[] keyList = listEmoji.keySet().toArray();
        Object randomKey1 = keyList[new Random().nextInt(keyList.length)];
        Object randomKey2 = keyList[new Random().nextInt(keyList.length)];
        Object randomKey3 = keyList[new Random().nextInt(keyList.length)];
        Object randomKey4 = keyList[new Random().nextInt(keyList.length)];

        String[] listKeys = new String[]{randomKey1.toString(), randomKey2.toString(), randomKey3.toString(), randomKey4.toString()};
        int rnd = new Random().nextInt(listKeys.length);
        System.out.println("Chon slang word cua definition: " + listEmoji.get(listKeys[rnd]));

        System.out.println("a, " + randomKey1);
        System.out.println("b, " + randomKey2);
        System.out.println("c, " + randomKey3);
        System.out.println("d, " + randomKey4);

        System.out.print("Nhap lua chon cua ban: ");
        String answer = reader.readLine();

        switch (answer) {
            case "a":
                answer = randomKey1.toString();
                break;
            case "b":
                answer = randomKey2.toString();
                break;
            case "c":
                answer = randomKey3.toString();
                break;
            case "d":
                answer = randomKey4.toString();
                break;
        }

        if (answer == listKeys[rnd]) {
            System.out.println("Chinh xac !!!");
        } else {
            System.out.println("Sai roi :(((");
        }
    }
}
