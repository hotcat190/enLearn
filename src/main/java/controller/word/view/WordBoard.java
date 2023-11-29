package controller.word.view;

import graphics.animation.Listener;
import controller.my_dictionary.data.MyDictionaryTableData;
import controller.my_dictionary.data.MyNewWord;
import controller.word.AntonymsController;
import controller.word.IrregularVerbsController;
import controller.word.PartOfSpeechController;
import controller.word.SynonymsController;
import controller.search.view.SearchEngineHistoryView;
import graphics.style.StyleHelper;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import graphics.load.SingletonAnimationLoading;
import sql.dictionary.SQLMyDictionary;
import sql.statistic.SQLStatisticWeek;
import sql.user.SQLUser;
import sql.dictionary.SQLDictionary;
import controller.word.data.Word;
import graphics.StandardParameter;
import controller.search.view.SearchEngineView;
import graphics.style.Decorator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class WordBoard extends VBox implements Decorator, Listener, SingletonAnimationLoading {
    /**
     * Controllers.
     */
    private final PartOfSpeechController partOfSpeechController = new PartOfSpeechController();
    private final IrregularVerbsController irregularVerbsController = new IrregularVerbsController();
    private final SynonymsController synonymsController = new SynonymsController(163, 153, 15, 15, Color.rgb(38, 247, 142));
    private final AntonymsController antonymsController = new AntonymsController(163, 153, 15, 15, Color.rgb(234, 164, 40));
    private final Button speechButton = new Button();
    private final Button addButton = new Button();


    /**
     * Word.
     */
    public Word word = null;

    /**
     * View.
     */
    private final Label textWord = new Label();
    private final Label textPronunciation = new Label();
    private final ImageView nullIcon = new ImageView();
    private final static WordBoard INSTANCE = new WordBoard();
    private final VBox mainVBox = new VBox();
    private final HBox modifierHBox = new HBox();
    private final HBox hBoxSpeech = new HBox(textPronunciation, speechButton, addButton);


    /**
     * Singleton.
     */

    public static WordBoard getInstance() {
        return INSTANCE;
    }

    private WordBoard() {
        setId();
        setCSS();
        set();
        setListener();
    }


    @Override
    public void setId() {
        addButton.setId("word-board__button--add");
        mainVBox.setId("word-board__pane--layout");
        textWord.setId("word-board__text--word");
        textPronunciation.setId("word-board__text--pronunciation");
        speechButton.setId("word-board__button--speech");

        hBoxSpeech.setId("word-board__hbox--speech");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_word_board.css"));
        this.applyCss();
        textWord.applyCss();
        textPronunciation.applyCss();
    }

    public void load(Word word) {
        this.word = word;
        irregularVerbsController.loadData(word);
        partOfSpeechController.loadData(word);
        synonymsController.loadData(word);
        antonymsController.loadData(word);
    }


    @Override
    public void set() {
        textWord.setPadding(new Insets(10, 0, 0, 0));

        nullIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/image/null_icon_grammar_app.png"
        ))));
        nullIcon.setFitHeight(nullIcon.getImage().getHeight() * 0.3);
        nullIcon.setFitWidth(nullIcon.getImage().getWidth() * 0.3);
        nullIcon.setTranslateX(30);

        mainVBox.setMinSize(528 * StandardParameter.SCALE, 368 * StandardParameter.SCALE);
        mainVBox.setMaxSize(528 * StandardParameter.SCALE, 368 * StandardParameter.SCALE);
