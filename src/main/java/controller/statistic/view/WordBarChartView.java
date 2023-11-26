package controller.statistic.view;

import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import view.model.Connector;

import java.sql.SQLException;

public class WordBarChartView extends BarChart<String, Number> implements Decorator, Connector {
    /**
     * Singletons.
     */
    private static final WordBarChartView INSTANCE = new WordBarChartView();

    private final XYChart.Series<String, Number> series = new XYChart.Series<>();

    private WordBarChartView() {
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
        this.setMaxSize(640 * StandardParameter.SCALE, 300);
        this.setMinSize(640 * StandardParameter.SCALE, 300);
        this.getYAxis().setTickMarkVisible(false);
        this.setLegendVisible(false);
        if (this.getYAxis() instanceof NumberAxis yAxis) {
            yAxis.setAutoRanging(false);
            yAxis.setTickUnit(30);
            yAxis.setUpperBound(180);
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

    public static WordBarChartView getInstance() {
        return INSTANCE;
    }
}
