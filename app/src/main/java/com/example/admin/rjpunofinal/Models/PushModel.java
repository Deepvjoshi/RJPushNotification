package com.example.admin.rjpunofinal.Models;

import com.google.gson.Gson;

/**
 * Created by admin on 16/11/2017.
 */

public class PushModel {


    /**
     * status : 1
     * message : Your Category Insert
     */

    private int status;
    private String message;

    public static PushModel objectFromData(String str) {

        return new Gson().fromJson(str, PushModel.class);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
