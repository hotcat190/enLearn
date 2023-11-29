package sql.user;

import sql.model.SQLData;
import user.UserSkill;

import java.sql.*;

public class SQLUser extends SQLData {
    private int streakDay = 0;
    private  int band = 100;
    private  String skillPoint = "A";
    private  int totalWords = 0;

    private static final SQLUser INSTANCE = new SQLUser();

    public static SQLUser getInstance() {
        return INSTANCE;
    }
    private SQLUser() {
        String sql;
        sql = """
                select datediff(current_date(),(
                select lastTimeLogin from user where id=(select max(id) from user))
                ) from user limit 1;
                """;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int delta = Math.abs(resultSet.getInt(1));
            System.out.println(delta);
            if (delta == 1) {
                sql = """
                        insert into user(streakday,lastTimeLogin)
                        select streakday+1,current_date()  from user order by id desc limit 1;
                        """;
                statement.executeUpdate(sql);
            } else if (delta != 0) {
                sql = """
                        insert into user(streakday,lastTimeLogin)
                        values(0 ,current_date());
                        """;
                statement.executeUpdate(sql);
            }
            sql = "select streakday,totalWords from user order by id desc limit 1";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            streakDay = resultSet.getInt(1);
            totalWords = resultSet.getInt(2);

            sql = "select max(score) from user;";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            band = resultSet.getInt(1);

            sql = "select max(skillPoint) from user order by skillPoint";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            skillPoint = resultSet.getString(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public  int getStreakDay() {
        return streakDay;
    }

    public  int getBand() {
        return band;
    }

    public  String getSkillPoint() {
        return skillPoint;
    }

    public  int getTotalWords() {
        String sql = "select sum(totalWords) from user;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  void setBand(int totalBand) {
        band = totalBand;
    }

    public void update() {
        String sql = String.format("""
                update user\s
                set score=%d
                where lastTimeLogin=current_date();
                """, band);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        skillPoint = UserSkill.getSkillPoint();
        String sql1 = String.format("""
                update user
                set skillPoint='%s'
                where lastTimeLogin=current_date();
                """, skillPoint);
        try {
            statement.executeUpdate(sql1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public  void increaseTotalWords() throws SQLException {
        String sql = """
                update user
                set totalWords=totalWords+1
                where lastTimeLogin=date(now());""";
        statement.executeUpdate(sql);
        totalWords++;
    }
}
