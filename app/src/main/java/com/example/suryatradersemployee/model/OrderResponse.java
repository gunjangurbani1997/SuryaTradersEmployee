package com.example.suryatradersemployee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse {

    @SerializedName("status")
    @Expose
    private Integer orderResponseStatus;
    @SerializedName("result")
    @Expose
    private com.example.suryatradersemployee.model.OrderResult orderResponseResult;


    public Integer getOrderResponseStatus() {
        return orderResponseStatus;
    }

    public void setOrderResponseStatus(Integer orderResponseStatus) {
        this.orderResponseStatus = orderResponseStatus;
    }

    public com.example.suryatradersemployee.model.OrderResult getOrderResponseResult() {
        return orderResponseResult;
    }

    public void setOrderResponseResult(com.example.suryatradersemployee.model.OrderResult orderResponseResult) {
        this.orderResponseResult = orderResponseResult;
    }
}
