package com.example.bequem.register.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RegisterResponse {
    @SerializedName("message")
    public String message;

    @SerializedName("user")
    public ArrayList<User> user;

    @SerializedName("status")
    public String status;

    public String getMessage() {
        return message;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    public class User {
        @SerializedName("user_id")
        public String user_id;

        @SerializedName("phone")
        public String phone;

        @SerializedName("email")
        public String email;

        @SerializedName("username")
        public String username;


        public String getUser_id() {
            return user_id;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }
    }
}
