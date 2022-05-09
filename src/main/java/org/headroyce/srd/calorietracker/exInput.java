package org.headroyce.srd.calorietracker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class exInput extends BorderPane {


    private int metPass = 5;
    private Stage s;

    public exInput(Stage stage){

        s = stage;


        Text title = new Text("Input your exercise");
        title.setFont(new Font(30));

        TextField exerciseName = new TextField();
        TextField time = new TextField();

        exerciseName.setPromptText("Name of Exercise");
        time.setPromptText("Time spent");

        HBox textFields = new HBox(exerciseName, time);
        textFields.setAlignment(Pos.CENTER);
        textFields.setSpacing(20);

        Text metTitle = new Text("Your activity's MET (Metabolic equivalent of task)");
        metTitle.setFont(new Font(20));

        Text metDes = new Text("Your MET is the objective measure of the ratio of the rate at which a person expends energy, " +
                "ralative to the mass of that person (Wikipedia).");
        metDes.setTextAlignment(TextAlignment.CENTER);
        metDes.setWrappingWidth(stage.getWidth()/2);
        metDes.setFont(new Font(13));

        Slider met = new Slider(0, 10, 5);
        met.setShowTickMarks(true);
        met.setShowTickLabels(true);
        met.setMajorTickUnit(2);
        met.setBlockIncrement(1);

        Text metVal = new Text("Your activity MET: " + met.getValue());
        metVal.setFont(new Font(13));

        met.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                metVal.setText("Your activity MET: " + newValue.intValue());
                metPass = newValue.intValue();
            }
        });

        VBox yur = new VBox(title, textFields, metDes, met, metVal);
        yur.setAlignment(Pos.TOP_CENTER);
        yur.setPadding(new Insets(25));
        yur.setSpacing(25);
        this.setTop(yur);

        Button back = new Button("Back");

        HBox backButton = new HBox(back);
        backButton.setAlignment(Pos.TOP_LEFT);

        HBox wholePage = new HBox(backButton, yur);
        this.setTop(wholePage);



        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) exInput.this.getScene().getWindow();
                exGraphics exGraphic = new exGraphics();
                Scene exPage = new Scene(exGraphic, s.getWidth(), s.getHeight());
                s.setScene(exPage);
                s.setTitle("exGraphics");

            }
        });

    }

}
