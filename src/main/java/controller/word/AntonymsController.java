package controller.word;

import controller.model.Update;
import controller.word.word.ContronymData;
import controller.word.data.Word;
import controller.word.view.ContronymView;
import controller.word.view.WordBoard;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.Objects;

public class AntonymsController extends ContronymView implements Update {
    private final ContronymData contronymData = new ContronymData() {
        @Override
        public void load(Word word) {
            this.word = word;
            contronymList.clear();
            contronymList.addAll(word.getAntonymsList());
            System.out.println(contronymList.size());
        }
    };
    public AntonymsController(double width, double height, double leftMargin, double topMargin, Color colorGraphic) {
        super(width, height, leftMargin, topMargin, colorGraphic);
        connect(contronymData.contronymList);
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
