package graphics.engine.word;

import dictionary.Word;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Objects;

public class AntonymsUI extends Contronym{
    protected AntonymsUI(double width, double height, double leftMargin, double topMargin, Color colorGraphic) {
        super(width, height, leftMargin, topMargin, colorGraphic);
    }

    @Override
    public void loadData(Word word) {
        this.word = word;
        contronymList = word.getAntonymsList();
    }

    @Override
    protected void setIcon() {
        icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/image/antonyms_icon_app.png"
        ))));
    }

    @Override
    protected void setTitleUI() {
        titleText.setText("Antonyms");
    }
}
