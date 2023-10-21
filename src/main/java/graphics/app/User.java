package graphics.app;

import sql.Sever;

import java.sql.*;

public class User {
    private int streakDay = 0;
    private int highestScore = 100;

    private final Statement statement;

    public User() {
        try {
            Connection connection = DriverManager.getConnection(Sever.URL, Sever.USER, Sever.PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "insert into user(lasttimelogin) values(current_date())";
//
        sql = "select datediff(current_date(),(select lasttimelogin from user where id=(select max(id) from user))) from user limit 1;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int delta = Math.abs(resultSet.getInt(1));
            System.out.println(delta);
            if (delta == 1) {
                sql = "insert into user(streakday,lasttimelogin) \n" +
                        "select streakday+1 ,current_date(), from user where id=(select max(id) from user) ;";
                statement.executeUpdate(sql);
            } else if (delta != 0) {
                sql = "insert into user(streakday,lasttimelogin) \n" +
                        "0 ,current_date ;";
                statement.executeUpdate(sql);
            }
            sql="select streakday from user where id=(select max(id) from user)";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
