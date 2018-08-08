package com.charanajayworks.pubgguide.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.charanajayworks.pubgguide.Models.GeneralDataModel;
import com.charanajayworks.pubgguide.R;

import java.util.List;

public class GeneralDataAdapter extends RecyclerView.Adapter<GeneralDataAdapter.ViewHolder> {
    private Context mContext;
    private List<GeneralDataModel> generalDataList;

    public GeneralDataAdapter(Context mContext, List<GeneralDataModel> generalDataList) {
        this.mContext = mContext;
        this.generalDataList = generalDataList;
    }

    @NonNull
    @Override
    public GeneralDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.genearaldataitemview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralDataAdapter.ViewHolder holder, int position) {
        final GeneralDataModel dataModel = generalDataList.get(position);

        if(dataModel.getDataTitle()==null)
            holder.generalDataTitle.setVisibility(View.GONE);
        else
            holder.generalDataTitle.setText(dataModel.getDataTitle());

        if(dataModel.getLootQuality()==null){
            holder.lootsAttributeLayout.setVisibility(View.GONE);
        }else {
            holder.lootQuality.setText(dataModel.getLootQuality());
            holder.lootQuantity.setText(dataModel.getLootQuantity());
            holder.lootRisk.setText(dataModel.getLootRisk());
        }

        Glide.with(mContext).load(dataModel.getImageLink()).into(holder.generalImageView);

        String dataDescription = dataModel.getDataDescription();
        dataDescription = dataDescription.replaceAll("\\*\\*","\\\n");
        holder.generalDataDesc.setText(dataDescription);
    }

    @Override
    public int getItemCount() {
        return generalDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView generalDataTitle,generalDataDesc,lootQuality,lootQuantity,lootRisk;
        ImageView generalImageView;
        LinearLayout lootsAttributeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            generalDataTitle = itemView.findViewById(R.id.generalDataTitle);
            generalDataDesc = itemView.findViewById(R.id.generalDataDesc);
            generalImageView = itemView.findViewById(R.id.generalImageView);

            lootQuality = itemView.findViewById(R.id.loot_quality_text);
            lootQuantity = itemView.findViewById(R.id.loot_quantity_text);
            lootRisk = itemView.findViewById(R.id.loot_risk_text);

            lootsAttributeLayout = itemView.findViewById(R.id.lootsattributelayout);
        }
    }
}
