package controller.word;

import controller.model.Controller;
import controller.model.Update;
import data.word.IrregularVerbsData;
import dictionary.Word;
import view.word.IrregularVerbsView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;

public class IrregularVerbsController extends Controller implements Update {
    private final IrregularVerbsView irregularVerbsView = new IrregularVerbsView(130, 165, 15, 15);
    private final IrregularVerbsData irregularVerbsData = new IrregularVerbsData();

    private final StringProperty pastSimpleProperty = new SimpleStringProperty();
    private final StringProperty pastParticipleProperty = new SimpleStringProperty();

    public IrregularVerbsController() {
        irregularVerbsView.connect(pastSimpleProperty, pastSimpleProperty);
    }
    public Pane getView() {
        return irregularVerbsView.get();
    }

    /**
     * Update view.
     */
    @Override
    public void updateView() {
        irregularVerbsView.update();
    }

    @Override
    public void loadData() {

    }

    /**
     * Load and update data.
     *
     * @param object word.
     */
    @Override
    public void loadData(Object object) {
        if (object instanceof Word word) {
            irregularVerbsData.load((word));
            pastSimpleProperty.set(irregularVerbsData.getPastSimple());
            pastParticipleProperty.set(irregularVerbsData.getPastParticiple());
        }
    }
}