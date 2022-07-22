package com.example.suryatradersemployee.repositories;

import android.util.Log;

import com.example.suryatradersemployee.api.RetroFitClient;
import com.example.suryatradersemployee.model.AuthenticationResponse;
import com.example.suryatradersemployee.model.AuthenticationResult;
import com.example.suryatradersemployee.model.CSRFToken;
import com.example.suryatradersemployee.model.Employee;
import com.example.suryatradersemployee.model.Original;
import com.example.suryatradersemployee.model.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {

    private MutableLiveData<List<Employee>> mutableAdminList;
    private static final String TAG = "EmployeeRepository ";

    public MutableLiveData<Map<String,String>> ACCESS_TOKEN;
    public MutableLiveData<CSRFToken> CSRF_TOKEN=null;
    public HashMap<String,String> accessTokenMap=null;
    ;
    public LiveData<Map<String, String>> getAccessToken()
    {
        return ACCESS_TOKEN;
    }

    public LiveData<CSRFToken> getCSRFToken()
    {
        CSRF_TOKEN=new MutableLiveData<>();
        CSRFToken();


        return CSRF_TOKEN;
    }

    public void CSRFToken()
    {
        Call<CSRFToken> csrfTokenCall= RetroFitClient.getInstance().getApi().getCSRFToken();
        csrfTokenCall.enqueue(new Callback<CSRFToken>() {

            @Override
            public void onResponse(Call<CSRFToken > call, Response<CSRFToken> response) {

                if (response.body()!=null) {

                    CSRFToken csrfToken=response.body();
                    CSRF_TOKEN.setValue(csrfToken);
                }
            }

            @Override
            public void onFailure(Call<CSRFToken> call, Throwable t)
            {
                android.util.Log.d(TAG, "onFailure:"+t.getCause());
                android.util.Log.d(TAG, "onFailure: failed to get CSRF Token");
            }

        });
    }

    public void login(final String mobileNo, String password, final MutableLiveData<Boolean> isSuccessful,final MutableLiveData<String>  authenticationFailure) {

        Call<AuthenticationResponse> call= RetroFitClient.getInstance().getApi().login(mobileNo, password);
        call.enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                AuthenticationResponse authenticationResponse=response.body();
                AuthenticationResult authenticationResult=authenticationResponse.getResult();

                if(response.body().getStatus()==200) {
                    String customerId = authenticationResult.getAdmin_id();
                    Token token = authenticationResult.getToken();
                    Original original = token.getOriginal();
                    String accessToken = original.getAccessToken();
                    accessTokenMap = new HashMap<String, String>();
                    accessTokenMap.put(customerId, accessToken);
                    ACCESS_TOKEN = new MutableLiveData<Map<String, String>>();
                    ACCESS_TOKEN.setValue(accessTokenMap);
                    isSuccessful.postValue(true);
                    Log.d(TAG, "onSuccess: admin login success" + response.code());
                }
                else
                {
                    String message=authenticationResult.getAuthenticationMsg();
                    authenticationFailure.postValue(message);
                    Log.d(TAG, "onFailure: admin login failure- "+message + response.body().getStatus());
                }

            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t)
            {
                android.util.Log.d(TAG, "onFailure:"+t.getCause());
                android.util.Log.d(TAG, "onFailure: failed to login user");
            }
        });

    }


    public void changePassword( String currentPassword,String newPassword,final MutableLiveData<Boolean> isSuccessful, final MutableLiveData<String> changePasswordMsg)
    {
        Call<AuthenticationResponse> call= RetroFitClient.getInstance().getApi().changePassword(currentPassword,newPassword);
        call.enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                if (response.isSuccessful())
                {
                    AuthenticationResponse authenticationResponse = response.body();
                    AuthenticationResult authenticationResult = authenticationResponse.getResult();
                    String msg = authenticationResult.getAuthenticationMsg();
                    changePasswordMsg.postValue(msg);
                    isSuccessful.postValue(true);
                    Log.d(TAG, "onSuccess: change password success " + response.code());
                }
                else
                {
                    AuthenticationResponse authenticationResponse=response.body();
                    AuthenticationResult authenticationResult=authenticationResponse.getResult();
                    String msg=authenticationResult.getAuthenticationMsg();
                    changePasswordMsg.postValue(msg);
                    isSuccessful.postValue(false);
                    Log.d(TAG, "onFailure: failed to change password "+response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to change password ");
            }
        });
    }
}
