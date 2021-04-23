package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Cart {
    public int item_id;
    public String item_name;
    public String price;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }

    public int item_image;

    public Cart(String item_name, String price, int item_image) {
        this.item_name = item_name;
        this.price = price;
        this.item_image = item_image;
    }
    @BindingAdapter("cart")
    public static void loadImage(ImageView imageView, int image){
        imageView.setImageResource(image);
    }




}
