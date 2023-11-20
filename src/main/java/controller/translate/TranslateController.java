package controller.translate;

import controller.model.Controller;
import controller.model.Listener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import view.translate.TranslateView;

public class TranslateController extends Controller implements Listener {
    private final TranslateView translateView = new TranslateView();
    private final StringProperty outputProperty = new SimpleStringProperty();
    private final StringProperty inputProperty = new SimpleStringProperty();

    public TranslateController() {
        translateView.connect(outputProperty);
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
        return null;
    }

    /**
     * Set listener with other object.
     *
     */
    @Override
    public void setListener(Object object) {

    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {

    }
}
