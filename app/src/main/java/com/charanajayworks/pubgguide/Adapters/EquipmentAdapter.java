package com.charanajayworks.pubgguide.Adapters;

public class EquipmentAdapter {
    public EquipmentAdapter(String equipmentName, String imagelink, String capacity, String damage, String durability, String weight) {
        this.equipmentName = equipmentName;
        this.imagelink = imagelink;
        this.capacity = capacity;
        this.damage = damage;
        this.durability = durability;
        this.weight = weight;
    }

    private String equipmentName;
    private String imagelink;
    private String capacity;
    private String damage;
    private String durability;
    private String weight;

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDurability() {
        return durability;
    }

    public void setDurability(String durability) {
        this.durability = durability;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

}
