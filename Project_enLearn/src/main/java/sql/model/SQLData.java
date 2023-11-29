package sql.model;

import java.sql.*;

public abstract class SQLData {
    protected Connection connection;
    protected  Statement statement;

    protected SQLData() {
        try {
            connection = DriverManager.getConnection(SQLSever.URL, SQLSever.USER, SQLSever.PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.execute("set sql_safe_updates=0;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
