package graphics.style;

import graphics.StandardParameter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class UIComponent implements Decorator {
    public final double WIDTH;
    public final double HEIGHT;
    public final double ICON_WIDTH;
    public final double ICON_HEIGHT;

    protected final VBox layout = new VBox();
    protected final ImageView icon = new ImageView();
    protected final Text titleText = new Text();
    protected final HBox headerHBox = new HBox(titleText, icon);
    protected final VBox mainVBox = new VBox();
    protected final double LEFT_MARGIN;
    protected final double TOP_MARGIN;

    protected UIComponent(double width, double height, double leftMargin, double topMargin) {
        WIDTH = width * StandardParameter.SCALE;
        HEIGHT = height * StandardParameter.SCALE;
        ICON_HEIGHT = 23 * StandardParameter.SCALE;
        ICON_WIDTH = 23 * StandardParameter.SCALE;
        LEFT_MARGIN = leftMargin * StandardParameter.SCALE;
        TOP_MARGIN = topMargin * StandardParameter.SCALE;


        setIcon();
        setTitleUI();

        titleText.applyCss();
        icon.applyCss();

        icon.setFitWidth(ICON_WIDTH );
        icon.setFitHeight(ICON_HEIGHT);
        icon.setTranslateY(-2);

        layout.getChildren().add(headerHBox);
        layout.getChildren().add(mainVBox);
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.setMinSize(WIDTH, HEIGHT);
        layout.setMaxSize(WIDTH, HEIGHT);

        headerHBox.setTranslateX(LEFT_MARGIN);
        headerHBox.setTranslateY(TOP_MARGIN);
        headerHBox.setSpacing(WIDTH - titleText.getLayoutBounds().getWidth() - icon.getFitWidth()-20);

        mainVBox.setTranslateX(LEFT_MARGIN);
        mainVBox.setTranslateY(TOP_MARGIN);
    }

    public VBox get() {
        return layout;
    }


    protected abstract void setIcon();

    protected abstract void setTitleUI();

}
