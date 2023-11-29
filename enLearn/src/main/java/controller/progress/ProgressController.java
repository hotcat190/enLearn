package controller.progress;

import controller.model.Controller;
import sql.user.SQLUser;
import javafx.scene.Node;
import controller.progress.view.ProgressView;

public class ProgressController extends Controller {
    /**
     * Singleton.
     */
    private static final ProgressController INSTANCE = new ProgressController();
    /**
     * Views.
     */
    public final ProgressView progressView = ProgressView.getInstance();

    public ProgressController() {
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

    public static ProgressController getInstance() {
        return INSTANCE;
    }
}
