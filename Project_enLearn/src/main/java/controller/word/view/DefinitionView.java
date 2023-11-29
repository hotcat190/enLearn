package controller.word.view;

import controller.word.data.Word;
import javafx.scene.layout.VBox;

public class DefinitionView extends VBox {
    private Word word = null;


    public DefinitionView() {
        this.setSpacing(5);
    }
    public void loadData(Word word) {
        this.word = word;
    }
}
