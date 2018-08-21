package com.charanajayworks.pubgguide;

public class AssaultsAdapter {
    private String weaponName, imageLink, weaponDesc, magazineSize,timeBnShots, firingMode, reloadMethod,fullReloadTime, tacticalReloadTime, pickupDelay, readyDelay, ammo;
    int hitDamage, bulletSpeed, impactPower,range;

    public String getAirDrop() {
        return airDrop;
    }

    public void setAirDrop(String airDrop) {
        this.airDrop = airDrop;
    }

    private String airDrop;

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getWeaponDesc() {
        return weaponDesc;
    }

    public void setWeaponDesc(String weaponDesc) {
        this.weaponDesc = weaponDesc;
    }

    public String getMagazineSize() {
        return magazineSize;
    }

    public void setMagazineSize(String magazineSize) {
        this.magazineSize = magazineSize;
    }

    public String getTimeBnShots() {
        return timeBnShots;
    }

    public void setTimeBnShots(String timeBnShots) {
        this.timeBnShots = timeBnShots;
    }

    public String getFiringMode() {
        return firingMode;
    }

    public void setFiringMode(String firingMode) {
        this.firingMode = firingMode;
    }

    public String getReloadMethod() {
        return reloadMethod;
    }

    public void setReloadMethod(String reloadMethod) {
        this.reloadMethod = reloadMethod;
    }

    public String getFullReloadTime() {
        return fullReloadTime;
    }

    public void setFullReloadTime(String fullReloadTime) {
        this.fullReloadTime = fullReloadTime;
    }

    public String getTacticalReloadTime() {
        return tacticalReloadTime;
    }

    public void setTacticalReloadTime(String tacticalReloadTime) {
        this.tacticalReloadTime = tacticalReloadTime;
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

    public String getAmmo() {
        return ammo;
    }

    public void setAmmo(String ammo) {
        this.ammo = ammo;
    }

    public int getHitDamage() {
        return hitDamage;
    }

    public void setHitDamage(int hitDamage) {
        this.hitDamage = hitDamage;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public int getImpactPower() {
        return impactPower;
    }

    public void setImpactPower(int impactPower) {
        this.impactPower = impactPower;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public AssaultsAdapter(String weaponName, String imageLink, String weaponDesc, String magazineSize, String timeBnShots, String firingMode, String reloadMethod, String fullReloadTime, String tacticalReloadTime, String pickupDelay, String readyDelay, String ammo, String airDrop, int bulletSpeed, int impactPower, int range, int hitDamage) {
        this.weaponName = weaponName;
        this.imageLink = imageLink;
        this.weaponDesc = weaponDesc;
        this.magazineSize = magazineSize;
        this.timeBnShots = timeBnShots;
        this.firingMode = firingMode;
        this.reloadMethod = reloadMethod;
        this.fullReloadTime = fullReloadTime;
        this.tacticalReloadTime = tacticalReloadTime;
        this.pickupDelay = pickupDelay;
        this.readyDelay = readyDelay;
        this.ammo = ammo;
        this.airDrop = airDrop;
        this.hitDamage = hitDamage;
        this.bulletSpeed = bulletSpeed;
        this.impactPower = impactPower;
        this.range = range;
    }

}
