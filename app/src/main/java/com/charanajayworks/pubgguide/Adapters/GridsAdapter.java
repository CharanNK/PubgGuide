package com.charanajayworks.pubgguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.charanajayworks.pubgguide.GridsActivity;
import com.charanajayworks.pubgguide.Models.GridLayoutModel;
import com.charanajayworks.pubgguide.R;
import com.charanajayworks.pubgguide.ThrowablesActivity;
import com.charanajayworks.pubgguide.WeaponsActivity;

import java.util.List;

public class GridsAdapter extends RecyclerView.Adapter<GridsAdapter.ViewHolder> {
    private Context mContext;
    private List<GridLayoutModel> gridLayoutModelList;

    public GridsAdapter(Context mContext, List<GridLayoutModel> gridLayoutModelList) {
        this.mContext = mContext;
        this.gridLayoutModelList = gridLayoutModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final GridLayoutModel gridLayoutModel = gridLayoutModelList.get(position);

        holder.gridLayoutText.setText(gridLayoutModel.getGridName());
        Glide.with(mContext).load(gridLayoutModel.getImageLink()).into(holder.gridLayoutImage);

        holder.gridCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView gridName = view.findViewById(R.id.gridTextView);
                Log.d("gridName : ", gridName.getText().toString());

                Intent intent = null;

                switch (gridName.getText().toString()) {
                    case "Bows/Guns":
                        intent = new Intent(mContext, GridsActivity.class);
                        intent.putExtra("gridtype", "guns");
                        mContext.startActivity(intent);
                        break;
                    case "Attachments":
                        intent = new Intent(mContext, GridsActivity.class);
                        intent.putExtra("gridtype", "attachment");
                        mContext.startActivity(intent);
                        break;
                    case "Assault Rifles":
                        intent = new Intent(mContext, WeaponsActivity.class);
                        intent.putExtra("weaponType","Assault Rifles");
                        mContext.startActivity(intent);
                        break;
                    case "Throwables":
                        intent = new Intent(mContext, ThrowablesActivity.class);
                        mContext.startActivity(intent);
                        break;
                    default:
                        intent = new Intent(mContext, WeaponsActivity.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gridLayoutModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView gridLayoutText;
        private ImageView gridLayoutImage;
        private CardView gridCardView;

        public ViewHolder(View itemView) {
            super(itemView);

            gridLayoutText = itemView.findViewById(R.id.gridTextView);
            gridLayoutImage = itemView.findViewById(R.id.gridImageView);
            gridCardView = itemView.findViewById(R.id.gridCardLayout);
        }
    }
}
