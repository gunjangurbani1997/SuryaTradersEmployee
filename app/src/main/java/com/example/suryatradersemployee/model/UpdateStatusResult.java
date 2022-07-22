package com.example.suryatradersemployee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateStatusResult {

    @SerializedName("msg")
    @Expose
    private String updateStatus;

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }
}
