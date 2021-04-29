package com.example.bequem.home.pojo;

public class Order {
    public int order_id;
    public String date;
    public String total;

    public Order(int order_id, String date, String total, String status) {
        this.order_id = order_id;
        this.date = date;
        this.total = total;
        this.status = status;
    }

    public String status;

    public int getOrder_id() {
        return order_id;
    }

    public String getDate() {
        return date;
    }

    public String getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }



}
