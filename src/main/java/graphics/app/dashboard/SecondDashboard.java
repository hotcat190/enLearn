package graphics.app.dashboard;

import controller.model.Listener;
import controller.my_dictionary.MyDictionaryTableController;
import graphics.StandardParameter;
import graphics.app.AppWindow;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.my_dictionary.my_word_box.AddMyWordBox;
import view.my_dictionary.my_word_box.InformationBox;
import view.my_dictionary.my_word_box.SearchFilter;

import java.sql.SQLException;
import java.text.ParseException;

public class SecondDashboard extends Dashboard {
    /**
     * Controllers.
     */
    private final MyDictionaryTableController myDictionaryTableController = MyDictionaryTableController.myDictionaryTableController;

    /**
     * Views.
     */
    private final VBox vBox = new VBox();
    private final HBox hBox = new HBox();
    private final HBox hBox1 = new HBox();
    private final Button addButton = new Button("+ Add my new word");

    /**
     * Engines.
     */
    private final AddMyWordBox addMyWordBox = new AddMyWordBox(285, 354, 15, 15);
    private final SearchFilter searchFilter = new SearchFilter();
    private final InformationBox informationBox = new InformationBox();
    private final GridPane gridPane = new GridPane();

    public SecondDashboard() {
        setId();
        setCSS();
        set();
        setListener();
    }

    /**
     * Set listener with other object.
     */
    @Override
    public void setListener(Object object) {
    }

    @Override
    public void setListener() {
        addMyWordBox.setListener(myDictionaryTableController.getData());
        searchFilter.setListener(myDictionaryTableController.getData().getFilteredList());
        addButton.setOnMouseClicked(e -> {
            addMyWordBox.get().setVisible(true);
        });
    }

    @Override
    public void setId() {
        this.setId("pane");
        addButton.setId("addButton");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_second_dashboard.css"));
    }

    @Override
    public void set() {
//        this.getChildren().add(hBox);
//        this.getChildren().add(addMyWordBox.get());


        setTitle();
        getTitle().setTranslateY((AppWindow.REAL_APP_HEIGHT - getTitle().getHeight()) / 2);

        addButton.setPrefSize(151 * StandardParameter.SCALE, 38 * StandardParameter.SCALE);
        addMyWordBox.get().setVisible(false);
        addMyWordBox.get().setTranslateY(20);


//        hBox1.getChildren().addAll(searchFilter, informationBox, addButton);
//        hBox1.setSpacing(20);

//        vBox.getChildren().addAll(hBox1, myDictionaryTableController.getView());
//        vBox.setTranslateY(100);
//        vBox.setSpacing(20);

//        hBox.setLayoutX(DELTA_X + 90);
//        hBox.setLayoutY(DELTA_Y);
//        hBox.getChildren().add(getTitle());
//        hBox.getChildren().add(vBox);
//        hBox.setSpacing(60);

//        addMyWordBox.get().setLayoutX(500);
//        addMyWordBox.get().setLayoutY(addButton.getLayoutY()+addButton.getTranslateY());

        this.getChildren().add(gridPane);
        this.getChildren().add(addMyWordBox.get());
//        gridPane.setMaxWidth(1250*StandardParameter.SCALE);
        gridPane.add(getTitle(), 0, 0, 1, 2);
        gridPane.add(searchFilter, 1, 0);
        gridPane.add(informationBox, 3, 0);
        gridPane.add(addButton, 4, 0);
        gridPane.add(myDictionaryTableController.getView(), 1, 1, 4, 2);

        gridPane.setHgap(70);
        gridPane.setLayoutX(80);
        gridPane.setLayoutY(100);
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
                "-fx-fill: #898b8c;");
        textBottom.setStyle("-fx-font-family: 'Segoe UI Variable'; " +
                "-fx-font-size: 23;" +
                "-fx-fill: #1f2f3f;" +
                "-fx-font-weight: bold;");
    }
}
