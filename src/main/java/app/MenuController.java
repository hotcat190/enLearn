package app;

import controller.model.Listener;
import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.css.Stylesheet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Objects;

public class MenuController extends VBox implements Decorator, Listener,Animation {
    public final static MenuController menuController = new MenuController();
    private MenuController() {
        setId();
        setCSS();
        set();
        setListener();
        setAnimation();
    }

    private final ToggleButton homeButton = new ToggleButton();
    private final ToggleButton myDictionaryButton = new ToggleButton();
    private final ToggleButton  translateButton = new ToggleButton();
    private final ToggleGroup toggleGroup = new ToggleGroup();

    /**
     * Set id.
     */
    @Override
    public void setId() {
        homeButton.getStyleClass().add("menu-controller__button");
        myDictionaryButton.getStyleClass().add("menu-controller__button");
        translateButton.getStyleClass().add("menu-controller__button");
        this.setId("menu-controller__layout");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_app_controller.css"));
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        double size_icon = 18 * StandardParameter.SCALE;

        ImageView homeIcon = new ImageView(Objects.requireNonNull(getClass().getResource(
                "/image/home_dashboard_icon.png"
        )).toExternalForm());
        homeIcon.setFitHeight(size_icon);
        homeIcon.setFitWidth(size_icon);
        homeButton.setGraphic(homeIcon);
        homeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        ImageView myDictionaryIcon = new ImageView(Objects.requireNonNull(getClass().getResource(
                "/image/my_dictionary_dashboard_icon.png"
        )).toExternalForm());
        myDictionaryIcon.setFitHeight(size_icon);
        myDictionaryIcon.setFitWidth(size_icon);
        myDictionaryButton.setGraphic(myDictionaryIcon);
        myDictionaryButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        ImageView translateIcon = new ImageView(Objects.requireNonNull(getClass().getResource(
                "/image/translate_dashboard_icon.png"
        )).toExternalForm());
        translateIcon.setFitHeight(size_icon);
        translateIcon.setFitWidth(size_icon);
        translateButton.setGraphic(translateIcon);
        translateButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);


        this.setMaxWidth(44*StandardParameter.SCALE);
        this.getChildren().addAll(homeButton, myDictionaryButton, translateButton);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

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
     *
     */
    @Override
    public void setListener(Object object) {
    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
        toggleGroup.getToggles().addAll(homeButton, translateButton, myDictionaryButton);
    }

    public ToggleButton getHomeButton() {
        return homeButton;
    }

    public ToggleButton getMyDictionaryButton() {
        return myDictionaryButton;
    }

    public ToggleButton getTranslateButton() {
        return translateButton;
    }

    @Override
    public void setAnimation() {
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(200),
                    new KeyValue(this.maxWidthProperty(), 200)
            ));
            timeline.play();
        });
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(100),
                    new KeyValue(this.maxWidthProperty(), 30)
            ));
            timeline.play();
        });

    }
}
