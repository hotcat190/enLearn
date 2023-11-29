package graphics.main;

import app.AppController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        AppStartPage appStartPage = new AppStartPage(stage);
//        appStartPage.showPage();
//        HomeDashboard appUserInterface = new HomeDashboard(stage);
//        appUserInterface.showDashboard();
//        MyDictionaryDashboard secondDashboard = new MyDictionaryDashboard(stage);
//        secondDashboard.showDashboard();
//        InformationBox informationBox = new InformationBox();
//        StatisticBoard statisticBoard = new StatisticBoard();
//        Scene scene = new Scene(appUserInterface);
//        scene.setFill(Color.TRANSPARENT);
//        appUserInterface.getStageWindow().setScene(scene);
//        appUserInterface.getStageWindow().show();
//        TranslatorDashboard thirdDashboard = new TranslatorDashboard(stage);
//        thirdDashboard.showDashboard();
        AppController appController = new AppController(stage);
//        appController.showDashboard();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
