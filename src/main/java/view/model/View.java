package view.model;

import graphics.style.Decorator;
import javafx.scene.layout.Pane;


public abstract class View extends Pane implements Decorator {
    public void connect(Object object) {
        return;
    }
    public Pane get() {
        return this;
    }

}
