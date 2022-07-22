package com.example.suryatradersemployee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private com.example.suryatradersemployee.model.AuthenticationResult result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public com.example.suryatradersemployee.model.AuthenticationResult getResult() {
        return result;
    }

    public void setResult(com.example.suryatradersemployee.model.AuthenticationResult result) {
        this.result = result;
    }
}
