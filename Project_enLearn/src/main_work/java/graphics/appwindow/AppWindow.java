package graphics.appwindow;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utility.calendar.Time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
     * App Window is equal 85% Laptop Window
     */
    private final double SCALE = 0.85;
    private final double APP_HEIGHT = ScreenLaptop.SCREEN_HEIGHT * SCALE;
    private final double APP_WIDTH = ScreenLaptop.SCREEN_WIDTH * SCALE;
    private final double DELTA_Y = (APP_HEIGHT - APP_HEIGHT * 0.95) / 2;
    private final double DELTA_X = (APP_WIDTH - APP_WIDTH * 0.95) / 2;

    /**
     * Set background
     */
    private final Pane paneForBackground = new Pane();

    /**
     * Main components of app window, edit recently so public.
     */
    public Pane group = new Pane();
    public Scene scene = new Scene(paneForBackground);
    public Stage stage;
    public Scene scene2;
    /**
     * Button Learn to start
     */
    private final Button learnButton = new Button("Learn");

    /**
     * Time to display day name, time.
     */
    private final VBox vBoxForTime = new VBox();
    private final Label labelForDay = new Label();
    private final Label labelForTime = new Label();
    private final StackPane stackPaneForDate = new StackPane();
    private final Label labelForMonth = new Label();
    private final Label labelForDate = new Label();
    private final ImageView themeGradientDay = new ImageView(Objects.requireNonNull(getClass().getResource("/image/theme_gradient_day.png")).toExternalForm());

    /**
     * Link to css file
     */
    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_appwindow_class.css")).toExternalForm();

    /**
     * Constructor for AppWindow class
     */
    public AppWindow(Stage stageInit) {
        stage = stageInit;

        try {
            paneForBackground.getChildren().add(group);
            paneForBackground.getChildren().add(vBoxForTime);

            stackPaneForDate.getChildren().add(themeGradientDay);
            stackPaneForDate.getChildren().add(labelForDate);
            stackPaneForDate.getChildren().add(labelForMonth);

            vBoxForTime.getChildren().add(stackPaneForDate);
            vBoxForTime.getChildren().add(labelForDay);
            vBoxForTime.getChildren().add(labelForTime);

            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/icon_for_app.png"))));
        } catch (Exception e) {
            System.out.println("Error at graphics.appwindow.AppWindow:");
            System.out.println("Error when try add children!");
            System.out.println("Error: " + e.toString());

        }
        /*
        Set attribute for all node.
         */
        try {
            setIdForAll();
            setCSSForAll();
            scene.setFill(Color.TRANSPARENT);


            labelForDate.setTranslateX(2);
            labelForMonth.setTranslateX(2);
            labelForMonth.setTranslateY(-labelForDate.getHeight() - 13);

            vBoxForTime.setLayoutY(DELTA_Y + 20);
            vBoxForTime.setLayoutX((APP_WIDTH - vBoxForTime.getWidth()) / 2 - DELTA_X);
            vBoxForTime.setAlignment(Pos.CENTER);

            stackPaneForDate.setAlignment(Pos.CENTER);

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("enLearn");
            stage.setX((ScreenLaptop.SCREEN_WIDTH - APP_WIDTH) / 2);
            stage.setY((ScreenLaptop.SCREEN_HEIGHT - APP_HEIGHT) / 2);
            stage.setWidth(APP_WIDTH);
            stage.setHeight(APP_HEIGHT);
            stage.setScene(scene);

            themeGradientDay.setFitWidth(themeGradientDay.getImage().getWidth() * 0.25);
            themeGradientDay.setFitHeight(themeGradientDay.getImage().getHeight() * 0.25);
        } catch (Exception e) {
            System.out.println("Error at graphics.appwindow.AppWindow:");
            System.out.println("Error when try set for children!");
            System.out.println("Error: " + e.toString());
        }
    }

    private void setIdForAll() {
        labelForTime.setId("labelForTime");
        labelForDay.setId("labelForDay");
        labelForDate.setId("labelForDate");
        labelForMonth.setId("labelForMonth");
        paneForBackground.setId("paneForBackground");
        vBoxForTime.setId("vBoxForTime");
    }

    private void setCSSForAll() {
        vBoxForTime.getStylesheets().add(linkToCSS);
        paneForBackground.getStylesheets().add(linkToCSS);
    }

    /**
     * Method to show time now.
     */
    private void showCalendar() {
        final DateFormat patternDay = new SimpleDateFormat("EEEE");
        final DateFormat patternTime = new SimpleDateFormat("hh:mm");
        final DateFormat patternMark = new SimpleDateFormat("aa");
        final DateFormat patternDate = new SimpleDateFormat("dd");
        final DateFormat patternMonth = new SimpleDateFormat("MMM");
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                Date date = new Date();
                labelForDay.setText(patternDay.format(date).toUpperCase());
                labelForTime.setText(patternTime.format(date) + " " + patternMark.format(date).toLowerCase());
                labelForDate.setText(patternDate.format(date));
                labelForMonth.setText(patternMonth.format(date).toUpperCase());
            }
        };
        timer.start();
    }

    public void show() {
        showCalendar();
        stage.show();
    }
}
