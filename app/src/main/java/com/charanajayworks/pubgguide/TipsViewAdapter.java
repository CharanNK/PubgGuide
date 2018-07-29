package com.charanajayworks.pubgguide;

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
    Typeface typeface;
    Shimmer shimmer;
    public TipsViewAdapter (ArrayList<String> myValues){
        this.myValues= myValues;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_cardview, parent, false);
            typeface = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/ttlakes.ttf");
        return new MyViewHolder(listItem);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.myTextView.setText(myValues.get(position));
        holder.myTextView.setTypeface(typeface);
        holder.myCardView.setBackgroundResource(R.drawable.pubweaponvector);
        holder.arrowTextView.setTypeface(typeface);
        shimmer = new Shimmer();
        shimmer.setDuration(500);
        shimmer.start(holder.arrowTextView);
        Drawable cardViewBackground = holder.myCardView.getBackground();
        cardViewBackground.setAlpha(150);
    }


    @Override
    public int getItemCount() {
        return myValues.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView myTextView;
        ShimmerTextView arrowTextView;
        private CardView myCardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView)itemView.findViewById(R.id.category_name);
            myCardView = itemView.findViewById(R.id.category_cardview);
            arrowTextView = itemView.findViewById(R.id.arrow_text);
        }
    }
}
