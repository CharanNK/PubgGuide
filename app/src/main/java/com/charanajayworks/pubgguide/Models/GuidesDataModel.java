package com.charanajayworks.pubgguide.Models;

public class GuidesDataModel {

    public GuidesDataModel(String guide_title, String guide_desc) {
        this.guide_title = guide_title;
        this.guide_desc = guide_desc;
    }

    public String getGuide_title() {
        return guide_title;
    }

    public void setGuide_title(String guide_title) {
        this.guide_title = guide_title;
    }

    private String guide_title;

    public String getGuide_desc() {
        return guide_desc;
    }

    public void setGuide_desc(String guide_desc) {
        this.guide_desc = guide_desc;
    }

    private String guide_desc;
}
