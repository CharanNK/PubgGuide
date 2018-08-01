package com.charanajayworks.pubgguide;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.charanajayworks.pubgguide.Adapters.CategoryAdapter;
import com.charanajayworks.pubgguide.LayoutManagers.VegaLayoutManager;
import com.charanajayworks.pubgguide.Models.CategoryModel;
import com.romainpiel.shimmer.Shimmer;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity {
    RecyclerView mapsRecycler;
    ArrayList<CategoryModel> mapsArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_layout);

        mapsRecycler = findViewById(R.id.category_recycler);

        mapsArrayList = new ArrayList<>();

        mapsArrayList.add(new CategoryModel("ERANGEL","https://i.imgur.com/2WmPeAg.jpg","Bushes and trees make great hiding places for ambushing"));
        mapsArrayList.add(new CategoryModel("MIRAMAR","https://i.imgur.com/CvVTJDn.jpg","Great for urban assaults and sniping"));
        mapsArrayList.add(new CategoryModel("SANHOK","https://i.imgur.com/N2tNWbv.jpg","Great for urban assaults and sniping"));


        mapsRecycler.setLayoutManager(new VegaLayoutManager());

        final CategoryAdapter mapsAdapter = new CategoryAdapter(this,mapsArrayList);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mapsRecycler.setLayoutManager(llm);

        mapsRecycler.setAdapter(mapsAdapter);
    }
}
