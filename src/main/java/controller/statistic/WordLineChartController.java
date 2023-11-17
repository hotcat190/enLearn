package controller.statistic;

import controller.model.Controller;
import data.statistic.WordLineChartData;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import view.statistic.WordLineChartView;

public class WordLineChartController extends Controller {
    private final WordLineChartView wordLineChartView = new WordLineChartView();
    private final WordLineChartData wordLineChartData = new WordLineChartData();

    public WordLineChartController() {
        wordLineChartView.connect(wordLineChartData.getObservableList());
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
        return wordLineChartView;
    }
}
