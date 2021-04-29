package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Wishlist {
    public int id;
    public int image;
    public String item_name;
    public String item_price;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }



    public Wishlist(int image, String item_name, String item_price) {
        this.image = image;
        this.item_name = item_name;
        this.item_price = item_price;
    }

    @BindingAdapter("wishlist")
    public static void loadImage(ImageView imageView, int image){
        imageView.setImageResource(image);
    }




}
