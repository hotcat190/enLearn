package app.dashboard.home;

import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Objects;

public class CommentView extends VBox implements Decorator {
    public static final CommentView INSTANCE = new CommentView();

    /**
     * View.
     */
    private final HBox hBoxDef = new HBox();
    private final HBox hBoxEx = new HBox();
    private final HBox hBoxPhr = new HBox();

    private final ImageView defIcon = new ImageView(
            new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/definition_icon_app.png")
            ))
    );
    private final ImageView exIcon = new ImageView(
            new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/example_icon_app.png")
            ))
    );
    private final ImageView phrIcon = new ImageView(
            new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/phrase_icon_app.png")
            ))
    );

    private final Text defText = new Text("Definition");
    private final Text exText = new Text("Example");
    private final Text phrText = new Text("Phrase");

    private CommentView() {
        setId();
        setCSS();
        set();
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        defText.setId("comment-view__text--common");
        exText.setId("comment-view__text--common");
        phrText.setId("comment-view__text--common");
        this.setId("comment-view__vbox--layout");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        this.setSpacing(20);
        this.getChildren().addAll(hBoxDef, hBoxEx, hBoxPhr);
        this.setPrefSize(100 * StandardParameter.SCALE, 160 * StandardParameter.SCALE);
        this.setAlignment(Pos.CENTER);

        exIcon.setFitHeight(22);
        phrIcon.setFitHeight(22);
        defIcon.setFitHeight(22);
        exIcon.setFitWidth(22);
        defIcon.setFitWidth(22);
        phrIcon.setFitWidth(22);

        hBoxDef.getChildren().addAll(defIcon, defText);
        hBoxEx.getChildren().addAll(exIcon, exText);
        hBoxPhr.getChildren().addAll(phrIcon, phrText);

        hBoxDef.setSpacing(10);
        hBoxEx.setSpacing(10);
        hBoxPhr.setSpacing(10);

        hBoxEx.setTranslateX(10);
        hBoxDef.setTranslateX(10);
        hBoxPhr.setTranslateX(10);

        hBoxPhr.setAlignment(Pos.CENTER_LEFT);
        hBoxEx.setAlignment(Pos.CENTER_LEFT);
        hBoxDef.setAlignment(Pos.CENTER_LEFT);
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
    public static CommentView getInstance () {
        return INSTANCE;
    }
}
