package com.example.nkicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DailyEntry extends AppCompatActivity {

    private ViewPager viewPager;
    private EntryFragmentCollectionAdapter adapter;
    int position;

    public static ArrayList<SingleEntry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_entry);

        Toolbar toolbar = findViewById(R.id.entryToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);

        entries = AllEntries.getEntries();

        viewPager = findViewById(R.id.pager);
        adapter= new EntryFragmentCollectionAdapter(getSupportFragmentManager(), entries,position);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(position);
    }

    @Override
    protected void onStart() {
        entries = AllEntries.getEntries();
        adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(entries.size());
        super.onStart();
    }

    public void newEntry(View view) {
        Intent myIntent = new Intent(this,
                NewEntry.class);
        startActivity(myIntent);
    }

    public void nextEntry(View view) {
        if (position < entries.size()){
            position+=1;
            viewPager.setCurrentItem(position);
        }
        else{
            Toast.makeText(this, "Last Entry", Toast.LENGTH_SHORT).show();
            position=entries.size()-1;
        }
    }

    public void prevEntry(View view) {
        if (position > 0){
            position-=1;
            viewPager.setCurrentItem(position);
        }
        else {
            Toast.makeText(this, "No new Entry", Toast.LENGTH_SHORT).show();
            position=0;
        }
    }
}
