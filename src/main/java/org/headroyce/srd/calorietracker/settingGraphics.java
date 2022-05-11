package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class settingGraphics extends BorderPane {


    public settingsLogic settingsLogic;

    public settingGraphics(Stage stage, settingsLogic settingsLogic){

        this.settingsLogic = settingsLogic;


        Text title = new Text("Settings");
        title.setFont(new Font(30));


        Text setRMR = new Text("SET RMR");
        Text rmrExplained = new Text("Your resting metabolic rate (RMR) is how many calories you burn during the " +
                "day without exercise. Calculate your RMR here:");
        rmrExplained.setTextAlignment(TextAlignment.CENTER);
        rmrExplained.setWrappingWidth(stage.getWidth()/2);

        Button rmrButton = new Button("Find RMR");

//        Text calGoal = new Text( logic.getDailyCals() + " calories");
//        calGoal.setTextAlignment(TextAlignment.JUSTIFY);

        VBox rmrBox = new VBox(10, setRMR, rmrExplained, rmrButton);
        rmrBox.setAlignment(Pos.CENTER);

        Text setGoal = new Text("SET GOAL");
        Text goalExplained = new Text("Your goal is how many calories you want to add or cut from your " +
                "diet. You can also choose to maintain weight. Set a goal here:");
        goalExplained.setTextAlignment(TextAlignment.CENTER);
        goalExplained.setWrappingWidth(stage.getWidth()/2);

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            rmrExplained.setWrappingWidth(stage.getWidth()/2);

            goalExplained.setWrappingWidth(stage.getWidth()/2);
        });




        Button goalButton = new Button("Choose Goal");

        VBox goalBox = new VBox(10, setGoal, goalExplained, goalButton);
        goalBox.setAlignment(Pos.CENTER);


        Region spacer1 = new Region();
        VBox.setVgrow(spacer1, Priority.ALWAYS);
        Region spacer2 = new Region();
        VBox.setVgrow(spacer2, Priority.ALWAYS);
        Region spacer3 = new Region();


        VBox totalBox = new VBox(30, spacer1, title, rmrBox, goalBox, spacer2);
        totalBox.setAlignment(Pos.CENTER);

        this.setCenter(totalBox);



        rmrButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                RmrCalculator rmrCalc = new RmrCalculator(stage, settingsLogic);
                Scene rmrScene = new Scene(rmrCalc, stage.getWidth(), stage.getHeight());
                stage.setScene(rmrScene);
                stage.show();
                stage.toFront();

            }
        });

        goalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                GoalSettings goalStuff = new GoalSettings(stage, settingsLogic);
                Scene goalScene = new Scene(goalStuff, stage.getWidth(), stage.getHeight());
                stage.setScene(goalScene);
                stage.show();
                stage.toFront();

            }
        });

        Button bruh = new Button("Back");
        this.setTop(bruh);

        bruh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) settingGraphics.this.getScene().getWindow();
                homeGraphics homeGraphic = new homeGraphics(s, new settingsLogic());
                Scene homeScene = new Scene(homeGraphic, s.getWidth(), s.getHeight());
                s.setScene(homeScene);
                s.setTitle("Home");
            }
        });

    }

    public settingsLogic getSettingsLogic() {
        return this.settingsLogic;
    }

}


