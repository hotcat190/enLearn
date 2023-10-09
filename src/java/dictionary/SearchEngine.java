package dictionary;

import graphics.app.AppWindow;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class SearchEngine {
    private Pane searchShape = new Pane();
    private TextField searchEngine = new TextField();
    private ImageView searchIcon = null;
    private StackPane stackSearch = new StackPane();

    public StackPane getStackSearch() {
        return stackSearch;
    }
    public SearchEngine() {
        searchIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/image/search_icon_app.png")).toExternalForm()));
        setID();
        setCSS();
        try {
            stackSearch.getChildren().add(searchShape);
            stackSearch.getChildren().add(searchIcon);
            stackSearch.getChildren().add(searchEngine);
            stackSearch.setAlignment(Pos.CENTER_LEFT);
            stackSearch.setLayoutX(370 + AppWindow.DELTA_X);
            stackSearch.setLayoutY(27 + AppWindow.DELTA_Y);

            searchIcon.setTranslateX(10);

            stackSearch.layout();
            searchEngine.setTranslateX(searchIcon.getImage().getWidth() + 10);
            searchEngine.setMaxWidth(240);
            searchEngine.setText("Search for word");

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_dashboard1_class.css")).toExternalForm();

    /**
     * Set ID and CSS.
     */
    private void setID() {
        searchShape.setId("searchShape");
        searchEngine.setId("searchEngine");
    }

    private void setCSS() {
        stackSearch.getStylesheets().add(linkToCSS);
        searchEngine.applyCss();
        searchShape.applyCss();
        stackSearch.applyCss();
    }

    public void setAnimation() {
        searchEngine.setOnMouseClicked(e -> {
            searchEngine.setEditable(true);
            if (searchEngine.getText().equals("Search for word")) {
                searchEngine.setText("");
            }
        });
        searchEngine.setOnMouseExited(e -> {
            if (searchEngine.getText().isEmpty()) {
                searchEngine.setText("Search for word");
            }
            searchEngine.setEditable(false);
        });
    }
}
