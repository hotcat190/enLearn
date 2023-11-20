package graphics.app.dashboard;

import controller.model.Listener;
import controller.my_dictionary.MyDictionaryTableController;
import data.my_dictionary.MyDictionaryTableData;
import data.my_dictionary.MyNewWord;
import data.my_dictionary.SQLMyDictionary;
import graphics.StandardParameter;
import graphics.app.dashboard.home.NotationButton;
import graphics.engine.SearchEngine;
import graphics.engine.TranslateEngine;
import graphics.app.AppWindow;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import view.progress.ProgressBoard;
import view.statistic.StatisticBoard;
import view.word.WordBoard;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;


public class FirstDashboard extends Dashboard {
    private final HBox hBox = new HBox();
    private final VBox part1 = new VBox();
    private final VBox part2 = new VBox();
    /**
     * Component of dashboard1.
     */
    private final SearchEngine searchEngine = new SearchEngine();
    private final WordBoard wordBoard = new WordBoard();
    private final ProgressBoard progressBoard = new ProgressBoard();
    private final StatisticBoard statisticBoard = new StatisticBoard();

    private final Text textProgress = new Text("Progress");
    private final NotationButton notationButton = NotationButton.notationButton;
    private final Button addButton = new Button();
    /**
     * Parameter.
     */
    public static final String LINK_CSS = Objects.requireNonNull(FirstDashboard.class.getResource("/css/style_for_dashboard1_class.css")).toExternalForm();

    public FirstDashboard() throws SQLException {

        setId();
        setCSS();
        set();
        setListener();
    }

    @Override
    public void setId() {
        this.setId("pane");
        addButton.setId("first-dashboard__button--add");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_dashboard1_class.css"));
    }

    @Override
    public void setTitle() {
        textTop.setText("Home");
        textBottom.setText("Dashboard");
        textTop.setStyle("-fx-font-family: 'Segoe UI Semibold';" +
                "-fx-font-size: 14;" +
                "-fx-fill: #898b8c;");
        textBottom.setStyle("-fx-font-family: 'Segoe UI Variable'; " +
                "-fx-font-size: 23;" +
                "-fx-fill: #1f2f3f;" +
                "-fx-font-weight: bold;");
        textBottom.setTranslateY(-10);
        getTitle().setTranslateX(20);
        getTitle().setTranslateY(-10);
    }

    @Override
    public void set() {
        this.getChildren().add(hBox);
        this.getChildren().add(searchEngine.getPaneSearch());
        this.getChildren().add(notationButton);
        this.getChildren().add(addButton);

        notationButton.setLayoutX(500);
        notationButton.setLayoutY(350);


        setTitle();

        textProgress.setStyle("-fx-fill: #1f2f3f;" +
                "-fx-font-family: 'Segoe UI Variable';" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20px");


        hBox.getChildren().addAll(
                part1, part2
        );

        part1.getChildren().addAll(
                getTitle(),
                wordBoard.getLayout()
        );
        part2.getChildren().addAll(
                textProgress,
                progressBoard.progressController.progressView,
                statisticBoard
        );

        part2.setTranslateX(100 * StandardParameter.SCALE);
        part2.setSpacing(20);

        part1.setSpacing(30 * StandardParameter.SCALE);

        hBox.setLayoutY(24 * StandardParameter.SCALE + AppWindow.DELTA_Y);
        hBox.setLayoutX(AppWindow.DELTA_X+20);
        hBox.setLayoutY(5 * StandardParameter.SCALE + AppWindow.DELTA_Y);
        hBox.setSpacing(100 * StandardParameter.SCALE);


        searchEngine.getPaneSearch().setLayoutX(AppWindow.DELTA_X + 20 * StandardParameter.SCALE + 20);
        searchEngine.getPaneSearch().setLayoutY(50 + AppWindow.DELTA_Y);
        searchEngine.getPaneSearch().toFront();

        addButton.setLayoutX(searchEngine.getPaneSearch().getLayoutX());
        addButton.setLayoutY(searchEngine.getPaneSearch().getLayoutY() + 50);


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

    /**
     * Set listener with other object.
     */
    @Override
    public void setListener(Object object) {

    }

    @Override
    public void setListener() {
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    statisticBoard.update();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
        wordBoard.setListener(searchEngine);
        searchEngine.setEvent();
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            MyNewWord myNewWord = new MyNewWord(SQLMyDictionary.getOrder(),
                    wordBoard.word.getWord(),
                    wordBoard.word.getPronunciation(),
                    new Date(System.currentTimeMillis()),
                    wordBoard.word.getPriorityDefinition()
            );
            SQLMyDictionary.addToMyDictionary(myNewWord);
            MyDictionaryTableData.myDictionaryTableData.add(myNewWord);
        });
    }

}
