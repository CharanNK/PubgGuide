package com.charanajayworks.pubgguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.charanajayworks.pubgguide.Adapters.CategoryAdapter;
import com.charanajayworks.pubgguide.Models.CategoryModel;

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

        categoryModelArrayList.add(new CategoryModel("WEAPONS","https://i.imgur.com/x8lKVDO.jpg","Know your weapons before you shoot"));
        categoryModelArrayList.add(new CategoryModel("MAPS","https://i.imgur.com/yXNNRbm.jpg","Know your map before you land"));
        categoryModelArrayList.add(new CategoryModel("COMBINATIONS","https://i.imgur.com/h00UZAh.jpg","Combos for a good combat"));
        categoryModelArrayList.add(new CategoryModel("PUBG 4 NOOBS","https://i.imgur.com/VxEBtRB.jpg","Read this before you start"));
        categoryModelArrayList.add(new CategoryModel("MODES","https://i.imgur.com/J681qOm.jpg","Variants of the game"));
        categoryModelArrayList.add(new CategoryModel("LOOTS","https://i.imgur.com/uffubBY.png","Know the best loot location"));
        categoryModelArrayList.add(new CategoryModel("CONTROLS","https://i.imgur.com/rZnBpOu.png","Adjust your controls before you shoot"));
        categoryModelArrayList.add(new CategoryModel("GUIDES","https://i.imgur.com/9goWtoH.jpg","Overall knowledge"));
        categoryModelArrayList.add(new CategoryModel("TIPS","https://i.imgur.com/BPRVEJD.jpg","Hacks to get a chicken dinner"));
        categoryModelArrayList.add(new CategoryModel("VIDEOS","https://i.imgur.com/5rIzA9r.jpg","Watch the pros play the game"));
        categoryModelArrayList.add(new CategoryModel("ROYALE PASS","https://i.imgur.com/uBj6kYv.jpg","Know the Royale Pass"));
        categoryModelArrayList.add(new CategoryModel("QUESTIONS","https://i.imgur.com/5rIzA9r.jpg","Ask us your queries"));

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecycler.setLayoutManager(llm);
//        categoryRecycler.setHasFixedSize(true);
        final CategoryAdapter categoryAdapter = new CategoryAdapter(this,categoryModelArrayList);

        categoryRecycler.setAdapter(categoryAdapter);
    }
}
