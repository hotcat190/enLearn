package controller.my_dictionary.view.my_word_box;

import graphics.animation.Listener;
import controller.my_dictionary.data.MyDictionaryTableData;
import controller.my_dictionary.data.MyNewWord;
import javafx.geometry.Insets;
import sql.dictionary.SQLMyDictionary;
import graphics.style.UIComponent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

public class EditMyWordBox extends UIComponent implements Listener {
    private final static EditMyWordBox INSTANCE = new EditMyWordBox(285, 354, 15, 15);

    private final InputBox nameInput = new InputBox("Name", 105, 22,InputBox.TYPE_TEXT_FIELD);
    private final InputBox pronunciationInput = new InputBox("Pronunciation", 105, 22,InputBox.TYPE_TEXT_FIELD);
    private final InputBox definitionInput = new InputBox("Definition", 105 + 105 + 10, 100, InputBox.TYPE_TEXT_AREA);

    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");

    private final GridPane gridPane = new GridPane();
    public static MyNewWord curEditWord;

    private EditMyWordBox(double width, double height, double leftMargin, double topMargin) {
        super(width, height, leftMargin, topMargin);
        setId();
        setCSS();
        set();
        setListener();
    }

    public void setVisible(boolean visible) {
        layout.setVisible(visible);
        if (visible) {
            nameInput.setInput(curEditWord.getWord());
            pronunciationInput.setInput(curEditWord.getPronunciation());
            definitionInput.setInput(curEditWord.getDefinition());
        }
    }

    public boolean isVisible() {
        return layout.isVisible();
    }
    @Override
    public void setId() {
        layout.setId("add-my-word-box__pane--layout");
        saveButton.setId("add-my-word-box__button--save");
        cancelButton.setId("add-my-word-box__button--cancel");
    }

    @Override
    public void setCSS() {

    }

    @Override
    public void set() {
        setTitleUI();
        final HBox h_box = new HBox(saveButton, cancelButton);
        h_box.setTranslateX(5);
        h_box.setTranslateY(40);
        h_box.setSpacing(20);
        h_box.setAlignment(Pos.CENTER);

        gridPane.setHgap(10);
        mainVBox.getChildren().add(gridPane);
        mainVBox.setPadding(new Insets(30,0,0,0));

        gridPane.add(nameInput.get(), 0, 0);
        gridPane.add(pronunciationInput.get(), 1, 0);
        gridPane.add(definitionInput.get(),0,1,2,1);
        gridPane.add(h_box, 0, 2, 2, 1);

        layout.setTranslateX(-60);
    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    protected void setIcon() {

    }

    @Override
    protected void setTitleUI() {
        titleText.setText("Edit my word");
        titleText.setStyle("-fx-font-family: 'Segoe UI';" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #f2f2f2;" +
                "-fx-font-size: 15px");

    }

    @Override
    public void setListener(Object object) {
    }

    @Override
    public void setListener() {
        this.setVisible(false);
        saveButton.setOnMouseClicked(e -> {
            if (!nameInput.getInput().isEmpty()) {
                MyNewWord myNewWord = new MyNewWord(SQLMyDictionary.getInstance().getOrder(),
                        nameInput.getInput(),
                        pronunciationInput.getInput(),
                        new Date(System.currentTimeMillis()),
                        Time.valueOf(LocalTime.now()), definitionInput.getInput());
                SQLMyDictionary.getInstance().update(new MyNewWord(curEditWord), myNewWord);
                MyDictionaryTableData.getInstance().update(new MyNewWord(curEditWord), myNewWord);
            }
            this.setVisible(false);
        });
        cancelButton.setOnMouseClicked(e->{
            this.setVisible(false);
        });

    }

    public static EditMyWordBox getInstance() {
        return INSTANCE;
    }
}
