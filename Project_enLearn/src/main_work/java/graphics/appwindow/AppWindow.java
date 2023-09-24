package graphics.appwindow;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
     * App Window is equal 35/48 Laptop Window
     */
    private final double APP_HEIGHT = ScreenLaptop.SCREEN_HEIGHT * 35.0 / 48.0;
    private final double APP_WIDTH = ScreenLaptop.SCREEN_WIDTH * 35.0 / 48.0;
    private Rectangle connerRect;
    /**
     * Main components of app window, edit recently so public.
     */
    public Group group;
    public Scene scene;
    public Stage stage;

    /**
     * Constructor for AppWindow class
     */
    public AppWindow(Stage stageInit) {
        connerRect = new Rectangle(APP_WIDTH, APP_HEIGHT);
        stage = stageInit;
        group = new Group(connerRect);
        scene = new Scene(group);
        stage.initStyle(StageStyle.TRANSPARENT);
        try {
            connerRect.setFill(Color.LIGHTPINK);
            connerRect.setHeight(APP_HEIGHT);
            connerRect.setWidth(APP_WIDTH);
            connerRect.setArcWidth(50);
            connerRect.setArcHeight(50);
            scene.setFill(Color.TRANSPARENT);

            stage.setY((ScreenLaptop.SCREEN_HEIGHT - APP_HEIGHT) / 2);
            stage.setX((ScreenLaptop.SCREEN_WIDTH - APP_WIDTH) / 2);
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("Error at graphics.appwindow.AppWindow:");
            System.out.println("Error: " + e.toString());
        }
    }
}
