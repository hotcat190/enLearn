import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
    private final List<Word> listOfWords;

    public void validateDictionary() {
        Collections.sort(listOfWords);
    }

    public Dictionary() {
        this.listOfWords = new ArrayList<>();
    }

    public void add(int index, Word word) {
        if (!listOfWords.isEmpty() && listOfWords.get(index).compareTo(word) == 0) {
            System.out.println(word.getWord_target() + " has already existed in dictionary.");
        } else {
            this.listOfWords.add(index, word);
        }
    }

    public Word getWordfromIndex(int index) {
        return listOfWords.get(index);
    }

    public void add(Word word) {
        if (!listOfWords.isEmpty() && listOfWords.contains(word)) {
            System.out.println(word.getWord_target() + " has already existed in dictionary.");
        } else {
            this.listOfWords.add(word);
        }
    }

    public void remove(Word word) {
        this.listOfWords.remove(word);
    }

    public void update(int position, Word word) {
        this.listOfWords.set(position, word);
    }

    public List<Word> getListOfWords() {
        return this.listOfWords;
    }

}
