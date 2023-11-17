package graphics.app.dashboard;

import controller.model.Listener;
import graphics.StandardParameter;
import graphics.engine.SearchEngine;
import graphics.engine.TranslateEngine;
import graphics.app.AppWindow;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.animation.AnimationTimer;
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
    /**
     * Parameter.
     */
    public static final String LINK_CSS = Objects.requireNonNull(FirstDashboard.class.getResource("/css/style_for_dashboard1_class.css")).toExternalForm();
    private final Text textTop1 = new Text("Home");
    private final Text textBottom1 = new Text("Dashboard");

    public FirstDashboard() throws SQLException {
        setId();
        setCSS();
        set();
        setListener();
    }

    @Override
    public void setId() {
        this.setId("pane");
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
        textTop1.setStyle("-fx-font-family: 'Segoe UI Semibold';" +
                "-fx-font-size: 14;" +
                "-fx-fill: #898b8c;");
        textBottom1.setStyle("-fx-font-family: 'Segoe UI Variable'; " +
                "-fx-font-size: 23;" +
                "-fx-fill: #1f2f3f;" +
                "-fx-font-weight: bold;");
//        getTitle().setLayoutX(AppWindow.DELTA_X + 80);
//        getTitle().setLayoutY(AppWindow.DELTA_Y + 15);
    }

    @Override
    public VBox getTitle() {
        return textLayout;
    }

    @Override
    public void set() {
        VBox vBox = new VBox(textTop1, textBottom1);
        vBox.setSpacing(-10);
        vBox.setTranslateX(25);

        this.getChildren().add(hBox);
        this.getChildren().add(searchEngine.getPaneSearch());
        textProgress.setStyle("-fx-fill: #1f2f3f;" +
                "-fx-font-family: 'Segoe UI Variable';" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20px");

        setTitle();

        hBox.getChildren().addAll(
                part1, part2
        );

        part1.getChildren().addAll(
                vBox,
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
//        translateEngine.setEvent(dialog, this);
    }

}
