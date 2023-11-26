package graphics.engine.search;

import controller.model.Listener;
import controller.search.SearchEngineController;
import sql.dictionary.SQLDictionary;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import controller.word.view.WordBoard;

import java.sql.SQLException;
import java.util.List;

public class SearchEngineHistoryView extends FlowPane implements Decorator, Listener {
    private final static SearchEngineHistoryView INSTANCE = new SearchEngineHistoryView();
    public List<String> history= SQLDictionary.getHistory();
    private String wordSelected;

    private SearchEngineHistoryView() {
        setId();
        setCSS();
        set();
        setListener();
    }

    /**
     * Set listener with other object.
     *
     */
    @Override
    public void setListener(Object object) {

    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
        for (Node node : this.getChildren()) {
            if (node instanceof Button button) {
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    wordSelected = button.getText();
                });
            }
        }

    }

    /**
     * Set id.
     */
    @Override
    public void setId() {

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
        this.setMaxWidth(558* StandardParameter.SCALE);
        this.setHgap(5);
        this.setVgap(5);
        try {
            update();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Update layout.
     */
    @Override
    public void update() throws SQLException {
        new Thread(() -> {
        history = SQLDictionary.getHistory();
            Platform.runLater(() -> {
                this.getChildren().clear();
                int i=0;
                for (String word : history) {
                    if(word.isEmpty()) continue;
                    if(i++>30) break;
                    Button button = new Button(word);
                    button.getStyleClass().add("search-engine-view__button--word");
                    this.getChildren().add(button);
                    setListener();
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                        WordBoard.getInstance().lastUpdate(word);
                        SearchEngineController.getInstance().hide();
                        SearchEngineView.getInstance().isSearching = false;
                    });
                }
            });
        }).start();

    }

    @Override
    public String getLink() {
        return null;
    }

    public String getWordSelected() {
        return wordSelected;
    }
    public static SearchEngineHistoryView getInstance() {
        return INSTANCE;
    }

}
