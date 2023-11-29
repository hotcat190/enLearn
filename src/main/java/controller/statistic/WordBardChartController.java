package controller.statistic;

import controller.model.Controller;
import controller.statistic.data.WordBarChartData;
import javafx.scene.Node;
import controller.statistic.view.WordBarChartView;

public class WordBardChartController extends Controller {
    private final WordBarChartData wordBarChartData = WordBarChartData.getInstance();
    private final WordBarChartView wordBarChartView = WordBarChartView.getInstance();
    public WordBardChartController() {
        wordBarChartView.connect(wordBarChartData.getObservableList());
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
        return wordBarChartView;
    }
}
