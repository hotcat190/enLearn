package graphics;

import javafx.animation.AnimationTimer;
import sql.statistic.SQLStatisticWeek;
import sql.statistic.SQLTimeStudy;
import graphics.control.Dialog;
import graphics.style.StyleHelper;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;
import java.util.*;

/**
 * This is package for graphics.
 *
 * @author Hoang Duc Bach
 * @since 24/09/2023
 */

class ScreenLaptop {
    /**
     * Get current width and height of laptop to align.
     */
    final static double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    final static double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
}

public class Canvas extends Pane {

    /**
     * Basic component of window
     */
    protected Stage stageWindow;
    /**
     * App Window is equal 85% Laptop Window
     */
    public static double SCALE = 0.9;
    public final static double APP_HEIGHT = 820*SCALE;
    public final static double APP_WIDTH = 1450*SCALE;
    public final static double DELTA_Y = 0;
    public final static double DELTA_X = 0;

    /**
     * Real size, not calculate drop shadow
     */
    public final static double REAL_APP_WIDTH = APP_WIDTH - 2 * DELTA_X;
    public final static double REAL_APP_HEIGHT = APP_HEIGHT - 2 * DELTA_Y;

    protected Button exitButton = new Button();
    protected Button minimizeButton = new Button();
    protected HBox controlHBox = new HBox(minimizeButton, exitButton);
    private final Rectangle moveStage = new Rectangle(APP_WIDTH - 2 * DELTA_X, 30, Color.TRANSPARENT);

    /**
     * Dialog global.
     */
    public static Dialog dialog = new Dialog();

    protected void setAppWindow(Stage stageInit) {
        stageWindow = stageInit;

    }

    public Canvas(Stage stage) {
        super();
        this.stageWindow = stage;


        setId();
        setCSS();
        set();
        setListener();


    }

    /**
     * Set id.
     */
    private void setId() {
        exitButton.setId("canvas__button--exit");
        controlHBox.setId("canvas__hbox--control");
        minimizeButton.setId("canvas__button--minimize");
        this.setId("canvas__pane--layout");
    }

    private void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_canvas.css"));
        exitButton.applyCss();
        minimizeButton.applyCss();
    }

    /**
     * Set layout.
     */
    private void set() {
        stageWindow.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/logo_icon.png"))));
        this.getChildren().add(moveStage);
        this.getChildren().add(controlHBox);
        this.getChildren().add(dialog.getLayout());

        controlHBox.layout();
        controlHBox.setLayoutY(DELTA_Y);
        controlHBox.setLayoutX(DELTA_X + REAL_APP_WIDTH - controlHBox.getBoundsInLocal().getWidth() - 80);

        dialog.getLayout().setOpacity(0);

        moveStage.setLayoutX(DELTA_X);
        moveStage.setLayoutY(DELTA_Y);

        stageWindow.initStyle(StageStyle.TRANSPARENT);
        stageWindow.setTitle("enLearn");
        stageWindow.setX((ScreenLaptop.SCREEN_WIDTH - APP_WIDTH) / 2);
        stageWindow.setY((ScreenLaptop.SCREEN_HEIGHT - APP_HEIGHT) / 2);
        stageWindow.setWidth(APP_WIDTH);
        stageWindow.setHeight(APP_HEIGHT);
    }

    /**
     * Update layout.
     */
    private void setListener() {
        moveStage.setOnMousePressed(l -> {
            this.setOnMousePressed(e -> {
                this.setOnMouseDragged(e1 -> {
                    stageWindow.setX(e1.getScreenX() - e.getSceneX());
                    stageWindow.setY(e1.getScreenY() - e.getSceneY());
                });
            });
        });
        exitButton.setOnMouseClicked((l) -> {
            try {
                SQLTimeStudy.update();
                SQLStatisticWeek.updateTimes();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Platform.exit();
            System.exit(0);
        });
        minimizeButton.setOnMouseClicked((l) -> {
            stageWindow.setIconified(true);
        });
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                controlHBox.toFront();
            }
        }.start();
    }

    public Stage getStageWindow() {
        return stageWindow;
    }
}
