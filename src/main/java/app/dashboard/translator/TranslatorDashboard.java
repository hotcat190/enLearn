package app.dashboard.translator;

import app.MenuController;
import graphics.Canvas;
import app.dashboard.model.Dashboard;
import graphics.style.StyleHelper;
import javafx.scene.text.Text;
import controller.translate.view.TranslateView;

import java.sql.SQLException;

public class TranslatorDashboard extends Dashboard {
    /**
     * Singleton.
     */
    private static final TranslatorDashboard INSTANCE = new TranslatorDashboard();
    /**
     * Views.
     */
    private final TranslateView translateView = TranslateView.getInstance();
    private final Text textSource = new Text("API FROM GOOGLE");

    public TranslatorDashboard() {
        setId();
        setCSS();
        set();
    }

    /**
     * Listener.
     */
    @Override
    public void setListener(Object object) {
    }

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
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_translate_dashboard.css"));
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        this.getChildren().add(translateView);
        this.getChildren().add(getTitle());
        this.getChildren().add(textSource);
        this.setLayoutX(Canvas.DELTA_X + MenuController.getInstance().getPrefWidth());

        setTitle();
        getTitle().setTranslateX(30);
        getTitle().setTranslateY(10);

        translateView.setTranslateY(-20);
        textSource.setTranslateX(50);
        textSource.setLayoutX(getTitle().getLayoutX());
        textSource.setLayoutY(700);
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
                "-fx-fill: #f2f2f2;");
        textBottom.setStyle("-fx-font-family: 'Segoe UI Variable'; " +
                "-fx-font-size: 23;" +
                "-fx-fill: #f2f2f2;" +
                "-fx-font-weight: bold;");
    }

    public static TranslatorDashboard getInstance() {
        return INSTANCE;
    }
}
