package com.example.nkicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public SharedPreferences entryEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.entryToolbar);
        setSupportActionBar(toolbar);

    }

    public void newEntry(View view) {
        Intent myIntent = new Intent(MainActivity.this,
                NewEntry.class);
        startActivity(myIntent);
    }

    public void updateEntries(){

    }

    public void onEntrySelected(View v){

    }
}
