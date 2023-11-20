package controller.my_dictionary;

import controller.model.Controller;
import data.my_dictionary.MyDictionaryTableData;
import data.my_dictionary.MyNewWord;
import data.my_dictionary.SQLMyDictionary;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import utility.calendar.Time;
import view.my_dictionary.MyDictionaryTableView;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

public class MyDictionaryTableController extends Controller {
    public static final MyDictionaryTableController myDictionaryTableController = new MyDictionaryTableController();
    private final MyDictionaryTableView myDictionaryTableView = new MyDictionaryTableView();
    private final MyDictionaryTableData myDictionaryTableData = MyDictionaryTableData.myDictionaryTableData;

    private MyDictionaryTableController() {
        myDictionaryTableView.connect(myDictionaryTableData.getFilteredList());
        try {
            myDictionaryTableData.getObservableList().addAll(SQLMyDictionary.getMyDictionary());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MyDictionaryTableData getData() {
        return myDictionaryTableData;
    }
    @Override
    public void updateView() {
    }

    @Override
    public void loadData() {
    }

    @Override
    public void loadData(Object object) {
    }

    @Override
    public Node getView() {
        return myDictionaryTableView;
    }
}
