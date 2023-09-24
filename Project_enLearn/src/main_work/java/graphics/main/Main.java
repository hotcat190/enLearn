package graphics.main;

import graphics.appwindow.*;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppWindow appWindow = new AppWindow(stage);
        appWindow.stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
