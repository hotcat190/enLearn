package controller.statistic.data;

import data.model.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import sql.statistic.SQLStatisticWeek;
import utility.calendar.DayOfWeek;

import java.sql.SQLException;

public class TimeBarChartData extends Data {
    private static final TimeBarChartData INSTANCE = new TimeBarChartData();
    private final ObservableList<XYChart.Data<String, Number>> observableList = FXCollections.observableArrayList();


    private TimeBarChartData() {
        set();
    }

    /**
     * Load and update data.
     */
    @Override
    public void load() {

    }

    /**
     * Set data.
     */
    @Override
    public void set() {
        try {
            /*
                Total time with unit minutes.
             */
            observableList.add(new XYChart.Data<>("Mon", SQLStatisticWeek.getInstance().getTotalTimes(DayOfWeek.Monday.name())));
            observableList.add(new XYChart.Data<>("Tue", SQLStatisticWeek.getInstance().getTotalTimes(DayOfWeek.Tuesday.name())));
            observableList.add(new XYChart.Data<>("Wed", SQLStatisticWeek.getInstance().getTotalTimes(DayOfWeek.Wednesday.name())));
            observableList.add(new XYChart.Data<>("Thus", SQLStatisticWeek.getInstance().getTotalTimes(DayOfWeek.Thursday.name())));
            observableList.add(new XYChart.Data<>("Fri", SQLStatisticWeek.getInstance().getTotalTimes(DayOfWeek.Friday.name())));
            observableList.add(new XYChart.Data<>("Sat", SQLStatisticWeek.getInstance().getTotalTimes(DayOfWeek.Saturday.name())));
            observableList.add(new XYChart.Data<>("Sun", SQLStatisticWeek.getInstance().getTotalTimes(DayOfWeek.Sunday.name())));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<XYChart.Data<String, Number>> getObservableList() {
        return observableList;
    }

    public static TimeBarChartData getInstance() {
        return INSTANCE;
    }
}
