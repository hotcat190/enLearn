import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    private final Dictionary dictionary;
    private final DictionaryCommandline dictionaryCommandline;

    public DictionaryManagement() {
        this.dictionary = new Dictionary();
        this.dictionaryCommandline = new DictionaryCommandline(dictionary);
    }

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of inserts: ");
        int numberOfInserts = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfInserts; i++) {
            System.out.print("Insert word: ");
            String target = scanner.nextLine();

            System.out.print("Specify explanation: ");
            String explain = scanner.nextLine();

            Word word = new Word(target, explain);
            this.dictionary.add(word);
        }
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        this.dictionaryCommandline.showAllWords();
    }

    public static void main(String[] args) {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.dictionaryBasic();
    }
}
