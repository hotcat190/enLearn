package app.dashboard.home;

import graphics.animation.Animation;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Objects;

public class GoTestCard extends Pane implements Decorator, Animation {
    private static final GoTestCard INSTANCE = new GoTestCard();
    /**
     * Components.
     */
    private final Button goButton = new Button();
    private final ImageView theme = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
            "/image/go_test_icon.png"
    ))));

    public GoTestCard() {
        setId();
        setCSS();
        set();
        setAnimation();
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        this.setId("go-test-card__pane--layout");
        goButton.setId("go-test-card__button--go");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        this.getChildren().add(theme);
        this.getChildren().add(goButton);

        theme.setFitWidth(230 * StandardParameter.SCALE);
        theme.setFitHeight(290 * StandardParameter.SCALE);

        goButton.setLayoutX(40);
        goButton.setLayoutY(120);

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

    @Override
    public void setAnimation() {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(200), goButton);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(30);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setCycleCount(-1);
        goButton.setOnMouseMoved(e -> {
            rotateTransition.play();
        });
        goButton.setOnMouseExited(e -> {
            rotateTransition.setFromAngle(0);
            rotateTransition.stop();
        });
    }

    public Button getGoButton() {
        return goButton;
    }

    public static GoTestCard getInstance() {
        return INSTANCE;
    }

}
