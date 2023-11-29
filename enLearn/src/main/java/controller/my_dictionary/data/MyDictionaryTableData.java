package controller.my_dictionary.data;

import data.model.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

public class MyDictionaryTableData extends Data {
    /**
     * Singleton.
     */
    private final static MyDictionaryTableData INSTANCE = new MyDictionaryTableData();

    /**
     * Components.
     */
    private final ObservableList<MyNewWord> observableList = FXCollections.observableArrayList();
    private final FilteredList<MyNewWord> filteredList = new FilteredList<>(observableList);

    private MyDictionaryTableData() {

    }

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

    public FilteredList<MyNewWord> getFilteredList() {
        return filteredList;
    }

    public void add(MyNewWord myNewWord) {
        observableList.add(myNewWord);
    }

    public void remove(MyNewWord deleteWord) {
        for (int i = 0; i < observableList.size(); i++) {
            MyNewWord myNewWord = observableList.get(i);
            if (myNewWord.getWord().equals(deleteWord.getWord())
                    && myNewWord.getDefinition().equals(deleteWord.getDefinition())) {
                observableList.remove(i);
                return;
            }
        }
    }

    public void update(MyNewWord oldWord, MyNewWord newWord) {
        for (MyNewWord myNewWord : observableList) {
            if (myNewWord.getWord().equals(oldWord.getWord())
                    && myNewWord.getDefinition().equals(oldWord.getDefinition())) {
                myNewWord.setWord(newWord.getWord());
                myNewWord.setPronunciation(newWord.getPronunciation());
                myNewWord.setDefinition(newWord.getDefinition());
                System.out.println("Old Word: " + oldWord.getWord() + " and " + "new word " + newWord.getWord());
                myNewWord.setUpdate(new Date(System.currentTimeMillis()));
                myNewWord.setHour(Time.valueOf(LocalTime.now()));
                return;
            }
        }
    }

    public ObservableList<MyNewWord> getObservableList() {
        return observableList;
    }

    public static MyDictionaryTableData getInstance() {
        return INSTANCE;
    }
}
