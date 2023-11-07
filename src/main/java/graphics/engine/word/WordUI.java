package graphics.engine.word;

import dictionary.Word;
import graphics.ui.Decorator;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WordUI implements Decorator {
    private Word word;
    private WordUtilUI wordUtilUI;

    private final Label textWord = new Label();
    private final Label textPronunciation = new Label();
    private final ScrollPane scrollPane = new ScrollPane();

    private final PartOfSpeechUI posUI = new PartOfSpeechUI();
    private final VBox vBoxWord = new VBox();

    public WordUI(WordUtilUI wordUtilUI) {
        this.wordUtilUI = wordUtilUI;

        setID();
        setCSS();
        setUI();
    }

    private void setLayout() {
        textPronunciation.setTranslateY(-10);
        scrollPane.setPrefSize(435, 300);
        scrollPane.setContent(posUI.definitionUI.getLayout());

        vBoxWord.getChildren().addAll(textWord, textPronunciation, posUI.getLayout(),scrollPane);
    }

    public VBox getLayout() {
        return vBoxWord;
    }

    public void loadData(Word word) {
        this.word = word;
        posUI.loadData(word,wordUtilUI);
    }

    @Override
    public void setID() {
        textWord.setId("textWord");
        textPronunciation.setId("textPronunciation");
        scrollPane.setId("scrollPane");
    }

    @Override
    public void setCSS() {
        textWord.applyCss();
        textPronunciation.applyCss();
        scrollPane.applyCss();
    }

    @Override
    public void setUI() {
        posUI.setUI();
        setLayout();
    }

    @Override
    public void updateUI() {
        textWord.setText(word.getWord());
        textPronunciation.setText(word.getPronunciation());
        posUI.updateUI();
    }

    @Override
    public String getLink() {
        return null;
    }
}
