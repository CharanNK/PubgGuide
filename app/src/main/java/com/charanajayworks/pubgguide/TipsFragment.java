package com.charanajayworks.pubgguide;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class TipsFragment extends android.support.v4.app.Fragment {
    DBHelper databaseHelper;
    RecyclerView listView;

    private SharedPreferences sharedPreferences;
    private boolean isAdEnabled;
    private InterstitialAd interstitialAd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tips_layout,container,false);

        listView = view.findViewById(R.id.category_recycler);

//        sharedPreferences = this.getActivity().getSharedPreferences("PUBG_GUIDE", MODE_PRIVATE);
//        isAdEnabled = sharedPreferences.getBoolean("isadenabled", false);
//
//        if(Math.random()<0.2&&isAdEnabled)
//            showAd();
//
//
        ArrayList<String> tipsList = new ArrayList<>();
        tipsList.add("WEAPONS");
//
//        databaseHelper = new DBHelper(getActivity().getApplicationContext());
//        try {
//            databaseHelper.createDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try{
//            Cursor cursor = databaseHelper.getTips();
//
//            while (cursor.moveToNext()){
//                tipsList.add(cursor.getString(0));
//            }
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }

        TipsViewAdapter adapter = new TipsViewAdapter(tipsList);
        RecyclerView myView =  (RecyclerView)view.findViewById(R.id.category_recycler);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);

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
}
