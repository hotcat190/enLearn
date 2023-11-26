package controller.my_dictionary.view.my_word_box;

import sql.dictionary.SQLMyDictionary;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class InformationBox extends HBox implements Decorator {
    /**
     * View.
     */
    private final VBox vBoxTotal = new VBox();
    private final VBox vBoxModified = new VBox();
    private final Text textTotal = new Text();
    private final Text textTitleTotal = new Text("Total words");
    private final Text textModified = new Text();
    private final Text textTitleModified = new Text("Last modified");

    public InformationBox() {
        setId();
        setCSS();
        set();
    }

    @Override
    public void setId() {
        textTotal.setId("textInformationBox");
        textModified.setId("textInformationBox");
        textTitleModified.getStyleClass().add("information-box__text--title");
        textTitleTotal.getStyleClass().add("information-box__text--title");

        vBoxTotal.setId("information-box__vbox--info");
        vBoxModified.setId("information-box__vbox--info");
        textTotal.setId("information-box__text--info");
        textModified.setId("information-box__text--info");
    }

    @Override
    public void setCSS() {
        // CSS from parent
    }

    @Override
    public void set() {
        textTotal.setTranslateX(10);
        textTitleTotal.setTranslateX(10);
        textModified.setTranslateX(10);
        textTitleModified.setTranslateX(10);
        textTotal.setText(String.valueOf(SQLMyDictionary.getTotalWords()));
        textModified.setText(SQLMyDictionary.getLastModified());

        vBoxTotal.getChildren().addAll(textTotal, textTitleTotal);
        vBoxTotal.setPrefSize(103 * StandardParameter.SCALE, 72 * StandardParameter.SCALE);
        vBoxTotal.setAlignment(Pos.CENTER_LEFT);

        vBoxModified.getChildren().addAll(textModified, textTitleModified);
        vBoxModified.setPrefSize(200 * StandardParameter.SCALE, 72 * StandardParameter.SCALE);
        vBoxModified.setAlignment(Pos.CENTER_LEFT);


        this.getChildren().addAll(vBoxTotal, vBoxModified);
        this.setSpacing(40);
    }

    @Override
    public void update() throws SQLException {
        // No need to update
    }

    @Override
    public String getLink() {
        return null;
    }
}
