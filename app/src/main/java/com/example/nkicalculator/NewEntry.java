package com.example.nkicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class NewEntry extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner food_1;
    private Spinner food_2;
    private Spinner excercise_1;
    private Spinner excercise_2;
    private TextView date;
    private TextView totalNKI;
    private TextView totalFood;
    private TextView totalExercise;
    private EditText foodKJ_1;
    private EditText foodKJ_2;
    private EditText excerciseKJ_1;
    private EditText excerciseKJ_2;

    int excerciseTotal;
    int foodTotal;
    int NKITotal ;
    public ArrayList<String> food;
    public ArrayList<String> excercise;

    public static ArrayList<SingleEntry> entries;

    private boolean calculated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        Toolbar toolbar = findViewById(R.id.entryToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        date = findViewById(R.id.select_date);

        food_1 = findViewById(R.id.foodCateg1);
        food_2 = findViewById(R.id.foodCateg2);
        foodKJ_1 = findViewById(R.id.foodCal1);
        foodKJ_2 = findViewById(R.id.foodCal2);

        excerciseKJ_1 = findViewById(R.id.excerciseCal_1);
        excerciseKJ_2 = findViewById(R.id.excerciseCal_2);
        excercise_1 = findViewById(R.id.excerciseCateg_1);
        excercise_2 = findViewById(R.id.excerciseCateg_2);

        totalNKI = findViewById(R.id.nkiTotal);
        totalFood = findViewById(R.id.foodTotal);
        totalExercise = findViewById(R.id.excerciseTotal);

        createSpinnerAdapters();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        food = new ArrayList<String>();
        excercise = new ArrayList<String>();

    }
    public void createSpinnerAdapters(){

        ArrayAdapter<CharSequence> F_adapter = ArrayAdapter.createFromResource(this,
                R.array.FoodCategories, android.R.layout.simple_spinner_item);
        F_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        food_1.setAdapter(F_adapter);
        food_2.setAdapter(F_adapter);

        ArrayAdapter<CharSequence> E_adapter = ArrayAdapter.createFromResource(this,
                R.array.ExerciseCategories, android.R.layout.simple_spinner_item);
        E_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        excercise_1.setAdapter(E_adapter);
        excercise_2.setAdapter(E_adapter);
    }
    private void showDateDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog( this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String entryDate =day+"/"+month+"/"+year;
        date.setText(entryDate);


    }

    public void calculateNKI(View v){
        calculateNKI();
    }

    public void calculateNKI(){

        final String food1 = foodKJ_1.getText().toString();
        final String food2 = foodKJ_2.getText().toString();
        final String excer1 = excerciseKJ_1.getText().toString();
        final String excer2 =excerciseKJ_2.getText().toString();

        if (food1.isEmpty() || excer1.isEmpty() || food2.isEmpty() || excer2.isEmpty())
            Toast.makeText(this, getString(R.string.fill), Toast.LENGTH_SHORT).show();

        else {
                    excerciseTotal = Integer.parseInt(excer1) + Integer.parseInt(excer2);
                    foodTotal = Integer.parseInt(food1) + Integer.parseInt(food2);
                    NKITotal = foodTotal - excerciseTotal;

                    totalFood.setText("" + foodTotal);
                    totalExercise.setText("" + excerciseTotal);
                    totalNKI.setText("" + NKITotal);
                    calculated = true;


        }

    }

    public void saveEntry(View view){

        calculateNKI();

        if (calculated = true && food_1.getSelectedItemPosition()!=0 && food_2.getSelectedItemPosition()!=0 &&
                excercise_1.getSelectedItemPosition()!=0 && excercise_2.getSelectedItemPosition()!=0){


            SingleEntry entry = new SingleEntry(foodTotal, excerciseTotal,date.getText().toString());
            AllEntries.addEntry(entry);

            Toast.makeText(this, "Entry saved!", Toast.LENGTH_SHORT).show();
            finish();
        }

        else{
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    public  void cancel(View view){
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(this,
                MainActivity.class);
        startActivity(myIntent);
    }
}
