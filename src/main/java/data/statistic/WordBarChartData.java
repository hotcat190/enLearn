package data.statistic;

import data.model.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import utility.calendar.DayOfWeek;

import java.sql.SQLException;

public class WordBarChartData extends Data {
    private final ObservableList<XYChart.Data<String, Number>> observableList = FXCollections.observableArrayList();


    public WordBarChartData() {
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
            observableList.add(new XYChart.Data<>("Mon", SQLStatisticWeek.getTotalTimes(DayOfWeek.Monday.name())));
            observableList.add(new XYChart.Data<>("Tue", SQLStatisticWeek.getTotalTimes(DayOfWeek.Tuesday.name())));
            observableList.add(new XYChart.Data<>("Wed", SQLStatisticWeek.getTotalTimes(DayOfWeek.Wednesday.name())));
            observableList.add(new XYChart.Data<>("Thus", SQLStatisticWeek.getTotalTimes(DayOfWeek.Thursday.name())));
            observableList.add(new XYChart.Data<>("Fri", SQLStatisticWeek.getTotalTimes(DayOfWeek.Friday.name())));
            observableList.add(new XYChart.Data<>("Sat", SQLStatisticWeek.getTotalTimes(DayOfWeek.Saturday.name())));
            observableList.add(new XYChart.Data<>("Sun", SQLStatisticWeek.getTotalTimes(DayOfWeek.Sunday.name())));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<XYChart.Data<String, Number>> getObservableList() {
        return observableList;
    }
}
