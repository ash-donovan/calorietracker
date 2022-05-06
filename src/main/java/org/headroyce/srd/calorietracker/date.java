package org.headroyce.srd.calorietracker;

public class date {
    int month;
    int day;
    int calsIn;
    int calsOut;

    public date(int passMonth, int passDay){
        this.month = passMonth;
        this.day = passDay;


    }

   public boolean setCalsIn(int cals){
        if(cals > 0) {
            this.calsIn = cals;
            return true;
        }
        return false;
    }

    public int getCalsIn(){
        return this.calsIn;
    }

    public boolean setCalsOut(int cals){
        if (cals > 0){
            this.calsOut = cals;
            return true;
        }
        return false;
    }

    public int getCalsOut(){
        return this.calsOut;
    }



}
