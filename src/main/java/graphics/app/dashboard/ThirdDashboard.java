package graphics.app.dashboard;

import app.MenuController;
import controller.model.Listener;
import graphics.app.AppWindow;
import graphics.style.StyleHelper;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.translate.TranslateView;

import java.sql.SQLException;

public class ThirdDashboard extends Dashboard {
    private final TranslateView translateView = new TranslateView();
    private final Text textSource = new Text("API FROM GOOGLE");
    public ThirdDashboard() {
        setId();
        setCSS();
        set();
    }

    /**
     * Set listener with other object.
     *
     */
    @Override
    public void setListener(Object object) {
    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        this.setId("translate-view__hbox--main");
        textSource.setId("translate-view__text--source");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_third_dashboard.css"));
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        setTitle();
        this.getChildren().add(translateView);
        this.getChildren().add(getTitle());
        this.getChildren().add(textSource);
        this.setLayoutX(AppWindow.DELTA_X+MenuController.menuController.getPrefWidth());

        textSource.setLayoutX(getTitle().getLayoutX());
        textSource.setLayoutY(700);

//        translateView.setLayoutX(70+AppWindow.DELTA_X);
//        translateView.setLayoutY(AppWindow.DELTA_Y);
    }

    /**
     * Update layout.
     */
    @Override
    public void update() throws SQLException {

    }

    @Override
    public String getLink() {
        return null;
    }


    @Override
    public void setTitle() {
        textTop.setText("App");
        textBottom.setText("Translate");
        textTop.setStyle("-fx-font-family: 'Segoe UI Semibold';" +
                "-fx-font-size: 14;" +
                "-fx-fill: #898b8c;");
        textBottom.setStyle("-fx-font-family: 'Segoe UI Variable'; " +
                "-fx-font-size: 23;" +
                "-fx-fill: #1f2f3f;" +
                "-fx-font-weight: bold;");
        getTitle().setLayoutX(AppWindow.DELTA_X + 80);
        getTitle().setLayoutY(AppWindow.DELTA_Y + 15);
    }

    @Override
    public VBox getTitle() {
        return textLayout;
    }
}
