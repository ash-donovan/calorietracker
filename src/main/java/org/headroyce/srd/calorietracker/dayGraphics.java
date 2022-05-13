package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dayGraphics extends BorderPane {

    private int totalCals = 0;
    private int calsIn = 0;
    private int calsBurn = 0;

    public dayGraphics(int month, int day, settingsLogic settingsLogic) throws FileNotFoundException {

        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) dayGraphics.this.getScene().getWindow();
                calGraphics calgraphic = new calGraphics(settingsLogic);
                Scene calScene = new Scene(calgraphic, s.getWidth(), s.getHeight());
                s.setScene(calScene);
                s.setTitle("Calendar");
            }
        });

        Text netCals = new Text("Net Calories Today: " + totalCals);

        Text calsCons = new Text("You Consumed: " + calsIn + " Calories Today");
        Text calsEx = new Text("You Burned: " + calsBurn + "Calories Today");
        HBox top = new HBox(back,netCals);



        Scanner readScan = new Scanner(new File("foodStore") );

        while( readScan.hasNextLine() ) {
           String foodAte = readScan.nextLine();
            String[] parts = foodAte.split(",");

            int parsedMonth = Integer.parseInt(parts[0]);
            int parsedDay = Integer.parseInt(parts[1]);

            if (parsedMonth == month) {
                if (parsedDay == day) {
                    System.out.print(true);
                    calsIn += Integer.parseInt(parts[3]);
                    Text display = new Text(parts[2]);
                    this.setCenter(display);


                }
            }
        }





        this.setTop(top);
    }
}
