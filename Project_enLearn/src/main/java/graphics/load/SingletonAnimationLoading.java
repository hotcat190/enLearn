package graphics.load;

import javafx.scene.paint.Color;

public interface SingletonAnimationLoading {
    void setSingletonOnLoading();

    void removeSingletonLoading();

    Color COLOR = Color.rgb(56, 56, 56);
    Color NO_COLOR = Color.TRANSPARENT;
}
