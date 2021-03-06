package com.charanajayworks.pubgguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.charanajayworks.pubgguide.GeneralDataActivity;
import com.charanajayworks.pubgguide.GridsActivity;
import com.charanajayworks.pubgguide.GuidesActivity;
import com.charanajayworks.pubgguide.LootsActivity;
import com.charanajayworks.pubgguide.MapDisplayAcitivity;
import com.charanajayworks.pubgguide.MapsActivity;
import com.charanajayworks.pubgguide.Models.CategoryModel;
import com.charanajayworks.pubgguide.QuestionsActivity;
import com.charanajayworks.pubgguide.R;
import com.charanajayworks.pubgguide.TipsActivity;
import com.charanajayworks.pubgguide.VideoActivity;
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
        Glide.with(mContext).load(categoryModel.getCategoryImage()).apply(RequestOptions.placeholderOf(R.drawable.loadingimage)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onLoadStarted(@Nullable Drawable resource) {
                holder.cardImageLayout.setBackground(resource);
            }


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
                Intent intent = null;
                switch (name.getText().toString()) {
                    case "WEAPONS":
                        intent = new Intent(mContext, GridsActivity.class);
                        intent.putExtra("gridtype", "basic");
                        break;
                    case "MAPS":
                        intent = new Intent(mContext, MapsActivity.class);
                        break;
                    case "COMBINATIONS":
                        intent = new Intent(mContext, GeneralDataActivity.class);
                        break;
                    case "PUBG 4 NOOBS":
                        intent = new Intent(mContext, GeneralDataActivity.class);
                        break;
                    case "MODES":
                        intent = new Intent(mContext, GeneralDataActivity.class);
                        break;
                    case "LOOTS":
                        intent = new Intent(mContext, LootsActivity.class);
                        break;
                    case "CONTROLS":
                        intent = new Intent(mContext, LootsActivity.class);
                        break;
                    case "GUIDES":
                        intent = new Intent(mContext, GuidesActivity.class);
                        break;
                    case "ERANGEL":
                    case "MIRAMAR":
                    case "SANHOK":
                        intent = new Intent(mContext, MapDisplayAcitivity.class);
                        intent.putExtra("mapName", name.getText().toString());
                        break;
                    case "TIPS":
                        intent = new Intent(mContext, TipsActivity.class);
                        break;
                    case "VIDEOS":
                        intent = new Intent(mContext, VideoActivity.class);
                        break;
                    case "ROYALE PASS":
                        intent = new Intent(mContext, GeneralDataActivity.class);
                        break;
                    case "QUESTIONS":
                        intent = new Intent(mContext, QuestionsActivity.class);
                        break;
                }
                mContext.startActivity(intent);
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
