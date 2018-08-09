package com.charanajayworks.pubgguide;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.charanajayworks.pubgguide.Adapters.CategoryAdapter;
import com.charanajayworks.pubgguide.LayoutManagers.VegaLayoutManager;
import com.charanajayworks.pubgguide.Models.CategoryModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView categoryRecycler;
    ArrayList<CategoryModel> categoryModelArrayList;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share_app :
                try{
                    Uri imageUri = null;
                    try {
                        imageUri = Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(),
                                BitmapFactory.decodeResource(getResources(), R.drawable.pubg_app_share), null, null));
                    } catch (NullPointerException e) {
                    }

                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setType("image/jpg");
                    //intent.putExtra(Intent.EXTRA_SUBJECT,"PUBG Guide");
                    String shareMessage = "\nWant to have a Chicken Dinner?\n\nInstall this cool app to know the best practices to win a game in PUBG\n\n";
                    shareMessage+="https://play.google.com/store/apps/details?id="+getPackageName();
                    shareMessage+="\n\nWinner Winner Chicken Dinner";
                    intent.putExtra(Intent.EXTRA_TEXT,shareMessage);
                    intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(Intent.createChooser(intent,"Share app"));
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "No App Available", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.rate_app :
                AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this)
                        .setTitle("Rate Us!")
                        .setIcon(R.drawable.pubgguide_icon)
                        .setMessage("Please, rate the app at PlayStore")
                        .setPositiveButton("RATE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (getApplicationContext() != null) {
                                    String link = "market://details?id=";
                                    try {
                                        // play market available
                                        getApplicationContext().getPackageManager()
                                                .getPackageInfo("com.android.vending", 0);
                                        // not available
                                    } catch (PackageManager.NameNotFoundException e) {
                                        e.printStackTrace();
                                        // should use browser
                                        link = "https://play.google.com/store/apps/details?id=";
                                    }
                                    getApplicationContext().startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(link + getApplicationContext().getPackageName())));
                                }
                            }
                        })
                        .setNegativeButton("CANCEL", null);
                builder.show();
                return true;
                default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_layout);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        categoryRecycler = findViewById(R.id.category_recycler);

        categoryModelArrayList = new ArrayList<>();

        categoryModelArrayList.add(new CategoryModel("WEAPONS","https://i.imgur.com/x8lKVDO.jpg","Know your weapons before you shoot"));
        categoryModelArrayList.add(new CategoryModel("MAPS","https://i.imgur.com/yXNNRbm.jpg","Know your map before you land"));
        categoryModelArrayList.add(new CategoryModel("COMBINATIONS","https://i.imgur.com/h00UZAh.jpg","Combos for a good combat"));
        categoryModelArrayList.add(new CategoryModel("PUBG 4 NOOBS","https://i.imgur.com/VxEBtRB.jpg","Read this before you start"));
        categoryModelArrayList.add(new CategoryModel("MODES","https://i.imgur.com/J681qOm.jpg","Variants of the game"));
        categoryModelArrayList.add(new CategoryModel("LOOTS","https://i.imgur.com/uffubBY.png","Know the best loot location"));
        categoryModelArrayList.add(new CategoryModel("CONTROLS","https://i.imgur.com/rZnBpOu.png","Adjust your controls before you shoot"));
        categoryModelArrayList.add(new CategoryModel("GUIDES","https://i.imgur.com/9goWtoH.jpg","Overall knowledge"));
        categoryModelArrayList.add(new CategoryModel("TIPS","https://i.imgur.com/BPRVEJD.jpg","Hacks to get a chicken dinner"));
        categoryModelArrayList.add(new CategoryModel("VIDEOS","https://i.imgur.com/5rIzA9r.jpg","Watch the pros play the game"));
        categoryModelArrayList.add(new CategoryModel("ROYALE PASS","https://i.imgur.com/uBj6kYv.jpg","Know the Royale Pass"));
        categoryModelArrayList.add(new CategoryModel("QUESTIONS","https://i.imgur.com/5rIzA9r.jpg","Ask us your queries"));

        categoryRecycler.setLayoutManager(new VegaLayoutManager());

        final CategoryAdapter categoryAdapter = new CategoryAdapter(this,categoryModelArrayList);

        categoryRecycler.setAdapter(categoryAdapter);
    }
}
