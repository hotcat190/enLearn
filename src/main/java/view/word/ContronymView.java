package view.word;

import dictionary.Dictionary;
import dictionary.Word;
import graphics.StandardParameter;
import graphics.app.AppWindow;
import graphics.style.UIComponent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ContronymView extends UIComponent {
    protected ArrayList<String> contronymList;
    protected final FlowPane contronymFlowPane = new FlowPane();
    protected final Color GRAPHIC_TEXT_COLOR_OF_BUTTON;

    protected ContronymView(double width, double height, double leftMargin, double topMargin, Color colorGraphic) {
        super(width, height, leftMargin, topMargin);
        GRAPHIC_TEXT_COLOR_OF_BUTTON = colorGraphic;
        setId();
        setCSS();
        set();
        addUI();
    }

    private Button getButton() {
        Button button = new Button();
        Circle circle = new Circle(3.6 * StandardParameter.SCALE, Color.TRANSPARENT);
        circle.setStroke(GRAPHIC_TEXT_COLOR_OF_BUTTON);
        button.setId("contronymButton");
        button.setMinSize(60 * StandardParameter.SCALE, 15 * StandardParameter.SCALE);
        button.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            circle.setFill(GRAPHIC_TEXT_COLOR_OF_BUTTON);
        });
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            circle.setFill(Color.TRANSPARENT);
        });
        button.setGraphic(circle);
        button.setGraphicTextGap(3);
        return button;
    }

    private void addUI() {
        for (int i = 0; i < 8; i++) {
            contronymFlowPane.getChildren().add(getButton());
        }
    }

    private void setContronymFlowPane() {
        contronymFlowPane.setMaxWidth(WIDTH - 2 * LEFT_MARGIN + 10);
        contronymFlowPane.setHgap(5);
        contronymFlowPane.setVgap(8);

    }

    @Override
    public void setId() {
        layout.setId("contronymLayout");
        titleText.setId("contronymTitle");
    }

    @Override
    public void setCSS() {
    }

    @Override
    public void set() {
        setContronymFlowPane();
        mainVBox.getChildren().add(contronymFlowPane);
        mainVBox.setTranslateY(25);
    }

    public void connect(ArrayList<String> contronymList) {
        this.contronymList = contronymList;
    }
    public void update(WordBoard wordBoard) throws SQLException {
        int i = 0;
        for (int j = 0; j < contronymList.size() && i < contronymFlowPane.getChildren().size(); j++) {
            String word = contronymList.get(i);
            Button button = (Button) contronymFlowPane.getChildren().get(i++);
            button.setOpacity(1);
            button.setText(word);

            button.setOnMouseClicked(null);
            button.setOnMouseMoved(null);
            button.setOnMouseExited(null);

            if (!Dictionary.isExisted(word)) {
                button.setOnMouseMoved(e -> {
                    AppWindow.dialog.getLayout().setOpacity(1);
                    AppWindow.dialog.getLayout().toFront();
                    AppWindow.dialog.setText("Not in dictionary");
                    AppWindow.dialog.getLayout().setOpacity(1);
                    AppWindow.dialog.getLayout().setLayoutX(e.getSceneX());
                    AppWindow.dialog.getLayout().setLayoutY(e.getSceneY());

                });
                button.setOnMouseExited(e -> {
                    AppWindow.dialog.getLayout().setOpacity(0);
                });
            } else {
                button.setDisable(false);
                button.setOnMouseClicked(e -> {
                    try {
                        wordBoard.load(new Word(word));
                        wordBoard.update();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
        while (i < contronymFlowPane.getChildren().size()) {
            Button button = (Button) contronymFlowPane.getChildren().get(i++);
            button.setOpacity(0);
            button.setDisable(true);
        }

    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public String getLink() {
        return null;
    }

    /**
     * Component.
     */


    public Pane getView() {
        return get();
    }

}
