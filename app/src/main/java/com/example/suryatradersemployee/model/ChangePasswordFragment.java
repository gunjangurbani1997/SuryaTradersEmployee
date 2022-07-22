package com.example.suryatradersemployee.model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.suryatradersemployee.R;
import com.example.suryatradersemployee.databinding.FragmentChangePasswordBinding;
import com.example.suryatradersemployee.viewmodels.EmployeeViewModel;

public class ChangePasswordFragment extends Fragment {

    private NavController navController;
    private FragmentChangePasswordBinding fragmentChangePasswordBinding;
    private EmployeeViewModel employeeViewModel;

    public ChangePasswordFragment() { }

    public static ChangePasswordFragment newInstance(String param1, String param2) {
        ChangePasswordFragment fragment = new ChangePasswordFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentChangePasswordBinding= FragmentChangePasswordBinding.inflate(inflater,container,false);
        return  fragmentChangePasswordBinding.getRoot();
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        employeeViewModel=new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);
        fragmentChangePasswordBinding.setEmployeeViewModel(employeeViewModel);

        fragmentChangePasswordBinding.btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeViewModel.changePassword();
                employeeViewModel.getChangePasswordMsg().observe(getViewLifecycleOwner(), new Observer<String>()
                {
                    public void onChanged(String message)
                    {
                        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        navController = Navigation.findNavController(view);
    }
}