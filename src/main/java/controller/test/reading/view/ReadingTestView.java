package controller.test.reading.view;

import graphics.animation.Listener;
import controller.test.controller.TestController;
import controller.test.reading.data.ReadingQuestion;
import controller.test.reading.data.ReadingTestData;
import controller.test.view.model.TestView;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import user.UserTest;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;

public class ReadingTestView extends TestView implements Decorator, Listener {
    /**
     * Components.
     */
    private final HashMap<Integer, QuestionView> hashMapQuestionView = new HashMap<>();
    private ReadingTestData readingTestData;
    private final Button nextButton = new Button("NEXT");
    private final Text textTask = new Text();
    private final HBox hBoxController = new HBox(textTask, nextButton);
    private final HashMap<Integer, String> correctAnswerHashMap = new HashMap<>();

    private int currentIndex = 1;

    public ReadingTestView(ReadingTestData readingTestData) {
        this.readingTestData = readingTestData;
        setId();
        setCSS();
        set();
        setListener();
    }

    /**
     * Set listener with other object.
     */
    @Override
    public void setListener(Object object) {

    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            currentIndex = (currentIndex++) % readingTestData.getAmount() + 1;
            readingArea.getChildren().removeLast();
            readingArea.getChildren().add(hashMapQuestionView.get(currentIndex));
            textTask.setText("Task " + currentIndex);
        });
        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            UserTest.INSTANCE.markRTest(correctAnswerHashMap);
            TestController.getInstance().getReadingTestOpen().updateView();
            TestController.getInstance().getReadingTestOpen().setSolved(true);
        });

    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        textTask.setId("reading-test-view__text--task");
        infoTest.setId("reading-test-view__vbox--info");
        backButton.setId("vocabulary-test-view__button--back");
        submitButton.setId("vocabulary-test-view__button--submit");
        readingArea.setId("reading-test-view__vbox--reading-area");
        nextButton.setId("reading-test-view__button--next");
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
        readingArea.setMaxSize(630 * StandardParameter.SCALE, 640 * StandardParameter.SCALE);
        readingArea.setAlignment(Pos.TOP_CENTER);
        nextButton.setMaxSize(70 * StandardParameter.SCALE, 25 * StandardParameter.SCALE);
        nextButton.setMinSize(70 * StandardParameter.SCALE, 25 * StandardParameter.SCALE);

        hBoxController.setSpacing(340);
        hBoxController.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().add(readingArea);
        for (int i = 0; i < readingTestData.getAmount(); i++) {
            try {
                final ReadingQuestion readingQuestion = new ReadingQuestion();
                hashMapQuestionView.put(i + 1, new QuestionView(readingQuestion));
                correctAnswerHashMap.put(i + 1, readingQuestion.getCorrectAnswer());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        textTask.setText("Task 1");
        readingArea.getChildren().addAll(hBoxController, hashMapQuestionView.get(1));
        readingArea.setSpacing(10);
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

}
