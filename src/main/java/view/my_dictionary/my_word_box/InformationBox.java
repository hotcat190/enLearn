package view.my_dictionary.my_word_box;

import data.my_dictionary.SQLMyDictionary;
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
        vBoxTotal.setId("vBoxInformationBox");
        vBoxModified.setId("vBoxInformationBox");
        textTotal.setId("textInformationBox");
        textModified.setId("textInformationBox");

        vBoxTotal.setId("information-box__vbox--total");
        vBoxModified.setId("information-box__vbox--modified");
        textTotal.setId("information-box__text--total");
        textModified.setId("information-box__text--modified");
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
