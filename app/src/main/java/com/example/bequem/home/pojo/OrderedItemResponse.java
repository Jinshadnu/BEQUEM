package com.example.bequem.home.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderedItemResponse {
    @SerializedName("items")
    public ArrayList<OrderedItems> items;

    @SerializedName("status")
    public String status;

    public ArrayList<OrderedItems> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public class OrderedItems {
        public String quantity;

        public String price;

        public String item_name;

        public String getQuantity() {
            return quantity;
        }

        public String getPrice() {
            return price;
        }

        public String getItem_name() {
            return item_name;
        }
    }
}
