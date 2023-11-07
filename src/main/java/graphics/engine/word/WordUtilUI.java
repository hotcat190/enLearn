package graphics.engine.word;

import dictionary.Dictionary;
import graphics.StandardParameter;
import graphics.app.AppWindow;
import graphics.app.dashboard.Dashboard1;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WordUtilUI {
    public final double WRAP_TEXT_WIDTH = 295 * StandardParameter.SCALE;
    private final ArrayList<Button> synonymsListButton = new ArrayList<>();
    private final ArrayList<Button> antonymsListButton = new ArrayList<>();
    private final ArrayList<ToggleButton> posListButton = new ArrayList<>();
    private final ArrayList<HBox> definitionListHBox = new ArrayList<>();

    public void setSynonymsListButton(ArrayList<StringProperty> stringPropertyArrayList) throws SQLException {
        for (StringProperty stringProperty : stringPropertyArrayList) {
            Button button = new Button();
            button.textProperty().bind(stringProperty);
            if (Dictionary.isExisted(stringProperty.toString())) {
                button.setId("synonymButton");
                button.setOnMouseClicked(e -> {
                });
            } else {
                button.setOnMouseMoved(e -> {
                    AppWindow.dialog.getLayout().setOpacity(1);
                    AppWindow.dialog.getLayout().toFront();
                    AppWindow.dialog.setText("Not in dictionary");
                    AppWindow.dialog.getLayout().setOpacity(1);
                    AppWindow.dialog.getLayout().setLayoutX(e.getSceneX());
                    AppWindow.dialog.getLayout().setLayoutY(e.getSceneY());
                });
                button.setOnMouseExited(e -> {
                    AppWindow.dialog.getLayout().setOpacity(0);
                });
                button.getStylesheets().add(Dashboard1.LINK_CSS);
                button.setId("synonymNotButton");
            }
            button.applyCss();
            System.out.println(button.getText());
            synonymsListButton.add(button);
        }
    }

    public void setAntonymsListButton(ArrayList<StringProperty> stringPropertyArrayList) throws SQLException {
        for (StringProperty stringProperty : stringPropertyArrayList) {
            Button button = new Button();
            button.textProperty().bind(stringProperty);
            if (Dictionary.isExisted(stringProperty.toString())) {
                button.setId("antonymButton");
                button.setOnMouseClicked(e -> {
                });
            } else {
                button.setOnMouseMoved(e -> {
                    AppWindow.dialog.getLayout().setOpacity(1);
                    AppWindow.dialog.getLayout().toFront();
                    AppWindow.dialog.setText("Not in dictionary");
                    AppWindow.dialog.getLayout().setOpacity(1);
                    AppWindow.dialog.getLayout().setLayoutX(e.getSceneX());
                    AppWindow.dialog.getLayout().setLayoutY(e.getSceneY());
                });
                button.setOnMouseExited(e -> {
                    AppWindow.dialog.getLayout().setOpacity(0);
                });
                button.getStylesheets().add(Dashboard1.LINK_CSS);
                button.setId("antonymNotButton");
            }
            button.applyCss();
            antonymsListButton.add(button);
        }
    }

    public void setPosListButton() {
        ToggleGroup toggleGroup = new ToggleGroup();
        for (int i = 0; i < 4; i++) {
            ToggleButton toggleButton = getButtonPos();
            toggleGroup.getToggles().add(toggleButton);
            posListButton.add(toggleButton);
        }
    }

    public void setDefUI(List<String> defList) {
        definitionListHBox.clear();
        StringBuilder exampleSection = new StringBuilder();
        for (int i = 0; i < defList.size(); i++) {
            String def = defList.get(i);
            HBox hBox = new HBox();
            Text text = new Text();
            ImageView icon = new ImageView();
            if (def.startsWith("-")) {
                if (i - 1 > 0 && !defList.get(i - 1).startsWith("-")) {
                    definitionListHBox.add(new HBox(new Text("")));
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

    public ArrayList<HBox> getDefUI() {
        return definitionListHBox;
    }

    private void addDefUI(HBox hBox, Text text, ImageView icon, String type) {
        hBox.getStylesheets().add(WordEngine.LINK_CSS);
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
        definitionListHBox.add(hBox);
    }

    public ArrayList<ToggleButton> getPosListButton() {
        return posListButton;
    }

    private ToggleButton getButtonPos() {
        ToggleButton button = new ToggleButton("none");
        button.setId("buttonPOS");
        button.getStylesheets().add(WordEngine.LINK_CSS);
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

    public ArrayList<Button> getSynonymsListButton() {
        return synonymsListButton;
    }

    public ArrayList<Button> getAntonymsListButton() {
        return antonymsListButton;
    }

    public double getDefWidth() {
        return WRAP_TEXT_WIDTH + 22 + 10;
    }

}
