package controller.test.reading;

import controller.model.Controller;
import controller.test.reading.data.ReadingTestData;
import controller.test.reading.view.ReadingTestView;
import controller.test.view.clock.CountDownClock;
import javafx.scene.Node;

public class ReadingTestController extends Controller {
    /**
     * Singleton.
     */
    private static final ReadingTestController INSTANCE = new ReadingTestController();

    private ReadingTestView readingTestView;
    private ReadingTestData readingTestData;


    public ReadingTestController() {

    }

    @Override
    public void updateView() {
        readingTestView = new ReadingTestView(readingTestData);
    }

    @Override
    public void loadData() {
        readingTestData = new ReadingTestData(3);
    }

    @Override
    public void loadData(Object object) {

    }

    @Override
    public Node getView() {
        return readingTestView;
    }

    public static ReadingTestController getInstance() {
        return INSTANCE;
    }

    public CountDownClock getCountDownClock() {
        return readingTestView.getCountDownClock();
    }
}
