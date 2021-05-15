package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemResponse {
    @SerializedName("items")
    public ArrayList<Items> items;

    @SerializedName("status")
    public String status;

    public ArrayList<Items> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public class Items {
        @SerializedName("item_id")
        public String item_id;

        @SerializedName("item_price")
        public String item_price;

        @SerializedName("item_name")
        public String item_name;

        @SerializedName("m_stock")
        public String m_stock;

        @SerializedName("xxl_stock")
        public String xxl_stock;

        @SerializedName("l_stock")
        public String l_stock;


        @SerializedName("xs_stock")
        public String xs_stock;

        @SerializedName("item_image")
        public String item_image;

        @SerializedName("s_stock")
        public String s_stock;

        @SerializedName("item_description")
        public String item_description;

        @SerializedName("xl_stock")
        public String xl_stock;

        @SerializedName("xxs_stock")
        public String xxs_stock;

        @SerializedName("pic1")
        public String pic1;

        @SerializedName("pic2")
        public String pic2;

        @SerializedName("pic3")
        public String pic3;

        @SerializedName("pic4")
        public String pic4;

        public String getPic1() {
            return pic1;
        }

        public String getPic2() {
            return pic2;
        }

        public String getPic3() {
            return pic3;
        }

        public String getPic4() {
            return pic4;
        }

        public String getItem_id() {
            return item_id;
        }

        public String getItem_price() {
            return item_price;
        }

        public String getItem_name() {
            return item_name;
        }

        public String getM_stock() {
            return m_stock;
        }

        public String getXxl_stock() {
            return xxl_stock;
        }

        public String getL_stock() {
            return l_stock;
        }

        public String getXs_stock() {
            return xs_stock;
        }

        public String getItem_image() {
            return item_image;
        }

        public String getS_stock() {
            return s_stock;
        }

        public String getItem_description() {
            return item_description;
        }

        public String getXl_stock() {
            return xl_stock;
        }

        public String getXxs_stock() {
            return xxs_stock;
        }
    }
    @BindingAdapter({"item_image"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
