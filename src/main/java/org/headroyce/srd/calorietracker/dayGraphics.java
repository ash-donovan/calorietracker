package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dayGraphics extends BorderPane {


    public dayGraphics(int month, int day) throws FileNotFoundException {

        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) dayGraphics.this.getScene().getWindow();
                calGraphics calgraphic = new calGraphics();
                Scene calScene = new Scene(calgraphic, s.getWidth(), s.getHeight());
                s.setScene(calScene);
                s.setTitle("Calendar");
            }
        });
        Scanner readScan = new Scanner(new File("dataStore"));
        String foodAte = readScan.nextLine();

        String[] parts = foodAte.split(",");
        int parsedMonth = Integer.parseInt(parts[0]);
        int parsedDay = Integer.parseInt(parts[1]);



        if(parsedMonth == month){
            if(parsedDay == day) {
                Text display = new Text(foodAte);
                this.setCenter(display);
            }
        }





        this.setTop(back);
    }
}
