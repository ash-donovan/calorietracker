package org.headroyce.srd.calorietracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class exLog extends BorderPane {

    public exLog(settingsLogic settingsLogic) throws FileNotFoundException {
        ListView<String> exName = new ListView<String>();
        ObservableList<String> activity = FXCollections.observableArrayList();
        exName.setItems(activity);


        ListView<Integer> exDuration = new ListView<Integer>();
        ObservableList<Integer> duration = FXCollections.observableArrayList();
        exDuration.setItems(duration);

        ListView<Integer> calsBurned = new ListView<Integer>();
        ObservableList<Integer> calories = FXCollections.observableArrayList();
        calsBurned.setItems(calories);


        Scanner scan = new Scanner(new File("exerciseStore"));
        while(scan.hasNextLine()){
            System.out.println(true);
            String[] parts = scan.nextLine().split(",");
            duration.add(Integer.parseInt(parts[0]));
            calories.add(Integer.parseInt(parts[1]));
            activity.add(parts[2]);

        }



        Button back = new Button("Back");
        this.setTop(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) exLog.this.getScene().getWindow();
                exGraphics exGraphics = new exGraphics(s, settingsLogic);
                Scene exScene = new Scene(exGraphics, s.getWidth(), s.getHeight());
                s.setScene(exScene);
            }
        });



        HBox lists = new HBox(exName, exDuration,  calsBurned);
        this.setCenter(lists);
    }

}
