package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Items {
    public int item_id;
    public String item_name;
    public int item_image;
    public String item_price;

    public Items(int item_id, String item_name, int item_image, String item_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_image = item_image;
        this.item_price = item_price;
    }



    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }
    @BindingAdapter("items")
    public static void loadImage(ImageView imageView, int image){
        imageView.setImageResource(image);
    }

}
