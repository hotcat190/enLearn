import java.util.List;

public class DictionaryCommandline {
    private final Dictionary dictionary;

    public DictionaryCommandline(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void showAllWords() {
        List<Word> listOfWords = this.dictionary.getListOfWords();

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
}
