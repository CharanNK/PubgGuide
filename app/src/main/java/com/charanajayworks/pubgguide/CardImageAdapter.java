package com.charanajayworks.pubgguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardImageAdapter extends RecyclerView.Adapter<CardImageAdapter.ViewHolder> {
    private ArrayList<String> weaponImages;

    public CardImageAdapter(ArrayList<String> weaponImages, Context mContext) {
        this.weaponImages = weaponImages;
        this.mContext = mContext;
    }

    private Context mContext;
    @NonNull
    @Override
    public CardImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View weaponCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_card_layout,parent,false);
        return new ViewHolder(weaponCardView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardImageAdapter.ViewHolder holder, int position) {
        String imageUrl = weaponImages.get(position);
        RequestOptions requestOptions = new RequestOptions().fitCenter().override(150,150).placeholder(R.drawable.reloadduration);
        Glide.with(mContext).load(imageUrl).apply(requestOptions).into(holder.weaponimage);
    }

    @Override
    public int getItemCount() {
        return weaponImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView weaponimage;
        public ViewHolder(View itemView) {
            super(itemView);
            weaponimage = itemView.findViewById(R.id.weaponimage);
        }
    }
}
