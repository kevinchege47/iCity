package com.example.icity.HelperClasses.HomeAdapter;

public class ViewedHelperClass {
    int viewed_image;
    String viewed_titles;

    public ViewedHelperClass(int viewed_image, String viewed_titles) {
        this.viewed_image = viewed_image;
        this.viewed_titles = viewed_titles;
    }

    public int getViewed_image() {
        return viewed_image;
    }

    public String getViewed_titles() {
        return viewed_titles;
    }
}
