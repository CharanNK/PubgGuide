package com.charanajayworks.pubgguide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.charanajayworks.pubgguide.LayoutManagers.ZoomableImageView;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.HashMap;

public class MapDisplayAcitivity extends AppCompatActivity {
    SubsamplingScaleImageView zoomableImageView;
    TextView mapNameDisplay;
    final String TAG = "mapDisplayActivity";
    private String mapName;
    private String mapSource;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_display_layout);

        zoomableImageView = findViewById(R.id.zoomable_mapimage);
        mapNameDisplay = findViewById(R.id.mapNameDisplay);

        mContext = getApplicationContext();

        HashMap<String,String> imageSourceMap = new HashMap<>();
        imageSourceMap.put("ERANGEL","https://i.imgur.com/hchKLkp.jpg");
        imageSourceMap.put("MIRAMAR","https://i.imgur.com/Yhltfvf.jpg");
        imageSourceMap.put("SANHOK","https://i.imgur.com/0gKaCq7.png");

        Bundle bundle = getIntent().getExtras();
        mapName = bundle.getString("mapName");

        mapSource = imageSourceMap.get(mapName);

        Log.d(TAG,"mapName:"+mapName);
        Log.d(TAG,"mapSource:"+mapSource);

        mapNameDisplay.setText(mapName);


        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.loadingimage).error(R.drawable.loadingimage)).asBitmap().load(mapSource).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    zoomableImageView.setImage(ImageSource.bitmap(resource));
                }
            }
        });
    }
}
