package sql.dictionary;

import sql.model.SQLData;
import sql.model.SQLSever;

import java.sql.*;
import java.util.*;

public class SQLDictionary extends SQLData {
    private static final SQLDictionary INSTANCE = new SQLDictionary();

    public static SQLDictionary getInstance() {
        return INSTANCE;
    }

    private SQLDictionary() {
    }
    public ResultSet getWord(String regexp) throws SQLException {

        String sql = String.format("""
                         select * from dictionary where word regexp('%s');
                """, regexp);
         return statement.executeQuery(sql);
    }

    public boolean isExisted(String word) throws SQLException {
        String sql = String.format("select * from dictionary where word =\"%s\";", word.trim());
        return statement.executeQuery(sql).next();
    }

    public void putHistory(String word)  {
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

    public String getMedia(String word) {
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
        return "";
    }

    public List<String> getHistory() {
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
        return list;
    }

    public ArrayList<String> getIrregularVerb(String word) throws SQLException {
        String sql = String.format("""
        select * from irregular_verbs where infinity='%s'
        """, word);
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<String> arrayList = new ArrayList<>();
        if (resultSet.next()) {
            arrayList.add(resultSet.getString(2));
            arrayList.add(resultSet.getString(3));
            return arrayList;
        }
        return arrayList;
    }

    public ArrayList<String> getAntonyms(String word) throws SQLException {
        String sql = String.format("select * from antonyms where word=\"%s\"", word);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) return new ArrayList<>(Arrays.asList(resultSet.getString(2).split(",")));
        return new ArrayList<>();
    }

    public ArrayList<String> getSynonyms(String word) throws SQLException {
        String sql = String.format("select * from synonyms where word=\"%s\"", word);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(resultSet.getString(2).split(";")));

            for (int i=0;i<arrayList.size();i++) {
                if (!SQLDictionary.getInstance().isExisted(arrayList.get(i))) {
                    arrayList.remove(i);
                    continue;
                }
                arrayList.set(i, arrayList.get(i).split("\\|")[0]);
            }
            if (arrayList.size() >= 4) {
                arrayList = new ArrayList<>(arrayList.subList(0,4));
            }
            return arrayList;
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getDictionary() {
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
