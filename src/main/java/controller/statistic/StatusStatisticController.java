package controller.statistic;

import controller.model.Controller;
import sql.user.SQLUser;
import graphics.style.Decorator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class StatusStatisticController extends Controller implements Decorator {
    private final Text textTop = new Text("Total Words Learn");
    private final Text textTotal = new Text(String.valueOf(SQLUser.getTotalWords()));
    private final Text textDate = new Text();
    private final VBox vBox = new VBox();

    public StatusStatisticController() {
        set();
    }

    @Override
    public void setId() {
    }

    @Override
    public void setCSS() {

    }

    @Override
    public void set() {
        textTop.setStyle("-fx-font-family: 'Segoe UI Semibold';" +
                "-fx-font-size: 12;" +
                "-fx-fill: #f2f2f2;");
        textTotal.setStyle("-fx-font-family: 'Segoe UI Variable';" +
                "-fx-font-size: 40;" +
                "-fx-fill: #f2f2f2;" +
                "-fx-font-weight: bold");
        textDate.setStyle("-fx-font-family: 'Segoe UI Variable';" +
                "-fx-font-size: 8;" +
                "-fx-fill: #f2f2f2;");
        textTotal.setTranslateY(-10);

        vBox.getChildren().addAll(
                textTop,
                textTotal
        );
    }

    @Override
    public void update() throws SQLException {
        textTotal.setText(String.valueOf(SQLUser.getTotalWords()));
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void updateView() {
        try {
            update();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String typeChart) {
        textTop.setText("Total " + typeChart + " Chart");
    }
    @Override
    public void loadData() {

    }

    @Override
    public void loadData(Object object) {

    }

    @Override
    public Pane getView() {
        return vBox;
    }
}
