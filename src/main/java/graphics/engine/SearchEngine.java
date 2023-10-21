package graphics.engine;

import dictionary.Dictionary;
import dictionary.Word;
import graphics.app.AppWindow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.FocusModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SearchEngine {
    private final Pane searchShape = new Pane();
    private final TextField textInput = new TextField();
    private ImageView searchIcon = null;
    private final Pane paneSearch = new Pane();
    private final Dictionary dictionary = new Dictionary();

    /**
     * List view.
     */
    private final ListView<String> listView = new ListView<>();
    private final ListView<String> listViewHistory = new ListView<>();

    /**
     * Get layout of search engine.
     *
     * @return Pane.
     */
    public Pane getPaneSearch() {
        return paneSearch;
    }

    public SearchEngine() throws SQLException {
        searchIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/image/search_icon_app.png")).toExternalForm()));
        setId();
        setCSS();
        try {
            paneSearch.getChildren().add(searchShape);
            paneSearch.getChildren().add(searchIcon);
            paneSearch.getChildren().add(textInput);
            paneSearch.getChildren().add(listView);
            paneSearch.getChildren().add(listViewHistory);
            paneSearch.setLayoutX(370 + AppWindow.DELTA_X);
            paneSearch.setLayoutY(27 + AppWindow.DELTA_Y);

            searchIcon.setTranslateX(10);
            searchIcon.setTranslateY(7);


            paneSearch.layout();

            textInput.setTranslateX(searchIcon.getImage().getWidth() + 10);
            textInput.setMaxWidth(240);
            textInput.setText("Search for word");
            textInput.setTranslateY(3);

            listView.setTranslateY(30);
            listView.setTranslateX(textInput.getTranslateX());
            listView.setMaxHeight(190);

            listViewHistory.setTranslateY(30);
            listViewHistory.setTranslateX(textInput.getTranslateX());
            listViewHistory.setMaxHeight(190);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     * Link to CSS.
     */
    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_dashboard1_class.css")).toExternalForm();

    /**
     * Set ID and CSS.
     */
    private void setId() {
        searchShape.setId("searchShape");
        textInput.setId("searchEngine");
        listView.setId("listView");
        listViewHistory.setId("listView");
    }

    private void setCSS() {
        paneSearch.getStylesheets().add(linkToCSS);
        textInput.applyCss();
        searchShape.applyCss();
        paneSearch.applyCss();
        listView.applyCss();
    }

    /**
     * Getter.
     */
    public ListView<String> getListView() {
        return listView;
    }

    public ListView<String> getListViewHistory() {
        return listViewHistory;
    }

    public TextField getTextInput() {
        return textInput;
    }

    /**
     * Check press in out area.
     */
    public boolean isPressed = false;

    public void setAction() {
        TreeSet<String> treeSetWord = new TreeSet<>();
        listViewHistory.setCellFactory(c -> new ListCell<>() {
            @Override
            protected void updateItem(String context, boolean empty) {
                super.updateItem(context, empty);
                setText(null);
                setGraphic(null);
                ImageView clockIcon = new ImageView(Objects.requireNonNull(getClass().getResource("/image/clock_icon_app.png")).toExternalForm());
                clockIcon.setFitWidth(15);
                clockIcon.setFitHeight(15);
                if (!empty) {
                    setGraphic(clockIcon);
                    setText(context);
                }
            }
        });
        textInput.setOnMouseClicked(e -> {
            textInput.setEditable(true);
            if (textInput.getText().isEmpty() || textInput.getText().equals("Search for word")) {
                LinkedList<String> linkedList = Dictionary.getHistory();
                textInput.clear();
                listViewHistory.getItems().addAll(linkedList.reversed());
                listViewHistory.toFront();
            } else {
                listViewHistory.getItems().clear();
            }
            if (!listView.getItems().isEmpty() || !listViewHistory.getItems().isEmpty()) {
                searchShape.setMinHeight(listView.getMaxHeight() + 40);
            } else {
                searchShape.setMinHeight(34);
            }
        });
        textInput.setOnKeyTyped(e -> {
            listViewHistory.getItems().clear();
            new Thread(() -> {
                try {
                    treeSetWord.clear();
                    if (!textInput.getText().isEmpty()) {
                        String regexp = "^" + textInput.getText();
                        ResultSet resultSet = Dictionary.getWord(regexp);
                        while (resultSet.next()) {
                            Word word = new Word(resultSet);
                            treeSetWord.add(word.getWord());
                        }
                    }

                } catch (SQLException ex) {
                    System.out.println("Loi: "+ex);
                }
                Platform.runLater(() -> {
                    if (!treeSetWord.isEmpty()) {
                        listView.getItems().clear();
                        listView.getItems().addAll(treeSetWord);
                    }
                    listView.toFront();
                    if (!listView.getItems().isEmpty() || !listViewHistory.getItems().isEmpty()) {
                        searchShape.setMinHeight(listView.getMaxHeight() + 40);
                    } else {
                        searchShape.setMinHeight(34);
                    }

                });
            }).start();
        });
        listView.setOnMousePressed(e -> {
            searchShape.setMinHeight(34);
            isPressed = true;
        });
        listView.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getTarget() != searchShape && e.getTarget() != paneSearch && e.getTarget() != textInput) {
                if (!isPressed) {
                    listView.getItems().clear();
                    listViewHistory.getItems().clear();
                }
                searchShape.setMinHeight(34);
                if (textInput.getText().isEmpty()) {
                    textInput.setText("Search for word");
                }
                textInput.setEditable(false);
            }
        });
    }
}
