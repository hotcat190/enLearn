package sql.statistic;

import sql.model.SQLData;
import utility.calendar.Time;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLStatisticWeek extends SQLData {
    private static long start;
    private static long end;
    private static final int totalTimes = 0;
    private static final int totalWords = 0;

    static {
        start = System.currentTimeMillis();
        try {
            checkNull(Time.getDays());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkNull(String day) throws SQLException {
        String sqlCheck = String.format("insert ignore into week_details(other,dayMark) values(week(now(),1),\"%s\")\n" +
                "on duplicate key update other=week(now(),1);",day);
        statement.executeUpdate(sqlCheck);
    }
    public static void increaseTotalWordsAndTotalTimes() throws SQLException {
        end = System.currentTimeMillis();
        String sqlUpdate = String.format("update week_details " +
                "set totalWords=totalWords+1," +
                "totalTimes=%d-%d " +
                "where other=week(now(),1) and dayMark=dayName(now());", end, start);
        statement.executeUpdate(sqlUpdate);
    }

    public static int getTotalWords(String day) throws SQLException {
        checkNull(day);
        String sql = String.format("select totalWords from week_details where other=week(now(),1) and dayMark=\"%s\"", day);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(1);
    }

    public static int getTotalTimes(String day) throws SQLException {
        checkNull(day);
        String sql = String.format("select totalTimes from week_details where other=week(now(),1) and dayMark=\"%s\"", day);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(1) / 1000 / 60;
    }

    public static void updateTimes() throws SQLException {
        end = System.currentTimeMillis();
        String sqlUpdate = String.format("update week_details " +
                "set totalTimes=totalTimes+%d-%d " +
                "where other=week(now(),1) and dayMark=dayName(now());", end, start);
        statement.executeUpdate(sqlUpdate);
    }
}
