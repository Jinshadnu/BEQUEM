package com.example.bequem.home.pojo;

public class SubCategory {
    public String subcategory_id;
    public String subcategory_name;

    public SubCategory(String subcategory_id, String subcategory_name) {
        this.subcategory_id = subcategory_id;
        this.subcategory_name = subcategory_name;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }
}
