package controller.my_dictionary.view.my_word_box;

import graphics.animation.Listener;
import controller.my_dictionary.data.MyNewWord;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.Objects;

public class SearchFilter extends HBox implements Decorator, Listener {
    private static final SearchFilter INSTANCE = new SearchFilter();

    private final TextField input = new TextField();
    private final ImageView iconSearch = new ImageView();
    private SearchFilter() {
        setId();
        setCSS();
        set();
    }

    @Override
    public void setId() {
        this.setId("search-filter__hbox--layout");
        input.setId("search-filter__textfield--input");
    }

    @Override
    public void setCSS() {

    }

    @Override
    public void set() {
        this.setPrefWidth(450 * StandardParameter.SCALE);
        this.setMaxHeight(38 * StandardParameter.SCALE);
        this.getChildren().addAll(iconSearch, input);

        iconSearch.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/image/search_icon_app.png"
        ))));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(3);
        input.setPrefWidth(450 * StandardParameter.SCALE);
        iconSearch.setFitWidth(8);
        iconSearch.setFitHeight(10);
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
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            assert filteredList != null;
            filteredList.setPredicate(myNewWord -> {
                if (oldValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerFilter = newValue.toLowerCase();
                return myNewWord.getWord().contains(lowerFilter);
            });
        });
    }

    @Override
    public void setListener() {

    }

    public static SearchFilter getInstance() {
        return INSTANCE;
    }
}
