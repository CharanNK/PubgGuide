package com.charanajayworks.pubgguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.charanajayworks.pubgguide.Models.VideosModel;
import com.charanajayworks.pubgguide.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class YoutubeVideosAdapter extends RecyclerView.Adapter<YoutubeVideosAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<VideosModel> videoLinks;
    private String TAG = "YoutubeVideos";

    public YoutubeVideosAdapter(Context mContext, ArrayList<VideosModel> videoLinks) {
        this.mContext = mContext;
        this.videoLinks = videoLinks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vidoeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_video_activity, parent, false);
        return new ViewHolder(vidoeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        try {
            final VideosModel videosModel = videoLinks.get(position);
            final String imgUrl = getImageUrl(videosModel.getYoutubeVideoURL());
            Glide.with(mContext).load(imgUrl).into(holder.webvideo_thumbnail);

            holder.webvideo_title.setText(videosModel.getVideoText());

            holder.webvideo_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick called");
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(videosModel.getYoutubeVideoURL())));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getImageUrl(String videoUrl) throws MalformedURLException {
        String videoId = extractYoutubeId(videoUrl);
        String imgUrl = "http://img.youtube.com/vi/" + videoId + "/0.jpg";
        return imgUrl;
    }

    private String extractYoutubeId(String videoUrl) throws MalformedURLException {
        String query = new URL(videoUrl).getQuery();
        String[] param = query.split("&");
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }


    @Override
    public int getItemCount() {
        return videoLinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView webvideo_thumbnail;
        TextView webvideo_title;
        LinearLayout webvideo_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            webvideo_thumbnail = itemView.findViewById(R.id.webvideo_thumbnail);
            webvideo_title = itemView.findViewById(R.id.webvideo_title);
            webvideo_layout = itemView.findViewById(R.id.webvideo_layout);
        }
    }
}
