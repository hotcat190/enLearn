package controller.test.reading.view;

import controller.test.reading.data.ReadingQuestion;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import user.UserTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionView extends VBox implements Decorator {
    private final Text textParagraphTitle = new Text("Paragraph");
    private final Text textParagraph = new Text();
    private final VBox vBoxParagraph = new VBox(textParagraphTitle, textParagraph);
    private final Text textQuestionTitle = new Text("Question");
    private final Text textQuestion = new Text();
    private final VBox vBoxQuestion = new VBox(textQuestionTitle, textQuestion);
    private final Text testAnswerTitle = new Text("Answer");
    private final VBox vBoxAnswer = new VBox(testAnswerTitle);
    private final ReadingQuestion readingQuestion;
    private final List<ToggleButton> answerButtons = new ArrayList<>();
    private final ToggleGroup toggleGroup = new ToggleGroup();

    public QuestionView(ReadingQuestion readingQuestion) {
        this.readingQuestion = readingQuestion;
        setId();
        setCSS();
        set();
    }
    /**
     * Set id.
     */
    @Override
    public void setId() {
        textParagraphTitle.getStyleClass().add("question-view__text--title");
        textQuestionTitle.getStyleClass().add("question-view__text--title");
        testAnswerTitle.getStyleClass().add("question-view__text--title");

        textParagraph.setId("question-view__text--paragraph");
        textQuestion.setId("question-view__text--question");
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
        textParagraph.setText(readingQuestion.getParagraph());
        textQuestion.setText(readingQuestion.getQuestion());

        textParagraph.setWrappingWidth(545 * StandardParameter.SCALE);
        textQuestion.setWrappingWidth(545 * StandardParameter.SCALE);
        for (int i = 0; i < 5; i++) {
            answerButtons.add(new ToggleButton());
            final ToggleButton toggleButton = answerButtons.get(i);
            toggleGroup.getToggles().add(answerButtons.get(i));
            toggleButton.setId("question-view__togglebutton--answer" + i);
            toggleButton.getStyleClass().add("question-view__togglebutton--answer");
            toggleButton.setMinSize(535 * StandardParameter.SCALE, 35 * StandardParameter.SCALE);
            toggleButton.setText(readingQuestion.getAnswers().get(i));
            int finalI = i;
            toggleButton.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                if (!UserTest.INSTANCE.hasTime()) {
                    toggleButton.setDisable(true);
                    toggleButton.setOpacity(1);
                }
                toggleButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e1 -> {
                    UserTest.INSTANCE.userAnswerROf(finalI, toggleButton.getText());
                });
            });
        }
        vBoxAnswer.getChildren().addAll(answerButtons);
        vBoxAnswer.setSpacing(20);
        this.getChildren().addAll(vBoxParagraph, vBoxQuestion, vBoxAnswer);
        this.setSpacing(10);
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
