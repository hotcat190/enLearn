package app.dashboard.home;

import app.Animation;
import controller.model.Listener;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Objects;

public class NotationButton extends Pane implements Decorator, Listener, Animation {
    private final static NotationButton INSTANCE = new NotationButton();
    private final ToggleButton buttonControl = new ToggleButton();
    private final ToggleButton commentButton = new ToggleButton();
    private final VBox vBox = new VBox();

    private final CommentView commentView = CommentView.getInstance();

    private final ImageView beforeImageView = new ImageView(
            new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/image/before_notation_icon.png")
            )));
    private final ImageView afterImageView = new ImageView(
            new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/image/after_notation_icon.png")
            )));
    private final ImageView commentIcon = new ImageView
            (new Image(Objects.requireNonNull(getClass().getResourceAsStream(
            "/image/comment_icon.png")
    )));
    private NotationButton() {
        setId();
        setCSS();
        set();
        setListener();
        setAnimation();
    }


    /**
     * Set id.
     */
    @Override
    public void setId() {
        vBox.setId("notation-button__vbox--layout");
        buttonControl.setId("notation-button__button--control");
        commentButton.setId("notation-button__button--comment");
    }

    /**
     * Set CSS if vBox class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        this.getChildren().add(vBox);
        this.getChildren().add(commentView);

        vBox.setPrefSize(30 * StandardParameter.SCALE, 30 * StandardParameter.SCALE);

        buttonControl.setMaxSize(30 * StandardParameter.SCALE, 30 * StandardParameter.SCALE);
        buttonControl.setMinSize(30 * StandardParameter.SCALE, 30 * StandardParameter.SCALE);

        commentButton.setMaxSize(20 * StandardParameter.SCALE, 20 * StandardParameter.SCALE);
        commentButton.setMinSize(20 * StandardParameter.SCALE, 20 * StandardParameter.SCALE);
        commentButton.setGraphic(commentIcon);
        commentButton.setVisible(false);
        commentView.setVisible(false);
        commentView.setTranslateX(vBox.getPrefWidth() + 10);
        commentView.setTranslateY(50);

        vBox.getChildren().addAll(commentButton,buttonControl);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setSpacing(20);
        buttonControl.setTranslateY(-10);
        beforeImageView.setFitWidth(beforeImageView.getImage().getWidth() * 0.3);
        beforeImageView.setFitHeight(beforeImageView.getImage().getHeight() * 0.3);

        afterImageView.setFitHeight(afterImageView.getImage().getHeight() * 0.3);
        afterImageView.setFitWidth(afterImageView.getImage().getWidth() * 0.3);

        commentIcon.setFitWidth(commentIcon.getImage().getWidth() * 0.3);
        commentIcon.setFitHeight(commentIcon.getImage().getHeight() * 0.3);

        buttonControl.setGraphic(beforeImageView);
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
        commentButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            commentView.setVisible(commentButton.isSelected());
        });
        buttonControl.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            double deltaX=this.getLayoutX()-e.getSceneX();
            double deltaY=this.getLayoutY()-e.getSceneY();
            buttonControl.addEventHandler(MouseEvent.MOUSE_DRAGGED, e1 -> {
                this.setLayoutX(e1.getSceneX()+deltaX);
                this.setLayoutY(e1.getSceneY()+deltaY);
            });
        });
    }

    @Override
    public void setAnimation() {
        buttonControl.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (buttonControl.isSelected()) {
                vBox.setStyle("-fx-background-color: rgb(27,27,27);");
                buttonControl.setGraphic(afterImageView);
                buttonControl.setMaxSize(20 * StandardParameter.SCALE, 20 * StandardParameter.SCALE);
                buttonControl.setMinSize(20 * StandardParameter.SCALE, 20 * StandardParameter.SCALE);
                new Timeline(
                        new KeyFrame(
                                Duration.millis(200),
                                new KeyValue(vBox.prefHeightProperty(), 100)
                        ),
                        new KeyFrame(
                                Duration.millis(200),
                                new KeyValue(commentButton.visibleProperty(), true)
                        )
                ).play();
            } else {
                vBox.setStyle("-fx-background-color: transparent;");
                commentButton.setVisible(false);
                commentView.setVisible(false);
                buttonControl.setGraphic(beforeImageView);
                buttonControl.setMaxSize(30 * StandardParameter.SCALE, 30 * StandardParameter.SCALE);
                buttonControl.setMinSize(30 * StandardParameter.SCALE, 30 * StandardParameter.SCALE);
                new Timeline(
                        new KeyFrame(
                                Duration.millis(200),
                                new KeyValue(vBox.prefHeightProperty(), 30*StandardParameter.SCALE)
                        )
                ).play();
            }
        });
    }

    public static NotationButton getInstance() {
        return INSTANCE;
    }
}
