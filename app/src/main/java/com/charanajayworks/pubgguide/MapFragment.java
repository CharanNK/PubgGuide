package com.charanajayworks.pubgguide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MapFragment extends Fragment {
    ZoomableImageView zoomableImageView;
    final String TAG = "mapFragment";
    Button erangelMapButton,miramarMapButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_layout,container,false);

        Log.d(TAG,"onCreateViewCalled");

        Toast.makeText(getContext(),"Pinch to zoom",Toast.LENGTH_SHORT).show();

        zoomableImageView = view.findViewById(R.id.map_image);
        erangelMapButton = view.findViewById(R.id.erangel_map_button);
        miramarMapButton = view.findViewById(R.id.miramar_map_button);

        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.erangel_map);
        Bitmap finalBitmap = Bitmap.createScaledBitmap(
                imageBitmap, 800, 800, false);

        zoomableImageView.setImageBitmap(finalBitmap);

        erangelMapButton.setBackgroundResource(R.drawable.grey_button);
        erangelMapButton.setTextColor(Color.parseColor("#000000"));

        miramarMapButton.setBackgroundResource(R.drawable.blue_button);
        miramarMapButton.setTextColor(Color.parseColor("#ffffff"));

        erangelMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                erangelMapButton.setBackgroundResource(R.drawable.grey_button);
                erangelMapButton.setTextColor(Color.parseColor("#000000"));

                miramarMapButton.setBackgroundResource(R.drawable.blue_button);
                miramarMapButton.setTextColor(Color.parseColor("#ffffff"));

                Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.erangel_map);
                Bitmap finalBitmap = Bitmap.createScaledBitmap(
                        imageBitmap, 1080, 1080, false);

                zoomableImageView.setImageBitmap(finalBitmap);
            }
        });

        miramarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                erangelMapButton.setBackgroundResource(R.drawable.blue_button);
                erangelMapButton.setTextColor(Color.parseColor("#ffffff"));

                miramarMapButton.setBackgroundResource(R.drawable.grey_button);
                miramarMapButton.setTextColor(Color.parseColor("#000000"));

                Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.miramar_map);
//                Bitmap finalBitmap = Bitmap.createScaledBitmap(
//                        imageBitmap, 1080, 1080, false);

                zoomableImageView.setImageBitmap(imageBitmap);
            }
        });
        return view;
    }
}
