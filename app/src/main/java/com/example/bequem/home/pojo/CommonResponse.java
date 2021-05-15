package com.example.bequem.home.pojo;

import com.google.gson.annotations.SerializedName;

public class CommonResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */


    @SerializedName("status")
    public String status;






    @SerializedName("message")
    public String message;

    public String getMessage() {
        return message;
    }

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public CommonResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getStatus() {
        return status;
    }




}