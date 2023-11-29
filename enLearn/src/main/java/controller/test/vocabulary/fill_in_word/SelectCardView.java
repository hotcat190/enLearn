package controller.test.vocabulary.fill_in_word;

import graphics.animation.Listener;
import controller.test.vocabulary.data.VocabularyWord;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import user.UserTest;

import java.sql.SQLException;
import java.util.HashMap;

public class SelectCardView extends VBox implements Decorator, Listener {
    private final VocabularyWord vocabularyWord;
    private final Text wordText = new Text();
    private final GridPane gridPane = new GridPane();
    private final ToggleGroup toggleGroup = new ToggleGroup();

    private final RadioButton firstAnswer = new RadioButton();
    private final RadioButton secondAnswer = new RadioButton();
    private final RadioButton thirdAnswer = new RadioButton();
    private final RadioButton fourthAnswer = new RadioButton();


    public SelectCardView(VocabularyWord vocabularyWord) {
        this.vocabularyWord = vocabularyWord;
        setId();
        setCSS();
        set();
//        setListener();
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        this.setId("select-card-view__vbox--layout");
        wordText.setId("select-card-view__text--word");
        firstAnswer.setId("select-card-view__radiobutton");
        secondAnswer.setId("select-card-view__radiobutton");
        thirdAnswer.setId("select-card-view__radiobutton");
        fourthAnswer.setId("select-card-view__radiobutton");
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
        wordText.setText(vocabularyWord.getDisplayWord());
        wordText.setTranslateY(30);

        toggleGroup.getToggles().addAll(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);

        firstAnswer.setText(vocabularyWord.getChoices().get(0));
        secondAnswer.setText(vocabularyWord.getChoices().get(1));
        thirdAnswer.setText(vocabularyWord.getChoices().get(2));
        fourthAnswer.setText(vocabularyWord.getChoices().get(3));

        gridPane.add(firstAnswer, 0, 0);
        gridPane.add(secondAnswer, 1, 0);
        gridPane.add(thirdAnswer, 0, 1);
        gridPane.add(fourthAnswer, 1, 1);
        gridPane.setHgap(80);
        gridPane.setVgap(20);
        gridPane.setTranslateY(20);
        gridPane.setPadding(new Insets(50));
        this.setSpacing(10);
        this.getChildren().addAll(wordText, gridPane);
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(300 * StandardParameter.SCALE, 300 * StandardParameter.SCALE);
        this.setMinSize(300 * StandardParameter.SCALE, 300 * StandardParameter.SCALE);
        this.setPrefSize(300 * StandardParameter.SCALE, 300 * StandardParameter.SCALE);
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

    public String getSelect() {
        ToggleButton toggleButton = (ToggleButton) toggleGroup.getSelectedToggle();
        return toggleButton.getText();
    }

    /**
     * Set listener with other object.
     */
    @Override
    public void setListener(Object object) {
        if (object instanceof HashMap<?, ?>) {
            HashMap<SelectCardView, Integer> hashMap = (HashMap<SelectCardView, Integer>) object;
            for (Toggle toggle : toggleGroup.getToggles()) {
                if (toggle instanceof ToggleButton toggleButton) {
                    toggleButton.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
                        if (!UserTest.INSTANCE.hasTime()) {
                            toggleButton.setDisable(true);
                            toggleButton.setOpacity(1);
                        }
                        toggleButton.addEventHandler(ActionEvent.ACTION, e1 -> {
                            String answer = toggleButton.getText();
                            new Thread(() -> {
                                UserTest.INSTANCE.userAnswerFIBOf(hashMap.get(this), answer);
                            }).start();
                        });
                    });

                }
            }

        }
    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
        for (Toggle toggle : toggleGroup.getToggles()) {
            if (toggle instanceof ToggleButton toggleButton) {
                toggleButton.addEventHandler(ActionEvent.ACTION, e -> {

                });
            }
        }
    }
}
