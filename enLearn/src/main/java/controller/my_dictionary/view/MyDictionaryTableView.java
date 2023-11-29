package controller.my_dictionary.view;

import controller.my_dictionary.data.MyDictionaryTableData;
import controller.my_dictionary.data.MyNewWord;
import sql.dictionary.SQLMyDictionary;
import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import view.model.Connector;
import controller.my_dictionary.view.my_word_box.EditMyWordBox;

import java.sql.SQLException;


public class MyDictionaryTableView extends TableView<MyNewWord> implements Connector, Decorator {
    /**
     * Singleton.
     */
    private static final MyDictionaryTableView INSTANCE = new MyDictionaryTableView();

    /**
     * Components
     */
    private final TableColumn<MyNewWord, Integer> orderColumn = new TableColumn<>("#");
    private final TableColumn<MyNewWord, String> wordColumn = new TableColumn<>("Word");
    private final TableColumn<MyNewWord, String> pronunciationColumn = new TableColumn<>("Pronunciation");
    private final TableColumn<MyNewWord, String> updateColumn = new TableColumn<>("Update");
    private final TableColumn<MyNewWord, String> definitionColumn = new TableColumn<>("Definition");
    private final TableColumn<MyNewWord, String> modifierColumn = new TableColumn<>("Edit");

    public MyDictionaryTableView() {
        setId();
        setCSS();
        set();
    }

    @Override
    public void connect(Object object) {
        if (object instanceof FilteredList<?>) {
            this.setItems((FilteredList<MyNewWord>) object);
        }
    }

    @Override
    public void setId() {
        this.setId("my-dictionary-view__tableview");
        updateColumn.setId("my-dictionary__column--update");
        wordColumn.setId("my-dictionary__column--word");
        pronunciationColumn.setId("my-dictionary__column--pronunciation");
        definitionColumn.setId("my-dictionary__column--definition");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_my_dictionary_dashboard.css"));
    }

    private void setUpdateColumn() {

        javafx.util.Callback<TableColumn<MyNewWord, String>, TableCell<MyNewWord, String>> cellFactory
                =
                new Callback<>() {

                    @Override
                    public TableCell<MyNewWord, String> call(TableColumn<MyNewWord, String> myNewWordStringTableColumn) {
                        return new TableCell<>() {
                            @Override
                            protected void updateItem(String string, boolean b) {
                                final Label label = new Label();
                                super.updateItem(string, b);
                                if (b) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    MyNewWord myNewWord = getTableView().getItems().get(getIndex());
                                    if (myNewWord.getHour().contains("PM")) {
                                        label.setId("my-dictionary-tableview__label-pm");
                                    } else {
                                        label.setId("my-dictionary-tableview__label-am");
                                    }
                                    label.setText(myNewWord.getHour());
                                    setText(string);
                                    setGraphic(label);
                                    setContentDisplay(ContentDisplay.RIGHT);
                                }
                            }
                        };

                    }
                };
        updateColumn.setCellFactory(cellFactory);
    }

    private void setModifierColumn() {
        MyDictionaryTableView myDictionaryTableView = this;
        javafx.util.Callback<TableColumn<MyNewWord, String>, TableCell<MyNewWord, String>> cellFactory
                =
                new Callback<>() {

                    @Override
                    public TableCell<MyNewWord, String> call(TableColumn<MyNewWord, String> myNewWordStringTableColumn) {
                        return new TableCell<>() {
                            @Override
                            protected void updateItem(String string, boolean b) {
                                super.updateItem(string, b);
                                final Button deleteButton = new Button();
                                deleteButton.getStyleClass().add("my-dictionary-tableview__button--delete");
                                final Button editButton = new Button();
                                editButton.getStyleClass().add("my-dictionary-tableview__button--edit");
                                final HBox hBox = new HBox(deleteButton, editButton);
                                hBox.setSpacing(3);
                                if (b) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    MyNewWord myNewWord = getTableView().getItems().get(getIndex());
                                    deleteButton.setOnMouseClicked(e -> {
                                        SQLMyDictionary.getInstance().remove(myNewWord);
                                        MyDictionaryTableData.getInstance().remove(myNewWord);
                                        myDictionaryTableView.refresh();
                                    });
                                    editButton.setOnMouseClicked(e -> {
                                        EditMyWordBox.curEditWord = myNewWord;
                                        EditMyWordBox.getInstance().setVisible(true);
                                        EditMyWordBox.getInstance().get().setLayoutX(e.getSceneX() - 100);
                                        EditMyWordBox.getInstance().get().setLayoutY(e.getSceneY());
                                        myDictionaryTableView.refresh();
                                    });
                                    setGraphic(hBox);
                                    setContentDisplay(ContentDisplay.RIGHT);
                                }
                            }
                        };

                    }
                };
        modifierColumn.setCellFactory(cellFactory);
    }

    @Override
    public void set() {
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        pronunciationColumn.setCellValueFactory(new PropertyValueFactory<>("pronunciation"));
        updateColumn.setCellValueFactory(new PropertyValueFactory<>("update"));
        definitionColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));
        modifierColumn.setCellValueFactory(new PropertyValueFactory<>("edit"));

        setUpdateColumn();
        setModifierColumn();

        orderColumn.setMaxWidth(50);
        orderColumn.setMinWidth(50);

        wordColumn.setMinWidth(200);
        wordColumn.setMaxWidth(200);

        pronunciationColumn.setMinWidth(200);
        pronunciationColumn.setMaxWidth(200);

        updateColumn.setMinWidth(200);
        updateColumn.setMaxWidth(200);

        definitionColumn.setMinWidth(300);

        this.getColumns().addAll(wordColumn, pronunciationColumn, updateColumn, definitionColumn, modifierColumn);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        this.setPrefHeight(625 * StandardParameter.SCALE);

    }

    @Override
    public void update() throws SQLException {
    }

    @Override
    public String getLink() {
        return null;
    }

    public static MyDictionaryTableView getInstance() {
        return INSTANCE;
    }
}
