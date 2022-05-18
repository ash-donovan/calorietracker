package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class exGraphics extends BorderPane {

    public exGraphics(Stage stage, settingsLogic settingsLogic) {



        Text title = new Text("Exercise");
        title.setFont(new Font(30));

        Text inputDes = new Text("Done some exercise? Input your activities here!");
        inputDes.setWrappingWidth(stage.getWidth()/1.5);
        inputDes.setTextAlignment(TextAlignment.CENTER);
        inputDes.setFont(new Font(15));

        Button input = new Button("Add Exercise");
        input.setFont(new Font(15));

        Text logDes = new Text("Access your daily exercise log here.");
        logDes.setTextAlignment(TextAlignment.CENTER);
        logDes.setFont(new Font(15));
        logDes.setWrappingWidth(stage.getWidth()/1.5);

        Button log = new Button("Log");
        log.setFont(new Font(15));

        VBox logBox = new VBox(10, logDes, log);
        logBox.setAlignment(Pos.CENTER);
        VBox inBox = new VBox(10, inputDes, input);
        inBox.setAlignment(Pos.CENTER);


        VBox components = new VBox(5, spacerMaker(), title, spacerMaker(), inBox,
                spacerMaker(), logBox, spacerMaker());
        components.setAlignment(Pos.TOP_CENTER);
        this.setCenter(components);


        Button back = new Button("Back");
        this.setTop(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) exGraphics.this.getScene().getWindow();
                homeGraphics homeGraphic = new homeGraphics(s, settingsLogic);
                Scene homeScene = new Scene(homeGraphic, s.getWidth(), s.getHeight());
                s.setScene(homeScene);
                s.setTitle("Home");

            }
        });

        input.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) exGraphics.this.getScene().getWindow();
                exInput exInput = new exInput(s, settingsLogic);
                Scene inputPage = new Scene(exInput, s.getWidth(), s.getHeight());
                s.setScene(inputPage);
                s.setTitle("Exercise Input");
            }
        });

        log.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) exGraphics.this.getScene().getWindow();
                exLog exLog = null;
                try {
                    exLog = new exLog(settingsLogic);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Scene logPage = new Scene(exLog, s.getWidth(), s.getHeight());
                s.setScene(logPage);
                s.setTitle("Exercise Log");
            }
        });

        
    }

    public Region spacerMaker() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }


}
