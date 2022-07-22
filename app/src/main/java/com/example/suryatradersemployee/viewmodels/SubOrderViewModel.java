package com.example.suryatradersemployee.viewmodels;


import com.example.suryatradersemployee.model.SubOrders;
import com.example.suryatradersemployee.repositories.SubOrderRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SubOrderViewModel extends ViewModel {

    SubOrderRepository subOrderRepository=new SubOrderRepository();
    public LiveData<List<SubOrders>> getCustomerOrderDetailList( Integer orderId)
    {
      return subOrderRepository.getSubOrderList(orderId);
    }
}
