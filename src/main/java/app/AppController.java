package app;

import controller.model.Listener;
import graphics.StandardParameter;
import graphics.app.AppWindow;
import graphics.app.dashboard.FirstDashboard;
import graphics.app.dashboard.FourthDashBoard;
import graphics.app.dashboard.SecondDashboard;
import graphics.app.dashboard.ThirdDashboard;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.sql.Time;

public class AppController extends AppWindow implements Decorator, Listener {
    private final static MenuController menuController = MenuController.menuController;
    private final FirstDashboard homeDashboard;
    private final SecondDashboard myDictionaryDashboard;
    private final ThirdDashboard translateDashboard;
    private final FourthDashBoard fourthDashBoard = FourthDashBoard.fourthDashBoard;
    private final Pane current = new Pane();
    private final Stage stage;
    private final StackPane layout = new StackPane(current, menuController);
    private final Rectangle rectSelected = new Rectangle(3 * StandardParameter.SCALE, 18 * StandardParameter.SCALE);


    public AppController(Stage stage) {
        super(stage);
        this.stage = stage;
        Scene scene = new Scene(this);
        scene.setFill(Color.TRANSPARENT);
        this.stage.setScene(scene);
        try {
            homeDashboard = new FirstDashboard();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        myDictionaryDashboard = new SecondDashboard();
        translateDashboard = new ThirdDashboard();

        setId();
        setCSS();
        set();
        setListener();
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        rectSelected.setId("menu-controller__rectangle--selected");

    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_app_controller.css"));
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        this.getChildren().add(layout);
        rectSelected.setLayoutX(menuController.getLayoutX() + menuController.getTranslateX());
        current.getChildren().add(fourthDashBoard);

        layout.getChildren().add(rectSelected);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setLayoutX(DELTA_X);
        layout.setLayoutY(DELTA_Y);
        layout.setPrefHeight(REAL_APP_HEIGHT);
        layout.setPrefWidth(REAL_APP_WIDTH-100);
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

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
        menuController.getHomeButton().setOnAction(e -> {
            current.getChildren().clear();
            current.getChildren().add(homeDashboard);
            menuController.toFront();
            rectSelected.toFront();
            new Timeline(
                    new KeyFrame(
                            Duration.millis(300),
                            new KeyValue(rectSelected.translateYProperty(), -47)
                    )
            ).play();

        });
        menuController.getTranslateButton().setOnAction(e -> {
            current.getChildren().clear();
            current.getChildren().add(translateDashboard);
            menuController.toFront();
            rectSelected.toFront();
            new Timeline(
                    new KeyFrame(
                            Duration.millis(300),
                            new KeyValue(rectSelected.translateYProperty(), 47)
                    )
            ).play();
        });
        menuController.getMyDictionaryButton().setOnAction(e -> {
            current.getChildren().clear();
            current.getChildren().add(myDictionaryDashboard);
            menuController.toFront();
            rectSelected.toFront();
            new Timeline(
                    new KeyFrame(
                            Duration.millis(300),
                            new KeyValue(rectSelected.translateYProperty(), 0)
                    )
            ).play();
        });

    }
}
