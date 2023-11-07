package graphics.engine.word;

import dictionary.Word;
import graphics.ui.Decorator;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PartOfSpeechUI implements Decorator {
    private Word word = null;
    private WordUtilUI wordUtilUI = null;
    private HashMap<String, String> hashMapPOS = null;

    private final HBox hBoxPOS = new HBox();

    public final DefinitionUI definitionUI = new DefinitionUI();

    public PartOfSpeechUI() {
        setID();
        setCSS();
        setUI();
    }

    private void setHBoxPOS() {
        hBoxPOS.setSpacing(10);
    }

    public void loadData(Word word,WordUtilUI wordUtilUI) {
        this.word = word;
        this.wordUtilUI = wordUtilUI;
        hashMapPOS = word.getHashMap();
        definitionUI.loadData(word, wordUtilUI);
    }

    public HBox getLayout() {
        return hBoxPOS;
    }
    @Override
    public void setID() {

    }

    @Override
    public void setCSS() {
        hBoxPOS.getStylesheets().add(WordEngine.LINK_CSS);
    }

    @Override
    public void setUI() {
        setHBoxPOS();
    }

    @Override
    public void updateUI() {
        hBoxPOS.getChildren().clear();
        hBoxPOS.getChildren().addAll(wordUtilUI.getPosListButton());

        Iterator<Map.Entry<String, String>> posSet = hashMapPOS.entrySet().iterator();
        for (ToggleButton button : wordUtilUI.getPosListButton()) {
            if (posSet.hasNext()) {
                button.setSelected(false);
                button.setOpacity(1);
                button.setDisable(false);
                button.setText(posSet.next().getKey());
                if (button.getOnAction() != null) {
                    button.removeEventHandler(ActionEvent.ACTION, button.getOnAction());
                }
                button.addEventHandler(ActionEvent.ACTION, actionEvent -> {
                    wordUtilUI.setDefUI(word.getLinesDefinitionOf(button.getText()));
                    definitionUI.updateUI();
                });
            } else {
                button.setOpacity(0);
                button.setDisable(true);
            }
        }

    }

    @Override
    public String getLink() {
        return null;
    }
}
