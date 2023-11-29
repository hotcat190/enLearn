package app.dashboard.home;

import controller.progress.ProgressController;
import controller.search.SearchEngineController;
import graphics.canvas.Canvas;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import graphics.StandardParameter;
import app.dashboard.model.Dashboard;
import graphics.style.StyleHelper;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import controller.statistic.view.StatisticBoard;
import controller.word.view.WordBoard;

import java.sql.SQLException;


public class HomeDashboard extends Dashboard {
    /**
     * Singleton.
     */
    private static final HomeDashboard INSTANCE = new HomeDashboard();
    /**
     * Views.
     */
    private final HBox hBox = new HBox();
    private final StackPane part1 = new StackPane();
    private final VBox part2 = new VBox();
    /**
     * Components.
     */
    private final SearchEngineController searchEngineController = SearchEngineController.getInstance();
    private final WordBoard wordBoard = WordBoard.getInstance();
    private final ProgressController progressController = ProgressController.getInstance();
    private final StatisticBoard statisticBoard = StatisticBoard.getInstance();
    private final NotationButton notationButton = NotationButton.getInstance();

    /**
     * Views
     */
    private final Text textProgress = new Text("Progress");
    private final GoTestCard goTestCard = GoTestCard.getInstance();

    public HomeDashboard() {
    }

    @Override
    public void setId() {
        this.setId("pane");
        part1.setId("first-dashboard__vbox--part1");
        part2.setId("first-dashboard__vbox--part2");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_home_dashboard.css"));
    }

    @Override
    public void setTitle() {
        textTop.setText("Home");
        textBottom.setText("Dashboard");
        textTop.setStyle("-fx-font-family: 'Segoe UI Semibold';" +
                "-fx-font-size: 14;" +
                "-fx-fill: #ffffff;");
        textBottom.setStyle("-fx-font-family: 'Segoe UI Variable'; " +
                "-fx-font-size: 23;" +
                "-fx-fill: #ffffff;" +
                "-fx-font-weight: bold;");
        textBottom.setTranslateY(-10);

        getTitle().setTranslateY(-5);
    }

    @Override
    public void set() {
        this.getChildren().add(hBox);
        this.getChildren().add(getTitle());
        this.getChildren().add(notationButton);
        notationButton.setLayoutX(500);
        notationButton.setLayoutY(350);

        setTitle();

        textProgress.setStyle("-fx-fill: #1f2f3f;" +
                "-fx-font-family: 'Segoe UI Variable';" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20px");

        final HBox h_box_util = new HBox(progressController.getView(), goTestCard);
        h_box_util.setSpacing(20);
        hBox.setTranslateY(30);
        hBox.getChildren().addAll(
                part1, part2
        );

        part1.getChildren().addAll(
                searchEngineController.getView(),
                wordBoard
        );
        part2.getChildren().addAll(
                h_box_util,
                statisticBoard
        );

        part2.setSpacing(20);


        wordBoard.setTranslateY(60);
        part1.setAlignment(Pos.TOP_LEFT);
        part1.setMinWidth(610 * StandardParameter.SCALE);
        part1.setMaxWidth(610 * StandardParameter.SCALE);
        hBox.setLayoutX(Canvas.DELTA_X + 35);
        hBox.setLayoutY(5 * StandardParameter.SCALE + Canvas.DELTA_Y+10);
        hBox.setSpacing(20);

        getTitle().setLayoutX(Canvas.DELTA_X + 35);
        searchEngineController.getView().setLayoutX(Canvas.DELTA_X + 35);
        searchEngineController.getView().setLayoutY(50 + Canvas.DELTA_Y);
        searchEngineController.getView().toFront();

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
     * Listeners.
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
        wordBoard.setListener(searchEngineController);


    }

    public static HomeDashboard getInstance() {
        return INSTANCE;
    }

    @Override
    public void load() {
        setId();
        setCSS();
        set();
        setListener();
    }
}
