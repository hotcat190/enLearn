package controller.statistic.view;

import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import view.model.Connector;

import java.sql.SQLException;

public class WordLineChartView extends LineChart<String, Number> implements Decorator, Connector {
    /**
     * Singletons.
     */
    private static final WordLineChartView INSTANCE = new WordLineChartView();
    /**
     * Components.
     */
    private final XYChart.Series<String, Number> series = new XYChart.Series<>();


    private WordLineChartView() {
        super(new CategoryAxis(), new NumberAxis());
        setId();
        setCSS();
        set();
    }

    @Override
    public void setId() {
        this.setId("lineChart");
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

        this.getData().add(series);
        this.setCreateSymbols(false);
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
        if (object instanceof ObservableList<?> observableList) {
            series.setData((ObservableList<XYChart.Data<String, Number>>) observableList);
        }
    }

    public static WordLineChartView getInstance() {
        return INSTANCE;
    }
}
