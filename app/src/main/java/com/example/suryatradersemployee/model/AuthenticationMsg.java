package com.example.suryatradersemployee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationMsg {

    @Expose
    @SerializedName("msg")
    String authenticationMsg;

    @Expose
    @SerializedName("admin_id")
    String employee_id;

    public String getAuthenticationMsg() {
        return authenticationMsg;
    }

    public void setAuthenticationMsg(String authenticationMsg) {
        this.authenticationMsg = authenticationMsg;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
}
