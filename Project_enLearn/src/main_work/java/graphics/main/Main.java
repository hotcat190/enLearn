package graphics.main;

import graphics.app.*;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppStartPage appStartPage = new AppStartPage(stage);
        appStartPage.showPage();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
