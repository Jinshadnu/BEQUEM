package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FavouritesResponse {
    @SerializedName("favourites")
    public ArrayList<Favourites> favourites;

    @SerializedName("status")
    public String status;

    public ArrayList<Favourites> getFavourites() {
        return favourites;
    }

    public String getStatus() {
        return status;
    }

    public class Favourites {
        @SerializedName("item_id")
        public String item_id;

        @SerializedName("price")
        public String price;

        @SerializedName("item_image")
        public String item_image;

        @SerializedName("item_name")
        public String item_name;

        public String getItem_id() {
            return item_id;
        }

        public String getPrice() {
            return price;
        }

        public String getItem_image() {
            return item_image;
        }

        public String getItem_name() {
            return item_name;
        }
    }
    @BindingAdapter({"favourite_image"})
    public static void loadItemImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
