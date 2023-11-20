package view.my_dictionary.my_word_box;

import controller.model.Listener;
import data.my_dictionary.MyNewWord;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SearchFilter extends TextField implements Decorator, Listener {
    public SearchFilter() {
        set();
    }

    @Override
    public void setId() {
        this.setId("inputSearchFilter");
    }

    @Override
    public void setCSS() {

    }

    @Override
    public void set() {
        this.setPrefWidth(350* StandardParameter.SCALE);
    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void setListener(Object object) {
        FilteredList<MyNewWord> filteredList;
        if (object instanceof FilteredList<?>) {
            filteredList = (FilteredList<MyNewWord>) object;
        } else {
            filteredList = null;
        }
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            assert filteredList != null;
            filteredList.setPredicate(myNewWord -> {
                if (oldValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerFilter = newValue.toLowerCase();
                if (myNewWord.getWord().contains(lowerFilter)) {
                    return true;
                }
                return false;
            });
        });
    }

    @Override
    public void setListener() {

    }
}
