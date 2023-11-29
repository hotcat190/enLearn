package controller.test.controller;

import graphics.animation.Listener;
import controller.model.Update;
import controller.test.vocabulary.VocabularyTestController;
import controller.test.reading.ReadingTestController;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import user.UserTest;

import java.sql.SQLException;
import java.util.Objects;

public class TestOpen extends Pane implements Decorator, Update, Listener {
    public static final int READING_TEST = 0;
    public static final int VOCABULARY_TEST = 1;
    private int type = -1;
    private boolean isTesting=false;
    private boolean isSolved=false;
    private final ImageView themeOpen = new ImageView();
    private final HBox infoHBox = new HBox();
    private final ToggleButton startButton = new ToggleButton("START");
    private final Text pointTitleText = new Text("Point");
    private final Text pointText = new Text("None");
    private final Text timeTitleText = new Text("Time");
    private final Text timeText = new Text("None");
    private final VBox titleVBox = new VBox(pointTitleText, timeTitleText);
    private final VBox valueVBox = new VBox(pointText, timeText);

    public TestOpen(int type) {
        this.type = type;
        setTheme();
        setId();
        setCSS();
        set();
        setListener();
    }

    public void setTheme() {
        String path = "";
        switch (type) {
            case READING_TEST -> path = "/image/reading_test_open.png";
            case VOCABULARY_TEST -> path = "/image/vocabulary_test_open.png";
        }
        themeOpen.setFitWidth(620 * StandardParameter.SCALE);
        themeOpen.setFitHeight(720 * StandardParameter.SCALE);
        themeOpen.setImage(
                new Image(Objects.requireNonNull(
                        getClass().getResource(path)
                ).toExternalForm())
        );
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        startButton.setId("test-open__button--start");
        pointText.getStyleClass().add("test-open__text");
        pointTitleText.getStyleClass().add("test-open__text");
        timeText.getStyleClass().add("test-open__text");
        timeTitleText.getStyleClass().add("test-open__text");
        infoHBox.setId("test-open__vbox");

    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {

    }

    @Override
    public void set() {
        this.getChildren().add(themeOpen);
        this.getChildren().add(startButton);
        this.getChildren().add(infoHBox);

        startButton.setPrefSize(120 * StandardParameter.SCALE, 45 * StandardParameter.SCALE);
        startButton.setLayoutX(themeOpen.getFitWidth() - startButton.getPrefWidth() - 30);
        startButton.setLayoutY(themeOpen.getFitHeight() - startButton.getPrefHeight() - 15);


        titleVBox.setSpacing(10);
        titleVBox.setPadding(new Insets(10, 0, 0, 0));
        valueVBox.setPadding(new Insets(10, 0, 0, 0));
        valueVBox.setSpacing(10);

        infoHBox.getChildren().addAll(titleVBox, valueVBox);
        infoHBox.setLayoutX(70);
        infoHBox.setLayoutY(themeOpen.getFitHeight() / 2 + 80);
        infoHBox.setPrefSize(325 * StandardParameter.SCALE, 110 * StandardParameter.SCALE);
        infoHBox.setAlignment(Pos.CENTER);
        infoHBox.setSpacing(100);
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
     * Update view.
     */
    @Override
    public void updateView() {
        if (type == READING_TEST) {
            pointText.setText(String.valueOf(UserTest.getInstance().getRPoint()));
            timeText.setText(ReadingTestController.getInstance().getCountDownClock().getTimePassed());
            UserTest.getInstance().setPassedRTime(ReadingTestController.getInstance().getCountDownClock().getTotalTimePassed());
        }
        if (type == VOCABULARY_TEST) {
            pointText.setText(String.valueOf(UserTest.getInstance().getFIBPoint()));
            timeText.setText(VocabularyTestController.getInstance().getCountDownClock().getTimePassed());
            UserTest.getInstance().setPassedFIBTime(VocabularyTestController.getInstance().getCountDownClock().getTotalTimePassed());
        }
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    /**
     * Load and update data.
     */
    @Override
    public void loadData(Object object) {

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
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (isSolved) {
                    startButton.setText("SOLVED");
                    startButton.setDisable(true);
                    startButton.setOpacity(1);
                    this.stop();
                }
            }
        }.start();
        startButton.setOnMouseClicked(e -> {
            startButton.setText("...");
            TestController.getInstance().goTo(this.type);
            switch (type) {
                case TestOpen.READING_TEST -> {
                    ReadingTestController.getInstance().getCountDownClock().playFrom(10, 0);
                    UserTest.getInstance().setHasTime(true);
                }
                case TestOpen.VOCABULARY_TEST -> {
                    VocabularyTestController.getInstance().getCountDownClock().playFrom(5, 0);
                    UserTest.getInstance().setHasTime(true);
                }
            }
        });
    }
}
