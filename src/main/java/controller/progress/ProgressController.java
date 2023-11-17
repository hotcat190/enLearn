package controller.progress;

import controller.model.Controller;
import graphics.app.User;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import view.progress.ProgressView;

public class ProgressController extends Controller {
    public final ProgressView progressView = new ProgressView();
    private final User user = new User();

    public ProgressController() {
        progressView.connect(user);
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
        return progressView;
    }
}
