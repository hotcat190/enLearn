package controller.statistic;

import controller.model.Controller;
import controller.statistic.data.WordLineChartData;
import javafx.scene.Node;
import controller.statistic.view.WordLineChartView;

public class WordLineChartController extends Controller {
    private final WordLineChartView wordLineChartView = WordLineChartView.getInstance();
    private final WordLineChartData wordLineChartData = WordLineChartData.getInstance();

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
