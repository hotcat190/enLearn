package data.model;

import dictionary.Word;

public abstract class DataWord extends Data {
    protected Word word = null;

    public abstract void load(Word word);
}
