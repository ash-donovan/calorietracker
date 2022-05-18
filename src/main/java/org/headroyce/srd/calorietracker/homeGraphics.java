package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class homeGraphics extends BorderPane {

    private Stage s;
    settingGraphics setting;
    settingsLogic settingsLogic;



    public homeGraphics(Stage stage, settingsLogic settingsLogic, int i) {
        this.settingsLogic = settingsLogic;

        s = stage;
        setting = new settingGraphics(s, settingsLogic);

        Text title = new Text("Welcome");
        Text welcomeText = new Text("Welcome to CalorieTracker, where we can help your fitness goals come true! " +
                "To start, lets configure your settings to personalize your experience.");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font(30));
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        welcomeText.setWrappingWidth(stage.getWidth()/1.5);

        Region space = new Region();
        space.setMaxHeight(10);
        space.setMinHeight(10);
//        space.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, new CornerRadii(0), new Insets(0))));

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            welcomeText.setWrappingWidth(stage.getWidth()/1.5);
        });



        Button settingsButton = new Button("Settings");

        VBox mainbox = new VBox(10, spacerMaker(), title, welcomeText,
                settingsButton, spacerMaker(), space);
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
        setting = new settingGraphics(s, this.settingsLogic);

        //buttons to change tabs
        Button exercise = new Button("Exercise");
        Button calendar = new Button("Calendar");
        Button diet = new Button("Diet");
        Button settings = new Button("Settings");

        //button sizing
        exercise.setMaxSize(300, 300);
        calendar.setMaxSize(300, 300);
        diet.setMaxSize(300, 300);


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
                exGraphics exGraphic = new exGraphics(s, homeGraphics.this.settingsLogic);
                Scene exScene = new Scene(exGraphic, s.getWidth(), s.getHeight());
                s.setScene(exScene);
                s.setTitle("Exercise Tab");
            }
        });

        diet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dietGraphics dietGraphic = new dietGraphics(homeGraphics.this.settingsLogic);
                Scene dietScene = new Scene(dietGraphic, s.getWidth(), s.getHeight());
                s.setScene(dietScene);
                s.setTitle("Diet Tab");
            }
        });

        calendar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calGraphics calGraphic = new calGraphics(homeGraphics.this.settingsLogic);
                Scene calScene = new Scene(calGraphic, s.getWidth(), s.getHeight());
                s.setScene(calScene);
                s.setTitle("Calendar Tab");
            }
        });

        VBox actions = new VBox(exercise, diet, calendar);
        actions.setSpacing(20);
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
