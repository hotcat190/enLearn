package controller.word;

import controller.model.Update;
import data.word.ContronymData;
import dictionary.Word;
import view.word.ContronymView;
import view.word.WordBoard;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.Objects;

public class SynonymsController extends ContronymView implements Update {
    private final ContronymData contronymData = new ContronymData() {
        @Override
        public void load(Word word) {
            this.word = word;
            contronymList.clear();
            contronymList.addAll(word.getSynonymsList());
        }
    };

    public SynonymsController(double width, double height, double leftMargin, double topMargin, Color colorGraphic) throws SQLException {
        super(width, height, leftMargin, topMargin, colorGraphic);
        connect(contronymData.contronymList);
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

    /**
     * Update view.
     */
    @Override
    public void updateView() {

    }

    public void updateView(WordBoard wordBoard) throws SQLException {
        update(wordBoard);
    }

    /**
     * Load and update data.
     *
     * @param object word.
     */
    @Override
    public void loadData(Object object) {
        if (object instanceof Word word) {
            contronymData.load(word);
        }
    }
}
