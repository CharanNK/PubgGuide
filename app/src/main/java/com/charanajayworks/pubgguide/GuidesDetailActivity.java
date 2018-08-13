package com.charanajayworks.pubgguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class GuidesDetailActivity extends AppCompatActivity {
    private String guideTitle;
    private String guideDescription;
    private TextView guide_det_title, guide_det_desc;
    private String TAG = "GUIDESDETAIL";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidesdetaillayout);

        guideTitle = getIntent().getExtras().getString("guideTitle");
        guideDescription = getIntent().getExtras().getString("guideDesc");

        Log.d(TAG,"title:"+guideTitle);
        Log.d(TAG,"desc:"+guideDescription);

        guide_det_title = findViewById(R.id.guide_det_title);
        guide_det_desc = findViewById(R.id.guide_det_desc);

        guide_det_desc.setSingleLine(false);

        guide_det_title.setText(guideTitle);
        guide_det_desc.setText(guideDescription);
    }

}
