package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {
    @SerializedName("categories")
    public ArrayList<Categoriess> categories;
    @SerializedName("status")
    public String status;

    public ArrayList<Categoriess> getCategories() {
        return categories;
    }

    public String getStatus() {
        return status;
    }


    public class Categoriess {
        @SerializedName("category_name")
        public String category_name;

        @SerializedName("category_id")
        public String category_id;

        @SerializedName("category_image")
        public String category_pic;

        public String getCategory_name() {
            return category_name;
        }

        public String getCategory_id() {
            return category_id;
        }

        public String getCategory_pic() {
            return category_pic;
        }
    }
    @BindingAdapter({"category_image"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
