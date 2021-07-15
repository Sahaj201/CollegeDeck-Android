package com.example.myapplication;

public class CollegeData {
    String description;
    String url;
    String title;
    String image;
    String admission;
    String cutoff;
    String fees;
    String how_to_apply;
    String placement;

    public CollegeData(String description, String url, String title,String image,String admission,String cutoff,String fees,String how_to_apply,String placement) {
        this.description = description;
        this.url = url;
        this.title = title;
        this.image=image;
        this.admission=admission;
        this.fees=fees;
        this.cutoff=cutoff;
        this.how_to_apply=how_to_apply;
        this.placement=placement;
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

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getCutoff() {
        return cutoff;
    }

    public void setCutoff(String cutoff) {
        this.cutoff = cutoff;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getHow_to_apply() {
        return how_to_apply;
    }

    public void setHow_to_apply(String how_to_apply) {
        this.how_to_apply = how_to_apply;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
