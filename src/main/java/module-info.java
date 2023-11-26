module apppackage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports graphics.main;
    exports utility.calendar;

    opens controller.my_dictionary.data;
    opens sql.dictionary;
    opens controller.word.data;

}