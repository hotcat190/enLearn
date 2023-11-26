package sql.dictionary;

import sql.model.SQLData;

import java.sql.*;
import java.util.*;

public class SQLDictionary extends SQLData {

    public static ResultSet getWord(String regexp) throws SQLException {
        String sql = String.format("select * from dictionary where word regexp(\"%s\");", regexp);
         return statement.executeQuery(sql);
    }

    public static boolean isExisted(String word) throws SQLException {
        String sql = String.format("select * from dictionary where word =\"%s\";", word.trim());
        return statement.executeQuery(sql).next();
    }

    public static void putHistory(String word)  {
        String sqlSafe = "set sql_safe_updates=0;";
        String sqlDelete = String.format("delete from history where word='%s';", word);
        String sql = String.format("insert into history values(now(),'%s');", word);

        try {
            statement.execute(sqlSafe);
            statement.executeUpdate(sqlDelete);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMedia(String word) {
        String sql = String.format("select distinct media from sound where word='%s'", word);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<String> getHistory() {
        List<String> list = new ArrayList<>();
        String sql = "select word from history order by lastModifier desc;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  list;
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

    public static ArrayList<String> getDictionary() {
        String sql = "select distinct word from dictionary;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
