package view.word;

import controller.model.Listener;
import controller.word.AntonymsController;
import controller.word.IrregularVerbsController;
import controller.word.PartOfSpeechController;
import controller.word.SynonymsController;
import data.model.DataWord;
import data.statistic.SQLStatisticWeek;
import dictionary.Dictionary;
import dictionary.Word;
import graphics.StandardParameter;
import graphics.app.User;
import graphics.engine.SearchEngine;
import graphics.style.Decorator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordBoard extends DataWord implements Decorator, Listener {
    /**
     * Standard Parameter.
     */
    public final static String LINK_CSS = Objects.requireNonNull(Objects.requireNonNull(WordBoard.class.getResource("/css/style_for_engine_word_package.css")).toExternalForm());
    public final static double WIDTH = 525 * StandardParameter.SCALE;
    public final static double HEIGHT = 500 * StandardParameter.SCALE;
    private final static double LEFT_MARGIN = 30 * StandardParameter.SCALE;
    private final static double TOP_MARGIN = 30 * StandardParameter.SCALE;
    /**
     * Controllers.
     */
    private final PartOfSpeechController partOfSpeechController = new PartOfSpeechController();
    private final IrregularVerbsController irregularVerbsController = new IrregularVerbsController();
    private final SynonymsController synonymsController = new SynonymsController(215, 205, 15, 15, Color.rgb(38, 247, 142));
    private final AntonymsController antonymsController = new AntonymsController(215, 205, 15, 15, Color.rgb(234, 164, 40));

    /**
     * Word.
     */
    public Word word = null;

    /**
     * View.
     */
    private final Pane layout = new Pane();
    private final VBox mainVBoxWord = new VBox();
    private final Label textWord = new Label();
    private final Label textPronunciation = new Label();
    private final ImageView nullIcon = new ImageView();


    public WordBoard() throws SQLException {
        setId();
        setCSS();
        set();
    }

    public Pane getLayout() {
        return layout;
    }

    @Override
    public void setId() {
        layout.setId("board");
        textWord.setId("textWord");
        textPronunciation.setId("textPronunciation");

    }

    @Override
    public void setCSS() {
        layout.getStylesheets().add(WordBoard.LINK_CSS);
        layout.applyCss();
        textWord.applyCss();
        textPronunciation.applyCss();
    }

    /**
     * Load and update data.
     */
    @Override
    public void load() {
    }

    @Override
    public void load(Word word) {
        this.word = word;
        if (word.isIrregularVerb()) {
            irregularVerbsController.loadData(word);
        }
        System.out.println(123456);
        partOfSpeechController.loadData(word);
        synonymsController.loadData(word);
        antonymsController.loadData(word);
    }


    @Override
    public void set() {
        nullIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/image/null_icon_grammar_app.png"
        ))));
        nullIcon.setFitHeight(nullIcon.getImage().getHeight() * 0.3);
        nullIcon.setFitWidth(nullIcon.getImage().getWidth() * 0.3);
        nullIcon.setTranslateX(30);

        layout.getChildren().add(mainVBoxWord);
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.getChildren().add(irregularVerbsController.getView());
        layout.getChildren().add(nullIcon);

        partOfSpeechController.getView().getChildren().addFirst(textPronunciation);
        partOfSpeechController.getView().getChildren().addFirst(textWord);

        textWord.setTranslateY(10);
        textPronunciation.setTranslateY(-10);
        HBox hBox_contronym = new HBox(synonymsController.getView(), antonymsController.getView());
        hBox_contronym.setSpacing(50 * StandardParameter.SCALE);
        hBox_contronym.setTranslateX(80);
        mainVBoxWord.getChildren().addAll(partOfSpeechController.getView());
        mainVBoxWord.getChildren().add(hBox_contronym);

        mainVBoxWord.setTranslateX(-15);
        mainVBoxWord.setAlignment(Pos.CENTER);
        mainVBoxWord.setSpacing(35 * StandardParameter.SCALE);

        irregularVerbsController.getView().setLayoutX(LEFT_MARGIN + 380);
        irregularVerbsController.getView().setLayoutY(TOP_MARGIN + 20);

    }

    @Override
    public void update() throws SQLException {
        System.out.println(999);
        irregularVerbsController.updateView();
        textWord.setText(word.getWord().replace(word.getWord().substring(0, 1), word.getWord().substring(0, 1).toUpperCase()));
        textPronunciation.setText(word.getPronunciation());
        partOfSpeechController.updateView();
        synonymsController.updateView(this);
        antonymsController.updateView(this);

        if (word != null) {
            nullIcon.setVisible(false);
        }
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void setListener(Object object) {
        if (object instanceof SearchEngine searchEngine) {
            ListView<String> listView = searchEngine.getListView();
            ListView<String> listViewHistory = searchEngine.getListViewHistory();
            listView.setOnMouseClicked(e -> {
                if (!listView.getItems().isEmpty()) {
                    String selectionWord = listView.getSelectionModel().getSelectedItem();
                    listView.getItems().clear();
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    Runnable loadData = () -> {
                        try {
                            word = new Word(selectionWord);
                            User.increaseTotalWords();
                            SQLStatisticWeek.increaseTotalWordsAndTotalTimes();
                            load(word);
                            Dictionary.putHistory(word.getWord());
                        } catch (SQLException error) {
                            System.err.println(error);
                        }
                        new Thread(() -> {
                            Platform.runLater(() -> {
                                try {
                                    update();
//                                antonymsController.updateView(this);
//                                synonymsController.updateView(this);

                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                                searchEngine.isPressed = false;
                                searchEngine.getTextInput().setText(word.getWord());
                            });
                        }).start();
                    };
                    executorService.execute(loadData);
                }
            });
            listViewHistory.setOnMouseClicked(e -> {
                if (!listViewHistory.getItems().isEmpty()) {
                    String selectionWord = listViewHistory.getSelectionModel().getSelectedItem();
                    listViewHistory.getItems().clear();
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    Runnable loadData = () -> {
                        try {
                            word = new Word(selectionWord);
                            load(word);
                            Dictionary.getHistory().remove(selectionWord);
                            Dictionary.putHistory(word.getWord());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        new Thread(() -> {
                            Platform.runLater(() -> {
                                searchEngine.isPressed = false;
                                searchEngine.getTextInput().setText(word.getWord());
                            });

                        }).start();
                    };
                    executorService.execute(loadData);
                }
            });
        }
    }

    @Override
    public void setListener() {

    }
}
