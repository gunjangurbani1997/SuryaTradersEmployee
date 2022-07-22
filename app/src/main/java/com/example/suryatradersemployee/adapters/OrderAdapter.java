package com.example.suryatradersemployee.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatradersemployee.databinding.OrderRowBinding;
import com.example.suryatradersemployee.model.Orders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends ListAdapter<Orders, OrderAdapter.OrderViewHolder> {

    private static final String TAG="EmployeeAdapter ";
    private OrderInterface orderInterface;

    public OrderAdapter(OrderInterface orderInterface)
    {
        super(Orders.itemCallback);
        this.orderInterface=orderInterface;


    }

    @NonNull
    @Override
    public com.example.suryatradersemployee.adapters.OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        OrderRowBinding orderRowBinding=OrderRowBinding.inflate(layoutInflater, parent,false);
        orderRowBinding.setOrderInterface(orderInterface);
        return new com.example.suryatradersemployee.adapters.OrderAdapter.OrderViewHolder(orderRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Orders orders =getItem(position);
        holder.orderRowBinding.setOrders(orders);

    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {

        OrderRowBinding orderRowBinding;

        public OrderViewHolder(OrderRowBinding binding)
        {
            super(binding.getRoot());
            this.orderRowBinding=binding;
        }


    }

    public interface OrderInterface
    {
        public void onOrderClick(Integer orderId);
        public void editStatus(final Integer orderId,String orderStatus);
        public void editBalesPacked(final Integer orderId,Integer balesPacked);
    }


}
