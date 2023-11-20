package controller.word;

import data.word.PartOfSpeechData;
import dictionary.Word;
import view.word.PartOfSpeechView;
import javafx.scene.layout.Pane;
import controller.model.Controller;
import controller.model.Update;

public class PartOfSpeechController extends Controller implements Update {
    private final PartOfSpeechView partOfSpeechView = new PartOfSpeechView();
    private final PartOfSpeechData partOfSpeechData = new PartOfSpeechData();

    public PartOfSpeechController() {
        partOfSpeechView.connect(partOfSpeechData.getHashMapPOS());
    }
    @Override
    public void updateView() {
        partOfSpeechView.update();
    }

    @Override
    public void loadData() {
    }

    @Override
    public void loadData(Object object) {
        if (object instanceof Word word) {
            partOfSpeechData.load(word);
            partOfSpeechView.loadData(word);
        }
    }

    @Override
    public Pane getView() {
        return partOfSpeechView.getLayout();
    }
}
