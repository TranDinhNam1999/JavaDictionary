package package_20424055;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class ProjectManager {
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
        }
    }

    public static void showMenu(SlangManager sm, FileManager fm) {

        char y = 'y';
        Instant start, end = null;
        Duration timeElapsed = null;
        Scanner scanner = new Scanner(System.in);
        while (y == 'y') {
            clearConsole();
            System.out.println("===========MENU=================");
            System.out.println("1. Find word with slang word");
            System.out.println("2. Find word with definition");
            System.out.println("3. Show History");
            System.out.println("4. Add slang word");
            System.out.println("5. Delete slang word");
            System.out.println("6. Update slang word");
            System.out.println("7. Reset to default");
            System.out.println("8. Random slang word");
            System.out.println("9. Slang word quiz");
            System.out.println("10. Definition quiz");
            System.out.println("0. Exit");
            System.out.println("================================");
            System.out.print("You chosse?  ");

            int chosse = scanner.nextInt();
            scanner.nextLine();
            if (chosse == 0) {
                break;
            }
            switch (chosse) {
                case 1:
                    System.out.print("Enter the search word: ");

                    String word = scanner.nextLine();
                    start = Instant.now();

                    sm.showDefinition(sm.findWithSlangWord(word));

                    end = Instant.now();
                    timeElapsed = Duration.between(start, end);
                    System.out.println("\nTime taken: " + timeElapsed.toMillis() + " milliseconds");

                    sm.addToHistory(word);
                    break;
                case 2:
                    System.out.print("Enter the definition to search: ");
                    String definition = scanner.nextLine();
                    start = Instant.now();
                    sm.showWords(sm.findWithDefinition(definition));
                    end = Instant.now();
                    timeElapsed = Duration.between(start, end);
                    System.out.println("\nTime taken: " + timeElapsed.toMillis() + " milliseconds");
                    sm.addToHistory(definition);
                    break;
                case 3:
                    sm.showHistory();
                    break;
                case 4:
                    sm.addNew();
                    break;
                case 5:
                    System.out.print("\nEnter slang word to delete: ");
                    String deleteWord = scanner.nextLine();
                    sm.delete(deleteWord);
                    break;
                case 6:
                    sm.update();
                    break;
                case 7:
                    System.out.println("Please wait for the default restore process...");
                    sm.dictionary = fm.resetDictionary();
                    System.out.println("Default restore is done");
                    break;
                case 8:
                    System.out.println("On this day slang word:" + sm.randomSlang()[0]);
                    break;
                case 9:
                    sm.slangQuiz();
                    break;
                case 10:
                    sm.definitionQuiz();
                    break;
                default:
                    break;
            }
            System.out.print("\nPress 'y' to continue or press any key to end program: ");
            y = scanner.next().charAt(0);
        }

        System.out.print("\nDo you want to save the current dictionary: ");
        char saveDictionary = Character.toLowerCase(scanner.next().charAt(0));
        if (saveDictionary == 'y' && fm.saveDictionary(sm.dictionary)) {
            System.out.println("Saved current dictionary");
            ;
        }

        System.out.print("\nDo you want to save the search history: ");
        char saveHistory = scanner.next().charAt(0);
        if (saveHistory == 'y' && fm.saveHistory(sm.searchHistory)) {
            System.out.println("Saved history");
            ;
        }

        System.out.print("\nDo you want to remove search history: ");
        char clearHistory = scanner.next().charAt(0);
        if (clearHistory == 'y') {
            fm.clearHistory();
            System.out.println("Removed history");
        }
    }
}
