package controller.flipcard.data;


import controller.my_dictionary.data.MyDictionaryTableData;
import controller.my_dictionary.data.MyNewWord;
import sql.dictionary.SQLMyDictionary;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class FlipCardData {
    private static final FlipCardData INSTANCE = new FlipCardData();
    private final List<MyNewWord> myDictionary ;
    public FlipCardData() {
        try {
            myDictionary = SQLMyDictionary.getInstance().getMyDictionary();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Collections.shuffle(myDictionary);

    }
    public List<MyNewWord> getMyDictionary() {
        return myDictionary;
    }

    public static FlipCardData getInstance() {
        return INSTANCE;
    }
}
