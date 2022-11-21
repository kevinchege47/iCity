package com.example.icity.HelperClasses.HomeAdapter;

public class FeaturedHelperClass {


    int images;
    String titles,descriptions;

    public FeaturedHelperClass(int images, String titles, String descriptions) {
        this.images = images;
        this.titles = titles;
        this.descriptions = descriptions;
    }

    public int getImages() {
        return images;
    }

    public String getTitles() {
        return titles;
    }

    public String getDescriptions() {
        return descriptions;
    }
}
