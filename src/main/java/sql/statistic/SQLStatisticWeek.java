package sql.statistic;

import sql.model.SQLData;
import utility.calendar.Time;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLStatisticWeek extends SQLData {
    private long start;
    private long end;
    private static final SQLStatisticWeek INSTANCE = new SQLStatisticWeek();

    public static SQLStatisticWeek getInstance() {
        return INSTANCE;
    }

    private SQLStatisticWeek() {
        start = System.currentTimeMillis() / 1000;
        try {
            checkNull(Time.getDays());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkNull(String day) throws SQLException {
        String sqlCheck = String.format("""
                insert ignore into week_details(other,dayMark) values(week(now(),1),'%s')
                on duplicate key update other=week(now(),1);""", day);
        statement.executeUpdate(sqlCheck);
    }

    public void increaseTotalWordsAndTotalTimes() throws SQLException {
        end = System.currentTimeMillis();
        String sqlUpdate = """
                update week_details
                set totalWords=totalWords+1
                where other=week(now(),1) and dayMark=dayName(now());
                """;
        statement.executeUpdate(sqlUpdate);
    }

    public int getTotalWords(String day) throws SQLException {
        checkNull(day);
        String sql = String.format("""
                select totalWords from week_details where other=week(now(),1)\s
                and dayMark='%s'
                """, day);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(1);
    }

    public int getTotalTimes(String day) throws SQLException {
        checkNull(day);
        String sql = String.format("""
                select totalTimes from week_details where other=week(now(),1)\s
                and dayMark='%s'
                """, day);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(1) / 60;
    }

    public void updateTimes() throws SQLException {
        end = System.currentTimeMillis() / 1000;
        String sqlUpdate = String.format("""
                update week_details
                set totalTimes=totalTimes+%d\s
                where other=week(now(),1) and dayMark=dayName(now());
                """, end - start);
        statement.executeUpdate(sqlUpdate);
    }
}