//        this.getChildren().add(nullIcon);

        speechButton.setVisible(false);
        addButton.setVisible(false);

        hBoxSpeech.setAlignment(Pos.CENTER_LEFT);
        hBoxSpeech.setSpacing(10);
        HBox hBox_contronym = new HBox(synonymsController.getView(), antonymsController.getView());
        hBox_contronym.setSpacing(100 * StandardParameter.SCALE);

        this.getChildren().addAll(mainVBox, modifierHBox);
        this.setAlignment(Pos.TOP_LEFT);
        this.setSpacing(30);

        mainVBox.getChildren().add(textWord);
        mainVBox.getChildren().add(hBoxSpeech);
        mainVBox.getChildren().addAll(partOfSpeechController.getView());
        mainVBox.setSpacing(10);
        mainVBox.setAlignment(Pos.TOP_LEFT);

        modifierHBox.setSpacing(17);
        modifierHBox.setAlignment(Pos.CENTER_LEFT);
        modifierHBox.getChildren().addAll(
                synonymsController.getView(),
                antonymsController.getView(),
                irregularVerbsController.getView());

    }

    public void lastUpdate(String selectionWord) {
        setSingletonOnLoading();
        new Thread(() -> {
            try {
                load(new Word(selectionWord));
                SQLUser.getInstance().increaseTotalWords();
                SQLStatisticWeek.getInstance().increaseTotalWordsAndTotalTimes();
                SQLDictionary.getInstance().putHistory(word.getWord());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            new Thread(() -> Platform.runLater(() -> {
                try {
                    update();

                    textWord.setBackground(Background.fill(Color.TRANSPARENT));
                    removeSingletonLoading();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            })).start();
        }).start();
    }

    @Override
    public void update() throws SQLException {
        irregularVerbsController.updateView();
        textWord.setText(word.getWord().replaceFirst(word.getWord().substring(0, 1), word.getWord().substring(0, 1).toUpperCase()));
        textPronunciation.setText(word.getPronunciation());
        partOfSpeechController.updateView();
        synonymsController.updateView(this);
        antonymsController.updateView(this);
        if (word != null) {
            nullIcon.setVisible(false);
            speechButton.setVisible(true);
            addButton.setVisible(true);
        }
        SearchEngineHistoryView.getInstance().update();
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void setListener(Object object) {

    }

    @Override
    public void setListener() {
        speechButton.setOnMouseClicked(e -> {
            System.out.println("Media is " + word.getWord());
            Media media = new Media(SQLDictionary.getInstance().getMedia(word.getWord().trim()));
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        });
        SearchEngineView searchEngineView = SearchEngineView.getInstance();
        searchEngineView.getListView().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> lastUpdate(searchEngineView.getWordSelected()));
        searchEngineView.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                lastUpdate(searchEngineView.getWordSelected());
            }
        });
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            MyNewWord myNewWord = new MyNewWord(SQLMyDictionary.getInstance().getOrder(),
                    word.getWord(),
                    word.getPronunciation(),
                    new Date(System.currentTimeMillis()),
                    Time.valueOf(LocalTime.now()), word.getPriorityDefinition()
            );
            SQLMyDictionary.getInstance().add(myNewWord);
            MyDictionaryTableData.getInstance().add(myNewWord);
        });
    }

    @Override
    public void setSingletonOnLoading() {
        textWord.setTranslateY(10);
        textPronunciation.setTranslateY(10);
        hBoxSpeech.setTranslateY(10);
        partOfSpeechController.getPartOfSpeechView().setTranslateY(10);

        textWord.setText(" ".repeat(10));
        textPronunciation.setText(" ".repeat(10));

        textWord.setStyle("-fx-background-color: rgb(56,56,56);");
        textPronunciation.setStyle("-fx-background-color: rgb(56,56,56);");
        partOfSpeechController.getPartOfSpeechView().setSingletonOnLoading();
        hBoxSpeech.setMaxWidth(400);
        hBoxSpeech.setStyle(
                "    -fx-background-color: rgb(56,56,56);");
        for (Node node : hBoxSpeech.getChildren()) {
            node.setOpacity(0);
        }
    }

    @Override
    public void removeSingletonLoading() {
        textWord.setTranslateY(0);
        textPronunciation.setTranslateY(0);
        hBoxSpeech.setTranslateY(0);
        partOfSpeechController.getPartOfSpeechView().setTranslateY(0);

        partOfSpeechController.getPartOfSpeechView().removeSingletonLoading();
        hBoxSpeech.setStyle(
                "    -fx-background-color: transparent;");
        textWord.setStyle("-fx-background-color: transparent;");
        textPronunciation.setStyle("-fx-background-color: transparent;");
        for (Node node : hBoxSpeech.getChildren()) {
            node.setOpacity(1);
        }
    }
}
