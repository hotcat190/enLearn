package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
        double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
        double APP_WIDTH = 35.0 / 48.0 * SCREEN_WIDTH;
        double APP_HEIGHT = 35.9 / 48.0 * SCREEN_HEIGHT;
        stage.initStyle(StageStyle.TRANSPARENT);
        Rectangle rect = new Rectangle();
        rect.setX(0);
        rect.setY(0);
        rect.setWidth(APP_WIDTH);
        rect.setHeight(APP_HEIGHT);
        rect.setFill(Color.LIGHTBLUE);
        rect.setArcWidth(50);
        rect.setArcHeight(50);
        Group root = new Group(rect);
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT, false);
        scene.setFill(Color.TRANSPARENT);
        stage.setX(SCREEN_WIDTH / 2 - APP_WIDTH / 2);
        stage.setY(SCREEN_HEIGHT / 2 - APP_HEIGHT / 2);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}