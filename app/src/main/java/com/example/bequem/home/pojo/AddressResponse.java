package com.example.bequem.home.pojo;

import java.util.ArrayList;

public class AddressResponse {

    private ArrayList<Address> address;

    private String status;

    private class Address {
        private String pincode;

        private String address_status ;

        private String address;

        private String latitude;

        private String landmark;

        private String add_id;

        private String longitude;
    }
}
