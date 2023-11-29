package sql.statistic;

import sql.model.SQLData;
import utility.calendar.Time;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLTimeStudy extends SQLData {
    public long start;
    public long end;

    private static final SQLTimeStudy INSTANCE = new SQLTimeStudy();

    public static SQLTimeStudy getInstance() {
        return INSTANCE;
    }

    private SQLTimeStudy() {
        start = System.currentTimeMillis();
    }

    public boolean update() throws SQLException {
        end = System.currentTimeMillis();
        String sqlCheck = "insert ignore into week(other) values(week(now(),1))\n" +
                "on duplicate key update other=week(now(),1);";

        String sqlUpdate = String.format(
                "update week " +
                        "set %s=%s+%d " +
                        "where other=week(now(),1);", Time.getDays(), Time.getDays(), end - start);
        statement.execute(sqlCheck);
        statement.executeUpdate(sqlUpdate);
        return false;
    }

    public int getTotalTimes(String day) throws SQLException {
        String sql = String.format("select %s from week order by other desc limit 1", day);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(1) / 1000 / 60;
    }
}
