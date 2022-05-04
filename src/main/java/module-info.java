module org.headroyce.srd.calorietracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.headroyce.srd.calorietracker to javafx.fxml;
    exports org.headroyce.srd.calorietracker;
}