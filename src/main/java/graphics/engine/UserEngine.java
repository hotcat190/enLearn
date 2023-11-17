package graphics.engine;

import graphics.StandardParameter;
import graphics.app.User;
import graphics.app.dashboard.FirstDashboard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class UserEngine {
    /**
     * Display layout.
     */
    private final Text title = new Text("Progress");
    private final VBox vBox = new VBox();
    private final HBox hBox = new HBox();

    /**
     * Data user.
     */
    User user = new User();
    /**
     * Display streak.
     */
    private final Text streakText = new Text();
    private final StackPane streakStack = new StackPane();

    /**
     * Display streak.
     */
    private final Text scoreText = new Text();
    private final StackPane scoreStack = new StackPane();

    public UserEngine() {
        hBox.setSpacing(20);
        hBox.getChildren().add(streakStack);
        hBox.getChildren().add(scoreStack);

        vBox.setSpacing(StandardParameter.STANDARD_TITLE_VBOX);
        vBox.getChildren().add(title);
        vBox.getChildren().add(hBox);

        setCSS();
        setId();

        setUpScoreLayout();
        setUpStreakLayout();
    }

    private void setId() {
        title.setId("title");
        streakText.setId("streakText");
        scoreText.setId("scoreText");
    }

    private void setCSS() {
        vBox.getStylesheets().add(FirstDashboard.LINK_CSS);
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setUpStreakLayout() {
        ImageView imageStreak = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/streak_app.png"))));
        imageStreak.setFitWidth(imageStreak.getImage().getWidth() * 0.22);
        imageStreak.setFitHeight(imageStreak.getImage().getHeight() * 0.22);
        streakText.setText(String.valueOf(user.getStreakDay()));
        streakText.setTranslateY(-3);
        streakStack.getChildren().add(imageStreak);
        streakStack.getChildren().add(streakText);
        streakStack.setMaxWidth(0);
    }

    public void setUpScoreLayout() {
        ImageView imageScore = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/progress_app.png"))));
        imageScore.setFitWidth(imageScore.getImage().getWidth() * 0.22);
        imageScore.setFitHeight(imageScore.getImage().getHeight() * 0.22);
        scoreText.setText(String.valueOf(user.getBand()));
        scoreText.setTranslateY(-3);
        scoreStack.getChildren().add(imageScore);
        scoreStack.getChildren().add(scoreText);
        scoreStack.setMaxWidth(0);
    }
}

