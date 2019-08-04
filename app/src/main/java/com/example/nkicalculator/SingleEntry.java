package com.example.nkicalculator;


import android.os.Parcel;
import android.os.Parcelable;

public class SingleEntry implements Parcelable {

    private String foodKJTotal;
    private String excerciseKJTotal;
    public String date;



    public SingleEntry( int foodKJTotal, int excerciseKJTotal, String date) {
        this.foodKJTotal = String.valueOf(foodKJTotal);
        this.excerciseKJTotal = String.valueOf(excerciseKJTotal);
        this.date = date;
    }

    protected SingleEntry(Parcel in) {
        foodKJTotal = in.readString();
        excerciseKJTotal = in.readString();
        date = in.readString();
    }

    public static final Creator<SingleEntry> CREATOR = new Creator<SingleEntry>() {
        @Override
        public SingleEntry createFromParcel(Parcel in) {
            return new SingleEntry(in);
        }

        @Override
        public SingleEntry[] newArray(int size) {
            return new SingleEntry[size];
        }
    };

    public String getDate(){
        return date;
    }

    public String getNKITotal(){
        int KJValue = Integer.parseInt(foodKJTotal) -
                Integer.parseInt(excerciseKJTotal);

        return String.valueOf(KJValue);
    }

    public String getExcerciseKJTotal() {
        return excerciseKJTotal;
    }

    public String getFoodKJTotal() {
        return foodKJTotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foodKJTotal);
        parcel.writeString(excerciseKJTotal);
        parcel.writeString(date);
    }
}
