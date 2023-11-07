package dictionary;

import sql.Sever;

import java.sql.*;
import java.util.*;

public class Dictionary {
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(Sever.URL, Sever.USER, Sever.PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Statement statement;

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Dictionary() throws SQLException {
    }

    public static ResultSet getWord(String regexp) throws SQLException {
        String sql = String.format("select * from dictionary where word regexp(\"%s\");", regexp);
        return statement.executeQuery(sql);
    }

    public static boolean isExisted(String word) throws SQLException {
        String sql = String.format("select * from dictionary where word =\"%s\";", word);
        return statement.executeQuery(sql).next();
    }

    public static void putHistory(String word) throws SQLException {
        String sql = String.format("insert into history values(\"%s\");", word);
        statement.executeUpdate(sql);
    }

    public static LinkedList<String> getHistory() {
        LinkedList<String> linkedList = new LinkedList<>();
        String sql = "select * from history;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                linkedList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return linkedList;
    }

    public static ArrayList<String> getIrregularVerb(String word) throws SQLException {
        String sql = String.format("select * from irregular_verbs where infinity=\"%s\"", word);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(resultSet.getString(2));
            arrayList.add(resultSet.getString(3));
            return arrayList;
        }
        return null;
    }

    public static ArrayList<String> getAntonyms(String word) throws SQLException {
        String sql = String.format("select * from antonyms where word=\"%s\"", word);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) return new ArrayList<>(Arrays.asList(resultSet.getString(2).split(",")));
        return null;
    }

    public static ArrayList<String> getSynonyms(String word) throws SQLException {
        String sql = String.format("select * from synonyms where word=\"%s\"", word);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(resultSet.getString(2).split(";")));
            if (arrayList.size() >= 6) {
                arrayList = new ArrayList<>(arrayList.subList(0,6));
            }
            for (int i=0;i<arrayList.size();i++) {
                arrayList.set(i, arrayList.get(i).split("\\|")[0]);
            }
            return arrayList;
        }

        return null;
    }
}
