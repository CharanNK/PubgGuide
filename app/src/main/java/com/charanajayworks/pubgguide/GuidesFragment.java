package com.charanajayworks.pubgguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charanajayworks.pubgguide.Adapters.guides_adapter;
import com.charanajayworks.pubgguide.Adapters.loots_adapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class GuidesFragment extends Fragment {
    RecyclerView guides_recycler;
    DBHelper databaseHelper;
    ArrayList<guides_adapter> guidesList;

    private SharedPreferences sharedPreferences;
    private boolean isAdEnabled;
    private InterstitialAd interstitialAd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tips_layout,container,false);

        guides_recycler = view.findViewById(R.id.category_recycler);

        sharedPreferences = this.getActivity().getSharedPreferences("PUBG_GUIDE", MODE_PRIVATE);
        isAdEnabled = sharedPreferences.getBoolean("isadenabled", false);

        if(Math.random()<0.2&&isAdEnabled)
            showAd();

        databaseHelper = new DBHelper(getActivity().getApplicationContext());
        try {
            databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        guidesList = new ArrayList<>();

        try{
            Cursor cursor = databaseHelper.getGuideList();

            while (cursor.moveToNext()){
                guides_adapter adapter = new guides_adapter(cursor.getString(0),cursor.getString(1));
                guidesList.add(adapter);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


        final guidesAdapterClass adapter = new guidesAdapterClass(getContext(),guidesList);
        RecyclerView myView =  (RecyclerView)view.findViewById(R.id.category_recycler);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);

        return view;
    }

    public void showAd() {
        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-3894268392664867/7965203868");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        Log.d("ADMOB","showAdCalled");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (interstitialAd.isLoaded())
                    interstitialAd.show();
            }
        }, 10000);
    }

    public class guidesAdapterClass extends RecyclerView.Adapter<guidesAdapterClass.MyViewHolder>{

        private Context mContext;
        private List<guides_adapter> guidesList;

        public guidesAdapterClass(Context context, ArrayList<guides_adapter> guidesList) {
            this.mContext = context;
            this.guidesList = guidesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_cardview,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            final guides_adapter guide = guidesList.get(position);
            String guideTitle = guide.getGuide_title();
            String guideDesc = guide.getGuide_desc();

            holder.guide_title.setText(guideTitle);
            holder.guide_desc.setText(guideDesc);

            holder.guide_cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment lootsDetailFragment = LootsDetailFragment.newInstance(guide.getGuide_title(),guide.getGuide_desc());
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                    fragmentTransaction.replace(android.R.id.content, lootsDetailFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return guidesList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView guide_title,guide_desc;
            public CardView guide_cv;

            public MyViewHolder(View itemView) {
                super(itemView);

                guide_title = itemView.findViewById(R.id.guide_title);
                guide_desc =  itemView.findViewById(R.id.guide_desc);

                guide_cv = itemView.findViewById(R.id.guide_cv);
            }
        }
    }


}
