package app.controller;

import graphics.canvas.Canvas;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import main.Main;

import java.sql.SQLException;
import java.util.Objects;

public class OpenController extends Canvas implements Decorator {
    private static final OpenController INSTANCE = new OpenController();
    private final AppController appController = new AppController();
    private final Button button = new Button("Learn");
    private final ImageView background = new ImageView(Objects.requireNonNull(getClass().getResource("/image/open_theme.png")).toExternalForm());
    private final StackPane stackPane = new StackPane();

    public OpenController() {
        super(Main.STAGE);
        Scene scene = new Scene(this);
        this.stageWindow.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        setId();
        setCSS();
        set();
    }

    public static OpenController getInstance() {
        return INSTANCE;
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        stackPane.setId("open-controller__stackpane--layout");
        button.setId("open-controller__button--learn");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_controller.css"));
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        stackPane.setTranslateY(10);
        stackPane.setMinWidth(Canvas.APP_WIDTH);
        background.setFitHeight(background.getImage().getHeight() * 0.22);
        background.setFitWidth(background.getImage().getWidth() * 0.22);
        stackPane.getChildren().addAll(background,button);
        this.getChildren().add(stackPane);
        button.setTranslateY(-80);
        button.setOnMouseClicked(e -> {
            this.getChildren().remove(stackPane);
            this.getChildren().add(appController);
            appController.load();
        });
    }

    public void setProgress(double value) {
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

    public void playAll() {
        Main.STAGE.show();
    }
}
