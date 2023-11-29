package controller.progress.view;

import graphics.animation.Listener;
import graphics.style.Decorator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class ProgressBoxIntroView extends VBox implements Decorator,Listener {
    private static final ProgressBoxIntroView INSTANCE = new ProgressBoxIntroView();
    private ProgressBoxIntroView() {
        setId();
        setCSS();
        set();
        setListener();
    }
    @Override
    public void setListener(Object object) {
    }

    @Override
    public void setListener() {
    }

    @Override
    public void setId() {

    }

    @Override
    public void setCSS() {
    }

    @Override
    public void set() {
        Text textTop = new Text("Your");
        Text textBottom1 = new Text("Progress");
        Text textBottom2 =new Text("Track your learning progress here.");

        textTop.setStyle("-fx-font-family: 'Segoe UI Semibold';" +
                "-fx-fill: #4f4f4f;" +
                "-fx-font-size: 12;");
        textBottom1.setStyle("-fx-font-family: 'Segoe UI Variable';" +
                "-fx-fill: #f2f2f2;" +
                "-fx-font-size: 20;" +
                "-fx-font-weight: bold");
        textBottom2.setStyle("-fx-font-family: 'Segoe UI Semibold';" +
                "-fx-fill: #4f4f4f;" +
                "-fx-font-size: 10;");
        textBottom1.setTranslateY(-5);
        textBottom2.setTranslateY(6);

        this.getChildren().add( new VBox(textTop, textBottom1, textBottom2));
        this.setTranslateY(15);
        this.setTranslateX(20);
    }

    @Override
    public void update() throws SQLException {
    }

    @Override
    public String getLink() {
        return null;
    }

    public static ProgressBoxIntroView getInstance() {
        return INSTANCE;
    }
}
