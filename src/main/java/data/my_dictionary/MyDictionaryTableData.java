package data.my_dictionary;

import controller.model.Update;
import data.model.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class MyDictionaryTableData extends Data {
    private final ObservableList<MyNewWord> observableList = FXCollections.observableArrayList();
    private final FilteredList<MyNewWord> filteredList = new FilteredList<>(observableList);

    public MyDictionaryTableData() {

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
    public ObservableList<MyNewWord> getObservableList() {
        return observableList;
    }
}
