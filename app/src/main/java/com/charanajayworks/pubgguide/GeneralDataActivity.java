package com.charanajayworks.pubgguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.charanajayworks.pubgguide.Adapters.GeneralDataAdapter;
import com.charanajayworks.pubgguide.Models.GeneralDataModel;

import java.util.ArrayList;

public class GeneralDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<GeneralDataModel> generalDataModelArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_layout);

        recyclerView = findViewById(R.id.category_recycler);

        generalDataModelArrayList = new ArrayList<>();

        generalDataModelArrayList.add(new GeneralDataModel("Best All-Rounder Loadout","This build focuses on dealing damage no matter what sort of situation you might find yourself in. It’ll be versatile enough to take on enemies at all ranges, but it’ll be most potent in mid range fights. **1st Weapon: M416**2nd Weapon: AKM**Melee: Pan**Attachments: 2x, 4x, Extended Quickdraw, Compensator.**Consumables: First Aid Kits, Painkillers, Bandages.**A fully kitted out M416 is easily the most reliable weapon in the game, with decent damage at all ranges too. The AKM’s a decent secondary as it does immense damage at short range, making it a strong alternative to an SMG.","https://i.imgur.com/h00UZAh.jpg",null,null,null));
        generalDataModelArrayList.add(new GeneralDataModel("Best Defensive Loadout","This build focuses on dealing damage no matter what sort of situation you might find yourself in. It’ll be versatile enough to take on enemies at all ranges, but it’ll be most potent in mid range fights. **1st Weapon: M416**2nd Weapon: AKM**Melee: Pan**Attachments: 2x, 4x, Extended Quickdraw, Compensator.**Consumables: First Aid Kits, Painkillers, Bandages.**A fully kitted out M416 is easily the most reliable weapon in the game, with decent damage at all ranges too. The AKM’s a decent secondary as it does immense damage at short range, making it a strong alternative to an SMG.","https://i.imgur.com/h00UZAh.jpg",null,null,null));
        generalDataModelArrayList.add(new GeneralDataModel("Best Close Quarters Loadout","This build focuses on dealing damage no matter what sort of situation you might find yourself in. It’ll be versatile enough to take on enemies at all ranges, but it’ll be most potent in mid range fights. **1st Weapon: M416**2nd Weapon: AKM**Melee: Pan**Attachments: 2x, 4x, Extended Quickdraw, Compensator.**Consumables: First Aid Kits, Painkillers, Bandages.**A fully kitted out M416 is easily the most reliable weapon in the game, with decent damage at all ranges too. The AKM’s a decent secondary as it does immense damage at short range, making it a strong alternative to an SMG.","https://i.imgur.com/h00UZAh.jpg",null,null,null));

//        generalDataModelArrayList.add(new GeneralDataModel(null,"Battle royale basically means all against all, a bit like The Hunger Games where you start with nothing and have to scavenge and collect weapons and equipment. The game is ultimately a battle to the last player standing, with 100 players on an 8 x 8 km island. **  **There are three modes of play: solo, duo and squad, with the latter letting you team up with three other players for a team of four. Each is a little different with various pros and cons, the big pro of team play being that have people to support you, revive you and bring an element of co-operative tactics to the game.**  **The game starts with you parachuting onto the game island. On landing your mission is to gather everything you can to help you win, from clothes to medical supplies to weapons, with a wide range of weapons and modifications for those weapons.**   **The island is a mixture of urban and rural environments, detailed in the map in the top right-hand corner of the game, and the full area is initially playable, but the play areas decreases in size as time progresses.**  **This forces the players closer together with ever decreasing circles, those left outside the safe play space will start to take damage and eventually die. The game reaches its climax when the last few players are condensed down into a small space and one player or team emerges as the victor.**  **Each game can last up to about 30 minutes if you're one of the last standing. Of course, you can die almost as soon as you're on the ground. Winner, winner, chicken dinner.","https://i.imgur.com/QuDzY2Q.jpg"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final GeneralDataAdapter generalDataAdapter = new GeneralDataAdapter(this,generalDataModelArrayList);

        recyclerView.setAdapter(generalDataAdapter);
    }
}
