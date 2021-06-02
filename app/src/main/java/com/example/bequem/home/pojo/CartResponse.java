package com.example.bequem.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CartResponse {
    @SerializedName("delivery_charge")
    public String delivery_charge;

    @SerializedName("total_price")
    public String total_price;

    @SerializedName("minimum_purchase_amount")
    public String minimum_purchase_amount;

    @SerializedName("cart")
    public ArrayList<Cart> cart;

    @SerializedName("status")
    public String status;

    public String getDelivery_charge() {
        return delivery_charge;
    }

    public String getTotal_price() {
        return total_price;
    }

    public String getMinimum_purchase_amount() {
        return minimum_purchase_amount;
    }

    public ArrayList<Cart> getCart() {
        return cart;
    }

    public String getStatus() {
        return status;
    }

    public class Cart {
        @SerializedName("quantity")
        public String quantity;

        @SerializedName("item_id")
        public String item_id;

        @SerializedName("cart_id")
        public String cart_id;

        @SerializedName("price")
        public String price;

        @SerializedName("size_name")
        public String size_name;


        @SerializedName("item_image")
        public String item_image;

        @SerializedName("item_name")
        public String item_name;

        public String getSize_name() {
            return size_name;
        }

        public String getCart_id() {
            return cart_id;
        }

        public String getQuantity() {
            return quantity;
        }

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

    @BindingAdapter({"cartitem_image"})
    public static void loadItemImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .into(view);
    }
}
