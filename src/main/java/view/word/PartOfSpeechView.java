package view.word;

import dictionary.Word;
import graphics.StandardParameter;
import graphics.style.Decorator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.*;

public class PartOfSpeechView implements Decorator {
    private Word word = null;
    private HashMap<String, String> hashMapPOS = null;

    private final VBox vBox = new VBox();
    private final HBox hBoxPOS = new HBox();
    private final ScrollPane scrollPane = new ScrollPane();

    public final DefinitionView definitionView = new DefinitionView();
    public final double WRAP_TEXT_WIDTH = 295 * StandardParameter.SCALE;


    public PartOfSpeechView() {
        setId();
        setCSS();
        set();
    }

    public void connect(HashMap<String, String> hashMapPOS) {
        this.hashMapPOS = hashMapPOS;
    }
    private void setHBoxPOS() {
        hBoxPOS.setSpacing(10);
        hBoxPOS.setAlignment(Pos.CENTER);

        ToggleGroup toggleGroup = new ToggleGroup();
        for (int i = 0; i < 4; i++) {
            ToggleButton toggleButton = getButtonPos();
            toggleGroup.getToggles().add(toggleButton);
            toggleButton.setVisible(false);
            hBoxPOS.getChildren().add(toggleButton);
        }
    }

    public void loadData(Word word) {
        this.word = word;
        hashMapPOS = word.getHashMap();
        definitionView.loadData(word);
    }

    public VBox getLayout() {
        return vBox;
    }
    @Override
    public void setId() {
        scrollPane.setId("scrollPane");
    }

    @Override
    public void setCSS() {
        hBoxPOS.getStylesheets().add(WordBoard.LINK_CSS);
    }

    @Override
    public void set() {
        setHBoxPOS();

        scrollPane.setPrefSize(350 * StandardParameter.SCALE, 260);
        scrollPane.setMinSize(350 * StandardParameter.SCALE, 260);
        scrollPane.setMaxSize(350 * StandardParameter.SCALE, 260);
        scrollPane.setContent(definitionView.getLayout());


        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(hBoxPOS);
        vBox.getChildren().add(scrollPane);
        vBox.setSpacing(10);
    }

    @Override
    public void update() {
        Iterator<Map.Entry<String, String>> posSet = hashMapPOS.entrySet().iterator();
        hBoxPOS.setTranslateX(0);
        for (Node node : hBoxPOS.getChildren()) {
            if(node instanceof ToggleButton button) {
                if (posSet.hasNext()) {
                    button.setSelected(false);
                    button.setVisible(true);

                    button.setText(posSet.next().getKey());
                    if (button.getOnAction() != null) {
                        button.removeEventHandler(ActionEvent.ACTION, button.getOnAction());
                    }
                    button.addEventHandler(ActionEvent.ACTION, actionEvent -> {
                        setDefUI(word.getLinesDefinitionOf(button.getText()));
                    });
                } else {
                    button.setVisible(false);
                    hBoxPOS.setTranslateX((hBoxPOS.getTranslateX() + hBoxPOS.getSpacing() + button.getBoundsInLocal().getWidth()) / 2);
                }
            }
        }

    }
    @Override
    public String getLink() {
        return null;
    }
    private ToggleButton getButtonPos() {
        ToggleButton button = new ToggleButton("none");
        button.setId("buttonPOS");
        button.getStylesheets().add(WordBoard.LINK_CSS);
        button.applyCss();
        button.setPrefWidth(66 * StandardParameter.SCALE);
        button.setPrefHeight(26 * StandardParameter.SCALE);

        button.setOnMouseMoved(e -> {
            new Timeline(
                    new KeyFrame(Duration.millis(100),
                            new KeyValue(button.translateYProperty(), -7))
            ).play();
        });
        button.setOnMouseExited(e -> {
            if (!button.isSelected()) {
                new Timeline(
                        new KeyFrame(Duration.millis(300),
                                new KeyValue(button.translateYProperty(), 0))
                ).play();
            }
        });
        return button;
    }
    private void addDefUI(HBox hBox, Text text, ImageView icon, String type) {
        hBox.getStylesheets().add(WordBoard.LINK_CSS);
        hBox.getChildren().addAll(icon, text);
        hBox.setSpacing(10);
        text.setWrappingWidth(WRAP_TEXT_WIDTH);
        switch (type) {
            case "definition" -> {
                icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/image/definition_icon_app.png"
                ))));
                text.setId("textDefinition");
            }
            case "phrase" -> {
                icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/image/phrase_icon_app.png"
                ))));
                text.setId("textPhrase");
            }
            case "example" -> {
                icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/image/example_icon_app.png"
                ))));
                text.setId("textExample");
            }
        }
        icon.setFitWidth(22);
        icon.setFitHeight(22);
        definitionView.getLayout().getChildren().add(hBox);
    }
    private void setDefUI(List<String> defList) {
        definitionView.getLayout().getChildren().clear();
        StringBuilder exampleSection = new StringBuilder();
        for (int i = 0; i < defList.size(); i++) {
            String def = defList.get(i);
            HBox hBox = new HBox();
            Text text = new Text();
            ImageView icon = new ImageView();
            if (def.startsWith("-")) {
                if (i - 1 > 0 && !defList.get(i - 1).startsWith("-")) {
                    definitionView.getLayout().getChildren().add(new HBox(new Text("")));
                }
                text.setText(def);
                addDefUI(hBox, text, icon, "definition");
            } else if (def.startsWith("▶")) {
                text.setText(def);
                addDefUI(hBox, text, icon, "phrase");
            } else {
                exampleSection.append(def).append("\n");
                if (i + 1 < defList.size() && !defList.get(i + 1).contains("•")) {
                    text.setText(exampleSection.toString());
                    addDefUI(hBox, text, icon, "example");
                    exampleSection = new StringBuilder();
                }
            }
        }
    }
}
