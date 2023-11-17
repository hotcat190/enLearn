package view.word;

import controller.word.PartOfSpeechController;
import dictionary.Word;
import graphics.style.Decorator;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WordUI implements Decorator {
    private Word word;
    private final Label textWord = new Label();
    private final Label textPronunciation = new Label();

    private final PartOfSpeechController partOfSpeechController = new PartOfSpeechController();
    private final VBox vBoxWord = new VBox();

    public WordUI() {
        setId();
        setCSS();
        set();
    }

    public VBox getLayout() {
        return vBoxWord;
    }

    public void loadData(Word word) {
        this.word = word;
        partOfSpeechController.loadData(word);
    }

    @Override
    public void setId() {
        textWord.setId("textWord");
        textPronunciation.setId("textPronunciation");
    }

    @Override
    public void setCSS() {
        textWord.applyCss();
        textPronunciation.applyCss();
    }

    @Override
    public void set() {
        textPronunciation.setTranslateY(-10);

        vBoxWord.getChildren().addAll(textWord, textPronunciation, partOfSpeechController.getView());
    }

    @Override
    public void update() {
        textWord.setText(word.getWord());
        textPronunciation.setText(word.getPronunciation());
        partOfSpeechController.updateView();
    }

    @Override
    public String getLink() {
        return null;
    }
}
