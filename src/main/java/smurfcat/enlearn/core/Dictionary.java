package smurfcat.enlearn.core;

import java.util.ArrayList;
import java.util.List;

class Dictionary {
    private final List<Word> listOfWords;

    Dictionary() {
        this.listOfWords = new ArrayList<>();
    }

    void add(Word word) {
        this.listOfWords.add(word);
    }

    List<Word> getListOfWords() {
        return this.listOfWords;
    }
}
