package data.word;

import data.model.DataWord;
import dictionary.Word;

import java.util.HashMap;

public class PartOfSpeechData extends DataWord {
    private Word word = null;
    private final HashMap<String, String> hashMapPOS = new HashMap<>();
    /**
     * Load and update data.
     */
    @Override
    public void load() {

    }

    /**
     * Set data.
     */
    @Override
    public void set() {

    }

    @Override
    public void load(Word word) {
        System.out.println("hai " +word.getWord());

        this.word = word;
        hashMapPOS.clear();
        hashMapPOS.putAll(word.getHashMap());
    }

    public HashMap<String, String> getHashMapPOS() {
        return hashMapPOS;
    }

    public Word getWord() {
        return word;
    }
}
