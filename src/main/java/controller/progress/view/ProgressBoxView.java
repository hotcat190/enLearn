package controller.progress.view;

import graphics.style.Decorator;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import view.model.Connector;

import java.sql.SQLException;
import java.util.Objects;

public class ProgressBoxView extends Pane implements Decorator, Connector {
    /**
     * Views.
     */
    protected final ImageView boxImage = new ImageView();
    protected final Text textDetail = new Text("*");

    public ProgressBoxView() {
        setId();
        setCSS();
        set();
    }

    public void setImage(String path) {
        boxImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(path))));
        boxImage.setFitWidth(boxImage.getImage().getWidth() / 5);
        boxImage.setFitHeight(boxImage.getImage().getHeight() / 5);
    }

    @Override
    public void setId() {

    }

    @Override
    public void setCSS() {
    }

    @Override
    public void set() {
        textDetail.setStyle("-fx-font-family: 'Segoe UI Variable';" +
                "-fx-fill: #f2f2f2;" +
                "-fx-font-size: 36;" +
                "-fx-font-weight: bold;");
        textDetail.setLayoutY(60);
        textDetail.setLayoutX(35);
        this.getChildren().add(boxImage);
        this.getChildren().add(textDetail);
    }

    @Override
    public void update() throws SQLException {
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void connect(Object object) {
        if (object instanceof SimpleStringProperty stringProperty) {
            textDetail.textProperty().bind(stringProperty);
        }
    }
}
