package com.charanajayworks.pubgguide.Adapters;

public class AmmunitionAdapter {
    private String ammoName;
    private String imagelink;

    public String getAmmoName() {
        return ammoName;
    }

    public void setAmmoName(String ammoName) {
        this.ammoName = ammoName;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getAmmoDesc() {
        return ammoDesc;
    }

    public void setAmmoDesc(String ammoDesc) {
        this.ammoDesc = ammoDesc;
    }

    private String ammoDesc;

    public AmmunitionAdapter(String ammoName, String imagelink, String ammoDesc) {
        this.ammoName = ammoName;
        this.imagelink = imagelink;
        this.ammoDesc = ammoDesc;
    }
}
