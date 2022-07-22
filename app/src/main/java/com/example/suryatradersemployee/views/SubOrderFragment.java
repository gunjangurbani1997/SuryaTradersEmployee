package com.example.suryatradersemployee.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.suryatradersemployee.adapters.SubOrderAdapter;
import com.example.suryatradersemployee.databinding.FragmentSubOrderBinding;
import com.example.suryatradersemployee.model.SubOrders;
import com.example.suryatradersemployee.viewmodels.SubOrderViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;


public class SubOrderFragment extends Fragment {

    FragmentSubOrderBinding fragmentSubOrderBinding;
    SubOrderViewModel subOrderViewModel;
    SubOrderAdapter subOrderAdapter;

    public SubOrderFragment() {

    }


    public static com.example.suryatradersemployee.views.SubOrderFragment newInstance(String param1, String param2) {
        com.example.suryatradersemployee.views.SubOrderFragment fragment = new com.example.suryatradersemployee.views.SubOrderFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      fragmentSubOrderBinding= FragmentSubOrderBinding.inflate(inflater,container,false);
      return fragmentSubOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Integer orderId = null;
        if (getArguments() != null) {

           SubOrderFragmentArgs args = SubOrderFragmentArgs.fromBundle(getArguments());
            orderId=args.getOrderId();

        }

        subOrderAdapter = new SubOrderAdapter();
        fragmentSubOrderBinding.subOrderRecyclerview.setAdapter(subOrderAdapter);

        subOrderViewModel=new ViewModelProvider(requireActivity()).get(SubOrderViewModel.class);

        subOrderViewModel.getCustomerOrderDetailList(orderId).observe(getViewLifecycleOwner(), new Observer<List<SubOrders>>() {
            @Override
            public void onChanged(List<SubOrders> subOrdersList) {
                subOrderAdapter.submitList(subOrdersList);
            }
        });

    }


}