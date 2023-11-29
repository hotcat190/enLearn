package controller.word.view;

import controller.word.data.Word;
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
import graphics.load.SingletonAnimationLoading;

import java.util.*;

public class PartOfSpeechView extends VBox implements Decorator, SingletonAnimationLoading {
    /**
     * Components.
     */
    private Word word = null;
    private HashMap<String, String> hashMapPOS = null;

    /**
     * Views.
     */
    private final HBox hBoxPOS = new HBox();
    private final ScrollPane scrollPane = new ScrollPane();
    public final DefinitionView definitionView = new DefinitionView();
    public final double WRAP_TEXT_WIDTH = 440 * StandardParameter.SCALE;


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
        hBoxPOS.setAlignment(Pos.CENTER_LEFT);

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

    public void setId() {
        scrollPane.setId("scrollPane");
        hBoxPOS.setId("part-of-speech-view__hbox-pos");
        this.setId("part-of-speech-view__vbox--layout");
    }

    @Override
    public void setCSS() {

    }

    @Override
    public void set() {
        setHBoxPOS();

        scrollPane.setMinSize(498 * StandardParameter.SCALE, 160);
        scrollPane.setMaxSize(498 * StandardParameter.SCALE, 160);
        scrollPane.setContent(definitionView);


        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().add(hBoxPOS);
        this.getChildren().add(scrollPane);
        this.setSpacing(10);
    }

    @Override
    public void update() {
        Iterator<Map.Entry<String, String>> posSet = hashMapPOS.entrySet().iterator();
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
        button.setId("part-of-speech-view__button--pos");
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
        hBox.getChildren().addAll(icon, text);
        hBox.setSpacing(10);
        text.setWrappingWidth(WRAP_TEXT_WIDTH);
        switch (type) {
            case "definition" -> {
                icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/image/definition_icon_app.png"
                ))));
                text.setId("part-of-speech-view__text--definition");
            }
            case "phrase" -> {
                icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/image/phrase_icon_app.png"
                ))));
                text.setId("part-of-speech-view__text--phrase");
            }
            case "example" -> {
                icon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/image/example_icon_app.png"
                ))));
                text.setId("part-of-speech-view__text--example");
            }
        }
        icon.setFitWidth(22);
        icon.setFitHeight(22);
        definitionView.getChildren().add(hBox);
    }
    private void setDefUI(List<String> defList) {
        definitionView.getChildren().clear();
        StringBuilder exampleSection = new StringBuilder();
        for (int i = 0; i < defList.size(); i++) {
            String def = defList.get(i);
            HBox hBox = new HBox();
            Text text = new Text();
            ImageView icon = new ImageView();
            if (def.startsWith("-")) {
                if (i - 1 > 0 && !defList.get(i - 1).startsWith("-")) {
                    definitionView.getChildren().add(new HBox(new Text("")));
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

    @Override
    public void setSingletonOnLoading() {
        for (Node node : hBoxPOS.getChildren()) {
            node.setOpacity(0);
        }
        definitionView.getChildren().clear();
        scrollPane.getContent().setOpacity(0);
        hBoxPOS.setStyle("-fx-background-color: rgb(56,56,56)");

        hBoxPOS.setMaxWidth(400);
        scrollPane.setMinSize(400 * StandardParameter.SCALE, 160);
        scrollPane.setMaxSize(400 * StandardParameter.SCALE, 160);
        this.setMaxWidth(400);
        this.setStyle("-fx-background-color: rgb(56,56,56)");

    }

    @Override
    public void removeSingletonLoading() {
        for (Node node : hBoxPOS.getChildren()) {
            node.setOpacity(1);
        }
        scrollPane.getContent().setOpacity(1);
        hBoxPOS.setStyle("-fx-background-color: transparent");
        scrollPane.setMinSize(498 * StandardParameter.SCALE, 160);
        scrollPane.setMaxSize(498 * StandardParameter.SCALE, 160);
        this.setStyle("-fx-background-color: transparent");
    }
}
