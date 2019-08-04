package com.example.nkicalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.MyViewHolder> {
    private ArrayList<SingleEntry> mDataset;

    private OnEntryListener eOnEntryListener;

    public EntryAdapter(ArrayList<SingleEntry> myDataset, OnEntryListener onEntryListener) {
        this.mDataset = myDataset;
        this.eOnEntryListener=onEntryListener;
    }

    @Override
    public EntryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View entryView = inflater.inflate(R.layout.single_entry, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(entryView, eOnEntryListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EntryAdapter.MyViewHolder holder, int position) {
        SingleEntry entryItem = mDataset.get(position);

        holder.nkiValue.setText(entryItem.getNKITotal());
        holder.NKI.setText(R.string.nki);
        holder.date.setText(entryItem.getDate());

        //holder.parentLayout.setOnClickListener();
    }


    public interface OnEntryListener{
        void onEntryClick(int position);//will use in activity
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView NKI;
        public TextView nkiValue;
        public TextView date;
        public View parentLayout;
        public OnEntryListener onEntryListener;

        public MyViewHolder(View v, OnEntryListener onEntryListener) {
            super(v);
            parentLayout = v.findViewById(R.id.appCard);
            NKI = v.findViewById(R.id.NKI);
            nkiValue = v.findViewById(R.id.nkiValue);
            date = v.findViewById(R.id.date);
            this.onEntryListener= onEntryListener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            onEntryListener.onEntryClick(getAdapterPosition());
        }

    }

}

//in main activity
//implemet onEntryClicked interface
// inside the method add the intent to move into the next
//entries.getPositio

/**

 */