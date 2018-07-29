package com.charanajayworks.pubgguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.charanajayworks.pubgguide.Adapters.CategoryAdapter;
import com.charanajayworks.pubgguide.Models.CategoryModel;
import com.stone.vega.library.VegaLayoutManager;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView categoryRecycler;
    ArrayList<CategoryModel> categoryModelArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_layout);

        categoryRecycler = findViewById(R.id.category_recycler);

        categoryModelArrayList = new ArrayList<>();

        categoryModelArrayList.add(new CategoryModel("WEAPONS",R.drawable.pubweaponvector));
        categoryModelArrayList.add(new CategoryModel("MAPS",R.drawable.pubg_plane));
        categoryModelArrayList.add(new CategoryModel("COMBINATIONS",R.drawable.pubweaponvector));
        categoryModelArrayList.add(new CategoryModel("PUBG 4 NOOBS",R.drawable.pubg_plane));
        categoryModelArrayList.add(new CategoryModel("MODES",R.drawable.pubweaponvector));
        categoryModelArrayList.add(new CategoryModel("LOOTS",R.drawable.pubweaponvector));
        categoryModelArrayList.add(new CategoryModel("CONTROLS",R.drawable.pubweaponvector));
        categoryModelArrayList.add(new CategoryModel("GUIDES",R.drawable.pubweaponvector));
        categoryModelArrayList.add(new CategoryModel("TIPS",R.drawable.pubweaponvector));
        categoryModelArrayList.add(new CategoryModel("VIDEOS",R.drawable.pubweaponvector));
        categoryModelArrayList.add(new CategoryModel("ROYALE PASS",R.drawable.pubweaponvector));
        categoryModelArrayList.add(new CategoryModel("QUESTIONS",R.drawable.pubweaponvector));

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecycler.setLayoutManager(llm);
//        categoryRecycler.setHasFixedSize(true);
        final CategoryAdapter categoryAdapter = new CategoryAdapter(this,categoryModelArrayList);

        categoryRecycler.setAdapter(categoryAdapter);
    }
}
