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

//        homeGraphics home = new homeGraphics(stage, new settingsLogic());

//        if (no text file created) {
          homeGraphics home = new homeGraphics(stage, new settingsLogic(), 1);
        // ^^creates first homeGraphics
//        }

        Scene scene = new Scene(home, 500, 500);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {launch();}
}