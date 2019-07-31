package com.example.nkicalculator;


public class SingleEntry {

    private String foodKJTotal;
    private String excerciseKJTotal;
    public String date;

    public SingleEntry(int foodKJTotal, int excerciseKJTotal, String date) {
        this.foodKJTotal = String.valueOf(foodKJTotal);
        this.excerciseKJTotal = String.valueOf(excerciseKJTotal);
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public String getNKITotal(){
        int KJValue = Integer.parseInt(foodKJTotal) -
                Integer.parseInt(excerciseKJTotal);

        return String.valueOf(KJValue);
    }
}
