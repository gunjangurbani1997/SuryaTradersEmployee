package com.example.suryatradersemployee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubOrderResponse {
    @SerializedName("status")
    @Expose
    private Integer subOrderResponseStatus;
    @SerializedName("result")
    @Expose
    private com.example.suryatradersemployee.model.SubOrderResult subOrderResponseResult;

    public Integer getSubOrderResponseStatus() {
        return subOrderResponseStatus;
    }

    public void setSubOrderResponseStatus(Integer subOrderResponseStatus) {
        this.subOrderResponseStatus = subOrderResponseStatus;
    }

    public com.example.suryatradersemployee.model.SubOrderResult getSubOrderResponseResult() {
        return subOrderResponseResult;
    }

    public void setSubOrderResponseResult(com.example.suryatradersemployee.model.SubOrderResult subOrderResponseResult) {
        this.subOrderResponseResult = subOrderResponseResult;
    }
}
