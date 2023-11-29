package controller.word.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import controller.word.data.Word;
import graphics.StandardParameter;
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
    private ArrayList<String> contronymList;
    private final FlowPane contronymFlowPane = new FlowPane();
    private final Color GRAPHIC_TEXT_COLOR_OF_BUTTON;
    private final Label textNoContent = new Label("No content here!");

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
        button.setId("contronym-view__button");
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
        for (int i = 0; i < 4; i++) {
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
        layout.setId("contronym--view__pane--layout");
        titleText.setId("contronym--view__text--title");
        textNoContent.setId("contronym-view__text--no-content");
    }

    @Override
    public void setCSS() {
    }

    @Override
    public void set() {
        setContronymFlowPane();
        mainVBox.getChildren().add(contronymFlowPane);
        mainVBox.setTranslateY(25);
        textNoContent.setPadding(new Insets(0, 0, 0, 35));
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
            button.setVisible(true);
            button.setOnMouseClicked(e -> {
                try {
                    wordBoard.load(new Word(word));
                    wordBoard.update();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        while (i < contronymFlowPane.getChildren().size()) {
            Button button = (Button) contronymFlowPane.getChildren().get(i++);
            button.setVisible(false);
        }

        if (contronymList.isEmpty()) {
            layout.getChildren().add(textNoContent);
        } else {
            layout.getChildren().remove(textNoContent);
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
