package org.headroyce.srd.calorietracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class calGraphics extends BorderPane {
    private Button[][] days;
    int counter;
    int month;
    int day;
    Text monthText;


    public calGraphics() {
        calLogic calLogic = new calLogic();
        month = 1;
        day = 1;
        days = new Button[5][7];
        monthText = new Text();
        monthText.setText(calLogic.getMonthText(month));

        GridPane griddy = new GridPane();
        griddy.setGridLinesVisible(true);
        counter = 0;


        //Creates column constraints for buttons to be in
        for (int i = 0; i < 7; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / 7);
            griddy.getColumnConstraints().add(colConst);
        }


        // creates row constraints for buttons to be in
        for (int i = 0; i < 5; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / 5);
            griddy.getRowConstraints().add(rowConst);
        }


        Button nextMonth = new Button("Next");


        // creates buttons and adds them to grid

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                counter += 1;
                Button button = new Button();

                button.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
                button.setPadding(Insets.EMPTY);


                if (counter > calLogic.getDays(month)) {
                    counter = 1;
                }
                button.setText("" + counter);
                int dayRight = counter;

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Stage s = (Stage) calGraphics.this.getScene().getWindow();
                        dayGraphics dayGraphic = null;
                        try {
                            dayGraphic = new dayGraphics(month, dayRight);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Scene dayScene = new Scene(dayGraphic, s.getWidth(), s.getHeight());
                        s.setScene(dayScene);
                        s.setTitle("" + month + "/" + dayRight);

                    }
                });

                days[i][j] = button;
                griddy.add(button, j, i);

            }
        }

        nextMonth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                month += 1;
                monthText.setText(calLogic.getMonthText(month));

                if(month > 12){
                    month = 1;
                }

                if(month < 1){
                    month = 12;
                }



                int day = 1;
                for(int i = 0; i < days.length; i++){
                    for(int j = 0; j < days[i].length; j++){
                        days[i][j].setText("" + day);
                        day++;

                        if(day > calLogic.getDays(month)){
                            day = 1;
                        }


                    }
                }

            }
        });

        Button backMonth = new Button("Back");
        backMonth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                month -= 1;
                monthText.setText(calLogic.getMonthText(month));

                if(month > 12){
                    month = 1;
                }

                if(month < 1){
                    month = 12;
                }


                int day = 1;
                for(int i = 0; i < days.length; i++){
                    for(int j = 0; j < days[i].length; j++){
                        days[i][j].setText("" + day);
                        day++;

                        if(day > calLogic.getDays(month)){
                            day = 1;
                        }


                    }
                }
            }
        });


        Button home = new Button("Home");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) calGraphics.this.getScene().getWindow();
                homeGraphics homeGraphic = new homeGraphics(s);
                Scene homeScene = new Scene(homeGraphic, s.getWidth(), s.getHeight());
                s.setScene(homeScene);
                s.setTitle("Home");
            }
        });


        HBox changeMonth = new HBox(backMonth, monthText, nextMonth );
        changeMonth.setSpacing(100);
        changeMonth.setAlignment(Pos.CENTER);
        this.setBottom(home);
        this.setTop(changeMonth);
        this.setCenter(griddy);

    }
}
