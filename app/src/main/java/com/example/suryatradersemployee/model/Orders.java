package com.example.suryatradersemployee.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class Orders {

    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("display_status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("sub_orders")
    @Expose
    private List<SubOrders> subOrderList;
    @SerializedName("no_of_bales_packed")
    @Expose
    private Integer balesPacked;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<SubOrders> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<SubOrders> subOrderList) {
        this.subOrderList = subOrderList;
    }

    public Integer getBalesPacked() {
        return balesPacked;
    }

    public void setBalesPacked(Integer balesPacked) {
        this.balesPacked = balesPacked;
    }

    public Orders()
    {}

    public Orders(Integer orderId, Customer customer, String status, String createdAt, String updatedAt, List<SubOrders> subOrderList,Integer balesPacked) {
        this.orderId = orderId;
        this.customer = customer;
        this.status = status;
        this.createdAt = createdAt;
        this.subOrderList = subOrderList;
        this.balesPacked=balesPacked;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.example.suryatradersemployee.model.Orders orders = (com.example.suryatradersemployee.model.Orders) o;
        return orderId.equals(orders.orderId) &&
                status.equals(orders.status) &&
                createdAt.equals(orders.createdAt) &&
                subOrderList.equals(orders.subOrderList);
    }

    public static DiffUtil.ItemCallback<com.example.suryatradersemployee.model.Orders> itemCallback= new DiffUtil.ItemCallback<com.example.suryatradersemployee.model.Orders>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull com.example.suryatradersemployee.model.Orders oldItem, @androidx.annotation.NonNull com.example.suryatradersemployee.model.Orders newItem) {
            return oldItem.getOrderId().equals(newItem.getOrderId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull com.example.suryatradersemployee.model.Orders oldItem, @androidx.annotation.NonNull com.example.suryatradersemployee.model.Orders newItem) {
            return oldItem.equals(newItem);
        }
    };
}
