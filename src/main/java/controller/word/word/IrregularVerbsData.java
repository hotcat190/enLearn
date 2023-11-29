package controller.word.word;

import data.model.DataWord;
import controller.word.data.Word;

public class IrregularVerbsData extends DataWord {
    @Override
    public void load(Word word) {
        this.word = word;
    }

    public String getPastSimple() {
        if(!word.getPastTenseList().isEmpty()) {
            return word.getPastTenseList().get(0);
        }
        return "";
    }

    public String getPastParticiple() {
        if(!word.getPastTenseList().isEmpty()) {
            return word.getPastTenseList().get(1);
        }
        return "";
    }

    /**
     * Load and update data.
     */
    @Override
    public void load() {
    }

    @Override
    public void set() {
    }
}
