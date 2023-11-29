package graphics.load;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class SkeletonLoad {
    private final Timeline timeline = new Timeline();

    public SkeletonLoad() {
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    public void add(Node node) {
        timeline.getKeyFrames().add(new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(node.opacityProperty(), 0.3)
                )
        );
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }
}
