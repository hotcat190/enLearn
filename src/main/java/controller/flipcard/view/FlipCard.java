package controller.flipcard.view;

import controller.my_dictionary.data.MyNewWord;
import graphics.StandardParameter;
import graphics.animation.Listener;
import graphics.style.Decorator;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Objects;

public class FlipCard extends StackPane implements Decorator, Listener {
    private boolean isFront = false;
    private final ImageView backImage = new ImageView();
    private final ImageView background = new ImageView();
    private final MyNewWord myNewWord;
    private final Text text = new Text();

    public FlipCard(MyNewWord myNewWord) {
        this.myNewWord = myNewWord;
        setId();
        setCSS();
        set();
        setListener();
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        this.setId("flip-card__stackpane--layout");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {

    }

    private RotateTransition createRotator() {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), this);
        rotator.setAxis(Rotate.Y_AXIS);
        text.setRotationAxis(Rotate.Y_AXIS);
        background.setRotationAxis(Rotate.Y_AXIS);
        if (isFront) {
            rotator.setFromAngle(0);
            rotator.setToAngle(180);
            text.setRotate(180);
            background.setRotate(180);

        } else {
            rotator.setFromAngle(180);
            rotator.setToAngle(360);
            text.setRotate(0);
            background.setRotate(0);

        }
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }

    private PauseTransition changeCardFace() {
        PauseTransition pause = new PauseTransition(Duration.millis(500));

        if (isFront) {
            pause.setOnFinished(
                    e -> {
                        this.setStyle("-fx-background-color: #1b1b1b");
                        text.setStyle("""
                                -fx-font-family: 'Segoe UI Variable';
                                -fx-font-size: 15px;
                                -fx-fill: #f2f2f2;
                                """);
                        background.setImage(new Image(Objects.requireNonNull(getClass().getResource("/image/front_card.png")).toExternalForm()));
                        text.setText(myNewWord.getDefinition());
                    });
        } else {
            pause.setOnFinished(
                    e -> {
                        text.setText(myNewWord.getWord());
                        text.setStyle("""
                                -fx-font-family: 'Segoe UI Variable';
                                -fx-font-size: 30px;
                                -fx-fill: #1b1b1b;
                                -fx-font-weight: bolder;
                                -fx-effect: dropshadow(gaussian,rgba(25,55,206,0.8),0,0,0,10);
                                """);

                        background.setImage(new Image(Objects.requireNonNull(getClass().getResource("/image/back_card.png")).toExternalForm()));
                        this.setStyle("-fx-background-color: #1b3eed");

                    });
        }

        return pause;
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        text.setWrappingWidth(150);
        text.setText(myNewWord.getDefinition());
        text.setStyle("""
                -fx-font-family: 'Segoe UI Variable';
                -fx-font-size: 15px;
                -fx-fill: #f2f2f2;
                """);

        background.setImage(new Image(Objects.requireNonNull(getClass().getResource("/image/front_card.png")).toExternalForm()));
        backImage.setImage(new Image(Objects.requireNonNull(getClass().getResource("/image/back_card.png")).toExternalForm()));
        background.setFitWidth(300 * StandardParameter.SCALE);
        background.setFitHeight(360 * StandardParameter.SCALE);

        this.setMinSize(300 * StandardParameter.SCALE, 360 * StandardParameter.SCALE);
        this.setMaxSize(300 * StandardParameter.SCALE, 360 * StandardParameter.SCALE);
        this.getChildren().addAll(background, text);
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
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            RotateTransition rotator = createRotator();
            PauseTransition ptChangeCardFace = changeCardFace();

            ParallelTransition parallelTransition = new ParallelTransition(rotator, ptChangeCardFace);
            parallelTransition.play();
            isFront = !isFront;
        });
    }
}
