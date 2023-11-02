package graphics.engine;

import dictionary.Dictionary;
import dictionary.Word;
import graphics.StandardParameter;
import graphics.app.AppWindow;
import graphics.app.dashboard.Dashboard1;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

public class WordEngine {
    /**
     * Display layout.
     */
    private final Text title = new Text("Word");
    private final VBox paneWordEngine = new VBox();
    private final Pane board = new Pane();

    /**
     * Word.
     */
    private Word word = null;
    private HashMap<String, String> hashMapPOS = null;

    /**
     * Display word.
     */
    private final ImageView nullImage = new ImageView();
    private final VBox vBoxWord = new VBox();
    private final Text textWord = new Text();
    private final Text textPronunciation = new Text();
    private final TextArea textDefinition = new TextArea();
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox vBoxDef = new VBox();

    /**
     * Display grammar.
     */

    private final ImageView grammarBox = new ImageView();
    private final VBox grammarPane = new VBox();
    /**
     * Display antonyms.
     */
    private final Text titleAntonyms = new Text("Antonyms");
    private final FlowPane antonymsFlowPane = new FlowPane();
    private final VBox antonymsVBox = new VBox(titleAntonyms, antonymsFlowPane);

    /**
     * Display synonyms.
     */
    private final Text titleSynonyms = new Text("Synonyms");
    private final FlowPane synonymsFlowPane = new FlowPane();
    private final VBox synonymsVBox = new VBox(titleSynonyms, synonymsFlowPane);

    /**
     * Display irregular verbs.
     */
    private final Text titleIrregularVerb = new Text("Irregular Verb");
    private final HBox irregularVerbsHBox = new HBox();
    private final VBox irregularVerbsVBox = new VBox(titleIrregularVerb, irregularVerbsHBox);


    /**
     * Display part of speech.
     */
    private final HBox hBoxPOS = new HBox();

    /**
     * Parameter.
     */
    public final static int DISPLAY_WORD_WIDTH = 320;
    public static final int TRANSLATE_GRAMMAR_Y = 60;
    public static final int TRANSLATE_GRAMMAR_X = 32;

    public WordEngine() {
        paneWordEngine.setSpacing(StandardParameter.STANDARD_TITLE_VBOX);
        paneWordEngine.getChildren().add(title);
        paneWordEngine.getChildren().add(board);

        board.getChildren().add(vBoxWord);
        board.getChildren().add(grammarPane);
        board.getChildren().add(nullImage);

        setId();
        setCSS();

        setUpWordLayout();
        setUpAntonymsLayout();
        setUpSynonymsLayout();
        setUpIrregularLayout();
        setUpGrammarLayout();
    }

    private void setId() {
        title.setId("title");
        board.setId("board");
        textWord.setId("textWord");
        textPronunciation.setId("textPronunciation");
        scrollPane.setId("scrollPane");
        grammarPane.setId("grammarPane");
        titleIrregularVerb.setId("titleGrammar");
        titleSynonyms.setId("titleGrammar");
        titleAntonyms.setId("titleGrammar");
        irregularVerbsHBox.setId("irregularVerbsHBox");

    }

    private void setCSS() {
        paneWordEngine.getStylesheets().add(Dashboard1.LINK_CSS);
        paneWordEngine.applyCss();
        title.applyCss();
        titleAntonyms.applyCss();
        titleSynonyms.applyCss();
    }

    public Pane getPane() {
        return paneWordEngine;
    }

    boolean isExample = true;

