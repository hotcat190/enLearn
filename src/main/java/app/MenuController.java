package app;

import controller.model.Listener;
import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.animation.*;
import javafx.css.Stylesheet;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Objects;

public class MenuController extends VBox implements Decorator, Listener, Animation {
    /**
     * Singleton.
     */
    public final static MenuController INSTANCE = new MenuController();

    private MenuController() {
        setId();
        setCSS();
        set();
        setListener();
        setAnimation();
    }

    private final ToggleButton homeButton = new ToggleButton();
    private final ToggleButton myDictionaryButton = new ToggleButton();
    private final ToggleButton translateButton = new ToggleButton();
    private final ToggleButton testButton = new ToggleButton();
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final ToggleButton controllerButton = new ToggleButton();
    private final VBox navigatorVBox = new VBox();

    /**
     * Set id.
     */
    @Override
    public void setId() {
        homeButton.getStyleClass().add("menu-controller__button");
        myDictionaryButton.getStyleClass().add("menu-controller__button");
        translateButton.getStyleClass().add("menu-controller__button");
        testButton.getStyleClass().add("menu-controller__button");

        homeButton.setId("menu-controller__button--home");
        myDictionaryButton.setId("menu-controller__button--my-dictionary");
        translateButton.setId("menu-controller__button--translate");
        testButton.setId("menu-controller__button--test");

        controllerButton.setId("menu-controller__button--controller");
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
        ImageView controllerIcon = new ImageView(Objects.requireNonNull(getClass().getResource(
                "/image/logo_icon.png"
        )).toExternalForm());
        controllerIcon.setFitWidth(25);
        controllerIcon.setFitHeight(25);
        controllerButton.setGraphic(controllerIcon);
        controllerButton.setContentDisplay(ContentDisplay.LEFT);
        controllerButton.setText("enLearn");

        navigatorVBox.getChildren().addAll(homeButton, myDictionaryButton, translateButton, testButton);
        navigatorVBox.setSpacing(10);
        navigatorVBox.setAlignment(Pos.CENTER);
        testButton.setText("Examination");
        homeButton.setText("Home");
        translateButton.setText("Translate");
        myDictionaryButton.setText("My dictionary");

        controllerButton.setTranslateY(20);
        navigatorVBox.setTranslateY(20);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(30);
        this.setMaxWidth(50);
        this.getChildren().addAll(controllerButton, navigatorVBox);

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
        toggleGroup.getToggles().addAll(homeButton, translateButton, myDictionaryButton, testButton);
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

    public ToggleButton getTestButton() {
        return testButton;
    }

    @Override
    public void setAnimation() {
        controllerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (this.getMaxWidth() == 150) {
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(100),
                        new KeyValue(this.maxWidthProperty(), 50)
                ));
                timeline.play();
                testButton.setStyle("-fx-max-width: 23px;");
                homeButton.setStyle("-fx-max-width: 23px;");
                translateButton.setStyle("-fx-max-width: 23px;");
                myDictionaryButton.setStyle("-fx-max-width: 23px;");
            } else if (this.getMaxWidth() == 50) {
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(200),
                        new KeyValue(this.maxWidthProperty(), 150)
                ));
                timeline.play();
                testButton.setStyle("-fx-max-width: 130px;");
                homeButton.setStyle("-fx-max-width: 130px;");
                translateButton.setStyle("-fx-max-width: 130px;");
                myDictionaryButton.setStyle("-fx-max-width: 130px;");

            }
        });



        double size_icon = 20 * StandardParameter.SCALE;
        ImageView homeIcon = new ImageView();
        homeIcon.setFitHeight(size_icon);
        homeIcon.setFitWidth(size_icon);
        homeButton.setGraphic(homeIcon);
        homeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (homeButton.isSelected()) {
                    homeIcon.setImage(new Image(
                            Objects.requireNonNull(getClass().getResource(
                                    "/image/home_pressed_dashboard_icon.png"
                            )).toExternalForm()
                    ));
                } else {
                    homeIcon.setImage(new Image(
                            Objects.requireNonNull(getClass().getResource(
                                    "/image/home_dashboard_icon.png"
                            )).toExternalForm()
                    ));
                }
            }
        }.start();

        ImageView myDictionaryIcon = new ImageView();
        myDictionaryIcon.setFitHeight(size_icon);
        myDictionaryIcon.setFitWidth(size_icon);
        myDictionaryButton.setGraphic(myDictionaryIcon);
        myDictionaryButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (myDictionaryButton.isSelected()) {
                    myDictionaryIcon.setImage(new Image(
                            Objects.requireNonNull(getClass().getResource(
                                    "/image/my_dictionary_pressed_dashboard_icon.png"
                            )).toExternalForm()
                    ));
                } else {
                    myDictionaryIcon.setImage(new Image(
                            Objects.requireNonNull(getClass().getResource(
                                    "/image/my_dictionary_dashboard_icon.png"
                            )).toExternalForm()
                    ));
                }
            }
        }.start();

        ImageView translateIcon = new ImageView();
        translateIcon.setFitHeight(size_icon);
        translateIcon.setFitWidth(size_icon);
        translateButton.setGraphic(translateIcon);
        translateButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (translateButton.isSelected()) {
                    translateIcon.setImage(new Image(
                            Objects.requireNonNull(getClass().getResource(
                                    "/image/translate_pressed_dashboard_icon.png"
                            )).toExternalForm()
                    ));
                } else {
                    translateIcon.setImage(new Image(
                            Objects.requireNonNull(getClass().getResource(
                                    "/image/translate_dashboard_icon.png"
                            )).toExternalForm()
                    ));
                }
            }
        }.start();

        ImageView testIcon = new ImageView(Objects.requireNonNull(getClass().getResource(
                "/image/test_dashboard_icon.png"
        )).toExternalForm());
        testIcon.setFitHeight(size_icon);
        testIcon.setFitWidth(size_icon);
        testButton.setGraphic(testIcon);
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (testButton.isSelected()) {
                    testIcon.setImage(new Image(
                            Objects.requireNonNull(getClass().getResource(
                                    "/image/test_pressed_dashboard_icon.png"
                            )).toExternalForm()
                    ));
                } else {
                    testIcon.setImage(new Image(
                            Objects.requireNonNull(getClass().getResource(
                                    "/image/test_dashboard_icon.png"
                            )).toExternalForm()
                    ));
                }
            }
        }.start();
    }

    public static MenuController getInstance() {
        return INSTANCE;
    }
}
