package com.charanajayworks.pubgguide.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class loots_adapter  {
       public String getLoot_risk() {
        return loot_risk;
    }

    public void setLoot_risk(String loot_risk) {
        this.loot_risk = loot_risk;
    }

    private String place_name;

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getLoot_quantity() {
        return loot_quantity;
    }

    public void setLoot_quantity(String loot_quantity) {
        this.loot_quantity = loot_quantity;
    }

    public String getLoot_quality() {
        return loot_quality;
    }

    public void setLoot_quality(String loot_quality) {
        this.loot_quality = loot_quality;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    private String loot_quantity,loot_quality,loot_risk;
    private String short_desc;

    public loots_adapter(String place_name,String loot_quantity,String loot_quality,String loot_risk,String short_desc){
        this.place_name = place_name;
        this.loot_quality = loot_quality;
        this.loot_quantity = loot_quantity;
        this.loot_risk = loot_risk;
        this.short_desc = short_desc;
    }


}
