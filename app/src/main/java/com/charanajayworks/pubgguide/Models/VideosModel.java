package com.charanajayworks.pubgguide.Models;

public class VideosModel {
    private String youtubeVideoURL;
    private String videoText;

    public String getYoutubeVideoURL() {
        return youtubeVideoURL;
    }

    public void setYoutubeVideoURL(String youtubeVideoURL) {
        this.youtubeVideoURL = youtubeVideoURL;
    }

    public String getVideoText() {
        return videoText;
    }

    public void setVideoText(String videoText) {
        this.videoText = videoText;
    }

    public VideosModel(String youtubeVideoURL, String videoText) {
        this.youtubeVideoURL = youtubeVideoURL;
        this.videoText = videoText;
    }

}
