package controller.my_dictionary;

import controller.model.Controller;
import controller.my_dictionary.data.MyDictionaryTableData;
import sql.dictionary.SQLMyDictionary;
import javafx.scene.Node;
import controller.my_dictionary.view.MyDictionaryTableView;

import java.sql.SQLException;

public class MyDictionaryTableController extends Controller {
    /**
     * Singleton.
     */
    private static final MyDictionaryTableController INSTANCE = new MyDictionaryTableController();

    /**
     * Components.
     */
    private final MyDictionaryTableView myDictionaryTableView = MyDictionaryTableView.getInstance();
    private final MyDictionaryTableData myDictionaryTableData = MyDictionaryTableData.getInstance();

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

    public static MyDictionaryTableController getInstance() {
        return INSTANCE;
    }
}
