package org.headroyce.srd.calorietracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class dietGraphics extends BorderPane {

   private String name;
    private int calTake;

    private ListView<String> names = new ListView<String>();
    private ObservableList<String> items = FXCollections.observableArrayList();

   private ListView<Integer> calCounts = new ListView<Integer>();
    private ObservableList<Integer> calItems = FXCollections.observableArrayList();


    public dietGraphics(settingsLogic settingsLogic) throws FileNotFoundException {




        Date datey = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datey);

        int dayNow = calendar.get(Calendar.DAY_OF_MONTH);
        int monthNow = calendar.get(Calendar.MONTH) + 1;

        names.setItems(items);
        calCounts.setItems(calItems);

        Scanner foodScan = new Scanner(new File("foodStore.txt"));

        while(foodScan.hasNextLine()){
           String parts = foodScan.nextLine();
           String[] partsy = parts.split(",");
           items.add(partsy[2]);
           calItems.add(Integer.parseInt(partsy[3]));
        }



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
                        items.add(name);
                        calItems.add(calTake);
                        addFood.close();
                    }
                });


                VBox enterFood = new VBox(namey, caloric, enter);
                namey.setAlignment(Pos.CENTER);
                caloric.setAlignment(Pos.CENTER);
                enter.setAlignment(Pos.CENTER);
                enterFood.setAlignment(Pos.CENTER);
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


                    try {
                        PrintWriter out = new PrintWriter("foodStore.txt");
                        for(int i = 0; i < items.size(); i++) {
                            out.println(monthNow + "," + dayNow + "," + items.get(i) + "," + calItems.get(i));
                        }
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                Stage s = (Stage) dietGraphics.this.getScene().getWindow();
                homeGraphics homeGraphic = new homeGraphics(s, settingsLogic);
                Scene homeScene = new Scene(homeGraphic, s.getWidth(), s.getHeight());
                s.setScene(homeScene);
                s.setTitle("Home");
            }
        });


        Button clear = new Button("Reset");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
             items.clear();
             calItems.clear();
            }
        });



        HBox actions = new HBox(home,spacerMaker2(), addMeal, spacerMaker2(), clear);
        actions.setAlignment(Pos.CENTER);
        HBox lists = new HBox(names, calCounts);
        this.setTop(actions);
      this.setCenter(lists);
    }

    private Region spacerMaker2() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }




}
