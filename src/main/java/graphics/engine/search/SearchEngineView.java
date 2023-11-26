package graphics.engine.search;

import controller.model.Listener;
import controller.search.SearchEngineController;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sql.dictionary.SQLDictionary;
import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Objects;

public class SearchEngineView extends VBox implements Decorator, Listener {
    private final static SearchEngineView INSTANCE = new SearchEngineView();
    public boolean isSearching = false;
    private final TextField input = new TextField();
    private final ImageView iconSearch = new ImageView();
    private final HBox hBoxSearch = new HBox(iconSearch, input);

    private final Text textTitle = new Text("All result");

    private final ListView<String> listView = new ListView<>();
    private final VBox vBox = new VBox(textTitle, listView);
    private final ObservableList<String> dictionaryList = FXCollections.observableArrayList(SQLDictionary.getDictionary());
    private final FilteredList<String> filteredList = new FilteredList<>(dictionaryList);
    private String wordSelected;

    public SearchEngineView() {
        setId();
        setCSS();
        set();
        setListener();
    }

    /**
     * Set listener with other object.
     */
    @Override
    public void setListener(Object object) {

    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
        listView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            wordSelected = listView.getSelectionModel().getSelectedItem();
            SQLDictionary.putHistory(wordSelected);
            try {
                SearchEngineHistoryView.getInstance().update();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            input.setText("");
            isSearching = false;
            SearchEngineController.getInstance().hide();

        });
        input.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            isSearching = !isSearching;
            if (SearchEngineController.getInstance().isHided()) {
                SearchEngineController.getInstance().unHide();
            } else {
                SearchEngineController.getInstance().hide();
            }
            if (input.getText().equals("Search word...")) {
                input.setText("");
            }
        });
        this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
//                listView.getItems().get(0));
                wordSelected = listView.getItems().get(0);
                isSearching=false;
                SearchEngineController.getInstance().hide();
            }
        });
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (input.getText().isEmpty() && !isSearching) {
                    input.setText("Search word...");
                }
            }
        }.start();
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_search_engine.css"));
        this.setId("search-engine-view__vbox--layout");
        listView.setId("search-engine-view__listview--preview");
        input.setId("search-engine-view__textfield--input");
        textTitle.setId("search-engine-history-controller__text--title");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {

    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        this.getChildren().add(hBoxSearch);
        this.setMinWidth(528 * StandardParameter.SCALE);
        this.setMaxWidth(528 * StandardParameter.SCALE);
        this.setMaxHeight(30);
        listView.setItems(filteredList);
        listView.setMaxHeight(300);

        input.setPrefWidth(500 * StandardParameter.SCALE);
        input.setPrefHeight(30);
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(value -> {
                if (oldValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerFilter = newValue.toLowerCase();
                return value.startsWith(lowerFilter);
            });
        });

        iconSearch.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/image/search_icon_app.png"
        ))));

        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 0, 30, 10));

        hBoxSearch.setPadding(new Insets(0, 0, 0, 10));
        hBoxSearch.setAlignment(Pos.CENTER_LEFT);
        hBoxSearch.setSpacing(3);
        iconSearch.setFitWidth(8);
        iconSearch.setFitHeight(10);
    }

    /**
     * Update layout.
     */
    @Override
    public void update() throws SQLException {

    }

    @Override
    public String getLink() {
        return null;
    }

    public static SearchEngineView getInstance() {
        return INSTANCE;
    }

    public TextField getInput() {
        return input;
    }

    public ListView<String> getListView() {
        return listView;
    }

    public void removeListView() {
        this.getChildren().remove(listView);
    }

    public void addListView() {
        if (!this.getChildren().contains(vBox)) {
            this.getChildren().add(vBox);
        }
    }

    public String getWordSelected() {
        return wordSelected;
    }
}
