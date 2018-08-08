package com.charanajayworks.pubgguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charanajayworks.pubgguide.Adapters.GeneralDataAdapter;
import com.charanajayworks.pubgguide.Models.GeneralDataModel;

import java.util.ArrayList;

public class ErangelLootFragment extends Fragment {
    DBHelper databaseHelper;
    RecyclerView erangellootsRecycler;
    ArrayList<GeneralDataModel> erangelLootList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loots_layout,container,false);

        erangellootsRecycler = view.findViewById(R.id.display_loots);

        erangelLootList = new ArrayList<>();

        erangelLootList.add(new GeneralDataModel("Military Base","The Military Base, on Erangel's smaller, southern island, has the most consistent amount of high-quality, military-grade loot. Military grade means things like level 3 helmets and body armour, plentiful scopes and attachments, medical gear and assault rifles. It's almost always busy, but it's so huge that your whole squad could loot it and not notice another one doing the same. Aim for the three long buildings in a U-shape for dense amounts of loot that's easy to work your way through. If you can get here from a flight path that's quite far away, say via fast vehicle like bike, then it's an incredible place to loot in peace - but just be wary of \"bridge trolls\" waiting to ambush you on the way to the mainland.","https://i.imgur.com/h00UZAh.jpg","Very High","High","Medium"));
        erangelLootList.add(new GeneralDataModel("School","The School - the big, central square-shaped building by Rozhok - is massively popular, and thus massively dangerous, because it's dead central positioning almost always puts it within landing distance of a drop from the plane.  ** The loot isn't military-grade, but there's probably a full squad's worth there - if it's at the edge of parachute distance then fighting it out with (and hopefully defeating) one other player or squad is a great way to set yourself up for a very strong push into the late game. Lots of level 2 gear, good weapons and attachments and medical loot, too.  ** The nearby \"puzzle\" buildings to the east (that look like jigsaw pieces on the map) are a great place to break out to, as is Rozhok. Just be wary of how open you are in the fields around it!","https://i.imgur.com/h00UZAh.jpg","Very High","Very High","Very High"));
        erangelLootList.add(new GeneralDataModel("Pochinki","Stop landing here! Unless you want to practise, warm up, or die quickly that is. Pochinki has good loot and loads of it, but it's always packed, it's very hard to know where other teams are (and so very easy to be killed by them) because of the sheer number of places to hide, and it's just generally an all round death zone. ** Great for practise though, or emergency looting if you're travelling through and short of gear later on, but there are other similar options with slightly lower risk and slightly higher reward that aren't far away. It's fine to go for it, just don't expect it to be safe, ever.","https://i.imgur.com/h00UZAh.jpg","Very High","Very High","Very High"));
        erangelLootList.add(new GeneralDataModel("Hospital","The Hospital-Georgopol-crates area is all sort of connected. The best place is probably the Hospital - the H-shaped building just south of the town - as there are vast quantities of loot that's just below military-grade packed into its various corridors here, enough to kit out a full squad. ** The containers, or creates, to the east of the town is the next best place to go. The handful of warehouses are the place to start, then work your way through the containers - and most importantly onto the top of them - to hoover up lots more good loot. Again, a squad can pretty much stock up here. ** Finally the buildings - on the south coast, not the northern one, which can be surprisingly frustrating in terms of loot-to-time-spent-looting ratio - are also full of good quality gear. You start to learn the interal structure of these houses quite quickly and can turn it into a very quick procedure with some practice. The lowered risk for all of these areas comes from the fact that they're out on the west coast, meaning less frequently under the busy flight path.","https://i.imgur.com/h00UZAh.jpg","Very High","Very High","Very High"));
        erangelLootList.add(new GeneralDataModel("Mylta Power","A personal favourite, the east coast's Mylta Power is a go-to drop when it's on the edge of the flight path. Thanks to its positioning, a path close to Mylta Power is also close to the Military Base, Mansion, Prison and Novorepnoye, which are all more popular options - and often distract players away from the Power plant. The loot here is occasionally military grade, although there's not quite as much of it. If you're dropping here directly aim for the roof of the large rectangular green building, clean that out, then go to the warehouses around it to finish off the sweep.","https://i.imgur.com/h00UZAh.jpg","Very High","Very High","Very High"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        erangellootsRecycler.setLayoutManager(layoutManager);

        final GeneralDataAdapter generalDataAdapter = new GeneralDataAdapter(this.getContext(),erangelLootList);

        erangellootsRecycler.setAdapter(generalDataAdapter);
        return view;
    }
}
