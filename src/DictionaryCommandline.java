import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class DictionaryCommandline {
    private final static int MAX_INDEX_DIGITS = 5; // Format up to 3 digits
    private final static int MAX_LEN_WORD = 45; // Longest English word length in a major dictionary = 45
    private final static int MAX_CMD_LENGTH = 60; // Cmd window length
    private final DictionaryManagement dictionaryManagement;
    private final Scanner cmdScanner;

    public DictionaryCommandline() {
        dictionaryManagement = new DictionaryManagement();
        this.cmdScanner = new Scanner(System.in);
        cleanCmdWindow();
    }

    public void importFromFile() {
        do {
            try {
                System.out.print("Type 1 if you want to use your resource, else 0: ");
                String response = cmdScanner.nextLine().trim();
                Integer numresponse = Integer.parseInt(response);
                if (numresponse == 1) {
                    System.out.print("Type your address: ");
                    response = cmdScanner.nextLine().trim();
                    boolean status = dictionaryManagement.insertFromFile(response);
                    if (!status) {
                        System.out.println("Your file address doesn't exist !");
                    } else {
                        System.out.println("Successfully imported !");
                    }
                    dictionaryManagement.getDictionary().validateDictionary();
                    break;
                } else if (numresponse == 0) {
                    dictionaryManagement.insertFromFile();
                    System.out.println("Successfully imported !");
                    break;
                } else {
                    System.out.println("You type the wrong format");
                    try {
                        Thread.sleep(1500);
                        cleanCmdWindow();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException e) {
                continue;
            }
        } while (true);
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public void showAllWords() {
        List<Word> listOfWords = dictionaryManagement.getDictionary().getListOfWords();

        System.out.printf("%-" + MAX_INDEX_DIGITS + "s", "No");
        System.out.print(" | ");
        System.out.printf("%-" + MAX_LEN_WORD + "s", "English");
        System.out.print(" | ");
        System.out.println("Vietnamese");
        int i = 1;

        for (Word word : listOfWords) {
            System.out.printf("%-" + MAX_INDEX_DIGITS + "s", i++);
            System.out.print(" | ");
            System.out.printf("%-" + MAX_LEN_WORD + "s", word.getWord_target());
            System.out.print(" | ");

            String word_explain = word.getWord_explain();
            int limit = word_explain.length();
            int index = 0;
            while (index < limit) {

                int start = index;
                int end = start + MAX_CMD_LENGTH > limit ? limit : start + MAX_CMD_LENGTH;
                if (end != limit)
                    while (word_explain.charAt(--end) != ' ') ;
                String word_explain_display = word_explain.substring(start, end);
                System.out.println(word_explain_display);

                if (end != limit) {
                    System.out.printf("%-" + MAX_INDEX_DIGITS + "s", " ");
                    System.out.print(" | ");
                    System.out.printf("%-" + MAX_LEN_WORD + "s", " ");
                    System.out.print(" | ");
                }

                index += end - start;
            }
        }
    }

    private void showWord(Word word) {
        System.out.printf("%-" + MAX_INDEX_DIGITS + "s", "No");
        System.out.print(" | ");
        System.out.printf("%-" + MAX_LEN_WORD + "s", "English");
        System.out.print(" | ");
        System.out.println("Vietnamese");

        System.out.printf("%-" + MAX_INDEX_DIGITS + "s", 1);
        System.out.print(" | ");
        System.out.printf("%-" + MAX_LEN_WORD + "s", word.getWord_target());
        System.out.print(" | ");

        String word_explain = word.getWord_explain();
        int limit = word_explain.length();
        int index = 0;
        while (index < limit) {

            int start = index;
            int end = start + MAX_CMD_LENGTH > limit ? limit : start + MAX_CMD_LENGTH;
            if (end != limit)
                while (word_explain.charAt(--end) != ' ') ;
            String word_explain_display = word_explain.substring(start, end);
            System.out.println(word_explain_display);

            if (end != limit) {
                System.out.printf("%-" + MAX_INDEX_DIGITS + "s", " ");
                System.out.print(" | ");
                System.out.printf("%-" + MAX_LEN_WORD + "s", " ");
                System.out.print(" | ");
            }

            index += end - start;
        }
    }

    private void showAllWords(List<Word> listOfWords) {
        System.out.printf("%-" + MAX_INDEX_DIGITS + "s", "No");
        System.out.print(" | ");
        System.out.printf("%-" + MAX_LEN_WORD + "s", "English");
        System.out.print(" | ");
        System.out.println("Vietnamese");
        int i = 1;

        for (Word word : listOfWords) {
            System.out.printf("%-" + MAX_INDEX_DIGITS + "s", i++);
            System.out.print(" | ");
            System.out.printf("%-" + MAX_LEN_WORD + "s", word.getWord_target());
            System.out.print(" | ");

            String word_explain = word.getWord_explain();
            int limit = word_explain.length();
            int index = 0;
            while (index < limit) {

                int start = index;
                int end = start + MAX_CMD_LENGTH > limit ? limit : start + MAX_CMD_LENGTH;
                if (end != limit)
                    while (word_explain.charAt(--end) != ' ') ;
                String word_explain_display = word_explain.substring(start, end);
                System.out.println(word_explain_display);

                if (end != limit) {
                    System.out.printf("%-" + MAX_INDEX_DIGITS + "s", " ");
                    System.out.print(" | ");
                    System.out.printf("%-" + MAX_LEN_WORD + "s", " ");
                    System.out.print(" | ");
                }

                index += end - start;
            }
        }
    }

    private void initateGame() {
        try {
            int correctcount = 0;
            Random rand = new Random();
            Set<Integer> questindex = new HashSet<>();
            while (questindex.size() < 20) {
                questindex.add(rand.nextInt(100));
            }
            int fileindex = 0;
            int questionindex = 0;
            BufferedReader bf = new BufferedReader(new FileReader("D:\\enLearn\\resource\\gameresource.txt"));
            while (bf.ready() && questionindex < 20) {
                String line = bf.readLine();
                if (!questindex.contains(fileindex++)) {
                    cleanCmdWindow();
                    continue;
                }
                System.out.println("You have to answer questions with 4 choices but there");
                System.out.println("is only one correct answer");
                String[] parts = line.split("\t");
                String question = parts[0];
                System.out.println(++questionindex+ ". " +question);
                System.out.println(parts[1] + " " + parts[2] + " " + parts[3] + " " + parts[4]);
                System.out.print("Your answer: ");
                String useranswer = cmdScanner.nextLine().trim();
                if(parts[5].compareToIgnoreCase(useranswer) == 0) {
                    correctcount++;
                }
                cleanCmdWindow();
            }
            System.out.println("You have completed the game with " + correctcount + "/20 correct " + (correctcount == 1 ? "answer" : "answers"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dictionarySearcher() {
        System.out.print("Insert the word you want to search for: ");
        String word_target = cmdScanner.nextLine().trim();
        List<Word> dictionary = dictionaryManagement.getDictionary().getListOfWords();
        Word word = new Word(word_target, "");
        int index = SearchEngine.binSearchIndex(dictionary, 0, dictionary.size() - 1, word);
        StringBuilder regex = new StringBuilder();
        regex.append("\\b");
        regex.append(word_target);
        regex.append("[' '\\w\\W]*");

        word = dictionary.get(index);

        List<Word> displaylist = new ArrayList<>();
        while (word.getWord_target().matches(regex.toString())) {
            displaylist.add(word);
            index++;
            word = dictionary.get(index);
        }
        showAllWords(displaylist);
    }

    public void cmdShutdownRecover() {
        dictionaryManagement.shutDownProcess();
    }

    public void dictionaryAdvanced() {
        String action = "";
        do {
            try {
                System.out.println("Welcome to My Application!");
                System.out.print("[0] Exit\n" +
                        "[1] Add\n" +
                        "[2] Remove\n" +
                        "[3] Update\n" +
                        "[4] Display\n" +
                        "[5] Lookup\n" +
                        "[6] Search\n" +
                        "[7] Game\n" +
                        "[8] Import from file\n" +
                        "[9] Export to file\n" +
                        "Your action: ");
                action = cmdScanner.nextLine().trim();
                int actiontakein = Integer.parseInt(action);
                cleanCmdWindow();
                switch (actiontakein) {
                    case 0:
                        return;
                    case 1:
                        dictionaryManagement.addWord();
                        break;
                    case 2:
                        if (!dictionaryManagement.isEmpty()) {
                            dictionaryManagement.deleteWord();
                        } else {
                            System.out.println("The dictionary is empty");
                        }
                        break;
                    case 3:
                        if (!dictionaryManagement.isEmpty()) {
                            dictionaryManagement.updateWord();
                        } else {
                            System.out.println("The dictionary is empty");
                        }
                        break;
                    case 4:
                        showAllWords();
                        break;
                    case 5:
                        Word word = dictionaryManagement.dictionaryLookup();
                        showWord(word);
                        break;
                    case 6:
                        dictionarySearcher();
                        break;
                    case 7:
                        initateGame();
                        break;
                    case 8:
                        importFromFile();
                        break;
                    case 9:
                        dictionaryManagement.dictionaryExportToFile();
                        break;
                    default:
                        System.out.println("Action not supported.");
                        Thread.sleep(1500);
                        cleanCmdWindow();
                }
                do {
                    System.out.print("Insert 0 to turn back to title screen : ");
                    action = cmdScanner.nextLine();
                    actiontakein = Integer.parseInt(action);
                } while (actiontakein != 0);
                cleanCmdWindow();
            } catch (NumberFormatException e) {
                System.out.println("Action not supported.");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                cleanCmdWindow();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private void cleanCmdWindow() {
        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ex) {
        }
    }
}
