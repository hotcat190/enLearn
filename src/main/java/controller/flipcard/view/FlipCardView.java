package controller.flipcard.view;

import controller.flipcard.data.FlipCardData;
import controller.my_dictionary.data.MyNewWord;
import graphics.style.Decorator;
import graphics.style.StyleHelper;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

public class FlipCardView extends HBox implements Decorator {
    public FlipCardView() {
        setId();
        setCSS();
        set();
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {

    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_game_dashboard.css"));
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        this.setSpacing(10);
        for (MyNewWord myNewWord : FlipCardData.getInstance().getMyDictionary()) {
            this.getChildren().add(new FlipCard(myNewWord));
        }
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
}
