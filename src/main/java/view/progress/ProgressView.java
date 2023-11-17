package view.progress;

import graphics.StandardParameter;
import graphics.app.User;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import view.model.Connector;

import java.sql.SQLException;

public class ProgressView extends Pane implements Decorator, Connector {
    /**
     * View.
     */
    private final GridPane gridPane = new GridPane();

    /**
     * Views.
     */
    private final ProgressBoxView bandBox = new ProgressBoxView();
    private final ProgressBoxView streakBox = new ProgressBoxView();
    private final ProgressBoxView skillBox = new ProgressBoxView();
    private final ProgressBoxIntroView progressBoxIntroView = new ProgressBoxIntroView();

    /**
     * Connects.
     */
    private final StringProperty bandConnect = new SimpleStringProperty();
    private final StringProperty streakConnect = new SimpleStringProperty();
    private final StringProperty skillConnect = new SimpleStringProperty();

    public ProgressView() {
        setId();
        setCSS();
        set();
    }

    @Override
    public void setId() {
        this.setId("progress-view__pane--layout");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_progress_view.css"));
    }

    @Override
    public void set() {
        bandBox.setImage("/image/band_box_app.png");
        streakBox.setImage("/image/streak_box_app.png");
        skillBox.setImage("/image/skill_box_app.png");

        gridPane.setLayoutX(10);
        gridPane.setLayoutY(15);
        gridPane.add(bandBox, 0, 0);
        gridPane.add(streakBox, 0, 1);
        gridPane.add(progressBoxIntroView, 1, 0);
        gridPane.add(skillBox, 1, 1);

        this.getChildren().add(gridPane);
        this.setMaxWidth(425*StandardParameter.SCALE);

    }

    @Override
    public void update() throws SQLException {
        bandConnect.set(String.valueOf(User.getBand()));
        streakConnect.set(String.valueOf(User.getStreakDay()));
        skillConnect.set(User.getSkillPoint());
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void connect(Object object) {
        bandBox.connect(bandConnect);
        skillBox.connect(skillConnect);
        streakBox.connect(streakConnect);
        try {
            update();
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }
}
