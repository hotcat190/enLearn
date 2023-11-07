package graphics.engine.word;

import dictionary.Dictionary;
import dictionary.Word;
import graphics.StandardParameter;
import graphics.app.dashboard.Dashboard1;
import graphics.engine.SearchEngine;
import graphics.load.SkeletonLoad;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordEngine {
    public static final String LINK_CSS = Objects.requireNonNull(Dashboard1.class.getResource("/css/style_for_engine_word_package.css")).toExternalForm();
    private final WordBoard wordBoard;
    private final SynonymsUI synUI = new SynonymsUI(250, 235, 15, 15, Color.rgb(38, 247, 142));
    private final AntonymsUI antonUI = new AntonymsUI(250, 235, 15, 15, Color.rgb(234, 164, 40));


    /**
     * Display layout.
     */
    private final GridPane layout = new GridPane();

    /**
     * Word.
     */
    private Word word = null;
    private final WordUtilUI wordUtilUI = new WordUtilUI();




    /**
     * Display antonyms.
     */
    private final Text titleAntonyms = new Text("Antonyms");
    private final FlowPane antonymsFlowPane = new FlowPane();
    private final VBox antonymsVBox = new VBox(titleAntonyms, antonymsFlowPane);
    private final ArrayList<String> stringAntonymsArrayList = new ArrayList<>();
    private final ArrayList<StringProperty> antonymsStringPropertyList = new ArrayList<>();

    /**
     * Display synonyms.
     */
    private final Text titleSynonyms = new Text("Synonyms");
    private final FlowPane synonymsFlowPane = new FlowPane();
    private final VBox synonymsVBox = new VBox(titleSynonyms, synonymsFlowPane);
    private final ArrayList<String> stringSynonymsArrayList = new ArrayList<>();
    private final ArrayList<StringProperty> synonymsStringPropertyList = new ArrayList<>();

    /**
     * Display irregular verbs.
     */
    private final Text titleIrregularVerb = new Text("Irregular Verb");
    private final HBox irregularVerbsHBox = new HBox();
    private final VBox irregularVerbsVBox = new VBox(titleIrregularVerb, irregularVerbsHBox);


    /**
     * Display part of speech.
     */
    private final HBox hBoxPOS = new HBox();

    /**
     * Parameter.
     */
    public final static int DISPLAY_WORD_WIDTH = 320;
    public static final int TRANSLATE_GRAMMAR_Y = 60;
    public static final int TRANSLATE_GRAMMAR_X = 32;

    public WordEngine() throws SQLException {
        wordBoard = new WordBoard(wordUtilUI);

        layout.add(wordBoard.getLayout(), 0, 0, 1, 2);
        layout.add(synUI.getLayout(), 1, 0);
        layout.add(antonUI.getLayout(), 1, 1);
        layout.setHgap(10);
        layout.setVgap(10);
        setId();
        setCSS();

        setUpSkeletonLoad();
        setUpWordUI();

    }

    private void setId() {
        titleIrregularVerb.setId("titleGrammar");
        titleSynonyms.setId("titleGrammar");
        titleAntonyms.setId("titleGrammar");
        irregularVerbsHBox.setId("irregularVerbsHBox");

    }

    private void setCSS() {
        layout.getStylesheets().add(Dashboard1.LINK_CSS);
        layout.applyCss();
        titleAntonyms.applyCss();
        titleSynonyms.applyCss();
    }

    public Pane getPane() {
        return layout;
    }


    public void setEvent(SearchEngine searchEngine) {
        ListView<String> listView = searchEngine.getListView();
        ListView<String> listViewHistory = searchEngine.getListViewHistory();
        listView.setOnMouseClicked(e -> {
            if (!listView.getItems().isEmpty()) {
                hBoxPOS.getChildren().clear();
                irregularVerbsHBox.getChildren().clear();
                String selectionWord = listView.getSelectionModel().getSelectedItem();
                listView.getItems().clear();
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Runnable loadData = () -> {
                    try {
                        word = new Word(selectionWord);
                        wordBoard.loadData(word);
                        synUI.loadData(word);
                        antonUI.loadData(word);
                        Dictionary.putHistory(word.getWord());
//                        setSynonymsData(word.getWord());
//                        setAntonymsData(word.getWord());
                    } catch (SQLException ignored) {
                    }
                    new Thread(() -> {
                        Platform.runLater(() -> {
                            wordBoard.updateUI();
                            try {
                                synUI.updateUI(wordBoard);
                                antonUI.updateUI(wordBoard);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            setAntonymsLayout();
                            searchEngine.isPressed = false;
                            searchEngine.getTextInput().setText(word.getWord());
                        });
                    }).start();
                };
                executorService.execute(loadData);
            }
        });
        listViewHistory.setOnMouseClicked(e -> {
            if (!listViewHistory.getItems().isEmpty()) {
                hBoxPOS.getChildren().clear();
                irregularVerbsHBox.getChildren().clear();
                String selectionWord = listViewHistory.getSelectionModel().getSelectedItem();
                listViewHistory.getItems().clear();
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                skeletonLoad.start();
                Runnable loadData = () -> {
                    try {
                        word = new Word(selectionWord);
                        wordBoard.loadData(word);
                        Dictionary.getHistory().remove(selectionWord);
                        Dictionary.putHistory(word.getWord());
                        setSynonymsData(word.getWord());
                        setAntonymsData(word.getWord());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    new Thread(() -> {
                        Platform.runLater(() -> {
                            setAntonymsLayout();
                            searchEngine.isPressed = false;
                            searchEngine.getTextInput().setText(word.getWord());
                            removeSkeletonLoad();
                        });

                    }).start();
                };
                executorService.execute(loadData);
            }
        });
    }

    private void setUpWordUI() throws SQLException {
        for (int i = 0; i < 6; i++) {
            stringSynonymsArrayList.add(" ");
            stringAntonymsArrayList.add(" ");
            StringProperty stringAntonymProperty = new SimpleStringProperty(stringAntonymsArrayList.get(i));
            StringProperty stringSynonymProperty = new SimpleStringProperty(stringSynonymsArrayList.get(i));
            synonymsStringPropertyList.add(stringSynonymProperty);
            antonymsStringPropertyList.add(stringAntonymProperty);
        }
        wordUtilUI.setSynonymsListButton(synonymsStringPropertyList);
        wordUtilUI.setAntonymsListButton(antonymsStringPropertyList);
        synonymsFlowPane.getChildren().addAll(wordUtilUI.getSynonymsListButton());
        antonymsFlowPane.getChildren().addAll(wordUtilUI.getAntonymsListButton());
    }

    private void setAntonymsData(String word) throws SQLException {
        ArrayList<String> arrayList = Dictionary.getAntonyms(word);
        if(arrayList==null) return;
        for (int i = 0; i < Objects.requireNonNull(arrayList).size() && i < 6; i++) {
            stringAntonymsArrayList.set(i, arrayList.get(i));
        }
    }

    private void setAntonymsLayout() {
        int i = 0;
        for (StringProperty stringProperty : antonymsStringPropertyList) {
            stringProperty.set(stringAntonymsArrayList.get(i));
            i++;
        }
    }

    private void setSynonymsData(String word) throws SQLException {
        ArrayList<String> arrayList = Dictionary.getSynonyms(word);
        if(arrayList==null) return;
        for (int i = 0; i < Objects.requireNonNull(arrayList).size() && i < 6; i++) {
            stringSynonymsArrayList.set(i, arrayList.get(i));
        }
    }


    private SkeletonLoad skeletonLoad = new SkeletonLoad();

    private void setUpSkeletonLoad() {
//        skeletonLoad.add(textWord);
    }

    private void removeSkeletonLoad() {
        skeletonLoad.stop();
    }

}

