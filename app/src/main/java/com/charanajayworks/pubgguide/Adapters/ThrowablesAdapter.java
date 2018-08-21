package com.charanajayworks.pubgguide.Adapters;

public class ThrowablesAdapter {
    private String throwablename;
    private String imagelink;
    private String throwableDesc;

    public String getThrowablename() {
        return throwablename;
    }

    public void setThrowablename(String throwablename) {
        this.throwablename = throwablename;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getThrowableDesc() {
        return throwableDesc;
    }

    public void setThrowableDesc(String throwableDesc) {
        this.throwableDesc = throwableDesc;
    }

    public String getThrowCoolDown() {
        return throwCoolDown;
    }

    public void setThrowCoolDown(String throwCoolDown) {
        this.throwCoolDown = throwCoolDown;
    }

    public String getFireDelay() {
        return fireDelay;
    }

    public void setFireDelay(String fireDelay) {
        this.fireDelay = fireDelay;
    }

    public String getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(String activationTime) {
        this.activationTime = activationTime;
    }

    public String getDetonationMode() {
        return detonationMode;
    }

    public void setDetonationMode(String detonationMode) {
        this.detonationMode = detonationMode;
    }

    public String getExplosionDelay() {
        return explosionDelay;
    }

    public void setExplosionDelay(String explosionDelay) {
        this.explosionDelay = explosionDelay;
    }

    public String getPickupDelay() {
        return pickupDelay;
    }

    public void setPickupDelay(String pickupDelay) {
        this.pickupDelay = pickupDelay;
    }

    public String getReadyDelay() {
        return readyDelay;
    }

    public void setReadyDelay(String readyDelay) {
        this.readyDelay = readyDelay;
    }

    private String throwCoolDown;
    private String fireDelay;
    private String activationTime;
    private String detonationMode;
    private String explosionDelay;
    private String pickupDelay;

    public String getThrowTime() {
        return throwTime;
    }

    public void setThrowTime(String throwTime) {
        this.throwTime = throwTime;
    }

    private String throwTime;

    public ThrowablesAdapter(String throwablename, String imagelink, String throwableDesc, String throwTime, String throwCoolDown, String fireDelay, String activationTime, String detonationMode, String explosionDelay, String pickupDelay, String readyDelay) {
        this.throwablename = throwablename;
        this.imagelink = imagelink;
        this.throwableDesc = throwableDesc;
        this.throwCoolDown = throwCoolDown;
        this.fireDelay = fireDelay;
        this.activationTime = activationTime;
        this.detonationMode = detonationMode;
        this.explosionDelay = explosionDelay;
        this.pickupDelay = pickupDelay;
        this.readyDelay = readyDelay;
        this.throwTime = throwTime;
    }

    private String readyDelay;
}
