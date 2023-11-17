package view.statistic;

import controller.model.Listener;
import controller.statistic.StatusStatisticController;
import controller.statistic.WordBardChartController;
import controller.statistic.WordLineChartController;
import graphics.StandardParameter;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import view.model.GraphButton;

import java.sql.SQLException;

public class StatisticBoard extends StackPane implements Decorator, Listener {
    /**
     * Controller.
     */
    private final WordBardChartController wordBardChartController = new WordBardChartController();
    private final StatusStatisticController statusStatisticController = new StatusStatisticController();
    private final WordLineChartController wordLineChartController = new WordLineChartController();

    /**
     * View.
     */
    private final GraphButton graphButton = new GraphButton();
    private final VBox vBox = new VBox();

    public StatisticBoard() {
        setId();
        setCSS();
        set();
        setListener();
    }

    @Override
    public void setId() {
        this.setId("statistic-board__layout");
        this.applyCss();
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_statistic_engine_package.css"));
    }

    @Override
    public void set() {
        HBox h_box = new HBox(statusStatisticController.getView(), graphButton);
        h_box.setAlignment(Pos.CENTER);
        h_box.setSpacing(200);
        h_box.setTranslateY(25);
        graphButton.get().setTranslateY(5);
        vBox.getChildren().addAll(
                h_box,
                wordBardChartController.getView()
        );
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(vBox);
        this.setPrefWidth(620 * StandardParameter.SCALE);
    }

    @Override
    public void update() throws SQLException {
        statusStatisticController.update();
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void setListener(Object object) {

    }

    @Override
    public void setListener() {
        for (MenuItem menuItem : graphButton.getMenuButton().getItems()) {
            menuItem.addEventHandler(ActionEvent.ACTION, e -> {
                statusStatisticController.update(menuItem.getText());
                if (menuItem.getText().equals("Time")) {
                    vBox.getChildren().remove(1);
                    vBox.getChildren().add(wordBardChartController.getView());
                } else if (menuItem.getText().equals("Word")) {
                    vBox.getChildren().remove(1);
                    vBox.getChildren().add(wordLineChartController.getView());
                }
            });
        }
    }
}
