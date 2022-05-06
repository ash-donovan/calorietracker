package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class dietGraphics extends BorderPane {

    public dietGraphics() {

        Button home = new Button("Home");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) dietGraphics.this.getScene().getWindow();
                homeGraphics homeGraphic = new homeGraphics(s);
                Scene homeScene = new Scene(homeGraphic, s.getWidth(), s.getHeight());
                s.setScene(homeScene);
                s.setTitle("Home");
            }
        });

        Button addMeal = new Button("Add Food");
        addMeal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final Stage addFood = new Stage();
                addFood.initModality(Modality.APPLICATION_MODAL);
                addFood.initOwner((Stage) dietGraphics.this.getScene().getWindow());

                Label spinnerLabel = new Label("Calories:");
                Spinner<Integer> calCount = new Spinner<Integer>();
                SpinnerValueFactory<Integer> valueFactory = new
                        SpinnerValueFactory.IntegerSpinnerValueFactory(0,500,0);
                calCount.setValueFactory(valueFactory);
                calCount.setMaxSize(200,20);
                calCount.setEditable(true);

                VBox caloric = new VBox(spinnerLabel, calCount);

                Label foodLabel = new Label("Food Name:");
                TextField foodName = new TextField();
                foodName.setMaxSize(200,20);
                VBox namey = new VBox(foodLabel, foodName);

                VBox enterFood = new VBox(namey, caloric);
                enterFood.setAlignment(Pos.TOP_CENTER);
                enterFood.setSpacing(50);



                Scene addFoodScene = new Scene(enterFood, 400, 400);
                addFood.setScene(addFoodScene);
                addFood.show();
            }
        });

      this.setTop(home);
      this.setCenter(addMeal);
    }




}
