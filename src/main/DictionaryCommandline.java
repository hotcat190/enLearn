package smurfcat.enLearn.core;

import java.util.List;

class DictionaryCommandline {
    private final DictionaryManagement dictionaryManagement;

    DictionaryCommandline() {
        this.dictionaryManagement = new DictionaryManagement();
    }

    void showAllWords() {
        List<Word> listOfWords = this.dictionaryManagement.getListOfWords();

        final int MAX_INDEX_DIGITS = 3; // Format up to 3 digits
        final int MAX_LEN_WORD = 45; // Longest English word length in a major dictionary = 45

        System.out.printf("%-" + MAX_INDEX_DIGITS + "s", "No");
        System.out.print(" | ");
        System.out.printf("%-" + MAX_LEN_WORD + "s", "English");
        System.out.print(" | ");
        System.out.println("Vietnamese");
        int i = 1;

        for (Word word : listOfWords) {
            System.out.printf("%-" + MAX_INDEX_DIGITS + "s", i++);
            System.out.print(" | ");
            System.out.printf("%-" + MAX_LEN_WORD + "s", word.word_target);
            System.out.print(" | ");
            System.out.println(word.word_explain);
        }
    }

    void dictionaryBasic() {
        this.dictionaryManagement.insertFromCommandline();
        this.showAllWords();
    }

    public static void main(String[] args) {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.dictionaryBasic();
    }
}
