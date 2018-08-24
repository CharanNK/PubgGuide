package com.charanajayworks.pubgguide.Adapters;

public class MeleeAdapter {
    private String meleename;
    private String imagelink;
    private String meleeDesc;
    private int damage;
    private int impact;
    private String hitRange;

    public String getMeleename() {
        return meleename;
    }

    public void setMeleename(String meleename) {
        this.meleename = meleename;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getMeleeDesc() {
        return meleeDesc;
    }

    public void setMeleeDesc(String meleeDesc) {
        this.meleeDesc = meleeDesc;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }

    public String getHitRange() {
        return hitRange;
    }

    public void setHitRange(String hitRange) {
        this.hitRange = hitRange;
    }

    public String getPickUpDelay() {
        return pickUpDelay;
    }

    public void setPickUpDelay(String pickUpDelay) {
        this.pickUpDelay = pickUpDelay;
    }

    public String getReadyDelay() {
        return readyDelay;
    }

    public void setReadyDelay(String readyDelay) {
        this.readyDelay = readyDelay;
    }

    private String pickUpDelay;
    private String readyDelay;

    public MeleeAdapter(String meleename, String imagelink, String meleeDesc, int damage, int impact, String hitRange, String pickUpDelay, String readyDelay) {
        this.meleename = meleename;
        this.imagelink = imagelink;
        this.meleeDesc = meleeDesc;
        this.damage = damage;
        this.impact = impact;
        this.hitRange = hitRange;
        this.pickUpDelay = pickUpDelay;
        this.readyDelay = readyDelay;
    }
}
