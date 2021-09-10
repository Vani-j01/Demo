package com.example.demo.model;

import android.transition.Slide;
import android.util.Log;

public class SlideshowDataModel {
    private  String image;


    public SlideshowDataModel(){

    }

    public SlideshowDataModel(String image) {
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
