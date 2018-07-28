package com.charanajayworks.pubgguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LootsDetailFragment extends Fragment {

    private String lootPlaceName, lootPlaceDesc;

    private TextView placeNameTV,placeDescTV;

    public static LootsDetailFragment newInstance(String loot_place,String loot_desc){
        Bundle bundle = new Bundle();
        bundle.putString("placename",loot_place);
        bundle.putString("description",loot_desc);

        LootsDetailFragment fragment = new LootsDetailFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }

    private void readBundle(Bundle bundle){
        if(bundle!=null){
            lootPlaceName = bundle.getString("placename");
            lootPlaceDesc = bundle.getString("description");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loot_detail_layout,container,false);

        placeNameTV = view.findViewById(R.id.loot_place_title);
        placeDescTV = view.findViewById(R.id.loot_place_desc);

        readBundle(getArguments());

        lootPlaceDesc = lootPlaceDesc.replaceAll("\\*\\*","\\\n");

        placeNameTV.setText(lootPlaceName);
        placeDescTV.setText(lootPlaceDesc);

        return view;
    }
}
