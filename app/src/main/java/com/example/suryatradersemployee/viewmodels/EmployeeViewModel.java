package com.example.suryatradersemployee.viewmodels;

import com.example.suryatradersemployee.model.CSRFToken;
import com.example.suryatradersemployee.model.Employee;
import com.example.suryatradersemployee.repositories.EmployeeRepository;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmployeeViewModel extends ViewModel {

    EmployeeRepository employeeRepository=new EmployeeRepository();

    private Employee employee;

    private MutableLiveData<Boolean> mIsSuccessful;
    private MutableLiveData<String> mAuthenticationFailure;

    //should be declared final becasue bindings only detect changes in the field's value, not of the field itself.
    public final MutableLiveData<String> mobileNo = new MutableLiveData<>();
    public final MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<String> mChangePasswordMsg;

    public final MutableLiveData<String> current_password = new MutableLiveData<>();
    public final MutableLiveData<String> new_password = new MutableLiveData<>();
    public final MutableLiveData<String> confirm_password = new MutableLiveData<>();

    public final MutableLiveData<String> errorMobileNo = new MutableLiveData<>();
    public final MutableLiveData<String> errorPassword = new MutableLiveData<>();


    public EmployeeViewModel()
    {
        mIsSuccessful = new MutableLiveData<>();
        mAuthenticationFailure=new MutableLiveData<>();
        mChangePasswordMsg=new MutableLiveData<>();
    }

    private boolean validateInputs()
    {
        boolean isValid = true;


        if (mobileNo.getValue() == null || mobileNo.getValue().length()!=10) {
            System.out.println("Mobile no "+mobileNo.getValue()+" Password "+password.getValue());
            errorMobileNo.setValue("Invalid Mobile No");
            isValid = false;

        } else {
            errorMobileNo.setValue("");
        }

        if (password.getValue() == null || password.getValue().length() < 6) {
            errorPassword.setValue("Password too short");

            isValid = false;

        } else {
            errorPassword.setValue("");
        }

        return isValid;
    }
    private Boolean validateNewOldPassword()
    {
        boolean isValid = true;

        if (current_password.getValue() == null) {
            mChangePasswordMsg.setValue("Invalid current password!");
            isValid = false;

        }

        if (new_password.getValue() == null || new_password.getValue().length() < 6) {
            mChangePasswordMsg.setValue("Invalid new password");
            isValid = false;

        }

        if (confirm_password.getValue() == null || confirm_password.getValue().length() < 6) {
            mChangePasswordMsg.setValue("Invalid confirm password!");
            isValid = false;
        }

        if(confirm_password.getValue()!=null && new_password.getValue()!=null) {
            if (!(confirm_password.getValue().equals(new_password.getValue()))) {
                mChangePasswordMsg.setValue("New Password and Confirm Password do not match!");
                isValid = false;
            }
        }

        return isValid;

    }

    public void changePassword() {
        if(validateNewOldPassword()) {
            employeeRepository.changePassword(current_password.getValue(), new_password.getValue(), mIsSuccessful, mChangePasswordMsg);
        } else {
            mIsSuccessful.postValue(false);
        }
    }

    public  LiveData<String> getChangePasswordMsg() {
        return mChangePasswordMsg;
    }



    public void onLoginClick() {
        if(validateInputs()) {
            employeeRepository.login(mobileNo.getValue(), password.getValue(), mIsSuccessful,mAuthenticationFailure);
        } else {
            mIsSuccessful.postValue(false);
            mAuthenticationFailure.setValue("Authentication Failed");
        }

    }
    public LiveData<Boolean> getIsSuccessful() {
        return mIsSuccessful;
    }

    public  LiveData<String> getAuthenticationFailure() {
        return mAuthenticationFailure;
    }


    public LiveData<Map<String,String>> getAccessToken() {
        return employeeRepository.getAccessToken();
    }

    public LiveData<CSRFToken> getCSRFToken() {

        return employeeRepository.getCSRFToken();
    }
}
