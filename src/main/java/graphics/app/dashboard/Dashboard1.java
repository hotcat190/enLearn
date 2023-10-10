package graphics.app.dashboard;

import dictionary.SearchEngine;
import dictionary.TranslateEngine;
import graphics.app.AppWindow;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;



public class Dashboard1 extends AppWindow {
    private final Stage stage;
    private final Scene scenePage;
    private final GridPane gridPane;
    /**
     * Component of dashboard1.
     */
    private SearchEngine searchEngine = new SearchEngine();
    private TranslateEngine translateEngine = new TranslateEngine();
    /**
     * Link to css file.
     */
    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_dashboard1_class.css")).toExternalForm();

    public Dashboard1(Stage stageInit) {
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
            paneWindow.getChildren().add(searchEngine.getStackSearch());
            paneWindow.getChildren().add(gridPane);
            gridPane.setLayoutX(100);
            gridPane.setLayoutY(100);
            gridPane.add(translateEngine.getPaneTranslateEngine(),0,0);
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

    private void runAnimation() {
        applyEventAppWindow();
        searchEngine.setAnimation();
        translateEngine.setAnimation(dialog,paneWindow);
        translateEngine.setLiveTranslate();

    }

    public void showPage() {
        runAnimation();
        stage.show();
    }
}
