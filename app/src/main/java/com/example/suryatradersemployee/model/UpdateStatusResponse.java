package com.example.suryatradersemployee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateStatusResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Integer updateStatusResponse;
    @SerializedName("result")
    @Expose
    private com.example.suryatradersemployee.model.UpdateStatusResult updateStatusResult;

    public Integer getUpdateStatusResponse() {
        return updateStatusResponse;
    }

    public void setUpdateStatusResponse(Integer updateStatusResponse) {
        this.updateStatusResponse = updateStatusResponse;
    }

    public UpdateStatusResult getUpdateStatusResult() {
        return updateStatusResult;
    }

    public void setUpdateStatusResult(UpdateStatusResult updateStatusResult) {
        this.updateStatusResult = updateStatusResult;
    }
}
