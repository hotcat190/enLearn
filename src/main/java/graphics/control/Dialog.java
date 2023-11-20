package graphics.control;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Objects;

public class Dialog {
    private final Text text = new Text();
    private final StackPane stackPane = new StackPane();
    private final Rectangle rect = new Rectangle();
    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_control_package.css")).toExternalForm();

    public Dialog() {
        stackPane.getStylesheets().add(linkToCSS);
        text.setId("text");
        rect.setId("rect");

        text.applyCss();

        stackPane.applyCss();
        stackPane.getChildren().add(rect);
        stackPane.getChildren().add(text);

        getLayout().setScaleX(0.7);
        getLayout().setScaleY(0.7);
    }

    public void setText(String text) {
        this.text.setText(text);
        rect.setWidth(this.text.getLayoutBounds().getWidth() * 1.5);
        rect.setHeight(this.text.getLayoutBounds().getHeight() * 1.5);
        rect.setArcWidth(10);
        rect.setArcHeight(10);
        stackPane.layout();
    }

    public Pane getLayout() {
        return stackPane;
    }
}
