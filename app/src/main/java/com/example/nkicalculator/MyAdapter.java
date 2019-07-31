package com.example.nkicalculator;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import static com.example.nkicalculator.R.string.nki;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<SingleEntry> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView NKI;
        public TextView nkiValue;
        public TextView date;

        public MyViewHolder(TextView v) {
            super(v);
            NKI = v.findViewById(R.id.NKI);
            nkiValue = v.findViewById(R.id.nkiValue);
            date = v.findViewById(R.id.date);
        }
    }

    public MyAdapter(ArrayList<SingleEntry> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_entry, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SingleEntry entryItem = mDataset.get(position);

        holder.nkiValue.setText(entryItem.getNKITotal());
        holder.NKI.setText(R.string.nki);
        holder.date.setText(entryItem.getDate());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}