package com.example.nkicalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EntryAdapter.OnEntryListener {

    public SharedPreferences.Editor entryEditor;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView totalView;

    public static ArrayList<SingleEntry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.entryToolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPrefs = getSharedPreferences("entry_settings", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("entry list", null);

        Type type = new TypeToken<ArrayList<SingleEntry>>() {}.getType();
        entries = gson.fromJson(json, type);

        totalView = findViewById(R.id.calcAvg);

        getEntries();
        buildRecyclerView();
    }

    public void saveEntries(){

        entries=AllEntries.getEntries();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPrefs = getSharedPreferences("entry_settings", Activity.MODE_PRIVATE);
                entryEditor = sharedPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(entries);
                entryEditor.putString("entry list", json);
                entryEditor.apply();
            }
        }).start();
    }

    public void getEntries(){ //thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPrefs = getSharedPreferences("entry_settings", Activity.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = sharedPrefs.getString("entry list", null);

                Type type = new TypeToken<ArrayList<SingleEntry>>() {}.getType();
                entries = gson.fromJson(json, type);
            }
        }).start();

        if (entries == null) {
            entries = new ArrayList<>();
        }

        AllEntries.entries = entries;
    }
    public void newEntry(View view) {
        Intent myIntent = new Intent(this,
                NewEntry.class);
        startActivity(myIntent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        getEntries();
        updateEntries();
        displayTotal();
        super.onStart();
    }

//    @Override
//    protected void onPause() {
//        saveEntries();
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        getEntries();
//        updateEntries();
//        displayTotal();
//        super.onResume();
//    }


    @Override
    protected void onStop() {
        saveEntries();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        saveEntries();
        super.onDestroy();
    }

    @Override
    public void finish() {
        saveEntries();
        super.finish();
    }

    public void updateEntries(){

        //get all entries
        entries = AllEntries.getEntries();

        //if there has been no update do nothing else add to adapter
        if (entries.size()>mAdapter.getItemCount())
            mAdapter.notifyItemInserted(0);
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.entryView);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new EntryAdapter(entries, this);
        mRecyclerView.setAdapter(mAdapter);

        //mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEntryClick(int position) {
        Intent intent = new Intent(this, DailyEntry.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    public void displayTotal(){
        int total= 0;
        for(int i=0; i < entries.size(); i++)
            total+= Integer.parseInt(entries.get(i).getNKITotal());
        if (total!=0)
            total=total/entries.size();
        totalView.setText(""+total);
    }
}
