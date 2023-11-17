package view.my_dictionary.my_word_box;

import controller.model.Listener;
import data.my_dictionary.MyDictionaryTableData;
import data.my_dictionary.MyNewWord;
import data.my_dictionary.SQLMyDictionary;
import graphics.style.UIComponent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

public class AddMyWordBox extends UIComponent implements Listener {
    private final InputBox nameInput = new InputBox("Name", 105, 20,InputBox.TYPE_TEXT_FIELD);
    private final InputBox pronunciationInput = new InputBox("Pronunciation", 105, 20,InputBox.TYPE_TEXT_FIELD);
    private final InputBox definitionInput = new InputBox("Definition", 105 + 105 + 10, 100, InputBox.TYPE_TEXT_AREA);

    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");

    private final GridPane gridPane = new GridPane();

    public AddMyWordBox(double width, double height, double leftMargin, double topMargin) {
        super(width, height, leftMargin, topMargin);
        setId();
        setCSS();
        set();
    }

    public void setVisible(boolean visible) {
        layout.setVisible(visible);
    }

    public boolean isVisible() {
        return layout.isVisible();
    }
    @Override
    public void setId() {
        layout.setId("layoutMyNewWordBox");
    }

    @Override
    public void setCSS() {

    }

    @Override
    public void set() {
        setTitleUI();
        gridPane.add(nameInput.get(), 0, 0);
        gridPane.add(pronunciationInput.get(), 1, 0);
        gridPane.add(definitionInput.get(),0,1,2,1);
        gridPane.add(saveButton, 0, 2);
        gridPane.add(cancelButton, 1, 2);
        gridPane.setHgap(10);

        mainVBox.getChildren().add(gridPane);
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
        titleText.setText("My new word");
        titleText.setStyle("-fx-font-family: 'Segoe UI';" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #1f2f3f;" +
                "-fx-font-size: 15px");

    }

    @Override
    public void setListener(Object object) {
        MyDictionaryTableData myDictionaryTableData;
        if (object instanceof MyDictionaryTableData) {
            myDictionaryTableData = (MyDictionaryTableData) object;
        } else {
            myDictionaryTableData = null;
        }
        saveButton.setOnMouseClicked(e -> {
            if (!nameInput.getInput().isEmpty()) {
                MyNewWord myNewWord = new MyNewWord(SQLMyDictionary.getOrder(),
                        nameInput.getInput(),
                        pronunciationInput.getInput(),
                        new Date(System.currentTimeMillis()),
                        definitionInput.getInput());
                SQLMyDictionary.addToMyDictionary(
                        nameInput.getInput(),
                        pronunciationInput.getInput(),
                        definitionInput.getInput()
                );
                Objects.requireNonNull(myDictionaryTableData).add(myNewWord);
            }
            this.setVisible(false);
        });
        cancelButton.setOnMouseClicked(e->{
            this.setVisible(false);
        });
    }

    @Override
    public void setListener() {

    }
}
