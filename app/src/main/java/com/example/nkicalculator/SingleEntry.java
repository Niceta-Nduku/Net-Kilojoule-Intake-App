package com.example.nkicalculator;


public class SingleEntry {

    private String foodKJTotal;
    private String excerciseKJTotal;
    private String date;

    public SingleEntry(int foodKJTotal, int excerciseKJTotal, String date) {
        this.foodKJTotal = String.valueOf(foodKJTotal);
        this.excerciseKJTotal = String.valueOf(excerciseKJTotal);
        this.date = date;
    }
}
