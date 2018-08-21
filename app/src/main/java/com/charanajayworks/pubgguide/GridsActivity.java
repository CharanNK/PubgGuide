package com.charanajayworks.pubgguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.charanajayworks.pubgguide.Adapters.GridsAdapter;
import com.charanajayworks.pubgguide.Models.GridLayoutModel;

import java.util.ArrayList;

public class GridsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ArrayList<GridLayoutModel> weaponsGridList;
    ArrayList<GridLayoutModel> gunsGridList;
    ArrayList<GridLayoutModel> attachmentGridList;
    String gridType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weaponselectlayout);

        Bundle bundle = getIntent().getExtras();
        gridType = bundle.getString("gridtype");

        recyclerView = findViewById(R.id.gridsRecycler);

        weaponsGridList = new ArrayList<>();

        weaponsGridList.add(new GridLayoutModel("Bows/Guns","https://i.imgur.com/zgfiWXw.png"));
        weaponsGridList.add(new GridLayoutModel("Melee","https://i.imgur.com/RkjDRHo.png"));
        weaponsGridList.add(new GridLayoutModel("Ammunition","https://i.imgur.com/kYwffmf.png"));
        weaponsGridList.add(new GridLayoutModel("Equipment","https://i.imgur.com/UgKsdyJ.png"));
        weaponsGridList.add(new GridLayoutModel("Throwables","https://i.imgur.com/PEVJRyw.png"));
        weaponsGridList.add(new GridLayoutModel("Consumables","https://i.imgur.com/W2jC0Gl.png"));
        weaponsGridList.add(new GridLayoutModel("Attachments","https://i.imgur.com/0SIZrf9.png"));

        gunsGridList = new ArrayList<>();

        gunsGridList.add(new GridLayoutModel("Assault Rifles","https://i.imgur.com/ZKo0HYi.png"));
        gunsGridList.add(new GridLayoutModel("Sub Machine Guns","https://i.imgur.com/3WFHpZc.png"));
        gunsGridList.add(new GridLayoutModel("Snipers","https://i.imgur.com/RgshBpg.png"));
        gunsGridList.add(new GridLayoutModel("Light Machine Guns","https://i.imgur.com/0ZV7QKx.png"));
        gunsGridList.add(new GridLayoutModel("Shotguns","https://i.imgur.com/noorpoy.png"));
        gunsGridList.add(new GridLayoutModel("Pistols","https://i.imgur.com/anC8K5e.png"));

        attachmentGridList = new ArrayList<>();

        attachmentGridList.add(new GridLayoutModel("Muzzle Mods","https://i.imgur.com/z4KejvC.png"));
        attachmentGridList.add(new GridLayoutModel("Lower Rail","https://i.imgur.com/zwg3hwj.png"));
        attachmentGridList.add(new GridLayoutModel("Upper Rail","https://i.imgur.com/uczpZlB.png"));
        attachmentGridList.add(new GridLayoutModel("Magazines","https://i.imgur.com/KEx35Ut.png"));
        attachmentGridList.add(new GridLayoutModel("Stocks","https://i.imgur.com/kXETUY6.png"));

        GridsAdapter gridsAdapter = new GridsAdapter(this,weaponsGridList);
        if(gridType.contains("guns"))
            gridsAdapter = new GridsAdapter(this,gunsGridList);
        else if(gridType.contains("attachment"))
            gridsAdapter = new GridsAdapter(this,attachmentGridList);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(gridsAdapter);
    }
}
