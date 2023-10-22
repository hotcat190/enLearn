package dictionary;

import sql.Sever;

import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Dictionary {
    private static final Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(Sever.URL,Sever.USER,Sever.PASSWORD);
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

    public static void putHistory(String word) throws SQLException {
        String sql = String.format("insert into history values(\"%s\");", word);
        statement.executeUpdate(sql);
    }
    public static LinkedList<String> getHistory() {
        LinkedList<String> linkedList = new LinkedList<>();
        String sql = "select * from history;";
        try {
            ResultSet resultSet= statement.executeQuery(sql);
            while (resultSet.next()) {
                linkedList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return linkedList;
    }
}
