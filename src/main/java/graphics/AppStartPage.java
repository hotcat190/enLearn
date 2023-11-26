package graphics;

import graphics.style.StyleHelper;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class AppStartPage extends Canvas {
    /**
     * Information about app like version, blockquote, author,...
     */
    private final Text version = new Text("Version 1.0.0");
    private final Text versionBlockquote = new Text("enLearn is an application that makes learning English easier,\n" +
            "this is the first gui version of the developer team.");
    private final VBox vBoxVersionInfo = new VBox(version, versionBlockquote);

    /**
     * Main components of app window, edit recently so public.
     */
    public Stage stage;
    public Scene scenePage = new Scene(this);

    /**
     * Button Learn to start
     */
    Text textForLearnButton = new Text("LEARN");
    private final ImageView arrowLearnButton = new ImageView(Objects.requireNonNull(getClass().getResource("/image/arrow_icon_learnbutton_app.png")).toExternalForm());
    private final ImageView arrowLineLearnButton = new ImageView(Objects.requireNonNull(getClass().getResource("/image/arrowline_icon_learnbutton_app.png")).toExternalForm());
    private final Rectangle circleLearnButton = new Rectangle();
    private final StackPane paneLearnButton = new StackPane(circleLearnButton, textForLearnButton, arrowLearnButton, arrowLineLearnButton);

    /**
     * Time to display day name, time.
     */
    private final Text labelForDay = new Text();
    private final Text labelForTime = new Text();
    private final Text labelForMonth = new Text();
    private final Text labelForDate = new Text();

    /**
     * VBox for vertical layout of stack pane for date & label for date & label for time
     */
    private final VBox vBoxForTime = new VBox();
    private final StackPane stackPaneForDate = new StackPane();
    private final ImageView themeGradientDay = new ImageView(Objects.requireNonNull(getClass().getResource("/image/theme_gradient_day_app.png")).toExternalForm());

    /**
     * State day.
     */
    private final Text stateDay = new Text();
    private final ImageView imageStateDay = new ImageView(Objects.requireNonNull(getClass().getResource("/image/morning_icon_app.png")).toExternalForm());
    private final HBox hBoxStateDay = new HBox(stateDay, imageStateDay);

    /**
     * Constructor for Canvas class
     */
    public AppStartPage(Stage stageInit) {
        super(stageInit);
        stage = stageInit;
        setAppWindow(stageInit);
        super.getChildren().add(vBoxForTime);
        super.getChildren().add(paneLearnButton);
        super.getChildren().add(vBoxVersionInfo);
        super.getChildren().add(hBoxStateDay);

        stackPaneForDate.getChildren().add(themeGradientDay);
        stackPaneForDate.getChildren().add(labelForDate);
        stackPaneForDate.getChildren().add(labelForMonth);

        vBoxForTime.getChildren().add(stackPaneForDate);
        vBoxForTime.getChildren().add(labelForDay);
        vBoxForTime.getChildren().add(labelForTime);

        setId();
        setCSS();
        scenePage.setFill(Color.TRANSPARENT);

        vBoxVersionInfo.setLayoutX(40);
        vBoxVersionInfo.setLayoutY(670);

        labelForDate.setTranslateX(2);
        labelForMonth.setTranslateX(2);
        labelForMonth.setTranslateY(-13);

        vBoxForTime.layout();
        vBoxForTime.setLayoutY(DELTA_Y + 20);
        vBoxForTime.setLayoutX((APP_WIDTH - vBoxForTime.getMinWidth()) / 2 - 30);
        vBoxForTime.setAlignment(Pos.CENTER);

        stackPaneForDate.setAlignment(Pos.CENTER);

        themeGradientDay.setFitWidth(themeGradientDay.getImage().getWidth() * 0.25);
        themeGradientDay.setFitHeight(themeGradientDay.getImage().getHeight() * 0.25);
        themeGradientDay.setTranslateX(-3);

        stage.setScene(scenePage);
    }

    private void setId() {
        labelForTime.setId("labelForTime");
        labelForDay.setId("labelForDay");
        labelForDate.setId("labelForDate");
        labelForMonth.setId("labelForMonth");
        textForLearnButton.setId("textForLearnButton");
        exitButton.setId("exitButton");
        controlHBox.setId("controlHBox");
        this.setId("paneForBackground");
        vBoxForTime.setId("vBoxForTime");
        minimizeButton.setId("minimizeButton");
        circleLearnButton.setId("circleLearnButton");
        version.setId("version");
        versionBlockquote.setId("versionBlockquote");
        stateDay.setId("stateDay");
    }

    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_canvas.css"));
        labelForMonth.applyCss();
        labelForDay.applyCss();
        labelForDate.applyCss();
        labelForTime.applyCss();
        vBoxForTime.applyCss();

        textForLearnButton.applyCss();
        circleLearnButton.applyCss();

        version.applyCss();
        versionBlockquote.applyCss();
        stateDay.applyCss();
    }

    private void setStateDay(Calendar calendar) {
        imageStateDay.setFitWidth(imageStateDay.getImage().getWidth() * 0.2);
        imageStateDay.setFitHeight(imageStateDay.getImage().getHeight() * 0.2);
        imageStateDay.setTranslateY(-3);

        hBoxStateDay.layout();
        hBoxStateDay.setSpacing(5);
        hBoxStateDay.setLayoutX((APP_WIDTH - stateDay.getLayoutBounds().getWidth() + imageStateDay.getFitWidth()) / 2);
        hBoxStateDay.setLayoutY(165);
        if (calendar.get(Calendar.HOUR) >= 1 && calendar.get(Calendar.AM_PM) == Calendar.AM) {
            stateDay.setText("Good morning!");
        } else if (calendar.get(Calendar.HOUR) >= 1 && calendar.get(Calendar.HOUR) <= 6 && calendar.get(Calendar.AM_PM) == Calendar.PM) {
            stateDay.setText("Good afternoon!");
            imageStateDay.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/afternoon_icon_app.png"))));
        } else if (calendar.get(Calendar.HOUR) >= 7 && calendar.get(Calendar.AM_PM) == Calendar.PM) {
            stateDay.setText("Good evening");
            imageStateDay.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/evening_icon_app.png"))));
        }

    }

    private void setAnimationPage() {
        TranslateTransition hBoxTranslateTransition = new TranslateTransition(Duration.seconds(1), hBoxStateDay);
        hBoxTranslateTransition.setFromY(40);
        hBoxTranslateTransition.setToY(-10);
        hBoxTranslateTransition.play();
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
                Calendar calendar = Calendar.getInstance();
                setStateDay(calendar);
                labelForDay.setText(patternDay.format(date).toUpperCase());
                labelForTime.setText(patternTime.format(date) + " " + patternMark.format(date).toLowerCase());
                labelForDate.setText(patternDate.format(date));
                labelForMonth.setText(patternMonth.format(date).toUpperCase());
            }
        };
        timer.start();
    }

    /**
     * Set event for learn button.
     */

    private void setLearnButton() {
        Duration duration = Duration.seconds(0.5);

        circleLearnButton.setHeight(42);
        circleLearnButton.setWidth(42);
        circleLearnButton.setArcWidth(42);
        circleLearnButton.setArcHeight(42);
        circleLearnButton.setFill(Color.rgb(185, 244, 243));

        arrowLineLearnButton.setFitWidth(arrowLineLearnButton.getImage().getWidth() * 0.05);
        arrowLineLearnButton.setFitHeight(arrowLineLearnButton.getImage().getHeight() * 0.05);
        arrowLineLearnButton.setOpacity(0);

        arrowLearnButton.setFitWidth(arrowLearnButton.getImage().getWidth() * 0.05);
        arrowLearnButton.setFitHeight(arrowLearnButton.getImage().getHeight() * 0.05);
        arrowLearnButton.setTranslateX(7);
        arrowLearnButton.setOpacity(0);

        textForLearnButton.setTranslateX(circleLearnButton.getWidth() + 8);
        paneLearnButton.setAlignment(Pos.CENTER_LEFT);
        paneLearnButton.layout();
        paneLearnButton.setLayoutX((APP_WIDTH - paneLearnButton.getBoundsInLocal().getWidth() - 35) / 2);
        paneLearnButton.setLayoutY(600);

        FillTransition fillTransitionCircle = new FillTransition(duration, circleLearnButton);
        TranslateTransition translateTransitionText = new TranslateTransition(duration, textForLearnButton);
        FadeTransition fadeTransitionArrowLine = new FadeTransition(duration, arrowLineLearnButton);
        FadeTransition fadeTransitionArrow = new FadeTransition(duration, arrowLearnButton);
        TranslateTransition translateTransitionArrowLine = new TranslateTransition(duration, arrowLineLearnButton);
        TranslateTransition translateTransitionArrow = new TranslateTransition(duration, arrowLearnButton);

        final double startXText = textForLearnButton.getTranslateX();
        final double endXText = textForLearnButton.getTranslateX() + 15;
        double startXArrow = arrowLearnButton.getTranslateX();
        double endXArrow = arrowLearnButton.getTranslateX() + 15 + arrowLineLearnButton.getFitWidth();

        /*
          Event handler.
         */
        paneLearnButton.setOnMouseEntered(e -> {
            if (!paneLearnButton.isPressed()) {
                Timeline timelineScale = new Timeline(new KeyFrame(
                        duration,
                        new KeyValue(circleLearnButton.widthProperty(), 145)
                ));
                timelineScale.play();

                translateTransitionArrow.stop();
                translateTransitionArrow.setByX(endXArrow - arrowLearnButton.getTranslateX());
                translateTransitionArrow.play();

                translateTransitionArrowLine.stop();
                translateTransitionArrowLine.setByX(endXArrow - arrowLineLearnButton.getTranslateX() - arrowLearnButton.getFitWidth() - 3);
                translateTransitionArrowLine.play();

                fadeTransitionArrowLine.stop();
                fadeTransitionArrowLine.setToValue(1);
                fadeTransitionArrowLine.play();

                fadeTransitionArrow.stop();
                fadeTransitionArrow.setToValue(1);
                fadeTransitionArrow.play();


                fillTransitionCircle.stop();
                fillTransitionCircle.setToValue(Color.rgb(31, 47, 63));
                fillTransitionCircle.play();

                translateTransitionText.stop();
                translateTransitionText.setByX(endXText - textForLearnButton.getTranslateX());
                translateTransitionText.play();

                textForLearnButton.setFill(Color.rgb(232, 232, 232));
            }
        });
        paneLearnButton.setOnMouseExited(e -> {
            if (!paneLearnButton.isPressed()) {
                Timeline timelineScale = new Timeline(new KeyFrame(
                        duration,
                        new KeyValue(circleLearnButton.widthProperty(), 42)
                ));
                timelineScale.play();
                translateTransitionArrow.stop();
                translateTransitionArrow.setByX(-arrowLearnButton.getTranslateX() + startXArrow);
                translateTransitionArrow.play();

                translateTransitionArrowLine.stop();
                translateTransitionArrowLine.setByX(-(arrowLearnButton.getTranslateX() - startXArrow));
                translateTransitionArrowLine.play();

                fadeTransitionArrowLine.stop();
                fadeTransitionArrowLine.setToValue(0);
                fadeTransitionArrowLine.play();

                fadeTransitionArrow.stop();
                fadeTransitionArrow.setToValue(0);
                fadeTransitionArrow.play();


                fillTransitionCircle.stop();
                fillTransitionCircle.setToValue(Color.rgb(185, 244, 243));
                fillTransitionCircle.play();

                translateTransitionText.stop();
                translateTransitionText.setByX(-(textForLearnButton.getTranslateX() - startXText));
                translateTransitionText.play();

                textForLearnButton.setFill(Color.rgb(31, 47, 63));
            }
        });
        paneLearnButton.setOnMousePressed(e -> {
            paneLearnButton.setOnMouseEntered(null);
            paneLearnButton.setOnMouseExited(null);

            paneLearnButton.getChildren().clear();
            paneLearnButton.getChildren().add(circleLearnButton);


            Timeline timeline = new Timeline();
            KeyFrame keyFrameArc = new KeyFrame(Duration.seconds(1),
                    new KeyValue(circleLearnButton.arcWidthProperty(), 18),
                    new KeyValue(circleLearnButton.arcHeightProperty(), 18),
                    new KeyValue(circleLearnButton.widthProperty(), 42),
                    new KeyValue(circleLearnButton.heightProperty(), 42),
                    new KeyValue(paneLearnButton.layoutXProperty(), (APP_WIDTH - 42) / 2)
            );

            timeline.getKeyFrames().add(keyFrameArc);
            timeline.play();
            paneLearnButton.layout();
        });
    }

    /**
     * Set event for button.
     */
    private void setEventAndRunForButton() {
        setLearnButton();
    }

    public Scene getScenePage() {
        return scenePage;
    }

    public void showPage() {
        setEventAndRunForButton();
        showCalendar();
        setAnimationPage();
        stage.show();
    }
}
