package data.my_dictionary;

import dictionary.Dictionary;
import dictionary.Word;
import sql.SQLData;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Queue;
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
                    resultSet.getString(5).replace("- ","")
            );
            hashSet.add(myNewWord);
            curOrder++;
        }
        Stack<MyNewWord> stack = new Stack<>();
        stack.addAll(hashSet);
        return stack;
    }

    public static void addToMyDictionary(String word) {
        try {
            Word wordUtil = new Word(word);
            String sqlUpdate = String.format("insert into my_dictionary(word,pronunciation,updateDate,definition)" +
                    "values('%s','%s',date(now()),'%s')", word, wordUtil.getPronunciation(), wordUtil.getPriorityDefinition());
            statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToMyDictionary(String word, String pronunciation, String definition) {
        try {
            String sqlUpdate = String.format("insert into my_dictionary(word,pronunciation,updateDate,definition)" +
                    "values('%s','%s',date(now()),'%s')", word, pronunciation, definition);
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
            resultSet.next();
            return dateFormat.format(resultSet.getDate(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
