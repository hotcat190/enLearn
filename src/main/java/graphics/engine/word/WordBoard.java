package graphics.engine.word;

import dictionary.Word;
import graphics.StandardParameter;
import graphics.ui.Decorator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WordBoard implements Decorator {

    public final static double WIDTH = 525 * StandardParameter.SCALE;
    public final static double HEIGHT = 500 * StandardParameter.SCALE;
    private final static double LEFT_MARGIN = 35 * StandardParameter.SCALE;
    private final static double TOP_MARGIN = 30 * StandardParameter.SCALE;

    public Word word = null;
    private final Pane layout = new Pane();
    private final WordUtilUI wordUtilUI;
    private final VBox mainVBoxWord = new VBox();
    private final IrregularVerbsUI irregularVerbsUI = new IrregularVerbsUI(130, 165, 15, 15);
    public final WordUI wordUI ;


    public WordBoard(WordUtilUI wordUtilUI) {
        this.wordUtilUI = wordUtilUI;
        this.wordUI = new WordUI(wordUtilUI);
        setID();
        setCSS();
        setUI();
    }

    private void setIrregularVerbsUI() {
        irregularVerbsUI.getLayout().setTranslateX(LEFT_MARGIN + wordUtilUI.getDefWidth());
        irregularVerbsUI.getLayout().setTranslateY(TOP_MARGIN);
    }

    private void setLayout() {
        layout.getChildren().add(mainVBoxWord);
        layout.getChildren().add(irregularVerbsUI.getLayout());
        mainVBoxWord.getChildren().add(wordUI.getLayout());

        layout.setPrefSize(WIDTH, HEIGHT);
        mainVBoxWord.setTranslateX(LEFT_MARGIN);
        mainVBoxWord.setLayoutY(TOP_MARGIN);
    }

    public void loadData(Word word) {
        this.word = word;
        if (word.isIrregularVerb()) {
            irregularVerbsUI.loadData(word);
        }
        wordUI.loadData(word);
    }

    public Pane getLayout() {
        return layout;
    }

    @Override
    public void setID() {
        layout.setId("board");
    }

    @Override
    public void setCSS() {
        layout.getStylesheets().add(WordEngine.LINK_CSS);
        layout.applyCss();
        wordUI.setCSS();

    }

    @Override
    public void setUI() {
        setIrregularVerbsUI();
        setLayout();
    }

    @Override
    public void updateUI() {
        wordUtilUI.setPosListButton();
        irregularVerbsUI.updateUI();
        wordUI.updateUI();
    }

    @Override
    public String getLink() {
        return null;
    }


}
