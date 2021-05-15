package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SubCategoryResponse {
    @SerializedName("subcategory")
    public ArrayList<Subcategory> subcategory;

    @SerializedName("status")
    public String status;

    public ArrayList<Subcategory> getSubcategory() {
        return subcategory;
    }

    public String getStatus() {
        return status;
    }

    public class Subcategory {
        @SerializedName("subcategory_id")
        public String subcategory_id;

        @SerializedName("subcategory_name")
        public String subcategory_name;

        @SerializedName("subcategory_image")
        public String subcategory_image;

        public String getSubcategory_id() {
            return subcategory_id;
        }

        public String getSubcategory_name() {
            return subcategory_name;
        }

        public String getSubcategory_image() {
            return subcategory_image;
        }
    }
    @BindingAdapter({"subcategory_image"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
