package com.example.bequem.home.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddressResponse {
    @SerializedName("address")
    public ArrayList<Address> address;

    @SerializedName("status")
    public String status;

    public ArrayList<Address> getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public class Address {
        @SerializedName("pincode")
        public String pincode;

        @SerializedName("address_status")
        public String address_status ;

        @SerializedName("address")
        public String address;

        @SerializedName("latitude")
        public String latitude;

        @SerializedName("landmark")
        public String landmark;

        @SerializedName("add_id")
        public String add_id;

        @SerializedName("longitude")
        public String longitude;

        public String getPincode() {
            return pincode;
        }

        public String getAddress_status() {
            return address_status;
        }

        public String getAddress() {
            return address;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLandmark() {
            return landmark;
        }

        public String getAdd_id() {
            return add_id;
        }

        public String getLongitude() {
            return longitude;
        }
    }
}
