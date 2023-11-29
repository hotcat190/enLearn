package controller.statistic.view;

import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import utility.calendar.Time;
import view.model.Connector;

import java.sql.SQLException;

public class TimeBarChartView extends BarChart<String, Number> implements Decorator, Connector {
    /**
     * Singletons.
     */
    private static final TimeBarChartView INSTANCE = new TimeBarChartView();

    private final XYChart.Series<String, Number> series = new XYChart.Series<>();

    private TimeBarChartView() {
        super(new CategoryAxis(), new NumberAxis());
        setId();
        setCSS();
        set();
    }

    @Override
    public void setId() {
        this.setId("time-bar-chart-view__chart--layout");
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
            yAxis.setTickUnit(15);
            yAxis.setUpperBound(75);
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
        for (XYChart.Series<String, Number> series : this.getData()) {
            for (XYChart.Data<String, Number> item : series.getData()) {
                Text text_date = new Text(Time.getDateWithFormat("dd MMMM, yyyy"));
                Label label_value = new Label(item.getYValue().toString() + " min");
                Rectangle rectangle = new Rectangle(5, 5, Color.rgb(27, 62, 237));
                label_value.setGraphic(rectangle);
                VBox h_box_for_display = new VBox(text_date, label_value);
                text_date.setStyle("""
                        -fx-font-size: 10px;
                        -fx-font-family: 'Segoe UI Semibold';
                        -fx-fill: rgb(56,56,56);
                        """);
                label_value.setStyle("""
                        -fx-font-family: 'Segoe UI Variable';
                        -fx-font-weight: bolder;
                        -fx-fill: #f2f2f2;
                        -fx-font-size: 12px;
                        """);
                Tooltip tooltip = new Tooltip();
                tooltip.setGraphic(h_box_for_display);
                Tooltip.install(item.getNode(),tooltip);

            }
        }
    }

    public static TimeBarChartView getInstance() {
        return INSTANCE;
    }
}
