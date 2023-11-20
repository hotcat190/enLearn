package graphics.style;

import javafx.scene.text.Font;

import java.util.Objects;

public class CustomFont {
    public static final Font ACUMIN_VARIABLE_CONCEPT = Font.loadFont(Objects.requireNonNull(CustomFont.class.getResource("/font/Acumin Variable Concept.ttf")).toExternalForm(),10);
    public static final Font ACUMIN_VARIABLE_CONCEPT_SEMI_BOLD = Font.loadFont(Objects.requireNonNull(CustomFont.class.getResource("/font/Acumin Variable Concept Semibold.ttf")).toExternalForm(),10);

}
