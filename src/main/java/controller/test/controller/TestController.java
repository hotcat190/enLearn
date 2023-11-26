package controller.test.controller;

import controller.model.Listener;
import controller.test.vocabulary.VocabularyTestController;
import controller.test.reading.ReadingTestController;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class TestController extends HBox implements Decorator, Listener {
    private static final TestController INSTANCE = new TestController();
    private final TestOpen readingTestOpen = new TestOpen(TestOpen.READING_TEST);
    private final TestOpen vocabularyTestOpen = new TestOpen((TestOpen.VOCABULARY_TEST));
    private final ReadingTestController readingTestController = ReadingTestController.getInstance();
    private final VocabularyTestController vocabularyTestController = VocabularyTestController.getInstance();

    public TestController() {
        setId();
        setCSS();
        set();
        update();
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

    }

    /**
     * Set id.
     */
    @Override
    public void setId() {

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
        this.setPrefSize(1350*StandardParameter.SCALE,788*StandardParameter.SCALE);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(readingTestOpen, vocabularyTestOpen);
        this.setSpacing(30);

        readingTestOpen.setTranslateY(30);
        vocabularyTestOpen.setTranslateY(30);
    }

    public void backToOpen() {
        this.getChildren().clear();
        this.getChildren().addAll(readingTestOpen, vocabularyTestOpen);
    }

    public void goTo(int type) {
        switch (type) {
            case TestOpen.READING_TEST -> {
                this.getChildren().clear();
                this.getChildren().add(readingTestController.getView());
            }
            case TestOpen.VOCABULARY_TEST -> {
                this.getChildren().clear();
                this.getChildren().add(vocabularyTestController.getView());
            }
        }
    }

    /**
     * Update layout.
     */
    @Override
    public void update() {
        readingTestController.loadData();
        readingTestController.updateView();

        vocabularyTestController.loadData();
        vocabularyTestController.updateView();
    }

    public TestOpen getReadingTestOpen() {
        return readingTestOpen;
    }

    public TestOpen getVocabularyTestOpen() {
        return vocabularyTestOpen;
    }
    @Override
    public String getLink() {
        return null;
    }

    public static TestController getInstance() {
        return INSTANCE;
    }
}
