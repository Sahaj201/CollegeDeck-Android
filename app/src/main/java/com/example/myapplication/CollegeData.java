package com.example.myapplication;

public class CollegeData {
    String description;
    String url;
    String title;
    String image;

    public CollegeData(String description, String url, String title,String image) {
        this.description = description;
        this.url = url;
        this.title = title;
        this.image=image;
    }

    public CollegeData() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
