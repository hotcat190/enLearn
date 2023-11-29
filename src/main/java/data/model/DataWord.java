package data.model;

import controller.word.data.Word;

public abstract class DataWord extends Data {
    protected Word word = null;

    public abstract void load(Word word);
}
