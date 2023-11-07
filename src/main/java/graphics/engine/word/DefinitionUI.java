package graphics.engine.word;

import dictionary.Word;
import graphics.ui.Decorator;
import javafx.scene.layout.VBox;

public class DefinitionUI implements Decorator {
    private Word word = null;
    private WordUtilUI wordUtilUI = null;

    private final VBox vBoxDef = new VBox();

    public DefinitionUI() {
        setID();
        setCSS();
        setUI();
    }

    public void setLayout() {
    }

    public VBox getLayout() {
        return vBoxDef;
    }

    public void loadData(Word word, WordUtilUI wordUtilUI) {
        this.word = word;
        this.wordUtilUI = wordUtilUI;
    }

    @Override
    public void setID() {

    }

    @Override
    public void setCSS() {

    }

    @Override
    public void setUI() {
        setLayout();
    }

    @Override
    public void updateUI() {
        wordUtilUI.setPosListButton();
        vBoxDef.getChildren().clear();
        vBoxDef.getChildren().addAll(wordUtilUI.getDefUI());
    }

    @Override
    public String getLink() {
        return null;
    }
}
