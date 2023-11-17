package view.word;

import dictionary.Word;
import javafx.scene.layout.VBox;

public class DefinitionView {
    private Word word = null;

    private final VBox vBoxDef = new VBox();

    public DefinitionView() {
    }

    public void setLayout() {
    }

    public VBox getLayout() {
        return vBoxDef;
    }

    public void loadData(Word word) {
        this.word = word;
    }
}
