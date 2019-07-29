package com.example.nkicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class NewEntry extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public Spinner food_1;
    public Spinner food_2;
    public Spinner excercise_1;
    public Spinner excercise_2;
    public TextView date;
    public TextView totalNKI;
    public EditText foodKJ_1;
    public EditText foodKJ_2;
    public EditText excerciseKJ_1;
    public EditText excerciseKJ_2;

    int excerciseTotal;
    int foodTotal;
    int NKITotal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        date = findViewById(R.id.date);

        food_1 = findViewById(R.id.foodCateg1);
        food_2 = findViewById(R.id.foodCateg2);
        foodKJ_1 = findViewById(R.id.foodCal1);
        foodKJ_2 = findViewById(R.id.foodCal2);

        excerciseKJ_1 = findViewById(R.id.excerciseCal_1);
        excerciseKJ_2 = findViewById(R.id.excerciseCal_2);
        excercise_1 = findViewById(R.id.excerciseCateg_1);
        excercise_2 = findViewById(R.id.excerciseCateg_2);
        totalNKI = findViewById(R.id.total);

        createSpinnerAdapters();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

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
        calculateNKI(this);
    }

    public void calculateNKI(NewEntry v){

        String food1 = foodKJ_1.getText().toString();
        String food2 = foodKJ_2.getText().toString();
        String excer1 = excerciseKJ_1.getText().toString();
        String excer2 =excerciseKJ_1.getText().toString();

        if (food1.isEmpty() || excer1.isEmpty() || food2.isEmpty() || excer2.isEmpty()){

            Toast.makeText(this, "Incomplete", Toast.LENGTH_SHORT).show();

        }

        else{
            excerciseTotal=  Integer.parseInt(excer1)+Integer.parseInt(excer2);
            foodTotal= Integer.parseInt(food1)+Integer.parseInt(food2);
            NKITotal= foodTotal- excerciseTotal;
            totalNKI.setText(""+NKITotal);
        }
    }

    public void saveEntry(View view){

        calculateNKI(this);

        SingleEntry entry = new SingleEntry(foodTotal,excerciseTotal,date.getText().toString());
        AllEntries.addEntry(entry);

        Toast.makeText(this, "Entry saved!", Toast.LENGTH_SHORT).show();

        finish();
    }
}
