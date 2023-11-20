package graphics.main;

import app.AppController;
import data.test.fill_in_blank.FillInBlankWord;
import graphics.app.AppStartPage;
import graphics.app.dashboard.FirstDashboard;
import graphics.app.dashboard.SecondDashboard;
import graphics.app.dashboard.ThirdDashboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.my_dictionary.my_word_box.InformationBox;
import view.statistic.StatisticBoard;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        AppStartPage appStartPage = new AppStartPage(stage);
//        appStartPage.showPage();
//        FirstDashboard appUserInterface = new FirstDashboard(stage);
//        appUserInterface.showDashboard();
//        SecondDashboard secondDashboard = new SecondDashboard(stage);
//        secondDashboard.showDashboard();
//        InformationBox informationBox = new InformationBox();
//        StatisticBoard statisticBoard = new StatisticBoard();
//        Scene scene = new Scene(appUserInterface);
//        scene.setFill(Color.TRANSPARENT);
//        appUserInterface.getStageWindow().setScene(scene);
//        appUserInterface.getStageWindow().show();
//        ThirdDashboard thirdDashboard = new ThirdDashboard(stage);
//        thirdDashboard.showDashboard();
        AppController appController = new AppController(stage);
//        appController.showDashboard();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
