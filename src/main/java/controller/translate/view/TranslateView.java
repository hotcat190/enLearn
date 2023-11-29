package controller.translate.view;

import controller.model.Listener;
import graphics.Canvas;
import sql.dictionary.Translate;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.model.Connector;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class TranslateView extends HBox implements Decorator, Connector, Listener {
    /**
     * Singleton.
     */
    private static final TranslateView INSTANCE = new TranslateView();
    /**
     * Components.
     */
    private final VBox vBoxFrom = new VBox();
    private final VBox vBoxTo = new VBox();
    private final MenuButton menuButtonFrom = new MenuButton("English");
    private final MenuButton menuButtonTo = new MenuButton("Vietnamese");

    private final MenuItem menuItemFrom = new MenuItem("Vietnamese");
    private final MenuItem menuItemTo = new MenuItem("English");
    private final TextArea textAreaFrom = new TextArea();
    private final TextArea textAreaTo = new TextArea();

    private final Text textFrom = new Text("FROM");
    private final Text textTo = new Text("TO");
    private final VBox vBoxSelectFrom = new VBox(textFrom, menuButtonFrom);
    private final VBox vBoxSelectTo = new VBox(textTo, menuButtonTo);

    private final CopyButton copyButton = new CopyButton();

    private final VBox vBox1 = new VBox(vBoxSelectFrom, textAreaFrom);
    private final VBox vBox2 = new VBox(vBoxSelectTo, textAreaTo, copyButton);


    private final HashMap<String, StringBuffer> tagLanguage = new HashMap<>();

    private TranslateView() {
        setId();
        setCSS();
        set();
        setListener();
        copyButton.setListener(textAreaTo.textProperty());
    }

    public static TranslateView getInstance() {
        return INSTANCE;
    }
    /**
     * Set id.
     */
    @Override
    public void setId() {
        vBoxFrom.setId("translate-view__vbox--from");
        vBoxTo.setId("translate-view__vbox--to");
        menuButtonFrom.setId("translate-view__menubutton--from");
        menuButtonTo.setId("translate-view__menubutton--to");
        textAreaFrom.setId("translate-view__textarea--from");
        textAreaTo.setId("translate-view__textarea--to");
        textFrom.setId("translate-view__text--from");
        textTo.setId("translate-view__text--to");
        copyButton.setId("translate-view__button--copy");
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
        tagLanguage.put("Vietnamese", new StringBuffer("vi"));
        tagLanguage.put("English", new StringBuffer("en"));

        double translateY = 100;
        double translateX = 50;
        vBox1.setTranslateY(translateY);
        vBox2.setTranslateY(translateY);

        vBox1.setTranslateX(translateX);
        vBox2.setTranslateX(translateX);

        vBox1.setSpacing(20);
        vBox2.setSpacing(20);

        vBoxSelectFrom.setSpacing(3);
        vBoxSelectTo.setSpacing(3);

        textAreaFrom.setWrapText(true);
        textAreaFrom.setMinSize(560 * StandardParameter.SCALE, 500 * StandardParameter.SCALE);
        textAreaFrom.setMaxSize(560 * StandardParameter.SCALE, 500 * StandardParameter.SCALE);

        textAreaTo.setWrapText(true);
        textAreaTo.setMinSize(560 * StandardParameter.SCALE, 500 * StandardParameter.SCALE);
        textAreaTo.setMaxSize(560 * StandardParameter.SCALE, 500 * StandardParameter.SCALE);
        textAreaTo.setEditable(false);

        textFrom.setTranslateX(5);
        textTo.setTranslateX(5);

        vBoxFrom.getChildren().add(vBox1);
        vBoxTo.getChildren().add(vBox2);
        double height = Canvas.APP_HEIGHT;
        double width = 711;
        vBoxFrom.setPrefSize(width * StandardParameter.SCALE, height * StandardParameter.SCALE);
        vBoxFrom.setMinSize(width * StandardParameter.SCALE, height * StandardParameter.SCALE);
        vBoxFrom.setMaxSize(width * StandardParameter.SCALE, height * StandardParameter.SCALE);

        vBoxTo.setPrefSize(width * StandardParameter.SCALE, height);
        vBoxTo.setMinSize(width * StandardParameter.SCALE, height);
        vBoxTo.setMaxSize(width * StandardParameter.SCALE, height);
        vBoxTo.setTranslateX(-24);

        menuButtonFrom.getItems().add(menuItemFrom);
        menuButtonTo.getItems().add(menuItemTo);

        copyButton.setTranslateX(200);

        this.getChildren().addAll(vBoxFrom, vBoxTo);
    }

    public String getInput() {
        return textAreaFrom.getText();
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

    @Override
    public void connect(Object object) {
        if (object instanceof SimpleStringProperty simpleStringProperty) {
            textAreaTo.textProperty().bind(simpleStringProperty);
        }
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
        textAreaFrom.setOnKeyTyped(e -> {
            StringBuffer input = new StringBuffer(textAreaFrom.getText());
            StringBuffer langFrom = tagLanguage.get(menuButtonFrom.getText());
            StringBuffer langTo = tagLanguage.get(menuButtonTo.getText());
            new Thread(() -> {
                StringBuffer output = null;
                try {
                    output = new StringBuffer(new StringBuffer(Translate.translate(langFrom, langTo, input)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                StringBuffer finalOutput = output;
                Platform.runLater(() -> {
                    textAreaTo.textProperty().set(String.valueOf(finalOutput));
                });
            }).start();
        });
        menuItemFrom.setOnAction(e -> {
            String temp = menuButtonFrom.getText();
            menuButtonFrom.setText(menuItemFrom.getText());
            menuItemFrom.setText(temp);
            menuButtonTo.setText(menuItemFrom.getText());
        });

        menuItemTo.setOnAction(e -> {
            String temp = menuButtonTo.getText();
            menuButtonTo.setText(menuItemTo.getText());
            menuItemTo.setText(temp);
            menuButtonFrom.setText(menuItemTo.getText());
        });
    }
}
