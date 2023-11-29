package controller.word;

import controller.word.word.PartOfSpeechData;
import controller.word.data.Word;
import controller.word.view.PartOfSpeechView;
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
        return partOfSpeechView;
    }

    public PartOfSpeechView getPartOfSpeechView() {
        return partOfSpeechView;
    }
}
