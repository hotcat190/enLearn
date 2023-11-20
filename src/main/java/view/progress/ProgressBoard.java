package view.progress;

import controller.model.Listener;
import controller.progress.ProgressController;
import data.model.Data;
import graphics.app.User;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ProgressBoard extends Data implements Listener {
    /**
     * Controller.
     */
    public final ProgressController progressController = new ProgressController();


    public Node getLayout(){
        return progressController.getView();
    }
    @Override
    public void setListener(Object object) {

    }

    @Override
    public void setListener() {

    }

    /**
     * Load and update data.
     */
    @Override
    public void load() {
    }

    /**
     * Set data.
     */
    @Override
    public void set() {
    }
}
