package com.charanajayworks.pubgguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference adRef = mRootReference.child("isadenabled");
    Context mContext;
    SharedPreferences sharedPreferences;
    TextView loadingText;
    ShimmerFrameLayout shimmerFrameLayout;
    Thread splashTread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen_layout);

        mContext = getApplicationContext();

        sharedPreferences = getSharedPreferences("PUBG_GUIDE", MODE_PRIVATE);

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);

//        Thread loading = new Thread() {
//            public void run() {
//                Intent main = new Intent(getApplicationContext(), CategoryActivity.class);
//                startActivity(main);
//                finish();
//            }
//        };

//        loading.start();
        StartAnimations();
    }

    @Override
    protected void onStart() {
        super.onStart();

        adRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean isAdEnabled = dataSnapshot.getValue(Boolean.class);
                Log.d("FirebaseDB", "isadenabled:" + isAdEnabled);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isadenabled", isAdEnabled);
                editor.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        FrameLayout l= findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        anim.reset();
        ImageView iv = findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                shimmerFrameLayout.startShimmer();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.trans_left_right);
//        anim.reset();
//        ImageView iv1 = (ImageView) findViewById(R.id.splash1);
//        iv1.clearAnimation();
//        iv1.startAnimation(anim1);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3200) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreenActivity.this,
                            CategoryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreenActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashScreenActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }
}
