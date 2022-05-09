package org.headroyce.srd.calorietracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class dietGraphics extends BorderPane {

    String name;
    int calTake;
    ArrayList<Integer> cals;
    ArrayList<String> allNames;

    public dietGraphics() {

        cals = new ArrayList<>();
        allNames = new ArrayList<>();
       ListView<String> names = new ListView<String>();
       ObservableList<String> items = FXCollections.observableArrayList();
        names.setItems(items);


        ListView<Integer> calCounts = new ListView<Integer>();
        ObservableList<Integer> calItems = FXCollections.observableArrayList();
        calCounts.setItems(calItems);



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
                        SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000000,0);
                calCount.setValueFactory(valueFactory);
                calCount.setMaxSize(200,20);
                calCount.setEditable(true);

                HBox caloric = new HBox(spinnerLabel, calCount);
                caloric.setSpacing(20);

                Label foodLabel = new Label("Food Name:");
                TextField foodName = new TextField();
                foodName.setMaxSize(200,20);
                HBox namey = new HBox(foodLabel, foodName);
                namey.setSpacing(20);


                Button enter = new Button("Enter");
                enter.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        name = foodName.getText();
                        calTake = calCount.getValue();

                        addFood.close();

                        try {
                            PrintWriter out = new PrintWriter("dataStore");
                            out.println(name + "," + calTake);

                            out.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                });

                VBox enterFood = new VBox(namey, caloric, enter);
                enterFood.setAlignment(Pos.TOP_CENTER);
                enterFood.setSpacing(50);

                Scene addFoodScene = new Scene(enterFood, 400, 400);
                addFood.setScene(addFoodScene);
                addFood.show();
            }
        });

        Button home = new Button("Home");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(allNames);

                Stage s = (Stage) dietGraphics.this.getScene().getWindow();
                homeGraphics homeGraphic = new homeGraphics(s);
                Scene homeScene = new Scene(homeGraphic, s.getWidth(), s.getHeight());
                s.setScene(homeScene);
                s.setTitle("Home");
            }
        });

        HBox lists = new HBox(names, calCounts);
        HBox actions = new HBox(home, addMeal);
      this.setTop(actions);
      this.setCenter(lists);
    }


    public String[] getNames(){
        return (String[]) allNames.toArray();
    }

public Integer[] getCals(){
        return (Integer[]) cals.toArray();
}



}
