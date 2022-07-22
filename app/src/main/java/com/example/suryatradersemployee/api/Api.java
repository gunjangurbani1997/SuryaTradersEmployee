package com.example.suryatradersemployee.api;


import com.example.suryatradersemployee.model.AuthenticationResponse;
import com.example.suryatradersemployee.model.CSRFToken;
import com.example.suryatradersemployee.model.OrderResponse;
import com.example.suryatradersemployee.model.SubOrderResponse;
import com.example.suryatradersemployee.model.UpdateStatusResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("employee/login")
    Call<AuthenticationResponse> login(@Field("mobile") String mobile, @Field("password") String password);

    @GET("csrf")
    Call<CSRFToken> getCSRFToken();

    @GET("employee/order/list")
    Call<OrderResponse> getAllOrders();

    @FormUrlEncoded
    @POST("employee/order/status/update?")
    Call<UpdateStatusResponse> editStatus(@Field("orderId") Integer orderId, @Field("orderStatus")String status);

    @FormUrlEncoded
    @POST("employee/order/bales/update?")
    Call<UpdateStatusResponse> editBales(@Field("orderId") Integer orderId,@Field("bales")Integer bales);

    @GET("employee/order/detail?")
    Call<SubOrderResponse> getSubOrderList(@Query("order_id") String orderId);

    @FormUrlEncoded
    @POST("employee/changePassword")
    Call<AuthenticationResponse> changePassword(@Field("current_password") String currentPassword, @Field("new_password") String newPassword);


}
