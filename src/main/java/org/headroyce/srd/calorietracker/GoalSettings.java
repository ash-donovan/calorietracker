package org.headroyce.srd.calorietracker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GoalSettings extends BorderPane {

    private int goalNumber;
    private String GML = "maintain";

    public GoalSettings(Stage stage, settingsLogic settingsLogic) {

        //set up title
        Text title = new Text("Set A Goal");
        title.setFont(new Font(30));

        //set up gain/maintain/lose buttons
        ToggleGroup chooseGoal = new ToggleGroup();
        ToggleButton gain = new ToggleButton("Gain");
        gain.setToggleGroup(chooseGoal);
        ToggleButton lose = new ToggleButton("Lose");
        lose.setToggleGroup(chooseGoal);
        ToggleButton maintain = new ToggleButton("Maintain");
        maintain.setToggleGroup(chooseGoal);
        maintain.setSelected(true);
        HBox g = new HBox(5, gain, maintain, lose);
        g.setAlignment(Pos.CENTER);

        //set up gain/maintain/lose box
        Text goalText1 = new Text("I want to...");
        Text goalText2 = new Text("weight");
        VBox goal = new VBox(10, goalText1, g, goalText2);
        goal.setAlignment(Pos.CENTER);

        //set up spinner + spinner vbox
        VBox chooseCalorieGoal = new VBox(10);
        chooseCalorieGoal.setAlignment(Pos.CENTER);
        Spinner calorieGoalSpinner = new Spinner(0, 500, 250, 25);
        calorieGoalSpinner.setPrefWidth(stage.getWidth()/5);
        calorieGoalSpinner.setEditable(true);
        goalNumber = (int) calorieGoalSpinner.getValueFactory().getValue();

        chooseGoal.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> changed,
                                Toggle oldVal, Toggle newVal) {
                if (gain.isSelected()) {
                    chooseCalorieGoal.getChildren().clear();
                    Text calorieText = new Text("I want to eat...");
                    Text calorieText2 = new Text("extra calories a day.");
                    setGML("gain");
                    chooseCalorieGoal.getChildren().addAll(calorieText, calorieGoalSpinner, calorieText2);
                }

                if (maintain.isSelected()) {
                    chooseCalorieGoal.getChildren().clear();

                    setGML("maintain");
                }

                if (lose.isSelected()) {
                    chooseCalorieGoal.getChildren().clear();
                    Text calorieText = new Text("I want to eat...");
                    Text calorieText2 = new Text("less calories a day.");
//                    Spinner calorieGoalSpinner = new Spinner(0, 500, 250, 25);
//                    calorieGoalSpinner.setPrefWidth(stage.getWidth()/5);
                    chooseCalorieGoal.getChildren().addAll(calorieText, calorieGoalSpinner, calorieText2);
                    setGML("lose");
                }
            }
        });

        calorieGoalSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                goalNumber = (int) calorieGoalSpinner.getValueFactory().getValue();
            }
        });



        Button set = new Button("Set Goal");

        set.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (maintain.isSelected()) {
                    settingsLogic.setGoal(0);
                }
                if (gain.isSelected()) {
                    settingsLogic.setGoal(goalNumber);
                }
                if (lose.isSelected()) {
                    settingsLogic.setGoal(goalNumber * -1);
                }

                GoalSettings GoalSettings = new GoalSettings(stage, settingsLogic, GML);
                Scene GoalScene = new Scene(GoalSettings, stage.getWidth(), stage.getHeight());
                stage.setScene(GoalScene);
                stage.setTitle("Goal has been set!");

            }
        });


        VBox screenBox = new VBox(2, spacerMaker(), spacerMaker(), title, spacerMaker(), goal, spacerMaker(),
                chooseCalorieGoal, spacerMaker(), set, spacerMaker(), spacerMaker());
        setCenter(screenBox);



        screenBox.setAlignment(Pos.CENTER);

//        this.checkChange(gain, maintain, lose);

        Button back = new Button("Back");
        this.setTop(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) GoalSettings.this.getScene().getWindow();
                settingGraphics setting = new settingGraphics(s, settingsLogic);
                Scene settingsScene = new Scene(setting, s.getWidth(), s.getHeight());
                s.setScene(settingsScene);
                s.setTitle("Settings");
            }
        });
    }

    private GoalSettings(Stage stage, settingsLogic settingsLogic, String GML){
        
        Text title = new Text("All Set!");
        title.setFont(new Font(30));

        Text a = new Text();

        Text b = new Text();
        Text c = new Text();

        a.setText("Your goal has been set to " + GML + " weight. You will gain " + settingsLogic.getGoal() + " calories a day.");

        if (settingsLogic.getGoal() < 0) {

            a.setText("Your goal has been set to " + GML + " weight. You will cut " + settingsLogic.getGoal()*-1 + " calories a day.");
        }
        if (settingsLogic.getGoal() == 0) {
            a.setText("Your goal has been set to " + GML + " weight.");
        }
        a.setWrappingWidth(stage.getWidth()/2);
        a.setTextAlignment(TextAlignment.CENTER);

        VBox body = new VBox(5, a, b);
        body.setAlignment(Pos.CENTER);

        Button action = new Button("OK");


        if (!settingsLogic.isRmrSet()) {
            c.setText("You haven't calculated your RMR yet! Calculate your RMR to find out how many calories you need to eat in a day");
            c.setWrappingWidth(stage.getWidth()/2);
            c.setFill(Color.INDIANRED);
            c.setTextAlignment(TextAlignment.CENTER);
            c.setLineSpacing(5);
            action = new Button("Calculate RMR");
            body.getChildren().addAll(spacerMaker(), c, action);
        }
        else {
            body.getChildren().add(action);
        }

        action.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (settingsLogic.isRmrSet()) {
                    homeGraphics home = new homeGraphics(stage, settingsLogic);
                    Scene homeScene = new Scene(home, stage.getWidth(), stage.getHeight());
                    stage.setScene(homeScene);
                    stage.setTitle("Home");
                }

                else {
                    RmrCalculator calc = new RmrCalculator(stage, settingsLogic);
                    Scene calcScene = new Scene(calc, stage.getWidth(), stage.getHeight());
                    stage.setScene(calcScene);
                    stage.setTitle("RMR Calculator");
                }
            }
        });



    VBox screenBox = new VBox(spacerMaker(), title, spacerMaker(), body, spacerMaker(), spacerMaker());
        setCenter(screenBox);
        screenBox.setAlignment(Pos.CENTER);

    }

    private boolean setGML(String s){
        if (s == "maintain" || s == "gain" || s == "lose") {
            GML = s;
            return true;
        }
        return false;
    }

    private Region spacerMaker() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

}
