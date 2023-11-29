package app.dashboard.game;

import app.dashboard.model.Dashboard;
import controller.flipcard.view.FlipCardView;
import graphics.style.StyleHelper;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Objects;

public class GameDashboard extends Dashboard {
    private final Button nextButton = new Button();
    private final Button backButton = new Button();
    private final FlipCardView flipCardView = new FlipCardView();
    private final ImageView title = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/image/game_dashboard_title.png")).toExternalForm()));


    public GameDashboard() {
    }

    @Override
    public void load() {
        setId();
        setCSS();
        set();
        setListener();
    }

    @Override
    public void setTitle() {

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
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            new Timeline(
                    new KeyFrame(Duration.millis(400),
                            new KeyValue(flipCardView.translateXProperty(), flipCardView.getTranslateX() - 300))
            ).play();
        });
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            new Timeline(
                    new KeyFrame(Duration.millis(400),
                            new KeyValue(flipCardView.translateXProperty(), flipCardView.getTranslateX() + 300))
            ).play();
        });
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        nextButton.getStyleClass().add("game-dashboard__button--control");
        backButton.getStyleClass().add("game-dashboard__button--control");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_game_dashboard.css"));
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        title.setFitHeight(title.getImage().getHeight() * 0.2);
        title.setFitWidth(title.getImage().getWidth() * 0.2);
        title.setTranslateX(50);
        title.setTranslateY(50);

        flipCardView.setLayoutY(180);
        flipCardView.setLayoutX(50);

        this.getChildren().add(title);
        this.getChildren().add(flipCardView);

        HBox h_box_button = new HBox(backButton, nextButton);
        h_box_button.setSpacing(30);

        this.getChildren().add(h_box_button);

        backButton.setRotate(180);

        h_box_button.setLayoutY(600);
        h_box_button.setLayoutX(570);

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
}
