package main;

import app.controller.AppController;
import app.controller.OpenController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage STAGE;

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        STAGE = stage;
        OpenController openController = OpenController.getInstance();
        openController.playAll();
    }
}
