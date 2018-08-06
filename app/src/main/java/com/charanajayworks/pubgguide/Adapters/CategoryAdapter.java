package com.charanajayworks.pubgguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.charanajayworks.pubgguide.GeneralDataActivity;
import com.charanajayworks.pubgguide.MapDisplayAcitivity;
import com.charanajayworks.pubgguide.MapsActivity;
import com.charanajayworks.pubgguide.Models.CategoryModel;
import com.charanajayworks.pubgguide.R;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context mContext;
    private List<CategoryModel> categoryList;
    Shimmer shimmer;
    Typeface typeface;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryList) {
        mContext = context;
        this.categoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_cardview, parent, false);
        typeface = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/ttlakes.ttf");
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CategoryModel categoryModel = categoryList.get(position);

        holder.categoryName.setText(categoryModel.getCategoryName());
        holder.categoryName.setTypeface(typeface);
        Glide.with(mContext).load(categoryModel.getCategoryImage()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    holder.cardImageLayout.setBackground(resource);
                    Drawable cardViewBackground = holder.cardImageLayout.getBackground();
                    cardViewBackground.setColorFilter(0x5F000000, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });
        //holder.cardImageLayout.setBackgroundResource(categoryModel.getCategoryImage());
        holder.arrowTextView.setTypeface(typeface);
        holder.categoryDesc.setTypeface(typeface);
        holder.categoryDesc.setText(categoryModel.getCategoryDesc());
        shimmer = new Shimmer();
        shimmer.setDuration(2500);
        shimmer.start(holder.arrowTextView);

        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CategoryClick:", "clicked");
                TextView name = view.findViewById(R.id.category_name);
                Log.d("clickeNAme:", name.getText().toString());
                if (name.getText().toString().equals("MAPS")) {
                    Intent intent = new Intent(mContext, MapsActivity.class);
                    mContext.startActivity(intent);
                }
                if (name.getText().toString().equals("ERANGEL")||name.getText().toString().equals("MIRAMAR")||name.getText().toString().equals("SANHOK")) {
                    Intent intent = new Intent(mContext,MapDisplayAcitivity.class);
                    intent.putExtra("mapName",name.getText().toString());
                    mContext.startActivity(intent);
                }
                if(name.getText().toString().equals("COMBINATIONS")){
                    Intent intent = new Intent(mContext, GeneralDataActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });

        holder.categoryCardView.setCardElevation(5);
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName, categoryDesc;
        public ShimmerTextView arrowTextView;
        public CardView categoryCardView;
        public LinearLayout cardImageLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.category_name);
            categoryCardView = itemView.findViewById(R.id.category_cardview);
            arrowTextView = itemView.findViewById(R.id.arrow_text);
            cardImageLayout = itemView.findViewById(R.id.cardImageLayout);
            categoryDesc = itemView.findViewById(R.id.category_desc);
        }
    }
}
