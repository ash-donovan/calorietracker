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

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RmrCalculator extends BorderPane {

    private settingsLogic settingsLogic;

    public RmrCalculator(Stage stage, settingsLogic settingsLogic) {

        this.settingsLogic = settingsLogic;

        //set up header
        Text title = new Text("RMR Calculator");
        title.setFont(new Font(30));


        //set up toggle buttons
        RadioButton metric = new RadioButton("Metric");
        RadioButton imperial = new RadioButton("Imperial");
        ToggleGroup systemToggle = new ToggleGroup();
        metric.setToggleGroup(systemToggle);
        imperial.setToggleGroup(systemToggle);
        systemToggle.selectToggle(metric);

        Text system = new Text("choose system:");

        VBox chooseSystem = new VBox();

        //set up text fields + prompt text
        TextField weight = new TextField();
        TextField height = new TextField();
        TextField age = new TextField();

        weight.setPromptText("input weight here");
        height.setPromptText("input height here");
        age.setPromptText("input age here");

        Label weightLabel = new Label("weight (in kilograms)");
        Label heightLabel = new Label("height (in centimeters)");
        Label ageLabel = new Label("age (in years)");
        Label sexLabel = new Label("biological sex");


        ToggleGroup chooseSex = new ToggleGroup();
        ToggleButton female = new ToggleButton("Female");
        female.setToggleGroup(chooseSex);
        ToggleButton male = new ToggleButton("Male");
        male.setToggleGroup(chooseSex);

        HBox sex = new HBox(10, male, female);


        Button calculate = new Button("Calculate");

        VBox warnings = new VBox(5);


        VBox mainbox = new VBox(2, spacerMaker(), title, spacerMaker(), system, metric, imperial, spacerMaker(),
                weightLabel, weight, spacerMaker(), heightLabel, height, spacerMaker(), ageLabel, age, spacerMaker(),
                sexLabel, sex, spacerMaker(), calculate, warnings, spacerMaker(), spacerMaker());
        mainbox.setAlignment(Pos.CENTER);
        mainbox.setFillWidth(false);
        this.setCenter(mainbox);

        metric.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    weightLabel.setText("weight (in kilograms)");
                    heightLabel.setText("height (in centimeters)");
                } else {
                    weightLabel.setText("weight (in pounds)");
                    heightLabel.setText("height (in inches)");
                }
            }
        });


        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                settingsLogic.setImperial(imperial.isSelected());

                warnings.getChildren().clear();

                boolean a = settingsLogic.setWeight(weight.getText());
                boolean b = settingsLogic.setHeight(height.getText());
                boolean c = settingsLogic.setAge(age.getText());
                boolean d = female.isSelected() || male.isSelected();

                if (!settingsLogic.setWeight(weight.getText())) {
                    Text weightWarning = new Text("Please input a legal weight.");
                    weightWarning.setFont(new Font(10));
                    weightWarning.setFill(Color.RED);
                    warnings.getChildren().add(weightWarning);
                }

                if (!settingsLogic.setHeight(height.getText())) {
                    Text heightWarning = new Text("Please input a legal height.");
                    heightWarning.setFont(new Font(10));
                    heightWarning.setFill(Color.RED);
                    warnings.getChildren().add(heightWarning);
                }

                if (!settingsLogic.setAge(age.getText())) {
                    Text ageWarning = new Text("Please input a legal age.");
                    ageWarning.setFont(new Font(10));
                    ageWarning.setFill(Color.RED);
                    warnings.getChildren().add(ageWarning);
                }

                if (!(female.isSelected() || male.isSelected())) {
                    Text sexWarning = new Text("Please choose a sex.");
                    sexWarning.setFont(new Font(10));
                    sexWarning.setFill(Color.RED);
                    warnings.getChildren().add(sexWarning);
                } else {
                    settingsLogic.setSex(female.isSelected());

                }

                settingsLogic.calculateRMR();

                if (a && b && c && d) {
                    Stage s = (Stage) RmrCalculator.this.getScene().getWindow();
                    RmrCalculator done = new RmrCalculator(s, settingsLogic, "done");
                    Scene doneScene = new Scene(done, s.getWidth(), s.getHeight());
                    s.setScene(doneScene);
                    s.setTitle("Settings");
                }



                try {
                    PrintWriter out = new PrintWriter("userData");
                    out.println(settingsLogic.getAge() + "," + settingsLogic.getWeight() + "," + settingsLogic.getHeight()
                            + "," + settingsLogic.getSex() + "," + settingsLogic.getRMR()
                            + "," + settingsLogic.getGoal() + "," + settingsLogic.getDailyCals());
                    out.close();
                } catch (FileNotFoundException f) {
                    f.printStackTrace();
                }
            }

        });

        Button back = new Button("Back");
        this.setTop(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) RmrCalculator.this.getScene().getWindow();
                settingGraphics setting = new settingGraphics(s, settingsLogic);
                Scene settingsScene = new Scene(setting, s.getWidth(), s.getHeight());
                s.setScene(settingsScene);
                s.setTitle("Settings");
            }
        });
    }

    private RmrCalculator(Stage stage, settingsLogic settingsLogic, String s) {

        Text title = new Text("All Set!");
        title.setFont(new Font(30));

        Text a = new Text();

        a.setText("Your RMR has been set to " + settingsLogic.getRMR() + " calories a day.");
        a.setWrappingWidth(stage.getWidth()/2);
        a.setTextAlignment(TextAlignment.CENTER);

        VBox body = new VBox(5, a, spacerMaker());
        body.setAlignment(Pos.CENTER);

        Button action = new Button("OK");


        if (!settingsLogic.isGoalSet()) {
            Text c = new Text("It looks like you haven't set your goal yet! You can do that here:");
            c.setWrappingWidth(stage.getWidth()/2);
            c.setFill(Color.INDIANRED);
            c.setTextAlignment(TextAlignment.CENTER);
            c.setLineSpacing(5);
            action.setText("Set Goal");
            body.getChildren().addAll(c, spacerMaker());
        }

        body.getChildren().add(action);

        action.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (settingsLogic.isGoalSet()) {

                    try {
                        PrintWriter out = new PrintWriter("userData");
                        out.println(settingsLogic.getAge() + "," + settingsLogic.getWeight() + "," + settingsLogic.getHeight()
                                + "," + settingsLogic.getSex() + "," + settingsLogic.getRMR()
                                + "," + settingsLogic.getGoal() + "," + settingsLogic.getDailyCals());
                        out.close();
                    } catch (FileNotFoundException f) {
                        f.printStackTrace();
                    }

                    homeGraphics home = new homeGraphics(stage, settingsLogic);
                    Scene homeScene = new Scene(home, stage.getWidth(), stage.getHeight());
                    stage.setScene(homeScene);
                    stage.setTitle("Home");
                }

                else {
                    GoalSettings goalSettings = new GoalSettings(stage, settingsLogic);
                    Scene goalSettingsScene = new Scene(goalSettings, stage.getWidth(), stage.getHeight());
                    stage.setScene(goalSettingsScene);
                    stage.setTitle("Set a goal");
                }
            }
        });



        VBox screenBox = new VBox(spacerMaker(), title, spacerMaker(), body, spacerMaker(), spacerMaker());
        setCenter(screenBox);
        screenBox.setAlignment(Pos.CENTER);

    }



        /**
         * generates spacers
         * @return a blank region with a Vgrow of ALWAYS
         */
    private Region spacerMaker() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }


}
