package controller.search;

import controller.model.Controller;
import controller.model.Listener;
import graphics.engine.search.SearchEngineView;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;

public class SearchEngineController extends Controller implements Listener {
    /**
     * Singleton.
     */
    private final static SearchEngineController INSTANCE = new SearchEngineController();
    /**
     * Components.
     */
    private final SearchEngineView searchEngineView = SearchEngineView.getInstance();
    private final SearchEngineHistoryController searchEngineHistoryController = SearchEngineHistoryController.getInstance();

    private SearchEngineController() {
        setListener();
    }

    @Override
    public void updateView() {
    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadData(Object object) {
    }

    @Override
    public Node getView() {
        return searchEngineView;
    }

    public static SearchEngineController getInstance() {
        return INSTANCE;
    }

    /**
     * Listener.
     */
    @Override
    public void setListener(Object object) {
    }


    @Override
    public void setListener() {
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(searchEngineView.isSearching) {
                    if (searchEngineView.getInput().getText().isEmpty()) {
                        if (!searchEngineView.getChildren().contains(searchEngineHistoryController.getView())) {
                            searchEngineView.getChildren().add(1, searchEngineHistoryController.getView());
                        }
                    } else {
                        searchEngineView.getChildren().remove(searchEngineHistoryController.getView());
                        if (!searchEngineView.getChildren().contains(searchEngineView.getListView())) {
                            searchEngineView.addListView();
                        }
                    }
                }
            }
        }.start();

    }

    public void hide() {
        if (searchEngineView.getChildren().size() == 2) {
            searchEngineView.getChildren().removeLast();
        }
        if (searchEngineView.getChildren().size() == 3) {
            searchEngineView.getChildren().removeLast();
            searchEngineView.getChildren().removeLast();
        }
        searchEngineView.setMaxHeight(30);

    }

    public void unHide() {
        if(searchEngineView.getChildren().size()==1) {
            searchEngineView.getChildren().add(searchEngineHistoryController.getView());
            searchEngineView.addListView();
        }
        searchEngineView.setMaxHeight(400);
    }

    public boolean isHided() {
        return searchEngineView.getChildren().size() == 1;
    }
}
