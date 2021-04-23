package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class FeaturedProducts {
    public int item_id;
    public int item_image;
    public String item_name;
    public String offer;
    public String price;

    public FeaturedProducts(int item_image, String item_name, String offer, String price) {
        this.item_image = item_image;
        this.item_name = item_name;
        this.offer = offer;
        this.price = price;
    }



    public String getItem_name() {
        return item_name;
    }

    public String getOffer() {
        return offer;
    }

    public String getPrice() {
        return price;
    }

    public int getItem_image() {
        return item_image;
    }






    @BindingAdapter("featuredproduct")
    public static void loadImage(ImageView imageView, int image){
        imageView.setImageResource(image);
    }

}
