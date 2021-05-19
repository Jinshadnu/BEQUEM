package com.example.bequem.home.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderResponse {
    @SerializedName("orders")
    public ArrayList<Orders> orders;

    @SerializedName("status")
    public String status;

    public ArrayList<Orders> getOrders() {
        return orders;
    }

    public String getStatus() {
        return status;
    }

    public class Orders {
        @SerializedName("order_no")
        public String order_no;

        @SerializedName("date")
        public String date;

        @SerializedName("order_status")
        public String order_status;

        @SerializedName("delivery_date")
        public String delivery_date;

        @SerializedName("total_price")
        public String total_price;

        @SerializedName("order_id")
        public String order_id;


        public String getOrder_no() {
            return order_no;
        }

        public String getDate() {
            return date;
        }

        public String getOrder_status() {
            return order_status;
        }

        public String getDelivery_date() {
            return delivery_date;
        }

        public String getTotal_price() {
            return total_price;
        }

        public String getOrder_id() {
            return order_id;
        }
    }
}
