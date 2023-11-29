package controller.search;

import controller.model.Controller;
import graphics.animation.Listener;
import controller.search.view.SearchEngineHistoryView;
import graphics.style.Decorator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class SearchEngineHistoryController extends Controller implements Decorator, Listener {
    /**
     * Singleton.
     */
    private final static SearchEngineHistoryController INSTANCE = new SearchEngineHistoryController();

    /**
     * Components.
     */
    private final SearchEngineHistoryView searchEngineHistoryView = SearchEngineHistoryView.getInstance();
    /**
     * Views.
     */
    private String wordSelected;
    private final Text textTitle = new Text("History");
    private final VBox vBox = new VBox(textTitle, searchEngineHistoryView);

    private SearchEngineHistoryController() {
        setId();
        setCSS();
        set();
        setListener();
    }

    public static SearchEngineHistoryController getInstance() {
        return INSTANCE;
    }

    @Override
    public void updateView() {
    }

    @Override
    public void loadData() {
    }

    @Override
    public void loadData(Object object) {

    }

    @Override
    public Node getView() {
        return vBox;
    }

    /**
     * Listeners.
     */
    @Override
    public void setListener(Object object) {
    }
    @Override
    public void setListener() {
        for (Node node : searchEngineHistoryView.getChildren()) {
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
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 0, 10, 10));
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
}
