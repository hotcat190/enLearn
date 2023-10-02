package graphics.app;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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

public abstract class AppWindow {
    /**
     * Basic component of window
     */
    protected Pane paneWindow = new Pane();
    protected Stage stageWindow;
    /**
     * App Window is equal 85% Laptop Window
     */
    double SCALE = 0.85;
    protected final double APP_HEIGHT = ScreenLaptop.SCREEN_HEIGHT * SCALE;
    protected final double APP_WIDTH = ScreenLaptop.SCREEN_WIDTH * SCALE;
    protected final double DELTA_Y = (APP_HEIGHT - APP_HEIGHT * 0.95) / 2;
    protected final double DELTA_X = (APP_WIDTH - APP_WIDTH * 0.95) / 2;

    /**
     * Real size, not calculate drop shadow
     */
    protected final double REAL_APP_WIDTH = APP_WIDTH - 2 * DELTA_X;
    protected final double REAL_APP_HEIGHT = APP_HEIGHT - 2 * DELTA_Y;
    /**
     * Control of app.
     */
    Button exitButton = new Button();
    Button minimizeButton = new Button();
    /**
     * HBox for horizontal layout of exit button & minimize button
     */
    HBox controlHBox = new HBox(minimizeButton, exitButton);

    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_appwindow_class.css")).toExternalForm();

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
        stageWindow=stageInit;
        try {
            stageWindow.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/icon_for_app.png"))));
            paneWindow.getChildren().add(controlHBox);
        } catch (Exception e) {
            System.out.println("Error at graphics.app.AppWindow and at method setAppWindow()!");
            System.out.println("Error when try add children!");
            System.out.println("Error: " + e);

        }
        /*
        Set attribute for all node.
         */
        try {
            setIdForAll();
            setCSSForAll();

            controlHBox.layout();
            controlHBox.setLayoutY(DELTA_Y);
            controlHBox.setLayoutX(DELTA_X + REAL_APP_WIDTH - controlHBox.getBoundsInLocal().getWidth());

            stageWindow.initStyle(StageStyle.TRANSPARENT);
            stageWindow.setTitle("enLearn");
            stageWindow.setX((ScreenLaptop.SCREEN_WIDTH - APP_WIDTH) / 2);
            stageWindow.setY((ScreenLaptop.SCREEN_HEIGHT - APP_HEIGHT) / 2);
            stageWindow.setWidth(APP_WIDTH);
            stageWindow.setHeight(APP_HEIGHT);
        } catch (Exception e) {
            System.out.println("Error at graphics.app.AppWindow:");
            System.out.println("Error when try set for children!");
            System.out.println("Error: " + e.toString());
        }
    }
    private void setIdForAll() {
        exitButton.setId("exitButton");
        controlHBox.setId("controlHBox");
        minimizeButton.setId("minimizeButton");
    }

    private void setCSSForAll() {
        paneWindow.getStylesheets().add(linkToCSS);
        controlHBox.getStylesheets().add(linkToCSS);

        exitButton.applyCss();
        minimizeButton.applyCss();
    }
    /**
     * Set event for button.
     */
    protected void applyEventAppWindow() {
        exitButton.setOnMouseClicked((l) -> {
            Platform.exit();
        });
        minimizeButton.setOnMouseClicked((l) -> {
            stageWindow.setIconified(true);
        });
    }
}
