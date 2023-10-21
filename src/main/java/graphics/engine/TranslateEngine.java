package graphics.engine;

import dictionary.Translate;
import graphics.control.Dialog;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

/**
 * Class Translator to translate text, using google API.
 */
public class TranslateEngine {
    private final Text title = new Text("Translate");

    /**
     * Text to translate.
     */
    StringBuffer stringInput = new StringBuffer("");
    StringBuffer stringOutput = new StringBuffer("");
    /**
     * Text area for input from user and output translated text.
     */
    private TextArea textInput = new TextArea("Type here");
    private TextArea textOutput = new TextArea();

    /**
     * Pane layout for translator.
     */
    private final VBox paneTranslateEngine = new VBox();

    private final StackPane inputBox = new StackPane();
    private final StackPane outputBox = new StackPane();
    private ImageView inputBoxImage = new ImageView();
    private ImageView outputBoxImage = new ImageView();

    private ImageView copyIcon = new ImageView();
    private Region switchIcon = new Region();
    /**
     *
     */
    private final Button tagLangFrom = new Button();
    private final Button tagLangTo = new Button();
    /**
     * Link to CSS.
     */
    private final String linkToCSS = Objects.requireNonNull(getClass().getResource("/css/style_for_dashboard1_class.css")).toExternalForm();

    public TranslateEngine() {
        setId();
        setCSS();

        setInputBox();
        setOutputBox();

        paneTranslateEngine.setSpacing(0);
        paneTranslateEngine.getChildren().add(title);
        paneTranslateEngine.getChildren().add(outputBox);
        paneTranslateEngine.getChildren().add(inputBox);
    }

    public Pane getPaneTranslateEngine() {
        return paneTranslateEngine;
    }

    private void setInputBox() {
        try {
            inputBoxImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/boxinput_translator_app.png"))));
            inputBoxImage.setFitWidth(inputBoxImage.getImage().getWidth() * 0.5);
            inputBoxImage.setFitHeight(inputBoxImage.getImage().getHeight() * 0.5);
            textInput.setWrapText(true);

            tagLangFrom.setText("en");
            tagLangFrom.setTranslateX(181);
            tagLangFrom.setTranslateY(95);

            switchIcon.setTranslateY(70);
            switchIcon.setTranslateX(366);

            textInput.setBorder(Border.EMPTY);
            textInput.setMaxWidth(390);
            textInput.setMaxHeight(170);
            textInput.setTranslateY(-20);

            inputBox.setAlignment(Pos.CENTER_LEFT);
            inputBox.getChildren().add(inputBoxImage);
            inputBox.getChildren().add(textInput);
            inputBox.getChildren().add(tagLangFrom);
            inputBox.getChildren().add(switchIcon);
            inputBox.setTranslateY(-210);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void setOutputBox() {
        try {
            outputBoxImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/boxoutput_translator_app.png"))));
            copyIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/copy_icon_app.png"))));
            outputBoxImage.setFitWidth(outputBoxImage.getImage().getWidth() * 0.5);
            outputBoxImage.setFitHeight(outputBoxImage.getImage().getHeight() * 0.5);
            textOutput.setWrapText(true);

            tagLangTo.setText("vi");
            tagLangTo.setTranslateX(226);
            tagLangTo.setTranslateY(-95);

            textOutput.setEditable(false);
            textOutput.setBorder(Border.EMPTY);
            textOutput.setMaxWidth(390);
            textOutput.setMaxHeight(170);
            textOutput.setTranslateY(20);

            copyIcon.setOpacity(0.5);
            copyIcon.setTranslateX(360);
            copyIcon.setTranslateY(90);

            outputBox.setTranslateY(-40);
            outputBox.setAlignment(Pos.CENTER_LEFT);
            outputBox.getChildren().add(outputBoxImage);
            outputBox.getChildren().add(textOutput);
            outputBox.getChildren().add(copyIcon);
            outputBox.getChildren().add(tagLangTo);
            outputBox.setTranslateY(210);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void setAnimationInputBox() {
        textInput.setOnMouseClicked(e -> {
            textInput.setEditable(true);
            if (textInput.getText().equals("Type here") || textInput.getText().equals("Nhập tại đây")) {
                textInput.setText("");
            }
        });
        textInput.setOnMouseExited(e -> {
            if (textInput.getText().isEmpty()) {
                if (tagLangFrom.getText().equals("en")) {
                    textInput.setText("Type here");
                } else {
                    textInput.setText("Nhập tại đây");
                }
            }
            textInput.setEditable(false);
        });
        switchIcon.setOnMouseClicked(e -> {
            if (switchIcon.getRotate() % 180 == 0) {
                new Timeline(new KeyFrame(
                        new Duration(500),
                        new KeyValue(
                                switchIcon.rotateProperty(),
                                switchIcon.getRotate() + 180
                        )
                )).play();
            }

            String temp = tagLangFrom.getText();
            tagLangFrom.setText(tagLangTo.getText());
            tagLangTo.setText(temp);

            String temp1 = textInput.getText();
            textInput.setText(textOutput.getText());
            textOutput.setText(temp1);

            if (textInput.getText().equals("Type here") || textInput.getText().equals("Nhập tại đây")) {
                if (tagLangFrom.getText().equals("en")) {
                    textInput.setText("Type here");
                } else {
                    textInput.setText("Nhập tại đây");
                }
            }
        });
    }

    private void setAnimationOutputBox(Dialog dialog, Pane paneWindow) {
        copyIcon.setOnMouseClicked(e -> {
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(stringOutput.toString());
            Clipboard.getSystemClipboard().setContent(clipboardContent);

            dialog.getStackPane().toFront();
            dialog.setText("Copied");
            dialog.getStackPane().setOpacity(1);
            dialog.getStackPane().setLayoutX(e.getSceneX() - copyIcon.getFitWidth() / 2 - dialog.getStackPane().getBoundsInLocal().getWidth() / 2);
            System.out.println(e.getSceneX());

            dialog.getStackPane().setLayoutY(e.getSceneY() + copyIcon.getFitHeight() + 40);
        });
        copyIcon.setOnMouseExited(e -> {
            dialog.getStackPane().setOpacity(0);
        });

    }

    private void setId() {
        title.setId("title");
        textInput.setId("textInput");
        textOutput.setId("textOutput");
        copyIcon.setId("copyIcon");
        tagLangFrom.setId("tagLangFrom");
        tagLangTo.setId("tagLangTo");
        switchIcon.setId("switchIcon");
    }

    private void setCSS() {
        paneTranslateEngine.getStylesheets().add(linkToCSS);
        title.applyCss();
        paneTranslateEngine.applyCss();
    }

    public void setAnimation(Dialog dialog, Pane paneWindow) {
        setAnimationInputBox();
        setAnimationOutputBox(dialog, paneWindow);
    }

    public void setLiveTranslate() {
        /*
         * Multithreading for 2 task.
         * Task1: Translate.
         * Task2: Wait until task1 on finished, update output with "..." loading sign.
         */
        textInput.setOnKeyTyped(e -> {
            String inputText = textInput.getText();
            textOutput.setText(stringOutput + ". . .");
            new Thread(() -> {
                try {
                    stringOutput = new StringBuffer(Translate.translate(new StringBuffer(tagLangFrom.getText()), new StringBuffer(tagLangTo.getText()), new StringBuffer(inputText)));
                    if (stringOutput.toString().startsWith("<!DOCTYPE html>")) {
                        stringOutput = new StringBuffer("Service Google Translate is unavailable");
                    }
                    Platform.runLater(() -> textOutput.setText(stringOutput.toString()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }).start();
        });
    }
}
