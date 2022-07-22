package com.example.suryatradersemployee.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import com.example.suryatradersemployee.R;
import com.example.suryatradersemployee.adapters.OrderAdapter;
import com.example.suryatradersemployee.databinding.FragmentOrderBinding;
import com.example.suryatradersemployee.model.Customer;
import com.example.suryatradersemployee.model.Orders;
import com.example.suryatradersemployee.viewmodels.OrderViewModel;
import com.example.suryatradersemployee.viewmodels.SubOrderViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class OrderFragment extends Fragment implements OrderAdapter.OrderInterface{

    FragmentOrderBinding fragmentOrderBinding;
    OrderViewModel orderViewModel;
    OrderAdapter orderAdapter;
    NavController navController;
    SubOrderViewModel subOrderViewModel;
    Spinner statusUpdateSpinner;
    private static final String TAG="OrderFragment ";

    public OrderFragment() {

    }


    public static com.example.suryatradersemployee.views.OrderFragment newInstance(String param1, String param2) {
        com.example.suryatradersemployee.views.OrderFragment fragment = new com.example.suryatradersemployee.views.OrderFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       fragmentOrderBinding=FragmentOrderBinding.inflate(inflater,container,false);
       return fragmentOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderAdapter=new OrderAdapter(this);
        fragmentOrderBinding.orderRecyclerview.setAdapter(orderAdapter);
        orderViewModel=new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        orderViewModel.getAllOrders().observe(getViewLifecycleOwner(), new Observer<List<Orders>>() {
            @Override
            public void onChanged(List<Orders> orders) {
                orderAdapter.submitList(orders);
            }
        });
        navController= Navigation.findNavController(view);

    }

    @Override
    public void onOrderClick(Integer orderId) {

        Log.d(TAG, "onOrderClick "+orderId);

        orderViewModel.setSubOrderList(orderId);
        OrderFragmentDirections.ActionOrderFragmentToSubOrderFragment action = OrderFragmentDirections.actionOrderFragmentToSubOrderFragment(orderId);
        action.setOrderId(orderId);
        navController.navigate(action);
    }

    @Override
    public void editStatus(final Integer orderId,String orderStatus) {

        final View view=getLayoutInflater().inflate(R.layout.change_order_status_spinner,null,false);

        Log.d(TAG,"Update Order Status ");
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Update Status");

        List<String> status=new ArrayList<>();
        status.add("Pending");
        status.add("On-Going");
        status.add("Dispatched");


        statusUpdateSpinner=view.findViewById(R.id.changeOrderStatusSpinner);
        ArrayAdapter statusUpdateAdapter=new ArrayAdapter(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,status);
        statusUpdateSpinner.setAdapter(statusUpdateAdapter);

        if(orderStatus!=null) {
            int position = statusUpdateAdapter.getPosition(orderStatus);
            statusUpdateSpinner.setSelection(position);
        }

        View titleView = getLayoutInflater().inflate(R.layout.change_order_status_title, null);
        builder.setCustomTitle(titleView);
        builder.setView(view);
        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(statusUpdateSpinner.getSelectedItem()!=null)
                {
                    String status = statusUpdateSpinner.getSelectedItem().toString().toLowerCase();
                    status=status.replaceAll("-","");
                    orderViewModel.editStatus(orderId,status);
                    orderViewModel.getAllOrders().observe(getViewLifecycleOwner(), new Observer<List<Orders>>() {
                        @Override
                        public void onChanged(List<Orders> orders) {
                            orderAdapter.submitList(orders);
                        }
                    });
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void editBalesPacked(final Integer orderId,Integer balesPacked) {


        final View view=getLayoutInflater().inflate(R.layout.edit_bales_packed_text_view,null,false);

        Log.d(TAG,"Update no. of bales ");
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

        /*final EditText balesEditText=new EditText(getActivity());*/
        final EditText balesEditText= view.findViewById(R.id.edit_bales_text_view);
        final String bales=balesEditText.getText().toString();
        View titleView = getLayoutInflater().inflate(R.layout.change_order_status_title, null);
        builder.setCustomTitle(titleView);


        if(bales!=null && (!bales.equals(""))) {
            balesEditText.setText(balesPacked);
        }
        builder.setView(view);

        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Integer no_of_bales=Integer.parseInt(balesEditText.getText().toString());
                orderViewModel.editBales(orderId,no_of_bales);
                orderViewModel.getAllOrders().observe(getViewLifecycleOwner(), new Observer <List<Orders>> (){
                    @Override
                    public void onChanged(List<Orders> orders ) {
                        orderAdapter.submitList(orders);
                    }
                });

            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}