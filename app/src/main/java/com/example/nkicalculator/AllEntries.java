package com.example.nkicalculator;

import java.util.ArrayList;

public class AllEntries {
    public static ArrayList<SingleEntry> entries;

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

    public static int size() {
        if (entries == null) {
            entries = new ArrayList<SingleEntry>();
        }

        return entries.size();
    }

    public static void clearEntries() {
        if (entries != null) {
            entries.clear();
        }
    }
}
