package view.my_dictionary;

import data.my_dictionary.MyNewWord;
import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.model.Connector;

import java.sql.SQLException;


public class MyDictionaryTableView extends TableView<MyNewWord> implements Connector, Decorator {
    private final TableColumn<MyNewWord, Integer> orderColumn = new TableColumn<>("#");
    private final TableColumn<MyNewWord, String> wordColumn = new TableColumn<>("Word");
    private final TableColumn<MyNewWord, String> pronunciationColumn = new TableColumn<>("Pronunciation");
    private final TableColumn<MyNewWord, String> updateColumn = new TableColumn<>("Update");
    private final TableColumn<MyNewWord, String> definitionColumn = new TableColumn<>("Definition");

    private final ObservableList<MyNewWord> observableList = FXCollections.observableArrayList();

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
        // You can set the ID here if needed
        this.setId("tableView");
        updateColumn.setId("updateColumn");
        wordColumn.setId("wordColumn");
        pronunciationColumn.setId("pronunciationColumn");
        orderColumn.setId("orderColumn");
        definitionColumn.setId("definitionColumn");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_second_dashboard.css"));
    }

    @Override
    public void set() {
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        pronunciationColumn.setCellValueFactory(new PropertyValueFactory<>("pronunciation"));
        updateColumn.setCellValueFactory(new PropertyValueFactory<>("update"));
        definitionColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));

        orderColumn.setMaxWidth(50);
        orderColumn.setMinWidth(50);

        wordColumn.setMinWidth(200);
        wordColumn.setMaxWidth(200);

        pronunciationColumn.setMinWidth(200);
        pronunciationColumn.setMaxWidth(200);

        updateColumn.setMinWidth(200);
        updateColumn.setMaxWidth(200);

        definitionColumn.setMinWidth(300);

        this.getColumns().addAll(orderColumn, wordColumn, pronunciationColumn, updateColumn, definitionColumn) ;
        this.setItems(observableList);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
//        tableView.getColumns().forEach(tableColumn -> tableColumn.setResizable(false));
//        tableView.setMinSize(1010 * StandardParameter.SCALE, 500 * StandardParameter.SCALE);
//        tableView.setMaxSize(1010 * StandardParameter.SCALE, 500 * StandardParameter.SCALE);
//        tableView.setMinWidth(50 + 200 + 200 + 200 + 300);
        this.setPrefHeight(625*StandardParameter.SCALE);

    }

    @Override
    public void update() throws SQLException {
    }

    @Override
    public String getLink() {
        return null;
    }
}
