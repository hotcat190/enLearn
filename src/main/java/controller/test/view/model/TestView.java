package controller.test.view.model;

import controller.test.controller.TestController;
import controller.test.view.clock.CountDownClock;
import graphics.StandardParameter;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class TestView extends HBox {
    protected final VBox infoTest = new VBox();
    protected final ScrollPane examArea = new ScrollPane();
    protected final VBox readingArea = new VBox();
    protected final ToggleButton backButton = new ToggleButton("BACK");
    protected final ToggleButton submitButton = new ToggleButton("SUBMIT");
    protected final HBox hBoxForButton = new HBox(backButton, submitButton);
    protected final CountDownClock countDownClock = new CountDownClock();

    protected TestView() {
        this.getChildren().addAll(infoTest);
        this.setSpacing(50);
        this.setAlignment(Pos.CENTER);
        examArea.setPrefWidth(660 * StandardParameter.SCALE);
        examArea.setMaxHeight(650 * StandardParameter.SCALE);

        infoTest.setPrefSize(555 * StandardParameter.SCALE, 650 * StandardParameter.SCALE);


        infoTest.getChildren().add(countDownClock);
        infoTest.getChildren().add(hBoxForButton);
        infoTest.setAlignment(Pos.CENTER);
        infoTest.setSpacing(500 * StandardParameter.SCALE);
        hBoxForButton.setSpacing(40);
        hBoxForButton.setAlignment(Pos.CENTER);

        backButton.setPrefSize(140 * StandardParameter.SCALE, 55 * StandardParameter.SCALE);
        submitButton.setPrefSize(140 * StandardParameter.SCALE, 55 * StandardParameter.SCALE);

        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            TestController.getInstance().backToOpen();
        });
        backButton.setOnMouseClicked(e -> {
            TestController.getInstance().backToOpen();
        });
    }

    public CountDownClock getCountDownClock() {
        return countDownClock;
    }
}
