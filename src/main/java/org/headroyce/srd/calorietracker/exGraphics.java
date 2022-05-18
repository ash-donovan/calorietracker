package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class exGraphics extends BorderPane {

    public exGraphics(Stage stage, settingsLogic settingsLogic) {



        Text title = new Text("Exercise");
        title.setFont(new Font(30));

        Text inputDes = new Text("Input your activities here:");
        inputDes.setWrappingWidth(stage.getWidth()/1.5);
        inputDes.setFont(new Font(15));

        Button input = new Button("Input");
       // input.setFont(Font.font(15));

        Text logDes = new Text("Access your activities from today here:");
        logDes.setFont(new Font(15));
        logDes.setWrappingWidth(stage.getWidth()/1.5);


        Button log = new Button("Log");
      //  log.setFont(new Font(15));




        VBox components = new VBox(50, title, inputDes, input, logDes, log);
        components.setAlignment(Pos.TOP_CENTER);
        this.setCenter(components);


        Button back = new Button("Back");
        this.setTop(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) exGraphics.this.getScene().getWindow();
                homeGraphics homeGraphic = new homeGraphics(s, new settingsLogic());
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


}