    public void setEvent(SearchEngine searchEngine) {
        ListView<String> listView = searchEngine.getListView();
        ListView<String> listViewHistory = searchEngine.getListViewHistory();

        listView.setOnMouseClicked(e -> {
            if (!listView.getItems().isEmpty()) {
                vBoxDef.getChildren().clear();
                hBoxPOS.getChildren().clear();
                irregularVerbsHBox.getChildren().clear();
                ResultSet resultSet = null;
                try {
                    resultSet = Dictionary.getWord("^" + listView.getSelectionModel().getSelectedItem() + "$");
                    word = new Word(resultSet);
                    listView.getItems().clear();
                    Dictionary.putHistory(word.getWord());
                } catch (SQLException ignored) {
                }
                try {
                    setSynonymsFlowPane(word.getWord());
                    setAntonymsFlowPane(word.getWord());
                    setIrregularVerbsHBox(word.getWord());

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                setButtonPOS();
                searchEngine.isPressed = false;
                searchEngine.getTextInput().setText(word.getWord());
                textWord.setText(word.getWord());
                textPronunciation.setText(word.getPronunciation());
            }
        });

        listViewHistory.setOnMouseClicked(e -> {
            if (!listViewHistory.getItems().isEmpty()) {
                vBoxDef.getChildren().clear();
                hBoxPOS.getChildren().clear();
                irregularVerbsHBox.getChildren().clear();
                ResultSet resultSet = null;
                try {
                    resultSet = Dictionary.getWord("^" + listViewHistory.getSelectionModel().getSelectedItem() + "$");
                    word = new Word(resultSet);
                    System.out.println(word.getWord());
                    Dictionary.getHistory().remove(listViewHistory.getSelectionModel().getSelectedItem());
                    Dictionary.putHistory(word.getWord());
                    listViewHistory.getItems().clear();
                } catch (SQLException ignored) {
                }
                try {
                    setSynonymsFlowPane(word.getWord());
                    setAntonymsFlowPane(word.getWord());
                    setIrregularVerbsHBox(word.getWord());

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                setButtonPOS();
                searchEngine.isPressed = false;
                searchEngine.getTextInput().setText(word.getWord());
                textWord.setText(word.getWord());
                textPronunciation.setText(word.getPronunciation());
            }
        });
    }

    private void setButtonPOS() {
        nullImage.setOpacity(0);
        hBoxPOS.getChildren().clear();
        hashMapPOS = word.getHashMap();
        for (Map.Entry<String, String> set : hashMapPOS.entrySet()) {
            Button buttonPOS = new Button(set.getKey());
            buttonPOS.setId("buttonPOS");
            buttonPOS.applyCss();
            buttonPOS.setOnMouseMoved(e -> {
                new Timeline(
                        new KeyFrame(Duration.millis(300),
                                new KeyValue(buttonPOS.translateYProperty(), -5))
                ).play();
            });
            buttonPOS.setOnMouseExited(e -> {
                new Timeline(
                        new KeyFrame(Duration.millis(300),
                                new KeyValue(buttonPOS.translateYProperty(), 5))
                ).play();
            });
            buttonPOS.setOnMouseClicked(e -> {
                buttonPOS.setStyle(buttonPOS.getStyle() + "-fx-background-color: #0044dd;" +
                        "-fx-border-width: 0;" +
                        "-fx-border-color: #e6e6e6;");
                buttonPOS.setTranslateY(5);
                for (Node button : hBoxPOS.getChildren()) {
                    if (button instanceof Button && button != buttonPOS) {
                        button.setStyle("-fx-background-color: transparent;" +
                                "-fx-border-width: 1;" +
                                "-fx-border-color: #e6e6e6;");
                    }
                }
                isExample = true;
                vBoxDef.getChildren().clear();
                String stringBuilder = hashMapPOS.get(buttonPOS.getText());
                Stream<String> lines = stringBuilder.lines();
                for (String line : lines.toList()) {
                    if (line.startsWith("-")) {
                        Text text_def_local = new Text();
                        if (!isExample) {
                            text_def_local.setText("\n" + line.replace("-", ""));
                        } else {
                            text_def_local.setText(line.replace("-", ""));
                        }
                        text_def_local.setStyle("-fx-font-weight: bold;" +
                                "-fx-font-family:'Segoe UI Variable';" +
                                "-fx-fill: #f0f0f2;" +
                                "-fx-font-size: 12px;");
                        text_def_local.setWrappingWidth(300);
                        vBoxDef.getChildren().add(text_def_local);
                        isExample = true;
                    } else {
                        Text text_def_local = new Text(line);
                        Text text_type = new Text("");
                        if (isExample) {
                            isExample = false;
                            text_type.setText("Example");
                            text_type.setStyle("-fx-font-weight: bold;" +
                                    "-fx-font-family:'Segoe UI Variable';" +
                                    "-fx-fill: #9595f9;" +
                                    "-fx-font-size: 12px;");
                            vBoxDef.getChildren().add(text_type);
                        }
                        text_def_local.setStyle(
                                "-fx-font-family:'Segoe UI Variable';" +
                                        "-fx-fill: #f0f0f2;" +
                                        "-fx-font-size: 12px;"
                        );
                        text_def_local.setWrappingWidth(DISPLAY_WORD_WIDTH - 30);
                        vBoxDef.getChildren().add(text_def_local);
                    }
                }
                scrollPane.setContent(vBoxDef);
            });
            hBoxPOS.getChildren().add(buttonPOS);
        }
    }

    private void setIrregularVerbsHBox(String word) throws SQLException {
        irregularVerbsHBox.getChildren().clear();
        ArrayList<String> irregularVerbs = Dictionary.getIrregularVerb(word);
        if (irregularVerbs != null) {
            String[] listTitleIrregularVerbs = {"Infinity", "Past simple", "Past participle"};
            for (int i = 0; i < irregularVerbs.size(); i++) {
                Text title = new Text(listTitleIrregularVerbs[i]);
                title.setStyle("-fx-font-weight: lighter;" +
                        "-fx-font-family:'Source Code Pro';" +
                        "-fx-fill: #0909d8;" +
                        "-fx-font-size: 7px;");
                String verb = irregularVerbs.get(i);
                Text textVerb1 = new Text(verb);
                Text textVerb2 = new Text();
                if (verb.contains("/")) {
                    textVerb1.setText(verb.split("/")[0]);
                    textVerb2.setText(verb.split("/")[1]);
                }
                textVerb1.setStyle("-fx-font-weight: bold;" +
                        "-fx-font-family:'Segoe UI Semibold';" +
                        "-fx-fill: #f0f0f2;" +
                        "-fx-font-size: 13px;");
                textVerb2.setStyle("-fx-font-weight: bold;" +
                        "-fx-font-family:'Segoe UI Semibold';" +
                        "-fx-fill: #f0f0f2;" +
                        "-fx-font-size: 13px;");
                VBox vBoxIrregularVerbs = new VBox(title, textVerb1);
                vBoxIrregularVerbs.setAlignment(Pos.CENTER);
                vBoxIrregularVerbs.setSpacing(-3);
                if (!textVerb2.getText().isEmpty()) vBoxIrregularVerbs.getChildren().add(textVerb2);
                irregularVerbsHBox.getChildren().add(vBoxIrregularVerbs);
            }
        }

    }

    private void setAntonymsFlowPane(String word) throws SQLException {
        antonymsFlowPane.getChildren().clear();
        ArrayList<String> arrayList = Dictionary.getAntonyms(word);
        if (arrayList != null) {
            for (String string : arrayList) {
                Button button = new Button(string);
                if (Dictionary.isExisted(string)) {
                    button.setId("antonymButton");
                    button.setOnMouseClicked(e -> {
                        try {
                            setNewWord(button.getText());
                        } catch (SQLException ignore) {
                        }
                    });
                } else {
                    button.setOnMouseMoved(e -> {
                        AppWindow.dialog.getStackPane().setOpacity(1);
                        AppWindow.dialog.getStackPane().toFront();
                        AppWindow.dialog.setText("Not in dictionary");
                        AppWindow.dialog.getStackPane().setOpacity(1);
                        AppWindow.dialog.getStackPane().setLayoutX(e.getSceneX());
                        AppWindow.dialog.getStackPane().setLayoutY(e.getSceneY());
                        System.out.println(button.getLayoutX());
                    });
                    button.setOnMouseExited(e -> {
                        AppWindow.dialog.getStackPane().setOpacity(0);
                    });
                    button.setId("antonymNotButton");
                }
                button.applyCss();
                antonymsFlowPane.getChildren().add(button);
            }
        }
    }

    private void setSynonymsFlowPane(String word) throws SQLException {
        synonymsFlowPane.getChildren().clear();
        ArrayList<String> arrayList = Dictionary.getSynonyms(word);
        if (arrayList != null) {
            for (String string : arrayList) {
                Button button = new Button(string);
                if (Dictionary.isExisted(string)) {
                    button.setId("synonymButton");
                    button.setOnMouseClicked(e -> {
                        try {
                            setNewWord(button.getText());
                        } catch (SQLException ignore) {
                        }
                    });
                } else {
                    button.setOnMouseMoved(e -> {
                        AppWindow.dialog.getStackPane().setOpacity(1);
                        AppWindow.dialog.getStackPane().toFront();
                        AppWindow.dialog.setText("Not in dictionary");
                        AppWindow.dialog.getStackPane().setOpacity(1);
                        AppWindow.dialog.getStackPane().setLayoutX(e.getSceneX());
                        AppWindow.dialog.getStackPane().setLayoutY(e.getSceneY());
                        System.out.println(button.getLayoutX());
                    });
                    button.setOnMouseExited(e -> {
                        AppWindow.dialog.getStackPane().setOpacity(0);
                    });
                    button.setId("synonymNotButton");
                }
                button.applyCss();
                synonymsFlowPane.getChildren().add(button);
            }
        }
    }

    private void setNewWord(String word) throws SQLException {
        ResultSet resultSet;
        scrollPane.setContent(null);
        try {
            /* Word in database has ' ' at last*/
            resultSet = Dictionary.getWord("^" + word + " $");
            this.word = new Word(resultSet);
            setAntonymsFlowPane(word);
            setSynonymsFlowPane(word);
            setIrregularVerbsHBox(word);
            setButtonPOS();
            textWord.setText(word);
            textPronunciation.setText(this.word.getPronunciation());

            Dictionary.putHistory(this.word.getWord());
        } catch (SQLException ignore) {
        }
    }

    private void setUpAntonymsLayout() {
        antonymsVBox.setTranslateX(TRANSLATE_GRAMMAR_X);
        antonymsVBox.setTranslateY(TRANSLATE_GRAMMAR_Y);
        antonymsVBox.setSpacing(5);
        antonymsFlowPane.setHgap(6);
        antonymsFlowPane.setVgap(5);
        antonymsFlowPane.setMaxWidth(240);
        antonymsFlowPane.setAlignment(Pos.BASELINE_LEFT);

    }

    private void setUpSynonymsLayout() {
        synonymsVBox.setTranslateX(TRANSLATE_GRAMMAR_X);
        synonymsVBox.setTranslateY(TRANSLATE_GRAMMAR_Y);
        synonymsVBox.setSpacing(5);
        synonymsFlowPane.setHgap(6);
        synonymsFlowPane.setVgap(5);
        synonymsFlowPane.setMaxWidth(240);
        synonymsFlowPane.setAlignment(Pos.BASELINE_LEFT);

    }

    private void setUpIrregularLayout() {
        irregularVerbsVBox.setTranslateY(TRANSLATE_GRAMMAR_Y);
        irregularVerbsVBox.setTranslateX(TRANSLATE_GRAMMAR_X);
        irregularVerbsHBox.setSpacing(10);
        irregularVerbsHBox.setMinWidth(230);
        irregularVerbsHBox.setMaxWidth(230);
        irregularVerbsHBox.setMinHeight(30);
        irregularVerbsHBox.setAlignment(Pos.BOTTOM_CENTER);
        irregularVerbsHBox.layout();
        irregularVerbsHBox.setTranslateY(5);

    }

    private void setUpWordLayout() {
        nullImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/null_icon_grammar_app.png"))));
        nullImage.setFitWidth(nullImage.getImage().getWidth() * 0.25);
        nullImage.setFitHeight(nullImage.getImage().getHeight() * 0.25);
        nullImage.setTranslateY(50);
        nullImage.setTranslateX(30);

        vBoxWord.setTranslateY(10);
        vBoxWord.setTranslateX(20);
        vBoxWord.getChildren().add(textWord);
        vBoxWord.getChildren().add(textPronunciation);
        vBoxWord.getChildren().add(hBoxPOS);
        vBoxWord.getChildren().add(scrollPane);

        hBoxPOS.setSpacing(10);
        hBoxPOS.setTranslateY(10);

        scrollPane.setTranslateY(20);
        scrollPane.setMinViewportWidth(570);
        scrollPane.setMinViewportHeight(250);
        scrollPane.setMaxWidth(570);
        scrollPane.setMaxHeight(250);

        textDefinition.setTranslateY(100);
    }

    private void setUpGrammarLayout() {
        grammarBox.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/grammar_box_app.png"))));
        grammarBox.setFitHeight(grammarBox.getImage().getHeight() * 0.34);
        grammarBox.setFitWidth(grammarBox.getImage().getWidth() * 0.34);

        BackgroundImage backgroundImage = new BackgroundImage(
                grammarBox.getImage(),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
        grammarPane.setBackground(new Background(backgroundImage));
        grammarPane.setMinSize(grammarBox.getFitWidth(), grammarBox.getFitHeight());
        grammarPane.setMaxSize(grammarBox.getFitWidth(), grammarBox.getFitHeight());
        grammarPane.getChildren().add(irregularVerbsVBox);
        grammarPane.getChildren().add(synonymsVBox);
        grammarPane.getChildren().add(antonymsVBox);
        grammarPane.setSpacing(20);
        grammarPane.setTranslateX(DISPLAY_WORD_WIDTH);
        grammarPane.setTranslateY(3);
    }
}

