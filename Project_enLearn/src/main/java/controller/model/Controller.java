package controller.model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Controller {
    public abstract void updateView();

    public abstract void loadData();

    public abstract void loadData(Object object);

    public abstract Node getView();
}
