package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OfferResponse {
    @SerializedName("special_offer_items")
    public ArrayList<Special_offer_items> special_offer_items;

    @SerializedName("status")
    public String status;

    public ArrayList<Special_offer_items> getSpecial_offer_items() {
        return special_offer_items;
    }

    public String getStatus() {
        return status;
    }


    public class Special_offer_items {
        @SerializedName("item_id")
        private String item_id;

        @SerializedName("discount_amount")
        private String discount_amount;

        @SerializedName("item_price")
        private String item_price;

        @SerializedName("item_name")
        private String item_name;

        @SerializedName("m_stock")
        private String m_stock;

        @SerializedName("xxl_stock")
        private String xxl_stock;

        @SerializedName("l_stock")
        private String l_stock;

        @SerializedName("xs_stock")
        private String xs_stock;

        @SerializedName("pic1")
        private String pic1;

        @SerializedName("item_image")
        private String item_image;

        @SerializedName("s_stock")
        private String s_stock;

        @SerializedName("item_description")
        private String item_description;

        @SerializedName("pic2")
        private String pic2;

        @SerializedName("pic3")
        private String pic3;

        @SerializedName("xl_stock")
        private String xl_stock;

        @SerializedName("pic4")
        private String pic4;

        @SerializedName("xxs_stock")
        private String xxs_stock;

        public String getItem_id() {
            return item_id;
        }

        public String getDiscount_amount() {
            return discount_amount;
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

        public String getPic1() {
            return pic1;
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

        public String getPic2() {
            return pic2;
        }

        public String getPic3() {
            return pic3;
        }

        public String getXl_stock() {
            return xl_stock;
        }

        public String getPic4() {
            return pic4;
        }

        public String getXxs_stock() {
            return xxs_stock;
        }
    }

    @BindingAdapter({"offeritem_image"})
    public static void loadItemImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
