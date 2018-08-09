package com.charanajayworks.pubgguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.charanajayworks.pubgguide.Adapters.GuidesAdapter;
import com.charanajayworks.pubgguide.LayoutManagers.VegaLayoutManager;
import com.charanajayworks.pubgguide.Models.GuidesDataModel;

import java.util.ArrayList;

public class GuidesActivity extends AppCompatActivity {
    private RecyclerView guidesRecycler;
    private ArrayList<GuidesDataModel> guidesArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loots_layout);

        guidesRecycler = findViewById(R.id.display_loots);

        guidesArrayList = new ArrayList<>();

        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));
        guidesArrayList.add(new GuidesDataModel("aefwihew","avaewivuwvona;oavaoanvanoveasfjdvasnjv"));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        guidesRecycler.setLayoutManager(new VegaLayoutManager());

        final GuidesAdapter guidesAdapter = new GuidesAdapter(this,guidesArrayList);

        guidesRecycler.setAdapter(guidesAdapter);
    }
}
