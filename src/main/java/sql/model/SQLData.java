package sql.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SQLData {
    protected static Connection connection;
    protected static Statement statement;

    static {
        try {
            connection = DriverManager.getConnection(SQLSever.URL, SQLSever.USER, SQLSever.PASSWORD);
            statement = connection.createStatement();
            statement.execute("set sql_safe_updates=0;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
