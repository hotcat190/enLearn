package app.dashboard.my_dictionary;

import controller.my_dictionary.MyDictionaryTableController;
import app.dashboard.model.Dashboard;
import graphics.style.StyleHelper;
import javafx.scene.layout.GridPane;
import controller.my_dictionary.view.my_word_box.AddMyWordBoxController;
import controller.my_dictionary.view.my_word_box.EditMyWordBox;
import controller.my_dictionary.view.my_word_box.InformationBox;
import controller.my_dictionary.view.my_word_box.SearchFilter;

import java.sql.SQLException;

public class MyDictionaryDashboard extends Dashboard {
    /**
     * Singleton/
     */
    private static final MyDictionaryDashboard INSTANCE = new MyDictionaryDashboard();
    /**
     * Components.
     */
    private final MyDictionaryTableController myDictionaryTableController = MyDictionaryTableController.getInstance();
    private final AddMyWordBoxController addMyWordBoxController = AddMyWordBoxController.getInstance();
    private final EditMyWordBox editMyWordBox = EditMyWordBox.getInstance();
    private final SearchFilter searchFilter = SearchFilter.getInstance();
    private final InformationBox informationBox = new InformationBox();

    /**
     * Views.
     */
    private final GridPane gridPane = new GridPane();

    public MyDictionaryDashboard() {
        setId();
        setCSS();
        set();
        setListener();
    }

    /**
     * Listener.
     */
    @Override
    public void setListener(Object object) {
    }

    @Override
    public void setListener() {
        searchFilter.setListener(myDictionaryTableController.getData().getFilteredList());
    }

    @Override
    public void setId() {
        this.setId("pane");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_my_dictionary_dashboard.css"));
    }

    @Override
    public void set() {
        setTitle();


        this.getChildren().add(gridPane);
        this.getChildren().add(addMyWordBoxController.getView());
        this.getChildren().add(editMyWordBox.get());

        addMyWordBoxController.getView().setLayoutX(1030);
        addMyWordBoxController.getView().setLayoutY(gridPane.getLayoutY() + 30);
        searchFilter.setTranslateX(70);
        gridPane.add(getTitle(), 0, 0, 1, 1);
        gridPane.add(informationBox, 1,0,2, 1);
        gridPane.add(searchFilter, 4, 0,3,1);
        gridPane.add(myDictionaryTableController.getView(), 0, 1, 15, 2);

        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setLayoutX(30);
        gridPane.setLayoutY(15);
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
        textTop.setText("My");
        textBottom.setText("Dictionary");
        textTop.setStyle("-fx-font-family: 'Segoe UI Semibold';" +
                "-fx-font-size: 14;" +
                "-fx-fill: #f2f2f2;");
        textBottom.setStyle("-fx-font-family: 'Segoe UI Variable'; " +
                "-fx-font-size: 23;" +
                "-fx-fill: #f2f2f2;" +
                "-fx-font-weight: bold;");
    }

    public static MyDictionaryDashboard getInstance() {
        return INSTANCE;
    }
}
