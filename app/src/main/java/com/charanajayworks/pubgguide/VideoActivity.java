package com.charanajayworks.pubgguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.charanajayworks.pubgguide.Adapters.YoutubeVideosAdapter;
import com.charanajayworks.pubgguide.LayoutManagers.VegaLayoutManager;
import com.charanajayworks.pubgguide.Models.VideosModel;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {
    RecyclerView videoListView;
    ArrayList<VideosModel> videosList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_layout);

        videoListView = findViewById(R.id.category_recycler);

        videosList = new ArrayList<>();

        videosList.add(new VideosModel("https://www.youtube.com/watch?v=WvMy8tu2NiE","Updates for pubg"));
        videosList.add(new VideosModel("https://www.youtube.com/watch?v=_hNgFYLum6M","this is video2"));
        videosList.add(new VideosModel("https://www.youtube.com/watch?v=Dk3xcMzVkgw","This is video3"));
        videosList.add(new VideosModel("https://www.youtube.com/watch?v=RUw3nooH5VE","This is video4"));
        videosList.add(new VideosModel("https://www.youtube.com/watch?v=1GE1-qcp6Ic","This is video5"));
        videosList.add(new VideosModel("https://www.youtube.com/watch?v=BuEh5011rmE","This is vidoe6"));

        YoutubeVideosAdapter youtubeVideosAdapter = new YoutubeVideosAdapter(this,videosList);

        videoListView.setAdapter(youtubeVideosAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        videoListView.setLayoutManager(llm);


    }
}
