package sql.dictionary;

import controller.my_dictionary.data.MyNewWord;
import sql.model.SQLData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class SQLMyDictionary extends SQLData {
    private static int curOrder = 1;

    public static Stack<MyNewWord> getMyDictionary() throws SQLException {
        String sql = "select * from my_dictionary;";
        ResultSet resultSet = statement.executeQuery(sql);
        HashSet<MyNewWord> hashSet = new HashSet<>();
        while (resultSet.next()) {
            MyNewWord myNewWord = new MyNewWord(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getTime(6),
                    resultSet.getString(5).replace("- ", "")
            );
            hashSet.add(myNewWord);
            curOrder++;
        }
        Stack<MyNewWord> stack = new Stack<>();
        stack.addAll(hashSet);
        return stack;
    }

    public static List<String> getMyWords() {
        List<String> myDictionary = new ArrayList<>();
        String sql = "select word from my_dictionary";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                myDictionary.add(resultSet.getString(1));
            }
            return myDictionary;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void add(MyNewWord myNewWord) {
        try {
            String sqlUpdate = String.format("insert into my_dictionary(word,pronunciation,updateDate,definition,hourTag)" +
                            "values('%s','%s',date(now()),'%s',time(now()))",
                    myNewWord.getWord(), myNewWord.getPronunciation(), myNewWord.getDefinition());
            statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void remove(MyNewWord myNewWord) {
        try {
            String sqlUpdate = String.format("delete from my_dictionary " +
                    "where word='%s' and definition='%s'", myNewWord.getWord(), myNewWord.getDefinition());
            statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void add(String word, String pronunciation, String definition) {
        try {
            String sqlUpdate = String.format("insert into my_dictionary(word,pronunciation,updateDate,definition,hourTag)" +
                    "values('%s','%s',date(now()),'%s')", word, pronunciation, definition);
            statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(MyNewWord oldWord, MyNewWord newWord) {
        String sqlUpdate = String.format("update my_dictionary " +
                        "set word='%s'," +
                        "pronunciation='%s', " +
                        "definition='%s' " +
                        "where word='%s' and definition='%s';",
                newWord.getWord(), newWord.getPronunciation(), newWord.getDefinition(),
                oldWord.getWord(), oldWord.getDefinition());
        try {
            statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getOrder() {
        System.out.println(curOrder);
        return curOrder++;
    }

    public static int getTotalWords() {
        String sql = "select count(*) from my_dictionary";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLastModified() {
        String sql = "select max(updateDate) from my_dictionary;";
        ResultSet resultSet = null;
        DateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return dateFormat.format(resultSet.getDate(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
