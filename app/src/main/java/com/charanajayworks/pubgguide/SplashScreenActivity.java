package com.charanajayworks.pubgguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreenActivity extends AppCompatActivity {

    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference adRef = mRootReference.child("isadenabled");

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen);

        sharedPreferences = getSharedPreferences("PUBG_GUIDE", MODE_PRIVATE);

        Thread loading = new Thread() {
            public void run() {
                try {
                    sleep(2500);
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                }

                catch (Exception e) {
                    e.printStackTrace();
                }

                finally {
                    finish();
                }
            }
        };

        loading.start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        adRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean isAdEnabled = dataSnapshot.getValue(Boolean.class);
                Log.d("FirebaseDB","isadenabled:"+isAdEnabled);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isadenabled", isAdEnabled);
                editor.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
