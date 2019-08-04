package com.example.nkicalculator;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {

    private TextView date;
    private TextView food;
    private TextView exercise;
    private TextView nki;

    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_diary, container, false);

        date = v.findViewById(R.id.diaryDate);
        food = v.findViewById(R.id.foodValue);
        exercise = v.findViewById(R.id.exerciseValue);
        nki = v.findViewById(R.id.nKIValue);

        String diary_date = getArguments().getString("date");
        String diary_foodValue = getArguments().getString("food");
        String diary_exerciseValue = getArguments().getString("exercise");
        String diary_nkiValue = getArguments().getString("nki");

        date.setText(diary_date);
        food.setText(diary_foodValue);
        exercise.setText(diary_exerciseValue);
        nki.setText(diary_nkiValue);

        return v;
    }

}
