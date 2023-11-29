package controller.word.view;

import controller.word.data.Word;
import graphics.style.UIComponent;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class IrregularVerbsView extends UIComponent {

    private Word word = null;
    private final VBox pastSimpleVBox = new VBox();
    private final VBox pastParticipleVBox = new VBox();
    private final Text titlePastTenseText = new Text("past tense");
    private final Text verbPastSimpleText = new Text();

    private final Text titleParticipleText = new Text("past participle");
    private final Text verbPastParticipleText = new Text();


    public IrregularVerbsView(double width, double height, double leftMargin, double topMargin) {
        super(width, height, leftMargin, topMargin);

        setId();
        setCSS();
        set();
    }

    public void loadData(Word word) {
        this.word = word;
    }

    /**
     * Component.
     */

    @Override
    protected void setIcon() {
        icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/image/irregular_icon_app.png"
        ))));
    }

    @Override
    protected void setTitleUI() {
        titleText.setText("Irregular");
        titleText.setId("title");
        titleText.setStyle(titleText.getStyle() + "-fx-fill:#f1f2f3;");
    }


    /**
     * Decorator.
     */
    @Override
    public void setId() {
        titlePastTenseText.setId("irregular-verbs-view__text--title");
        titleParticipleText.setId("irregular-verbs-view__text--title");
        verbPastSimpleText.setId("irregular-verbs-view__text--past-simple");
        verbPastParticipleText.setId("irregular-verbs-view__text--past-simple");
        layout.setId("irregular-verbs-view__pane--layout");
    }

    @Override
    public void setCSS() {

    }

    @Override
    public void set() {
        setIcon();
        setTitleUI();

        /*
         * Set main layout of irregular verbs.
         */
        mainVBox.setSpacing(20);
        mainVBox.getChildren().addAll(pastSimpleVBox, pastParticipleVBox);

        /*
         * Set display word layout.
         */
        pastSimpleVBox.getChildren().addAll(verbPastSimpleText, titlePastTenseText);
        pastParticipleVBox.getChildren().addAll(verbPastParticipleText, titleParticipleText);
    }

    @Override
    public void update() {
    }

    public void connect(StringProperty pastSimpleProperty, StringProperty pastParticipleProperty) {
        verbPastParticipleText.textProperty().bind(pastParticipleProperty);
        verbPastSimpleText.textProperty().bind(pastSimpleProperty);
    }
    @Override
    public String getLink() {
        return null;
    }
}
