package app;

import controller.model.Listener;
import graphics.StandardParameter;
import graphics.Canvas;
import app.dashboard.translator.TranslatorDashboard;
import app.dashboard.my_dictionary.MyDictionaryDashboard;
import app.dashboard.home.HomeDashboard;
import app.dashboard.test.TestDashboard;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;

public class AppController extends Canvas implements Decorator, Listener {
    private final static MenuController menuController = MenuController.getInstance();
    private final HomeDashboard homeDashboard;
    private final MyDictionaryDashboard myDictionaryDashboard;
    private final TranslatorDashboard translateDashboard;
    private final TestDashboard testDashboard = TestDashboard.getInstance();
    private final Pane current = new Pane();
    private final Stage stage;
    private final StackPane layout = new StackPane(current, menuController);


    public AppController(Stage stage) {
        super(stage);
        this.stage = stage;
        Scene scene = new Scene(this);
        scene.setFill(Color.TRANSPARENT);
        this.stage.setScene(scene);
        homeDashboard = HomeDashboard.getInstance();
        myDictionaryDashboard = new MyDictionaryDashboard();
        translateDashboard = new TranslatorDashboard();

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


        menuController.getHomeButton().fire();
        current.getChildren().add(homeDashboard);

        layout.setAlignment(Pos.CENTER_LEFT);
        menuController.setTranslateX(-50);
        menuController.setTranslateY(-20);
        layout.setTranslateX(50);
        layout.setLayoutY(20);
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
        });
        menuController.getMyDictionaryButton().setOnAction(e -> {
            current.getChildren().clear();
            current.getChildren().add(myDictionaryDashboard);
            menuController.toFront();
        });
        menuController.getTranslateButton().setOnAction(e -> {
            current.getChildren().clear();
            current.getChildren().add(translateDashboard);
            menuController.toFront();
        });

        menuController.getTestButton().setOnAction(e -> {
            current.getChildren().clear();
            current.getChildren().add(testDashboard);
            menuController.toFront();
        });
    }
}
