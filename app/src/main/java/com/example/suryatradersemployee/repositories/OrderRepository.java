package com.example.suryatradersemployee.repositories;

import android.util.Log;


import com.example.suryatradersemployee.api.RetroFitClient;
import com.example.suryatradersemployee.model.OrderResponse;
import com.example.suryatradersemployee.model.OrderResult;
import com.example.suryatradersemployee.model.Orders;
import com.example.suryatradersemployee.model.UpdateStatusResponse;
import com.example.suryatradersemployee.model.UpdateStatusResult;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {

    private MutableLiveData<List<Orders>> mutableOrdersList;

    private static final String TAG = "OrderRepository ";

    public LiveData<List<Orders>> getAllOrders()
    {
      /*  if (mutableOrdersList==null)
        {*/
            mutableOrdersList=new MutableLiveData<>();
            loadOrdersList();
       /* }*/
        return mutableOrdersList;
    }

    private void loadOrdersList() {

        Call<OrderResponse> call= RetroFitClient.getInstance().getApi().getAllOrders();
        call.enqueue(new Callback<OrderResponse>() {

            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful())
                {
                    Log.d(TAG,"Order List ");
                    System.out.println(response.body());

                    OrderResponse orderResponse=response.body();
                    OrderResult orderResult=orderResponse.getOrderResponseResult();
                    List<Orders> orderList=orderResult.getOrderList();
                    mutableOrdersList.setValue(orderList);
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch order list from server ");
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to fetch order list from server");
            }
        });
    }

    public void editStatus(Integer orderId,String status)
    {
        Call<UpdateStatusResponse> call= RetroFitClient.getInstance().getApi().editStatus(orderId,status);
        call.enqueue(new Callback<UpdateStatusResponse>() {

            @Override
            public void onResponse(Call<UpdateStatusResponse> call, Response<UpdateStatusResponse> response) {

                if (response.isSuccessful())
                {
                    UpdateStatusResponse updateStatusResponse=response.body();
                    UpdateStatusResult updateStatusResult=updateStatusResponse.getUpdateStatusResult();
                    String updateStatus=updateStatusResult.getUpdateStatus();
   //                 loadOrdersList();
//                    mutableEmployeeType.setValue(employeeType);
                    Log.d(TAG, "onSuccess: edit status "+response.body().getUpdateStatusResponse()+" "+ updateStatus);
                }
                else
                {
                    Log.d(TAG, "onFailure: edit status "+response.body().getUpdateStatusResponse());
                }
            }

            @Override
            public void onFailure(Call<UpdateStatusResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: edit status ");
            }
        });
    }

    public void editBales(Integer orderId,Integer bales)
    {
        Call<UpdateStatusResponse> call= RetroFitClient.getInstance().getApi().editBales(orderId,bales);
        call.enqueue(new Callback<UpdateStatusResponse>() {

            @Override
            public void onResponse(Call<UpdateStatusResponse> call, Response<UpdateStatusResponse> response) {

                if (response.isSuccessful())
                {
                    UpdateStatusResponse updateStatusResponse=response.body();
                    UpdateStatusResult updateStatusResult=updateStatusResponse.getUpdateStatusResult();
                    String updateStatus=updateStatusResult.getUpdateStatus();
                    loadOrdersList();
//                    mutableEmployeeType.setValue(employeeType);
                    Log.d(TAG, "onSuccess: edit bales success "+response.body().getUpdateStatusResponse()+" "+ updateStatus);
                }
                else
                {
                    Log.d(TAG, "onFailure: edit bales "+response.body().getUpdateStatusResponse());
                }
            }

            @Override
            public void onFailure(Call<UpdateStatusResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: edit bales ");
            }
        });
    }
}
