package com.example.testmenu.entidades;

public class SliderItem {

    private String imageUrl;
    private long timestamp;

    public SliderItem(){

    }

    public SliderItem(String imageUrl, long timestamp) {
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
