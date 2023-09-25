package graphics.engine;

import dictionary.Dictionary;
import dictionary.Word;
import graphics.StandardParameter;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class WordEngine {
    private final Text title = new Text("Word");
    private final VBox paneWordEngine = new VBox();
    private final Pane board = new Pane();

    /**
     * Structure of word to display.
     */
    private final VBox vBoxWord = new VBox();
    private final Text textWord = new Text("word");
    private final Text textPronunciation = new Text();
    /**
     * Link to CSS.
     */
    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_dashboard1_class.css")).toExternalForm();

    public WordEngine() {
        try {
            setId();
            setCSS();

            paneWordEngine.setSpacing(StandardParameter.STANDARD_TITLE_VBOX);
            paneWordEngine.getChildren().add(title);
            paneWordEngine.getChildren().add(board);

            board.getChildren().add(vBoxWord);

            vBoxWord.setTranslateY(10);
            vBoxWord.setTranslateX(20);
            vBoxWord.getChildren().add(textWord);
            vBoxWord.getChildren().add(textPronunciation);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    private void setId() {
        title.setId("title");
        board.setId("board");
        textWord.setId("textWord");
        textPronunciation.setId("textPronunciation");
    }

    private void setCSS() {
        paneWordEngine.getStylesheets().add(linkToCSS);
        paneWordEngine.applyCss();
        title.applyCss();
    }

    public Pane getPane() {
        return paneWordEngine;
    }

    public void setAction(SearchEngine searchEngine) {
        ListView<String> listView=searchEngine.getListView();
        ListView<String> listViewHistory = searchEngine.getListViewHistory();
        listView.setOnMouseClicked(e->{
            ResultSet resultSet= null;
            Word word = null;
            try {
                resultSet = Dictionary.getWord("^"+listView.getSelectionModel().getSelectedItem()+"$");
                resultSet.next();
                word = new Word(resultSet);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            try {
                Dictionary.putHistory(word.getWord());
            } catch (SQLException ex) {
                System.out.println("Loi: "+ex);
            }

            searchEngine.isPressed=false;
            searchEngine.getTextInput().setText(word.getWord());
            listView.getItems().clear();
            textWord.setText(word.getWord());
            textPronunciation.setText(word.getPronunciation());
        });

        listViewHistory.setOnMouseClicked(e->{
            ResultSet resultSet= null;
            Word word = null;
            try {
                resultSet = Dictionary.getWord("^"+listViewHistory.getSelectionModel().getSelectedItem()+"$");
                resultSet.next();
                word = new Word(resultSet);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            try {
                Dictionary.putHistory(word.getWord());
            } catch (SQLException ex) {
                System.out.println("Loi: "+ex);
            }

            searchEngine.isPressed=false;
            searchEngine.getTextInput().setText(word.getWord());
            listViewHistory.getItems().clear();
            textWord.setText(word.getWord());
            textPronunciation.setText(word.getPronunciation());
        });
    }
}