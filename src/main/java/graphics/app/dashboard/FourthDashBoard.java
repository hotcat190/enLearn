package graphics.app.dashboard;

import data.test.fill_in_blank.FillInBlankData;
import dictionary.Word;
import graphics.style.StyleHelper;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import view.test.fill_in_word.SelectCardView;

import java.sql.SQLException;

public class FourthDashBoard extends Dashboard{
    public final static FourthDashBoard fourthDashBoard = new FourthDashBoard();

    private FourthDashBoard() {
        FillInBlankData fillInBlankData = new FillInBlankData(5);

        SelectCardView selectCardView1 = new SelectCardView(fillInBlankData.getTest().get(0).getDisplayWord().toString());
        SelectCardView selectCardView2 = new SelectCardView(fillInBlankData.getTest().get(1).getDisplayWord().toString());
        SelectCardView selectCardView3 = new SelectCardView(fillInBlankData.getTest().get(2).getDisplayWord().toString());
        SelectCardView selectCardView4 = new SelectCardView(fillInBlankData.getTest().get(3).getDisplayWord().toString());
        SelectCardView selectCardView5 = new SelectCardView(fillInBlankData.getTest().get(4).getDisplayWord().toString());
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(new VBox(selectCardView1, selectCardView2, selectCardView3, selectCardView4, selectCardView5));
        scrollPane.setMaxHeight(500);
        this.getChildren().add(scrollPane);
        scrollPane.setLayoutX(300);

        setId();
        setCSS();
        set();
    }

    /**
     * Set listener with other object.
     *
     * @param object
     */
    @Override
    public void setListener(Object object) {

    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {

    }

    @Override
    public void setTitle() {

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
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_fourth_dashboard.css"));
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {

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
