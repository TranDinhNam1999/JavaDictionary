package package_20424055;

import java.util.*;
import java.util.Map.Entry;

public class SlangManager {
    public HashMap<String, String[]> dictionary = new HashMap<>();
    public ArrayList<String> searchHistory = new ArrayList<>();

    public SlangManager(HashMap<String, String[]> dictionary, ArrayList<String> searchHistory) {
        this.dictionary = dictionary;
        this.searchHistory = searchHistory;
    }

    public String[] findWithSlangWord(String word) {
        return this.dictionary.get(word);
    }

    public boolean isContains(String[] arr, String keyword) {
        for (String string : arr) {
            string = string.toLowerCase();
            keyword = keyword.toLowerCase();
            if (string.contains(keyword) || keyword.contains(string))
                return true;
        }
        return false;
    }

    public ArrayList<String> findWithDefinition(String definition) {
        ArrayList<String> words = new ArrayList<>();
        for (Entry<String, String[]> entry : this.dictionary.entrySet()) {
            if (isContains(entry.getValue(), definition))
                words.add(entry.getKey());
        }
        return words;
    }

    public void showWords(ArrayList<String> words) {
        if (words.size() <= 0) {
            System.out.print("Not found.");
            return;
        }
        for (int i = 0; i < words.size(); i++) {

            System.out.println(i + 1 + ": " + words.get(i));
        }
    }

    public void showDefinition(String[] definition) {
        if (definition == null) {
            System.out.print("Not found.");
            return;
        }
        for (int i = 0; i < definition.length; i++) {
            System.out.println(i + 1 + ": " + definition[i].trim());
        }
    }

    public void showHistory() {
        Collections.reverse(searchHistory);
        System.out.println("\n====Recent Searches====");
        for (int i = 0; i < searchHistory.size(); i++) {
            System.out.println((i + 1) + ". " + searchHistory.get(i));
        }
    }

    public void addNew() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a slang word to add: ");
        String word = sc.nextLine();
        System.out.print("Enter definition of this word(split with ','): ");
        String definition = sc.nextLine();
        String[] definitions = definition.split("\\s*,\\s*");
        String[] existWord = this.dictionary.get(word);

        if (existWord == null) {
            this.dictionary.put(word, definitions);
        } else {
            System.out.println("The slang already exists.");
            System.out.println("1. Override");
            System.out.println("2. Dupplicate");
            System.out.print("You choose? ");
            int choose = sc.nextInt();
            if (choose == 1) {
                this.dictionary.put(word, definitions);
            } else if (choose == 2) {
                String[] newDefinitions = new String[existWord.length + definitions.length];
                System.arraycopy(existWord, 0, newDefinitions, 0, existWord.length);
                System.arraycopy(definitions, 0, newDefinitions, existWord.length, definitions.length);
                this.dictionary.put(word, newDefinitions);
            } else {
                System.out.println("There is no such option");
            }
        }
    }

    public void addToHistory(String word) {
        String rs = word + " | " + DateTime.getTimeNow();
        this.searchHistory.add(rs);
    }

    public void delete(String word) {

        if (this.dictionary.get(word) == null) {
            System.out.println("Not found.");
            return;
        }

        System.out.println("Are you sure delete this word?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("You choose? ");
        int chosse = new Scanner(System.in).nextInt();
        if (chosse == 1) {
            if (this.dictionary.remove(word) != null) {
                System.out.println("This word has been deleted");
            } else {
                System.out.println("Somthing is missing. Please try again");
            }
        } else if (chosse == 2) {
            return;
        } else {
            System.out.println("There is no such option");
        }
    }

    public void update() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a slang word to edit: ");
        String word = sc.nextLine();
        String[] existWord = this.dictionary.get(word);

        if (existWord == null) {
            System.out.println("Not found");
        } else {
            System.out.print("Enter new definition of this word(split with ','): ");
            String definition = sc.nextLine();
            String[] definitions = definition.split("\\s*,\\s*");
            this.dictionary.put(word, definitions);
            System.out.println("Update successfully");
        }
    }

    public String[] randomSlang() {
        Random random = new Random();
        ArrayList<String> keys = new ArrayList<String>(this.dictionary.keySet());
        String word = keys.get(random.nextInt(keys.size()));
        String[] definitions = this.dictionary.get(word);
        String[] rs = { word, String.join(",", definitions) };
        return rs;
    }

    public void slangQuiz() {
        String[] question = randomSlang();
        ArrayList<String> answers = new ArrayList<String>();
        answers.add(question[1]);
        int idx = 0;
        while (idx < 3) {
            String[] newRandom = randomSlang();
            while (answers.contains(newRandom[1])) {
                newRandom = randomSlang();
            }
            answers.add(newRandom[1]);
            idx++;
        }
        Collections.shuffle(answers);
        System.out.println("Definition of [" + question[0] + "]: ");
        for (int i = 0; i < answers.size(); i++) {
            System.out.println((i + 1) + ". " + answers.get(i));
        }

        System.out.print("Enter your answer: ");
        int ans = new Scanner(System.in).nextInt();

        if (ans <= 4 && ans > 0 && answers.get(ans - 1).equals(question[1])) {
            System.out.println("(^_^)/ Congratulations!!");
        } else {
            System.out.println("(-_-) Incorrect");
        }

    }

    public void definitionQuiz() {
        String[] question = randomSlang();
        ArrayList<String> answers = new ArrayList<String>();
        answers.add(question[0]);
        int idx = 0;
        while (idx < 3) {
            String[] newRandom = randomSlang();
            while (answers.contains(newRandom[0])) {
                newRandom = randomSlang();
            }
            answers.add(newRandom[0]);
            idx++;
        }
        Collections.shuffle(answers);
        System.out.println("Slang word of [" + question[1] + "]: ");
        for (int i = 0; i < answers.size(); i++) {
            System.out.println((i + 1) + ". " + answers.get(i));
        }

        System.out.print("Enter your answer: ");
        int ans = new Scanner(System.in).nextInt();

        if (ans <= 4 && ans > 0 && answers.get(ans - 1).equals(question[0])) {
            System.out.println("(^_^)/ Congratulations!!");
        } else {
            System.out.println("(-_-) Incorrect");
        }

    }
}
