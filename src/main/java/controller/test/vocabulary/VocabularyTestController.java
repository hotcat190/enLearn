package controller.test.vocabulary;

import controller.model.Controller;
import controller.test.vocabulary.data.VocabularyTestData;
import controller.test.view.clock.CountDownClock;
import javafx.scene.Node;
import controller.test.vocabulary.fill_in_word.VocabularyTestView;

public class VocabularyTestController extends Controller {
    private static final VocabularyTestController INSTANCE = new VocabularyTestController();
    private VocabularyTestView fillInBlankTestView;
    private VocabularyTestData vocabularyTestData;

    private VocabularyTestController() {
    }

    @Override
    public void updateView() {
        fillInBlankTestView = new VocabularyTestView(vocabularyTestData);
    }

    @Override
    public void loadData() {
        vocabularyTestData = new VocabularyTestData(20);
    }

    @Override
    public void loadData(Object object) {

    }

    @Override
    public Node getView() {
        return fillInBlankTestView;
    }
    public CountDownClock getCountDownClock() {
        return fillInBlankTestView.getCountDownClock();
    }

    public static VocabularyTestController getInstance() {
        return INSTANCE;
    }
}
