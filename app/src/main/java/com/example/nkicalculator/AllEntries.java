package com.example.nkicalculator;

import java.util.ArrayList;
import java.util.Date;

public class AllEntries {
    public static ArrayList<SingleEntry> entries;
    public static SingleEntry entry;

    public static ArrayList<SingleEntry> getEntries() {

        if (entries == null){
            entries = new ArrayList<SingleEntry>();
        }

        return entries;

    }

    public static void addEntry(SingleEntry entry){

        if (entries == null){
            entries = new ArrayList<SingleEntry>();
        }

        entries.add(entry);
    }

    public class SingleEntry {

        private String foodKJTotal;
        private String excerciseKJTotal;
        private Date date;



    }
}
