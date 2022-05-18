package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class homeGraphics extends BorderPane {

    private Stage s;
    settingGraphics setting;



    public homeGraphics(Stage stage, settingsLogic settingsLogic, int i) {

        s = stage;
        setting = new settingGraphics(s, settingsLogic);

        Text title = new Text("Welcome");
        Text welcomeText = new Text("Welcome to CalorieTracker, where we can help your fitness goals come true! " +
                "To start, lets configure your settings to personalize your experience.");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font(30));
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        welcomeText.setWrappingWidth(stage.getWidth()/1.5);

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            welcomeText.setWrappingWidth(stage.getWidth()/1.5);
        });

        Text manualTitle = new Text("User Manual:");
        manualTitle.setFont(new Font(20));

        Hyperlink manual = new Hyperlink();
        manual.setText("https://docs.google.com/presentation/d/1XsGYBGDg_bU-SWOYgpGeiPl_7kyCpseViepp2Yp5FZc/edit#slide=id.g12a1a440d90_0_14");

        Button settingsButton = new Button("Settings");

        VBox mainbox = new VBox(10, spacerMaker(), title, welcomeText,
                settingsButton, manualTitle, manual, spacerMaker());
        mainbox.setAlignment(Pos.CENTER);

        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene settingScene = new Scene(setting, s.getWidth(),s.getHeight());
                s.setScene(settingScene);
                s.setTitle("Settings");
            }
        });


        this.setCenter(mainbox);
    }




    public homeGraphics(Stage stage, settingsLogic settingsLogic) {

        s = stage;
        setting = new settingGraphics(s, settingsLogic);

        //explanation texts
        Text exerciseTitle = new Text("EXERCISE");
        Text exerciseText = new Text("Click here to input exercise and calculate the amount of calories burned. " +
                "You can also view your activity from today.");
        exerciseText.setTextAlignment(TextAlignment.CENTER);
        exerciseTitle.setTextAlignment(TextAlignment.CENTER);
        exerciseTitle.setWrappingWidth(stage.getWidth()/1.5);



        Text calendarTitle = new Text("CALENDAR");
        Text calendarText = new Text("View your past exercise and diet history here");
        calendarText.setTextAlignment(TextAlignment.CENTER);
        calendarTitle.setTextAlignment(TextAlignment.CENTER);
        calendarTitle.setWrappingWidth(stage.getWidth()/1.5);



        Text dietTitle = new Text("DIET");
        Text dietText = new Text("Input food you've eaten here, or view what you've eaten today.");
        dietText.setTextAlignment(TextAlignment.CENTER);
        dietTitle.setTextAlignment(TextAlignment.CENTER);
        dietTitle.setWrappingWidth(stage.getWidth()/1.5);



        Text settingsTitle = new Text("SETTINGS");
        Text settingsText = new Text("Change your settings here. You can change your RMR or set a different goal.");
        settingsText.setTextAlignment(TextAlignment.CENTER);
        settingsTitle.setTextAlignment(TextAlignment.CENTER);
        settingsTitle.setWrappingWidth(stage.getWidth()/1.5);

        //buttons to change tabs
        Button exercise = new Button("Exercise");
        Button calendar = new Button("Calendar");
        Button diet = new Button("Diet");
        Button settings = new Button("Settings");

        VBox dietBox = new VBox(10, dietTitle, dietText, diet);
        VBox exerciseBox = new VBox(10, exerciseTitle, exerciseText, exercise);
        VBox calendarBox = new VBox(10, calendarTitle, calendarText, calendar);
        VBox settingsBox = new VBox(10, settingsTitle, settingsText, settings);

        dietBox.setAlignment(Pos.CENTER);
        exerciseBox.setAlignment(Pos.CENTER);
        calendarBox.setAlignment(Pos.CENTER);
        settingsBox.setAlignment(Pos.CENTER);






//        //button sizing
//        exercise.setMaxSize(300, 300);
//        calendar.setMaxSize(300, 300);
//        diet.setMaxSize(300, 300);


        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene settingScene = new Scene(setting, s.getWidth(),s.getHeight());
                s.setScene(settingScene);
                s.setTitle("Settings");
            }
        });

        exercise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exGraphics exGraphic = new exGraphics(s, settingsLogic);
                Scene exScene = new Scene(exGraphic, s.getWidth(), s.getHeight());
                s.setScene(exScene);
                s.setTitle("Exercise Tab");
            }
        });

        diet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dietGraphics dietGraphic = null;
                try {
                    dietGraphic = new dietGraphics(settingsLogic);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Scene dietScene = new Scene(dietGraphic, s.getWidth(), s.getHeight());
                s.setScene(dietScene);
                s.setTitle("Diet Tab");
            }
        });

        calendar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calGraphics calGraphic = new calGraphics(settingsLogic);
                Scene calScene = new Scene(calGraphic, s.getWidth(), s.getHeight());
                s.setScene(calScene);
                s.setTitle("Calendar Tab");
            }
        });

        VBox actions = new VBox(exercise, diet, calendar);
        actions.setAlignment(Pos.CENTER);

        this.setCenter(actions);
        this.setTop(settings);
    }



    private Region spacerMaker() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }


}
