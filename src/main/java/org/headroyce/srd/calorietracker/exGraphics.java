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

public class exGraphics extends BorderPane {

    public exGraphics() {

        Text title = new Text("Exercise");
        title.setFont(new Font(30));



        Button input = new Button("Input");
        input.setFont(new Font(15));





        VBox components = new VBox(50, title, input);
        components.setAlignment(Pos.TOP_CENTER);
        this.setCenter(components);


        Button back = new Button("Back");
        this.setTop(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) exGraphics.this.getScene().getWindow();
                homeGraphics homeGraphic = new homeGraphics(s);
                Scene homeScene = new Scene(homeGraphic, s.getWidth(), s.getHeight());
                s.setScene(homeScene);
                s.setTitle("Home");

            }
        });

        
    }


}
