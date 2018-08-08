package com.charanajayworks.pubgguide;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.charanajayworks.pubgguide.LayoutManagers.VegaLayoutManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class TipsActivity extends AppCompatActivity {
    DBHelper databaseHelper;
    RecyclerView listView;

    private SharedPreferences sharedPreferences;
    private boolean isAdEnabled;
    private InterstitialAd interstitialAd;


    public void showAd() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3894268392664867/7965203868");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        Log.d("ADMOB", "showAdCalled");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (interstitialAd.isLoaded())
                    interstitialAd.show();
            }
        }, 10000);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_layout);

        listView = findViewById(R.id.category_recycler);

        ArrayList<String> tipsList = new ArrayList<>();

        databaseHelper = new DBHelper(this);
        try {
            databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Cursor cursor = databaseHelper.getTips();

            while (cursor.moveToNext()) {
                tipsList.add(cursor.getString(0));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        TipsViewAdapter adapter = new TipsViewAdapter(this,tipsList);
        listView.setAdapter(adapter);
//        myView.setAdapter(adapter);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llm);

    }
}
