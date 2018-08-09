package com.charanajayworks.pubgguide.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charanajayworks.pubgguide.Models.GuidesDataModel;
import com.charanajayworks.pubgguide.R;

import java.util.List;

public class GuidesAdapter extends RecyclerView.Adapter<GuidesAdapter.ViewHolder> {
    private Context mContext;

    public GuidesAdapter(Context mContext, List<GuidesDataModel> guidesList) {
        this.mContext = mContext;
        this.guidesList = guidesList;
    }

    private List<GuidesDataModel> guidesList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_cardview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final GuidesDataModel guidesDataModel = guidesList.get(position);

        holder.guideTitle.setText(guidesDataModel.getGuide_title());
        holder.guideDesc.setText(guidesDataModel.getGuide_desc());
    }

    @Override
    public int getItemCount() {
        return guidesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView guideTitle,guideDesc;
        CardView guideCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            guideTitle = itemView.findViewById(R.id.guide_title);
            guideDesc = itemView.findViewById(R.id.guide_desc);

            guideCardView = itemView.findViewById(R.id.guide_cv);
        }
    }
}
