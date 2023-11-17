package graphics.app.dashboard;

import graphics.style.Decorator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public interface DashboardTitle {
    Text textTop = new Text();
    Text textBottom = new Text();
    VBox textLayout = new VBox(textTop, textBottom);

    void setTitle();


    VBox getTitle();
}
