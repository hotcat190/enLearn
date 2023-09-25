package smurfcat.enLearn.core;

import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    private final Dictionary dictionary;

    DictionaryManagement() {
        this.dictionary = new Dictionary();
    }

    void insertFromCommandline() {
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

    List<Word> getListOfWords() {
        return this.dictionary.getListOfWords();
    }
}
