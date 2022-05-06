package org.headroyce.srd.calorietracker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GoalSettings extends BorderPane {

    private int goalNumber;

    public GoalSettings(Stage stage, settingsLogic logic) {
        Text title = new Text("Set A Goal");
        title.setFont(new Font(30));

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

        Text goalText1 = new Text("I want to...");
        Text goalText2 = new Text("weight");

        VBox goal = new VBox(10, goalText1, g, goalText2);
        goal.setAlignment(Pos.CENTER);




        VBox chooseCalorieGoal = new VBox(10);
        chooseCalorieGoal.setAlignment(Pos.CENTER);
        Spinner calorieGoalSpinner = new Spinner(0, 500, 250, 25);
        calorieGoalSpinner.setPrefWidth(stage.getWidth()/5);
        calorieGoalSpinner.setEditable(true);




        chooseGoal.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> changed,
                                Toggle oldVal, Toggle newVal) {
                if (gain.isSelected()) {
                    chooseCalorieGoal.getChildren().clear();
                    Text calorieText = new Text("I want to eat...");
                    Text calorieText2 = new Text("extra calories a day.");
                    chooseCalorieGoal.getChildren().addAll(calorieText, calorieGoalSpinner, calorieText2);
                }

                if (maintain.isSelected()) {
                    chooseCalorieGoal.getChildren().clear();
                }

                if (lose.isSelected()) {
                    chooseCalorieGoal.getChildren().clear();
                    Text calorieText = new Text("I want to eat...");
                    Text calorieText2 = new Text("less calories a day.");
//                    Spinner calorieGoalSpinner = new Spinner(0, 500, 250, 25);
//                    calorieGoalSpinner.setPrefWidth(stage.getWidth()/5);
                    chooseCalorieGoal.getChildren().addAll(calorieText, calorieGoalSpinner, calorieText2);
                }
            }
        });


        Text goalNumberText = new Text("" + goalNumber);


        calorieGoalSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                goalNumber = (int) calorieGoalSpinner.getValueFactory().getValue();
                goalNumberText.setText("" + goalNumber);

            }
        });



        Button set = new Button("Set Goal");

        set.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (maintain.isSelected()) {
                    logic.setGoal(0);
                }
//
//                SpinnerValueFactory chooseCalorieGoalFac = chooseCalorieGoal.getValueFactory();
//
//                int spinnerVal = (int) chooseCalorieGoal.;

                if (gain.isSelected()) {
                }
            }
        });



        //set up spacers
        Region spacer1 = new Region();
        VBox.setVgrow(spacer1, Priority.ALWAYS);
        Region spacer2 = new Region();
        VBox.setVgrow(spacer2, Priority.ALWAYS);
        Region spacer3 = new Region();
        VBox.setVgrow(spacer3, Priority.ALWAYS);
        Region spacer4 = new Region();
        VBox.setVgrow(spacer4, Priority.ALWAYS);
        Region spacer5 = new Region();
        VBox.setVgrow(spacer5, Priority.ALWAYS);
        Region spacer6 = new Region();
        VBox.setVgrow(spacer6, Priority.ALWAYS);
        Region topSpacer = new Region();
        VBox.setVgrow(topSpacer, Priority.ALWAYS);
        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);
        Region bottomSpacer2 = new Region();
        VBox.setVgrow(bottomSpacer2, Priority.ALWAYS);

        VBox screenBox = new VBox(2, spacer1, spacer2, title, spacer3, goal, spacer4,
                chooseCalorieGoal, spacer5, goalNumberText, set, bottomSpacer, bottomSpacer2);
        setCenter(screenBox);



        screenBox.setAlignment(Pos.CENTER);

//        this.checkChange(gain, maintain, lose);

        Button back = new Button("Back");
        this.setTop(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) GoalSettings.this.getScene().getWindow();
                settingGraphics setting = new settingGraphics(s);
                Scene settingsScene = new Scene(setting, s.getWidth(), s.getHeight());
                s.setScene(settingsScene);
                s.setTitle("Settings");

            }
        });

    }

}
