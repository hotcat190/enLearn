package view.my_dictionary.my_word_box;

import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class InputBox implements Decorator {
    public final static int TYPE_TEXT_FIELD = 0;
    public final static int TYPE_TEXT_AREA = 1;
    private final Text title = new Text();
    private final TextField input = new TextField();
    private final TextArea input1 = new TextArea();
    private final VBox vBox = new VBox();
    private final double WIDTH;
    private final double HEIGHT;
    private int type = -1;

    public InputBox(String title, double width, double height, int type) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.title.setText(title);
        this.type = type;
        set();
    }

    public String getInput() {
        return type == InputBox.TYPE_TEXT_FIELD ? input.getText() : input1.getText();
    }
    public VBox get() {
        return vBox;
    }
    @Override
    public void setId() {

    }

    @Override
    public void setCSS() {

    }

    @Override
    public void set() {
        if (type == InputBox.TYPE_TEXT_FIELD) {
            vBox.getChildren().addAll(this.title, input);
            input.setPrefSize(WIDTH * StandardParameter.SCALE, HEIGHT * StandardParameter.SCALE);
            input.setMinSize(WIDTH * StandardParameter.SCALE, HEIGHT * StandardParameter.SCALE);
            input.setMaxSize(WIDTH * StandardParameter.SCALE, HEIGHT * StandardParameter.SCALE);
            input.setOnMouseExited(e->{
                input.setEditable(false);
            });
            input.setOnMouseClicked(e -> {
                input.setEditable(true);
            });
        } else {
            vBox.getChildren().addAll(this.title, input1);
            input1.setPrefSize(WIDTH * StandardParameter.SCALE, HEIGHT * StandardParameter.SCALE);
            input1.setMinSize(WIDTH * StandardParameter.SCALE, HEIGHT * StandardParameter.SCALE);
            input1.setMaxSize(WIDTH * StandardParameter.SCALE, HEIGHT * StandardParameter.SCALE);
            input1.setWrapText(true);
            input1.setOnMouseExited(e->{
                input1.setEditable(false);
            });
            input1.setOnMouseClicked(e -> {
                input1.setEditable(true);
            });
        }
        vBox.setSpacing(10);
    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public String getLink() {
        return null;
    }
}
