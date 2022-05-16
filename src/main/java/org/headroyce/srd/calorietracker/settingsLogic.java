package org.headroyce.srd.calorietracker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class settingsLogic {

    private int dailyCals;
    private boolean goalSet;
    private int netGoal;
    private boolean rmrSet;
    private int RMR;

    private boolean imperial;

    private double weight;
    private double height;
    private double age;
//    female = true, male = false
    private boolean sex;

    /**
     * creates an instance of settingsLogic
     */
    public settingsLogic() {
        imperial = false;

        goalSet = false;
        rmrSet = false;

        dailyCals = 2000;
        netGoal = 0;
        RMR = 2000;

        weight = 180;
        height = 67;
        age = 40;
    }

    /**
     * sets the calorie goal of the user
     * @param goal the goal (in calories) that the user set
     *             negative values = weight loss
     *             positive values = weight gain
     *             0 = weight maintenance
     */
    public void setGoal(int goal) {
        this.netGoal = goal;
        goalSet = true;
    }

    /**
     * sets the value of imperial
     * @param n true if using imperial measurements, false otherwise
     */
    public void setImperial(boolean n) {
        this.imperial = n;
    }

    /**
     * returns the user's calculated RMR, or default if they haven't set it yet
     * @return RMR
     */
    public int getRMR() {
        return this.RMR;
    }

    /**
     * returns the value of weight or a default weight of 180 if it hasn't been set
     * @return weight of user
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * returns the user's height or a default height of 67
     * @return weight of user
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * returns age of user
     * @return age of user or default age of 40
     */
    public double getAge() {
        return this.age;
    }

    /**
     * returns user's sex
     * @return true if female, false if male
     */
    public boolean getSex() {
        return this.sex;
    }

    /**
     * sets the height of the user based on their input
     * height cannot be negative, or have letters, symbols, or punctuation
     * @param s height input from RMR Calculator
     * @return true if the height can be set, false otherwise
     */
    public boolean setHeight(String s){

        if (s == null || s == "") {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4'
                    || s.charAt(i) == '5' || s.charAt(i) == '6'|| s.charAt(i) == '7'
                    || s.charAt(i) == '8' || s.charAt(i) == '9' || s.charAt(i) == '.' || s.charAt(i) == ' ')) {
                return false;
            }
        }

        int stringLength = s.length();

        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < stringLength; i++) {
            if (s.charAt(i) == ' ') {
                str.deleteCharAt(i);
                stringLength--;
            }
        }
        String ns = str.toString();



        double a = Double.parseDouble(ns);

        if (a < 1) {
            return false;
        }

        if (imperial == true) {
            this.height = a * 2.54;
        }

        else {
            this.height = a;
        }
        return true;
    }

    /**
     * sets the weight of the user based on their input
     * height cannot be negative, or have letters, symbols, or punctuation
     * @param s weight input from RMR Calculator
     * @return true if the weight can be set, false otherwise
     */
    public boolean setWeight(String s){
        if (s == null || s == "") {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4'
                    || s.charAt(i) == '5' || s.charAt(i) == '6'|| s.charAt(i) == '7'
                    || s.charAt(i) == '8' || s.charAt(i) == '9' || s.charAt(i) == '.' || s.charAt(i) == ' ')) {
                return false;
            }
        }

        int stringLength = s.length();
        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < stringLength; i++) {
            if (s.charAt(i) == ' ') {
                str.deleteCharAt(i);
                stringLength--;
            }
        }
        String ns = str.toString();

        double a = Double.parseDouble(ns);

        if (a < 1) {
            return false;
        }

        if (imperial == true) {
            this.weight = a * 0.45359237;
        }

        else {
            this.weight = a;
        }
        return true;

    }

    /**
     * sets the age of the user based on their input
     * age cannot be negative, or have letters, symbols, or punctuation
     * @param s age input from RMR Calculator
     * @return true if the weight can be set, false otherwise
     */
    public boolean setAge(String s){
        if (s == null || s == "") {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4'
                    || s.charAt(i) == '5' || s.charAt(i) == '6'|| s.charAt(i) == '7'
                    || s.charAt(i) == '8' || s.charAt(i) == '9' || s.charAt(i) == '.' || s.charAt(i) == ' ')) {
                return false;
            }
        }

        int stringLength = s.length();
        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < stringLength; i++) {
            if (s.charAt(i) == ' ') {
                str.deleteCharAt(i);
                stringLength--;
            }
        }
        String ns = str.toString();

        double a = Double.parseDouble(ns);

        if (a < 1) {
            return false;
        }

        this.age = a;



        return true;
    }

    /**
     * sets the sex of the user based on their input
     * sex is either male or female
     * @param s sex input from RMR Calculator (female = true, male = false)
     * @return true if the weight can be set, false otherwise
     */
    public boolean setSex(boolean s) {
        this.sex = s;        
        return true;
        
    }

    /**
     * calculates RMR of the user
     */
    public void calculateRMR() {
        rmrSet = true;
        if (this.sex == true) {
            RMR = (int) (.5 + (10 * this.weight) + (6.25 * this.height) - (5 * this.age) - 161);
        }

        if (this.sex == false) {
            RMR = (int) (.5 + (10 * this.weight) + (6.25 * this.height) - (5 * this.age) + 5);
        }


    }

    /**
     * returns value of goal attribute
     * @return the calorie
     */
    public int getGoal() {
        return this.netGoal;
    }

    /**
     * returns whether or not user's goal has been set
     * @return true if goal has been set, false otherwise
     */
    public boolean isGoalSet() {
        return goalSet;
    }

    /**
     * returns whether or not user's RMR has been set
     * @return true if RMR has been set, false otherwise
     */
    public boolean isRmrSet() {
        return rmrSet;
    }

    /**
     * returns dailyCals, or how many cals user should eat a day
     * according to their goal and RMR
     * @return dailyCals
     */
    public int getDailyCals() {
        return this.dailyCals;
    }


}
