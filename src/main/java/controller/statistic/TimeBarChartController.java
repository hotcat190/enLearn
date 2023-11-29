package controller.statistic;

import controller.model.Controller;
import controller.statistic.data.TimeBarChartData;
import javafx.scene.Node;
import controller.statistic.view.TimeBarChartView;

public class TimeBarChartController extends Controller {
    private final TimeBarChartData timeBarChartData = TimeBarChartData.getInstance();
    private final TimeBarChartView timeBarChartView = TimeBarChartView.getInstance();
    public TimeBarChartController() {
        timeBarChartView.connect(timeBarChartData.getObservableList());
    }

    @Override
    public void updateView() {
    }

    @Override
    public void loadData() {
    }

    @Override
    public void loadData(Object object) {
    }

    @Override
    public Node getView() {
        return timeBarChartView;
    }
}
