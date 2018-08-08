package com.charanajayworks.pubgguide;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;

public class TipsViewAdapter extends RecyclerView.Adapter<TipsViewAdapter.MyViewHolder> {
    public ArrayList<String> myValues;
    Context mContext;
    Typeface typeface;
    Shimmer shimmer;
    public TipsViewAdapter (Context context,ArrayList<String> myValues){
        mContext = context;
        this.myValues= myValues;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_cardview, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.myTextView.setText(myValues.get(position));
    }


    @Override
    public int getItemCount() {
        return myValues.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView myTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.text_cardview);
        }
    }
}
