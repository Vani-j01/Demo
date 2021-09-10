package com.example.demo.model;

import android.util.Log;

public class ProductsDataModel {
    String title, image, tag, category, price, quantity,id;
    int count;

    // getter method for our id
    public String getId() {
        return id;
    }

    // setter method for our id
    public void setId(String id) {
        this.id = id;
    }


    public ProductsDataModel(){

    }

    public ProductsDataModel(String title, String image, String tag, String category, String price, String quantity, int count) {
        this.title = title;
        this.image = image;
        this.tag = tag;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.count = count;
    }



    public String getTitle() {
        Log.e("TAG", "getTitle: "+title );
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
