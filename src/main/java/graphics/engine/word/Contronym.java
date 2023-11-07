package graphics.engine.word;

import dictionary.Dictionary;
import dictionary.Word;
import graphics.StandardParameter;
import graphics.app.AppWindow;
import graphics.ui.Component;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Contronym extends Component {
    protected ArrayList<String> contronymList;

    protected Word word = null;
    protected WordUtilUI wordUtilUI = null;
    protected final FlowPane contronymFlowPane = new FlowPane();
    protected final Color GRAPHIC_TEXT_COLOR_OF_BUTTON;
    protected Contronym(double width, double height, double leftMargin, double topMargin,Color colorGraphic) {
        super(width, height, leftMargin, topMargin);
        GRAPHIC_TEXT_COLOR_OF_BUTTON = colorGraphic;
        setID();
        setCSS();
        setUI();
        addUI();
    }

    private Button getButton() {
        Button button = new Button();
        Circle circle = new Circle(3.6 * StandardParameter.SCALE, GRAPHIC_TEXT_COLOR_OF_BUTTON);
        button.setId("contronymButton");
        button.setMinSize(70 * StandardParameter.SCALE, 23 * StandardParameter.SCALE);
        button.setGraphic(circle);
        button.setGraphicTextGap(3);
        return button;
    }

    private void addUI() {
        for (int i = 0; i < 8; i++) {
            contronymFlowPane.getChildren().add(getButton());
        }
    }
    public void updateUI(WordBoard wordBoard) throws SQLException {
        int i = 0;
        for (String word : contronymList) {
            Button button = (Button) contronymFlowPane.getChildren().get(i++);
            button.setText(word);
            if (!Dictionary.isExisted(word)) {
                button.setOnMouseMoved(e -> {
                    AppWindow.dialog.getLayout().setOpacity(1);
                    AppWindow.dialog.getLayout().toFront();
                    AppWindow.dialog.setText("Not in dictionary");
                    AppWindow.dialog.getLayout().setOpacity(1);
                    AppWindow.dialog.getLayout().setLayoutX(e.getSceneX());
                    AppWindow.dialog.getLayout().setLayoutY(e.getSceneY());

                });
                button.setOnMouseExited(e->{
                    AppWindow.dialog.getLayout().setOpacity(0);
                });
            } else {
                button.setOnMouseClicked(e -> {
                    try {
                        wordBoard.loadData(new Word(word));
                        wordBoard.updateUI();
                        loadData(wordBoard.word);
                        updateUI(wordBoard);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
    }
    public abstract void loadData(Word word);
    private void setContronymFlowPane() {
        contronymFlowPane.setMaxWidth(WIDTH - 2 * LEFT_MARGIN);
        contronymFlowPane.setHgap(5);
        contronymFlowPane.setVgap(8);

    }
    @Override
    public void setID() {
        layout.setId("contronymLayout");
        titleText.setId("contronymTitle");
    }

    @Override
    public void setCSS() {
        layout.getStylesheets().add(WordEngine.LINK_CSS);
    }

    @Override
    public void setUI() {
        setContronymFlowPane();
        setLayout();
    }

    @Override
    public void updateUI() throws SQLException {

    }

    @Override
    public String getLink() {
        return null;
    }

    /**
     * Component.
     */

    @Override
    protected void setLayout() {
        mainVBox.getChildren().add(contronymFlowPane);
        mainVBox.setTranslateY(25);
    }



}
