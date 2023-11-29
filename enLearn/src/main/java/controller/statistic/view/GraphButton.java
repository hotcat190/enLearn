package controller.statistic.view;

import graphics.animation.Listener;
import graphics.style.Decorator;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import view.model.View;

import java.sql.SQLException;
import java.util.Objects;

public class GraphButton extends View implements Decorator, Listener {
    /**
     * View.
     */
    private final Text textTitle = new Text("Graph");
    private final HBox hBox = new HBox();
    private final MenuButton menuButton = new MenuButton();
    private final ListView<String> listView = new ListView<>();
    MenuItem menuItem = new MenuItem("Word");


    public GraphButton() {
        setId();
        setCSS();
        set();
        setListener();
    }

    @Override
    public void setId() {
        textTitle.setId("graph-button__text--title");
        listView.setId("listView");
        menuButton.setId("graph-button__button--menu");
    }

    @Override
    public void setCSS() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                "/css/style_for_statistic_engine_package.css"
        )).toExternalForm());
    }

    @Override
    public void set() {
        menuButton.getItems().addAll(menuItem);
        menuButton.setText("Time");
        menuButton.setMinWidth(70);

        hBox.getChildren().addAll(textTitle, menuButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.setTranslateX(30);

        this.getChildren().add(hBox);
    }

    @Override
    public void update() throws SQLException {
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void setListener(Object object) {
    }

    @Override
    public void setListener() {
        menuItem.setOnAction(e -> {
            menuButton.setText(menuItem.getText());
            menuButton.hide();
            if (menuItem.getText().equals("Time")) {
                menuItem.setText("Word");
            } else {
                menuItem.setText("Time");
            }
        });
    }

    public MenuButton getMenuButton() {
        return menuButton;
    }
}
