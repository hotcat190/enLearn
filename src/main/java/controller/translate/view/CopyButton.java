package controller.translate.view;

import controller.model.Listener;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.sql.SQLException;

public class CopyButton extends StackPane implements Decorator,Listener {
    private final Rectangle rect1 = new Rectangle(26 * StandardParameter.SCALE, 28 * StandardParameter.SCALE);
    private final Rectangle rect2 = new Rectangle(26 * StandardParameter.SCALE, 28 * StandardParameter.SCALE);
    private final StringProperty stringProperty = new SimpleStringProperty();

    public CopyButton() {
        set();
        setListener();
    }

    /**
     * Set listener with other object.
     *
     */
    @Override
    public void setListener(Object object) {
        if (object instanceof StringProperty sP) {
            this.stringProperty.bind(sP);
        }
    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
        this.setOnMouseClicked(e -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(stringProperty.get());
            Clipboard.getSystemClipboard().setContent(content);

        });
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {

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
        rect1.setFill(Color.rgb(27, 62, 237));
        rect2.setFill(Color.rgb(27, 62, 237,0.7));
        rect1.setArcWidth(7);
        rect1.setArcHeight(7);
        rect2.setArcWidth(7);
        rect2.setArcHeight(7);

        rect1.setTranslateX(6);
        rect1.setTranslateY(6);
        this.getChildren().addAll(rect2, rect1);
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
