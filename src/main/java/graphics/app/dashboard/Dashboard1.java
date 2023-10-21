package graphics.app.dashboard;

import graphics.app.User;
import graphics.engine.SearchEngine;
import graphics.engine.TranslateEngine;
import graphics.app.AppWindow;
import graphics.engine.WordEngine;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;



public class Dashboard1 extends AppWindow {
    private final Stage stage;
    private final Scene scenePage;
    private final GridPane gridPane;
    /**
     * Component of dashboard1.
     */
    private final SearchEngine searchEngine = new SearchEngine();
    private final TranslateEngine translateEngine = new TranslateEngine();
    private final WordEngine wordEngine = new WordEngine();

    /**
     * User.
     */
    private final User user = new User();
    /**
     * Link to css file.
     */
    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_dashboard1_class.css")).toExternalForm();

    public Dashboard1(Stage stageInit) throws SQLException {
        gridPane = new GridPane();
        scenePage = new Scene(paneWindow);
        stage = stageInit;
        setAppWindow(stageInit);
        stage.setScene(scenePage);

        try {
            setId();
            setCSS();
            scenePage.setFill(Color.TRANSPARENT);


        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            paneWindow.getChildren().add(gridPane);
            gridPane.add(wordEngine.getPane(),0,0);
            gridPane.add(translateEngine.getPaneTranslateEngine(),1,0);
            paneWindow.getChildren().add(searchEngine.getPaneSearch());
            gridPane.setHgap(35);
            gridPane.setLayoutX(130);
            gridPane.setLayoutY(90);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void setId() {
        paneWindow.setId("pane");
    }

    private void setCSS() {
        paneWindow.getStylesheets().add(linkToCSS);
        paneWindow.applyCss();

    }

    private void runAction() {
        applyEventAppWindow();
        searchEngine.setAction();
        translateEngine.setAnimation(dialog,paneWindow);
        translateEngine.setLiveTranslate();
        wordEngine.setAction(searchEngine);

    }

    public void showPage() {
        runAction();
        stage.show();
    }
}
