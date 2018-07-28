package com.charanajayworks.pubgguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.charanajayworks.pubgguide.Adapters.loots_adapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import static android.content.Context.MODE_PRIVATE;

public class LootsFragment extends Fragment {
    RecyclerView loots_list;
    DBHelper databaseHelper;
    Button erangelLootButton, miramarLootButton;
    ArrayList<loots_adapter> lootsList;
    private SharedPreferences sharedPreferences;
    private boolean isAdEnabled;

    private InterstitialAd interstitialAd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loots_layout,container,false);

        loots_list = view.findViewById(R.id.display_loots);

        sharedPreferences = this.getActivity().getSharedPreferences("PUBG_GUIDE", MODE_PRIVATE);
        isAdEnabled = sharedPreferences.getBoolean("isadenabled", false);

        if(Math.random()<0.2&&isAdEnabled)
            showAd();

        erangelLootButton = view.findViewById(R.id.erangel_loot_button);
        miramarLootButton = view.findViewById(R.id.miramar_loot_button);

        erangelLootButton.setBackgroundResource(R.drawable.grey_button);
        erangelLootButton.setTextColor(Color.parseColor("#000000"));

        miramarLootButton.setBackgroundResource(R.drawable.blue_button);
        miramarLootButton.setTextColor(Color.parseColor("#ffffff"));

        miramarLootButton.setBackgroundResource(R.drawable.blue_button);

        databaseHelper = new DBHelper(getActivity().getApplicationContext());
        try {
            databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lootsList = new ArrayList<>();

        try{
            Cursor cursor = databaseHelper.getErangelLootDetails();

            while (cursor.moveToNext()){
                loots_adapter adapter = new loots_adapter(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4));
                lootsList.add(adapter);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        final lootsAdapterClass adapter = new lootsAdapterClass(getContext(),lootsList);
        RecyclerView myView =  (RecyclerView)view.findViewById(R.id.display_loots);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);


        erangelLootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                erangelLootButton.setBackgroundResource(R.drawable.grey_button);
                erangelLootButton.setTextColor(Color.parseColor("#000000"));
                miramarLootButton.setBackgroundResource(R.drawable.blue_button);
                miramarLootButton.setTextColor(Color.parseColor("#FFFFFF"));
                lootsList.clear();
                try{
                    Cursor cursor = databaseHelper.getErangelLootDetails();

                    while (cursor.moveToNext()){
                        loots_adapter adapter = new loots_adapter(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                                cursor.getString(3),cursor.getString(4));
                        lootsList.add(adapter);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                adapter.notifyDataSetChanged();
            }
        });

        miramarLootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                erangelLootButton.setBackgroundResource(R.drawable.blue_button);
                erangelLootButton.setTextColor(Color.parseColor("#FFFFFF"));
                miramarLootButton.setBackgroundResource(R.drawable.grey_button);
                miramarLootButton.setTextColor(Color.parseColor("#000000"));
                lootsList.clear();
                try{
                    Cursor cursor = databaseHelper.getMiramarLootDetails();

                    while (cursor.moveToNext()){
                        loots_adapter adapter = new loots_adapter(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                                cursor.getString(3),cursor.getString(4));
                        lootsList.add(adapter);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                adapter.notifyDataSetChanged();
            }
        });



        return view;
    }

    public void showAd() {
        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-3894268392664867/7965203868");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        Log.d("ADMOB","showAdCalled");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (interstitialAd.isLoaded())
                    interstitialAd.show();
            }
        }, 10000);
    }

    public  class lootsAdapterClass extends RecyclerView.Adapter<lootsAdapterClass.MyViewHolder>{
        private Context mContext;
        private List<loots_adapter> lootList;
        String redColor = "#FF0000";
        String orangeColor = "#ff8c00";
        String greenColor = "#008000";

        public lootsAdapterClass(Context mContext,List<loots_adapter> lootList){
            this.mContext = mContext;
            this.lootList = lootList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loot_cardview,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            final loots_adapter loot = lootList.get(position);
            String lootQuantity = loot.getLoot_quantity();
            String lootQuality = loot.getLoot_quality();
            String lootRisk = loot.getLoot_risk();
            determineColors(holder,lootQuantity,lootQuality,lootRisk);
            holder.loot_place.setText(loot.getPlace_name());
            holder.loot_quantity.setText(loot.getLoot_quantity());
            holder.loot_quality.setText(loot.getLoot_quality());
            holder.loot_risk.setText(loot.getLoot_risk());
            holder.loot_shortdesc.setText(loot.getShort_desc());

            holder.loot_cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(mContext,holder.loot_place.getText(),Toast.LENGTH_SHORT).show();

                    Fragment lootsDetailFragment = LootsDetailFragment.newInstance(loot.getPlace_name(),loot.getShort_desc());
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                    fragmentTransaction.replace(android.R.id.content, lootsDetailFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        private void determineColors(MyViewHolder holder, String lootQuantity, String lootQuality, String lootRisk) {
            if(lootQuality.toLowerCase().contains("very high")){
                holder.loot_quality.setTextColor(Color.parseColor(redColor));
            } else if (lootQuality.toLowerCase().equals("high")){
                holder.loot_quality.setTextColor(Color.parseColor(greenColor));
            } else holder.loot_quality.setTextColor(Color.parseColor(orangeColor));

            if(lootQuantity.toLowerCase().contains("very high")){
                holder.loot_quantity.setTextColor(Color.parseColor(redColor));
            } else if (lootQuantity.toLowerCase().equals("high")){
                holder.loot_quantity.setTextColor(Color.parseColor(greenColor));
            } else holder.loot_quantity.setTextColor(Color.parseColor(orangeColor));

            if(lootRisk.toLowerCase().contains("very high")){
                holder.loot_risk.setTextColor(Color.parseColor(redColor));
            } else if (lootRisk.toLowerCase().equals("high")){
                holder.loot_risk.setTextColor(Color.parseColor(greenColor));
            } else holder.loot_risk.setTextColor(Color.parseColor(orangeColor));
        }

        @Override
        public int getItemCount() {
            return lootList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView loot_place, loot_quantity, loot_quality, loot_risk, loot_shortdesc;
            public CardView loot_cardview;

            public MyViewHolder(View itemView) {
                super(itemView);

                loot_cardview = itemView.findViewById(R.id.loot_cardview);
                loot_place = itemView.findViewById(R.id.loot_place);
                loot_quality = itemView.findViewById(R.id.loot_quality);
                loot_quantity = itemView.findViewById(R.id.loot_quantity);
                loot_risk = itemView.findViewById(R.id.loot_risk);
                loot_shortdesc = itemView.findViewById(R.id.loot_shortdes);
            }
        }
    }
}
