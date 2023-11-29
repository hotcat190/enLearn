package app.controller;

import app.dashboard.home.GoTestCard;
import app.dashboard.model.Dashboard;
import app.dashboard.model.DashboardFactory;
import app.dashboard.model.DashboardType;
import graphics.animation.Listener;
import graphics.canvas.Canvas;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.sql.SQLException;

public class AppController extends Pane implements Decorator, Listener {
    private final static MenuController menuController = MenuController.getInstance();
    private final Dashboard homeDashboard = DashboardFactory.createDashboard(DashboardType.HOME_DASHBOARD);
    private final Dashboard myDictionaryDashboard = DashboardFactory.createDashboard(DashboardType.MY_DICTIONARY_DASHBOARD);
    private final Dashboard translateDashboard = DashboardFactory.createDashboard(DashboardType.TRANSLATE_DASHBOARD);
    private final Dashboard testDashboard = DashboardFactory.createDashboard(DashboardType.TEST_DASHBOARD);
    private final Dashboard gameDashboard = DashboardFactory.createDashboard(DashboardType.GAME_DASHBOARD);

    private final Pane current = new Pane();
    private final StackPane layout = new StackPane(current, menuController);


    public AppController() {
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
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_controller.css"));
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
        layout.setPrefHeight(Canvas.REAL_APP_HEIGHT);
        layout.setPrefWidth(Canvas.REAL_APP_WIDTH - 100);
    }

    /**
     * Update layout.
     */
    @Override
    public void update() throws SQLException {

    }

    public Pane getCurrent() {
        return current;
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
        menuController.getGameButton().setOnAction(e -> {
            current.getChildren().clear();
            current.getChildren().add(gameDashboard);
            menuController.toFront();
        });
        GoTestCard.getInstance().getGoButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            current.getChildren().clear();
            current.getChildren().add(testDashboard);
            menuController.toFront();
        });

    }

    public void load() {
        homeDashboard.load();
        translateDashboard.load();
        myDictionaryDashboard.load();
        testDashboard.load();
        gameDashboard.load();

        setId();
        setCSS();
        set();
        setListener();
    }
}
