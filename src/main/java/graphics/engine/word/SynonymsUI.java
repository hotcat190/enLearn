package graphics.engine.word;

import dictionary.Dictionary;
import dictionary.Word;
import graphics.app.AppWindow;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.Objects;

public class SynonymsUI extends Contronym {
    protected SynonymsUI(double width, double height, double leftMargin, double topMargin, Color colorGraphic) {
        super(width, height, leftMargin, topMargin, colorGraphic);
    }

    @Override
    public void loadData(Word word) {
        this.word = word;
        contronymList = word.getSynonymsList();
    }

    @Override
    protected void setIcon() {
        icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/image/synonyms_icon_app.png"
        ))));
    }

    @Override
    protected void setTitleUI() {
        titleText.setText("Synonyms");
    }
}
