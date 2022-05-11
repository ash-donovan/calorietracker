package org.headroyce.srd.calorietracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Main extends Application {

    PrintWriter outputStream;
    @Override
    public void start(Stage stage) throws IOException {
        homeGraphics home = new homeGraphics(stage, new settingsLogic());
        Scene scene = new Scene(home, 500, 500);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {launch();}
}