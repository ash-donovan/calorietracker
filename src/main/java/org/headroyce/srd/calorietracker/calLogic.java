package org.headroyce.srd.calorietracker;

public class calLogic {

    public calLogic(){

    }

    public int getDays(int month){
        int maxMonth = 0;

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxMonth = 30;
        }
        else if (month == 2) {
            maxMonth = 28;
        }
        else {
            maxMonth = 31;
        }
    return maxMonth;
    }

    public String getMonthText(int month){
        String monthText = "";
        if(month == 1){
            monthText = "Janurary";
        }
        if (month == 2){
            monthText = "Febuary";
        }
        if (month == 3){
            monthText = "March";
        }
        if (month == 4){
            monthText = "April";
        }
        if (month == 5){
            monthText = "May";
        }
        if (month == 6){
            monthText = "June";
        }
        if (month == 7){
            monthText = "July";
        }
        if (month == 8){
            monthText = "August";
        }
        if (month == 9){
            monthText = "September";
        }
        if (month == 10){
            monthText = "October";
        }
        if (month == 11){
            monthText = "November";
        }
        if (month == 12){
            monthText = "December";
        }
        return monthText;
    }
}
