package view.test.fill_in_word;

import data.test.fill_in_blank.FillInBlankWord;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class SelectCardView extends VBox implements Decorator {
    private final FillInBlankWord fillInBlankWord;
    private final Text wordText = new Text();
    private final GridPane gridPane = new GridPane();
    private final ToggleGroup toggleGroup = new ToggleGroup();

    private final RadioButton firstAnswer = new RadioButton();
    private final RadioButton secondAnswer = new RadioButton();
    private final RadioButton thirdAnswer = new RadioButton();
    private final RadioButton fourthAnswer = new RadioButton();


    public SelectCardView(String word) {
        this.fillInBlankWord = new FillInBlankWord(word);
        setId();
        setCSS();
        set();
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
        wordText.setText(fillInBlankWord.getDisplayWord().toString());
        wordText.setTranslateY(30);

        toggleGroup.getToggles().addAll(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);

        firstAnswer.setText(fillInBlankWord.getChoices().get(0).toString());
        secondAnswer.setText(fillInBlankWord.getChoices().get(1).toString());
        thirdAnswer.setText(fillInBlankWord.getChoices().get(2).toString());
        fourthAnswer.setText(fillInBlankWord.getChoices().get(3).toString());

        gridPane.add(firstAnswer, 0, 0);
        gridPane.add(secondAnswer, 1, 0);
        gridPane.add(thirdAnswer, 0, 1);
        gridPane.add(fourthAnswer, 1, 1);
        gridPane.setHgap(80);
        gridPane.setVgap(20);
        gridPane.setTranslateY(20);
        gridPane.setPadding(new Insets(50));
        this.setSpacing(10);
        this.getChildren().addAll(wordText,gridPane);
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(300* StandardParameter.SCALE,300*StandardParameter.SCALE);
        this.setMinSize(300* StandardParameter.SCALE,300*StandardParameter.SCALE);
        this.setPrefSize(300* StandardParameter.SCALE,300*StandardParameter.SCALE);
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
