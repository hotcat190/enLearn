package graphics.engine.word;

import dictionary.Word;
import graphics.ui.Component;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class IrregularVerbsUI extends Component {

    private Word word = null;
    private final VBox pastSimpleVBox = new VBox();
    private final VBox pastParticipleVBox = new VBox();
    private final Text titlePastTenseText = new Text("past tense");
    private final Text verbPastSimpleText = new Text();

    private final Text titleParticipleText = new Text("past participle");
    private final Text verbPastParticipleText = new Text();


    public IrregularVerbsUI(double width, double height, double leftMargin, double topMargin) {
        super(width, height, leftMargin, topMargin);

        setID();
        setCSS();
        setUI();
    }

    public void setIrregularUI() {
        pastSimpleVBox.getChildren().addAll(verbPastSimpleText, titlePastTenseText);
        pastParticipleVBox.getChildren().addAll(verbPastParticipleText, titleParticipleText);
        mainVBox.setSpacing(20);
    }

    public void loadData(Word word) {
        this.word = word;
    }

    /**
     * Component.
     */
    @Override
    protected void setLayout() {
        mainVBox.getChildren().addAll(pastSimpleVBox, pastParticipleVBox);
        layout.setSpacing(-10);
    }

    @Override
    protected void setIcon() {
        icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/image/irregular_icon_app.png"
        ))));
    }

    @Override
    protected void setTitleUI() {
        titleText.setText("Irregular verb");
        titleText.setId("title");
        titleText.setStyle(titleText.getStyle() + "-fx-fill:#f1f2f3;");
    }


    /**
     * Decorator.
     */
    @Override
    public void setID() {
        titlePastTenseText.setId("titleIrregularVerb");
        titleParticipleText.setId("titleIrregularVerb");

        verbPastSimpleText.setId("irregularVerb");
        verbPastParticipleText.setId("irregularVerb");

        layout.setId("irregularLayout");
    }

    @Override
    public void setCSS() {
        layout.getStylesheets().add(WordEngine.LINK_CSS);
    }

    @Override
    public void setUI() {
        setIcon();
        setTitleUI();
        setIrregularUI();
        setLayout();
    }

    @Override
    public void updateUI() {
        verbPastSimpleText.setText(word.getPastTenseList().get(0));
        verbPastParticipleText.setText(word.getPastTenseList().get(1));
    }
    @Override
    public String getLink() {
        return WordEngine.LINK_CSS;
    }
}
