import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private final List<Word> listOfWords;

    public Dictionary() {
        this.listOfWords = new ArrayList<>();
    }

    public void add(Word word) {
        this.listOfWords.add(word);
    }

    public List<Word> getListOfWords() {
        return this.listOfWords;
    }
}
