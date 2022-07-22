package com.example.suryatradersemployee.viewmodels;



import com.example.suryatradersemployee.model.Orders;
import com.example.suryatradersemployee.repositories.OrderRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {


    MutableLiveData<Integer> customerOrderListLiveData=new MutableLiveData<>();
    MutableLiveData<Integer> subOrderListLiveData=new MutableLiveData<>();

    OrderRepository orderRepository=new OrderRepository();

    public LiveData<List<Orders>> getAllOrders()
    {
        return orderRepository.getAllOrders();
    }

    public void setSubOrderList(Integer orderId)
    {
        subOrderListLiveData.setValue(orderId);
    }
    public void editStatus(Integer orderId,String status)
    {
        orderRepository.editStatus(orderId, status);
    }

    public void editBales(Integer orderId,Integer bales)
    {
        orderRepository.editBales(orderId,bales);
    }
}
