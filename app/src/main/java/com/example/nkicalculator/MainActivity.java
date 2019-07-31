package com.example.nkicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public SharedPreferences entryEditor;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static ArrayList<SingleEntry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.entryToolbar);
        setSupportActionBar(toolbar);

        buildRecyclerView();
        updateEntries();

    }

//    public void createEntryAdapters(){
//
//    }

    public void newEntry(View view) {
        Intent myIntent = new Intent(MainActivity.this,
                NewEntry.class);
        startActivity(myIntent);
    }

    public void updateEntries(){

        final int position = entries.size()-1;

        new Thread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyItemInserted(position);
            }
        }).start();
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.entryView);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(entries);
        mRecyclerView.setAdapter(mAdapter);
    }

//    public void onEntrySelected(View v){
//
//    }
}
