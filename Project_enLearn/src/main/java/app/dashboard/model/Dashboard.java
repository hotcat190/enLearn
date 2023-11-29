package app.dashboard.model;

import graphics.animation.Listener;
import graphics.style.Decorator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class Dashboard extends Pane implements Listener, Decorator, DashboardTitle {
    protected Text textTop = new Text();
    protected Text textBottom = new Text();
    protected VBox textLayout = new VBox(textTop, textBottom);

    @Override
    public VBox getTitle() {
        return textLayout;
    }

    public abstract void load();
}
