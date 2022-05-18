package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dayGraphics extends BorderPane {

    private int totalCals = 0;
    private int calsIn = 0;
    private int calsBurn = 0;
    private int RMR = 0;
    private int calGoal = 0;


    public dayGraphics(int month, int day, settingsLogic settingsLogic) throws FileNotFoundException {

        Scanner userScan = new Scanner(new File("userData"));
        while(userScan.hasNextLine()){
            String userData = userScan.nextLine();
           String[] parts = userData.split(",");
            RMR = Integer.parseInt(parts[4]);
            calGoal = Integer.parseInt(parts[5]);
        }

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



        Scanner readScan = new Scanner(new File("foodStore.txt") );

        while( readScan.hasNextLine() ) {
           String foodAte = readScan.nextLine();
            String[] parts = foodAte.split(",");

            int parsedMonth = Integer.parseInt(parts[0]);
            int parsedDay = Integer.parseInt(parts[1]);
            if (parsedMonth == month) {
                if (parsedDay == day) {
                    System.out.print(true);
                    calsIn += Integer.parseInt(parts[3]);
                }
            }
        }

        totalCals += (calsIn + calsBurn) - RMR;

        Text netCals = new Text("Net Calories Today: " + totalCals);
        netCals.setFont(Font.font(30));

        Text calsCons = new Text("You Consumed: " + calsIn + " Calories Today");
        calsCons.setFont(Font.font(15));
        Text calsEx = new Text("You Burned: " + calsBurn + " Calories Today");
        calsEx.setFont(Font.font(15));
        HBox center = new HBox(calsCons, calsEx);
        center.setSpacing(50);
        center.setAlignment(Pos.CENTER);
        VBox all = new VBox(netCals,center);
        all.setAlignment(Pos.CENTER);
        all.setSpacing(50);

        Text goals = new Text("You aren't hitting your goal of "  + calGoal + " for today quite yet! Keep Working!");
        int goalDistance = calGoal - totalCals;
        goals.setFont(Font.font(15));
        if(Math.abs(goalDistance) < 50){
            goals.setText("You're doing well today! You are "  + goalDistance
                    + " calories away from your goal of " + calGoal + " !");
            }

        this.setCenter(all);
        this.setTop(back);
        this.setBottom(goals);

    }
}
