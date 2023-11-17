package data.word;

import data.model.DataWord;
import dictionary.Word;

public class IrregularVerbsData extends DataWord {
    @Override
    public void load(Word word) {
        this.word = word;
    }

    public String getPastSimple() {
        return word.getPastTenseList().get(0);
    }

    public String getPastParticiple() {
        return word.getPastTenseList().get(1);
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
