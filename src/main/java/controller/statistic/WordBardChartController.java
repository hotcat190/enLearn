package controller.statistic;

import controller.model.Controller;
import data.statistic.WordBarChartData;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import view.statistic.WordBarChartView;

public class WordBardChartController extends Controller {
    private final WordBarChartData wordBarChartData = new WordBarChartData();
    private final WordBarChartView wordBarChartView = new WordBarChartView();
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
