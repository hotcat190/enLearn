package graphics.main;

import graphics.app.AppStartPage;
import graphics.app.dashboard.Dashboard1;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        AppStartPage appStartPage = new AppStartPage(stage);
//        appStartPage.showPage();
        Dashboard1 appUserInterface = new Dashboard1(stage);
        appUserInterface.showPage();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
