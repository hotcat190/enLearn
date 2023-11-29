package controller.statistic.data;

import data.model.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import sql.statistic.SQLStatisticWeek;
import utility.calendar.DayOfWeek;

import java.sql.SQLException;

public class WordLineChartData extends Data {
    /**
     * Singletons.
     */
    private static final WordLineChartData INSTANCE = new WordLineChartData();
    private final ObservableList<XYChart.Data<String, Number>> observableList = FXCollections.observableArrayList();

    private WordLineChartData() {
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
            observableList.add(new XYChart.Data<>("Mon", SQLStatisticWeek.getInstance().getTotalWords(DayOfWeek.Monday.name())));
            observableList.add(new XYChart.Data<>("Tue", SQLStatisticWeek.getInstance().getTotalWords(DayOfWeek.Tuesday.name())));
            observableList.add(new XYChart.Data<>("Wed", SQLStatisticWeek.getInstance().getTotalWords(DayOfWeek.Wednesday.name())));
            observableList.add(new XYChart.Data<>("Thus", SQLStatisticWeek.getInstance().getTotalWords(DayOfWeek.Thursday.name())));
            observableList.add(new XYChart.Data<>("Fri", SQLStatisticWeek.getInstance().getTotalWords(DayOfWeek.Friday.name())));
            observableList.add(new XYChart.Data<>("Sat", SQLStatisticWeek.getInstance().getTotalWords(DayOfWeek.Saturday.name())));
            observableList.add(new XYChart.Data<>("Sun", SQLStatisticWeek.getInstance().getTotalWords(DayOfWeek.Sunday.name())));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<XYChart.Data<String, Number>> getObservableList() {
        return observableList;
    }

    public static WordLineChartData getInstance() {
        return INSTANCE;
    }
}
