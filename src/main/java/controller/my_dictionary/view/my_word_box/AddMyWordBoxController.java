package controller.my_dictionary.view.my_word_box;

import controller.model.Controller;
import controller.model.Listener;
import controller.my_dictionary.MyDictionaryTableController;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class AddMyWordBoxController extends Controller implements Decorator, Listener {
    private static final AddMyWordBoxController INSTANCE = new AddMyWordBoxController();
    private final VBox vBox = new VBox();
    private final AddMyWordBox addMyWordBox = AddMyWordBox.getInstance();
    private final ToggleButton addButton = new ToggleButton("+ Add my new word");

    private AddMyWordBoxController() {
        setId();
        setCSS();
        set();
        setListener();
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        addButton.setId("addButton");

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
        vBox.getChildren().addAll(addButton, addMyWordBox.get());
        addButton.setPrefSize(151 * StandardParameter.SCALE, 38 * StandardParameter.SCALE);
        addMyWordBox.get().setTranslateY(20);
        addMyWordBox.get().setTranslateX(-10);
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

    /**
     * Set listener with other object.
     */
    @Override
    public void setListener(Object object) {

    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {
        addMyWordBox.setListener(MyDictionaryTableController.getInstance().getData());
        this.hide();
        addButton.setOnAction(e -> {
            if (isHided()) {
                this.unHide();
            } else {
                this.hide();
            }
        });
    }

    public void hide() {
        if (vBox.getChildren().size() == 2) {
            vBox.getChildren().removeLast();
        }
    }

    public void unHide() {
        if (vBox.getChildren().size() == 1) {
            vBox.getChildren().add(addMyWordBox.get());
        }
    }

    public boolean isHided() {
        return vBox.getChildren().size() == 1;
    }
    public static AddMyWordBoxController getInstance() {
        return INSTANCE;
    }

    @Override
    public void updateView() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadData(Object object) {

    }

    @Override
    public Node getView() {
        return vBox;
    }
}
