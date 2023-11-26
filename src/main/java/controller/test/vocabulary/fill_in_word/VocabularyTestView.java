package controller.test.vocabulary.fill_in_word;

import controller.model.Listener;
import controller.test.controller.TestController;
import controller.test.vocabulary.data.VocabularyTestData;
import controller.test.vocabulary.data.VocabularyWord;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import user.UserTest;
import controller.test.view.model.TestView;

import java.sql.SQLException;
import java.util.HashMap;

public class VocabularyTestView extends TestView implements Decorator, Listener {
    private final VocabularyTestData vocabularyTestData;
    private final HashMap<SelectCardView,Integer> selectCardViewList = new HashMap<>();
    private final HashMap<Integer, String> correctAnswerHashMap = new HashMap<>();
    private final GridPane gridPane = new GridPane();

    public VocabularyTestView(VocabularyTestData vocabularyTestData) {
        this.vocabularyTestData = vocabularyTestData;
        setId();
        setCSS();
        set();
        setListener();
    }
    /**
     * Set id.
     */
    @Override
    public void setId() {
        infoTest.setId("vocabulary-test-view__vbox--info");
        backButton.setId("vocabulary-test-view__button--back");
        submitButton.setId("vocabulary-test-view__button--submit");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {

    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        gridPane.setMaxSize(650 * StandardParameter.SCALE, 650 * StandardParameter.SCALE);
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        for (VocabularyWord vocabularyWord : vocabularyTestData.getTest()) {
            SelectCardView selectCardView = new SelectCardView(vocabularyWord);
            selectCardViewList.put(selectCardView, selectCardViewList.size() + 1);
            correctAnswerHashMap.put(selectCardViewList.get(selectCardView), vocabularyWord.getCorrectAnswer());
            selectCardView.setListener(selectCardViewList);
            if (selectCardViewList.size() <= vocabularyTestData.getAmount()/2) {
                gridPane.addColumn(0, selectCardView);
            } else {
                gridPane.addColumn(1,selectCardView);
            }
        }
        examArea.setContent(gridPane);
        this.getChildren().add(examArea);
    }

    /**
     * Update layout.
     */
    @Override
    public void update() throws SQLException {

    }

    @Override
    public String getLink() {
        return null;
    }

    /**
     * Set listener with other object.
     *
     */
    @Override
    public void setListener(Object object) {

    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            UserTest.INSTANCE.markFIBTest(correctAnswerHashMap);
            TestController.getInstance().getVocabularyTestOpen().updateView();
            TestController.getInstance().getVocabularyTestOpen().setSolved(true);
        });
    }
}
