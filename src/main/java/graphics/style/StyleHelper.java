package graphics.style;

import java.util.Objects;

public class StyleHelper {
    /**
     * Get style sheet to add styleSheets of node.
     */
    public static String getStyleSheet(Object object, String path) {
        return Objects.requireNonNull(object.getClass().getResource(path)).toExternalForm();
    }
}
