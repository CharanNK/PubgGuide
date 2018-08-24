package com.charanajayworks.pubgguide.Adapters;

public class AttachmentsAdapter {
    private String attachmentName;

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getAttachmentDescription() {
        return attachmentDescription;
    }

    public void setAttachmentDescription(String attachmentDescription) {
        this.attachmentDescription = attachmentDescription;
    }

    public String getAttachableWeapons() {
        return attachableWeapons;
    }

    public void setAttachableWeapons(String attachableWeapons) {
        this.attachableWeapons = attachableWeapons;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    private String imagelink;
    private String attachmentDescription;
    private String attachableWeapons;
    private String capacity;
    private String attributes;

    public AttachmentsAdapter(String attachmentName, String imagelink, String attachmentDescription, String attachableWeapons, String capacity, String attributes) {
        this.attachmentName = attachmentName;
        this.imagelink = imagelink;
        this.attachmentDescription = attachmentDescription;
        this.attachableWeapons = attachableWeapons;
        this.capacity = capacity;
        this.attributes = attributes;
    }
}
