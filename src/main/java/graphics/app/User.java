package graphics.app;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sql.SQLData;

import java.sql.*;

public class User extends SQLData {
    private static int streakDay = 0;
    private static int band = 100;
    private static String skillPoint = "A";
    private static int totalWords = 0;

    static {
        String sql;
        sql = "select datediff(current_date(),(select lasttimelogin from user where id=(select max(id) from user))) from user limit 1;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int delta = Math.abs(resultSet.getInt(1));
            System.out.println(delta);
            if (delta == 1) {
                sql = "insert into user(streakday,lasttimelogin) \n" +
                        "select streakday+1,current_date()  from user order by id desc limit 1;";
                statement.executeUpdate(sql);
            } else if (delta != 0) {
                sql = "insert into user(streakday,lasttimelogin) \n" +
                        "values(0 ,current_date());";
                statement.executeUpdate(sql);
            }
            sql = "select streakday,score,totalWords from user order by id desc limit 1";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            streakDay = resultSet.getInt(1);
            band = resultSet.getInt(2);
            totalWords = resultSet.getInt(3);

            sql = "select max(skillPoint) from user order by skillPoint";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            skillPoint = resultSet.getString(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int getStreakDay() {
        return streakDay;
    }

    public static int getBand() {
        return band;
    }

    public static String getSkillPoint() {
        return skillPoint;
    }

    public static int getTotalWords() {
        return totalWords;
    }

    public static void increaseTotalWords() throws SQLException {
        String sql = "update user\n" +
                "set totalWords=totalWords+1\n" +
                "where lasttimelogin=date(now());";
        statement.executeUpdate(sql);
        totalWords++;
    }
}
