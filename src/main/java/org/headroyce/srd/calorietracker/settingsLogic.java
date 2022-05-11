package org.headroyce.srd.calorietracker;

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

    public void setGoal(int goal) {
        this.netGoal = goal;
        goalSet = true;
    }

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


        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                str.deleteCharAt(i);
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

        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                str.deleteCharAt(i);
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

        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                str.deleteCharAt(i);
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

    public boolean setSex(boolean s) {
        this.sex = s;        
        return true;
        
    }

    public void calculateRMR() {
        rmrSet = true;
        if (this.sex == true) {
            RMR = (int) (.5 + (10 * this.weight) + (6.25 * this.height) - (5 * this.age) - 161);
        }

        if (this.sex == false) {
            RMR = (int) (.5 + (10 * this.weight) + (6.25 * this.height) - (5 * this.age) + 5);
        }
        System.out.println(this.RMR);

    }

    public int getGoal() {
        return this.netGoal;
    }

    public boolean isGoalSet() {
        return goalSet;
    }

    public boolean isRmrSet() {
        return rmrSet;
    }


}
