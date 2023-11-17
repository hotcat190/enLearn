package view.statistic;

import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import view.model.Connector;

import java.sql.SQLException;

public class WordBarChartView extends BarChart<String, Number> implements Decorator, Connector {
    private final XYChart.Series<String, Number> series = new XYChart.Series<>();

    public WordBarChartView() {
        super(new CategoryAxis(), new NumberAxis());
        setId();
        setCSS();
        set();
    }

    @Override
    public void setId() {
        this.setId("wordLearnedBarChart");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_statistic_engine_package.css"));
    }

    @Override
    public void set() {
        this.setPrefSize(560 * StandardParameter.SCALE, 300);
        this.setMaxSize(560 * StandardParameter.SCALE, 300);
        this.setMinSize(560 * StandardParameter.SCALE, 300);
        this.getYAxis().setTickMarkVisible(false);
        this.setLegendVisible(false);
        if (this.getYAxis() instanceof NumberAxis yAxis) {
            yAxis.setAutoRanging(false);
            yAxis.setTickUnit(100);
            yAxis.setUpperBound(500);
        }
        this.setCategoryGap(35);
        this.getData().add(series);
    }

    @Override
    public void update() throws SQLException {
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void connect(Object object) {
        if (object instanceof ObservableList<?>) {
            series.setData((ObservableList<XYChart.Data<String, Number>>) object);
        }
    }
}
