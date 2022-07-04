package package_20424055;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    String resetPath, dataPath, searchHistoryPath;

    FileManager() {
        String sourceFolder = System.getProperty("user.dir");
        this.resetPath = sourceFolder + "/data/backup.txt";
        this.dataPath = sourceFolder + "/data/data.txt";
        this.searchHistoryPath = sourceFolder + "/data/search-history.txt";
    }

    public boolean isValidFile(File file) {
        if (!file.isDirectory())
            return true;
        return false;
    }

    public HashMap<String, String[]> getDictionary() {
        HashMap<String, String[]> dictionary = new HashMap<String, String[]>();

        File file = new File(this.dataPath);
        if (isValidFile(file)) {
            try {
                FileReader fis = new FileReader(file, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(fis);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] splitLine = line.split("`");
                    if (splitLine.length >= 2) {
                        String word = splitLine[0];
                        String[] definition = splitLine[1].split("\\s*\\|\\s*");
                        dictionary.put(word, definition);
                    }

                }
                br.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.print("The path is not valid");
        }

        return dictionary;
    }

    public HashMap<String, String[]> getDictionary(String Path) {
        HashMap<String, String[]> dictionary = new HashMap<String, String[]>();

        File file = new File(Path);
        if (isValidFile(file)) {
            try {
                FileReader fis = new FileReader(file);
                BufferedReader br = new BufferedReader(fis);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] splitLine = line.split("`");
                    if (splitLine.length >= 2) {
                        String word = splitLine[0];
                        String[] definition = splitLine[1].split("\\s*\\|\\s*");
                        dictionary.put(word, definition);
                    }

                }
                br.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.print("The path is not valid");
        }

        return dictionary;
    }

    public ArrayList<String> getHistory() {
        ArrayList<String> histories = new ArrayList<String>();

        File file = new File(this.searchHistoryPath);
        if (isValidFile(file)) {
            try {
                FileReader fis = new FileReader(file);
                BufferedReader br = new BufferedReader(fis);
                String line;
                while ((line = br.readLine()) != null) {
                    histories.add(line);
                }
                br.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.print("The search history path is not valid");
        }

        return histories;
    }

    public HashMap<String, String[]> resetDictionary() {
        return this.getDictionary(this.resetPath);
    }

    public boolean saveDictionary(HashMap<String, String[]> dictionary) {
        File file = new File(this.dataPath);
        if (isValidFile(file)) {

            try {
                if (file.delete()) {
                    file.createNewFile();
                }
                FileWriter fr = new FileWriter(file, StandardCharsets.UTF_8, true);
                BufferedWriter bw = new BufferedWriter(fr);
                for (Map.Entry<String, String[]> entry : dictionary.entrySet()) {
                    String key = entry.getKey();
                    String value = String.join("|", entry.getValue());
                    String line = key + "`" + value + System.lineSeparator();
                    bw.write(line);
                }
                bw.close();
                fr.close();
                return true;
            } catch (Exception ex) {

            }
        }
        return false;
    }

    public boolean saveHistory(ArrayList<String> histories) {
        File file = new File(this.searchHistoryPath);
        if (isValidFile(file)) {
            try {
                if (file.delete()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8, true);
                BufferedWriter bw = new BufferedWriter(fw);
                for (String search : histories) {
                    bw.write(search + System.lineSeparator());
                }
                bw.close();
                fw.close();
                return true;
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public void clearFile(String Path) {
        try {
            FileWriter fw = new FileWriter(Path, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception ex) {
        }
    }

    public void clearHistory() {
        this.clearFile(this.searchHistoryPath);
    }
}
