package com.charanajayworks.pubgguide.Adapters;

public class ConsumablesAdapter {
    public String getConsumableName() {
        return consumableName;
    }

    public void setConsumableName(String consumableName) {
        this.consumableName = consumableName;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getConsumableDesc() {
        return consumableDesc;
    }

    public void setConsumableDesc(String consumableDesc) {
        this.consumableDesc = consumableDesc;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    private String consumableName, imagelink, consumableDesc, castTime, capacity;

    public ConsumablesAdapter(String consumableName, String imagelink, String consumableDesc, String castTime, String capacity) {
        this.consumableName = consumableName;
        this.imagelink = imagelink;
        this.consumableDesc = consumableDesc;
        this.castTime = castTime;
        this.capacity = capacity;
    }
}
