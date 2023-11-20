package graphics.app.dashboard;

import controller.model.Listener;
import graphics.app.AppWindow;
import graphics.style.Decorator;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Dashboard extends Pane implements Listener, Decorator,DashboardTitle {
    protected Text textTop = new Text();
    protected Text textBottom = new Text();
    protected VBox textLayout = new VBox(textTop, textBottom);

    @Override
    public VBox getTitle() {
        return textLayout;
    }
}
