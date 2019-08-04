package com.example.nkicalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class EntryFragmentCollectionAdapter extends FragmentStatePagerAdapter {

    public ArrayList<SingleEntry> entries;
    private int position;

    public EntryFragmentCollectionAdapter(FragmentManager fm, ArrayList<SingleEntry> entries, int pos) {
        super(fm);
        this.entries=entries;
        position=pos;
    }

    @Override
    public Fragment getItem(int position) {
        DiaryFragment blankFragment = new DiaryFragment();
        Bundle bundle = new Bundle();

        bundle.putString("date", entries.get(position).getDate());
        bundle.putString("food",entries.get(position).getFoodKJTotal());
        bundle.putString("exercise",entries.get(position).getExcerciseKJTotal());
        bundle.putString("nki",entries.get(position).getNKITotal());

        blankFragment.setArguments(bundle);

        return blankFragment;
    }

    @Override
    public int getCount() {
        return entries.size();
    }
}
