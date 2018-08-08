package com.charanajayworks.pubgguide.Models;

public class GeneralDataModel {
    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(String dataDescription) {
        this.dataDescription = dataDescription;
    }

    private String dataTitle;
    private String imageLink;
    private String dataDescription;
    private String lootQuality;
    private String lootQuantity;

    public GeneralDataModel(String dataTitle, String dataDescription, String imageLink,  String lootQuality, String lootQuantity, String lootRisk) {
        this.dataTitle = dataTitle;
        this.imageLink = imageLink;
        this.dataDescription = dataDescription;
        this.lootQuality = lootQuality;
        this.lootQuantity = lootQuantity;
        this.lootRisk = lootRisk;
    }

    public String getLootQuality() {
        return lootQuality;
    }

    public void setLootQuality(String lootQuality) {
        this.lootQuality = lootQuality;
    }

    public String getLootQuantity() {
        return lootQuantity;
    }

    public void setLootQuantity(String lootQuantity) {
        this.lootQuantity = lootQuantity;
    }

    public String getLootRisk() {
        return lootRisk;
    }

    public void setLootRisk(String lootRisk) {
        this.lootRisk = lootRisk;
    }

    private String lootRisk;

    public GeneralDataModel(String dataTitle, String dataDescription, String imageLink) {
        this.dataTitle = dataTitle;
        this.imageLink = imageLink;
        this.dataDescription = dataDescription;
    }
}
