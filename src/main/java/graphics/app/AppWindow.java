package graphics.app;

import graphics.control.Dialog;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

public class AppWindow {
    /**
     * Basic component of window
     */
    protected Pane paneWindow = new Pane();
    protected Stage stageWindow;
    /**
     * App Window is equal 85% Laptop Window
     */
    public static double SCALE = 0.85;
    public final static double APP_HEIGHT = ScreenLaptop.SCREEN_HEIGHT * SCALE;
    public final static double APP_WIDTH = ScreenLaptop.SCREEN_WIDTH * SCALE;
    public final static double DELTA_Y = (APP_HEIGHT - APP_HEIGHT * 0.95) / 2;
    public final static double DELTA_X = (APP_WIDTH - APP_WIDTH * 0.95) / 2;

    /**
     * Real size, not calculate drop shadow
     */
    protected final double REAL_APP_WIDTH = APP_WIDTH - 2 * DELTA_X;
    protected final double REAL_APP_HEIGHT = APP_HEIGHT - 2 * DELTA_Y;
    /**
     * Control of app.
     */
    protected Button exitButton = new Button();
    protected Button minimizeButton = new Button();
    /**
     * HBox for horizontal layout of exit button & minimize button
     */
    protected HBox controlHBox = new HBox(minimizeButton, exitButton);
    private final Rectangle moveStage = new Rectangle(APP_WIDTH - 2 * DELTA_X, 30, Color.TRANSPARENT);

    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_appwindow_class.css")).toExternalForm();
    /**
     * Dialog global.
     */
    protected Dialog dialog = new Dialog();
    /**
     * Get pane of window to link current page.
     */
    public Pane getPaneWindow() {
        return paneWindow;
    }

    /**
     * Constructor for AppWindow class.
     */
    protected void setAppWindow(Stage stageInit) {
        stageWindow = stageInit;
        try {
            stageWindow.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/icon_for_app.png"))));
            paneWindow.getChildren().add(moveStage);
            paneWindow.getChildren().add(controlHBox);
            paneWindow.getChildren().add(dialog.getStackPane());
        } catch (Exception e) {
            System.out.println("Error at graphics.app.AppWindow and at method setAppWindow()!");
            System.out.println("Error when try add children!");
            System.out.println("Error: " + e);

        }
        /*
        Set attribute for all node.
         */
        try {
            setId();
            setCSS();

            controlHBox.layout();
            controlHBox.setLayoutY(DELTA_Y);
            controlHBox.setLayoutX(DELTA_X + REAL_APP_WIDTH - controlHBox.getBoundsInLocal().getWidth());

            dialog.getStackPane().setOpacity(0);

            moveStage.setLayoutX(DELTA_X);
            moveStage.setLayoutY(DELTA_Y);

            stageWindow.initStyle(StageStyle.TRANSPARENT);
            stageWindow.setTitle("enLearn");
            stageWindow.setX((ScreenLaptop.SCREEN_WIDTH - APP_WIDTH) / 2);
            stageWindow.setY((ScreenLaptop.SCREEN_HEIGHT - APP_HEIGHT) / 2);
            stageWindow.setWidth(APP_WIDTH);
            stageWindow.setHeight(APP_HEIGHT);
        } catch (Exception e) {
            System.out.println("Error at graphics.app.AppWindow:");
            System.out.println("Error when try set for children!");
            System.out.println("Error: " + e);
        }
    }

    private void setId() {
        exitButton.setId("exitButton");
        controlHBox.setId("controlHBox");
        minimizeButton.setId("minimizeButton");
    }

    private void setCSS() {
        paneWindow.getStylesheets().add(linkToCSS);
        controlHBox.getStylesheets().add(linkToCSS);

        exitButton.applyCss();
        minimizeButton.applyCss();
    }

    /**
     * Set event for button.
     */
    protected void applyEventAppWindow() {
        moveStage.setOnMousePressed(l -> {
            paneWindow.setOnMousePressed(e -> {
                paneWindow.setOnMouseDragged(e1 -> {
                    stageWindow.setX(e1.getScreenX() - e.getSceneX());
                    stageWindow.setY(e1.getScreenY() - e.getSceneY());
                });
            });
        });
        exitButton.setOnMouseClicked((l) -> {
            Platform.exit();
            System.exit(0);
        });
        minimizeButton.setOnMouseClicked((l) -> {
            stageWindow.setIconified(true);
        });
    }
}
