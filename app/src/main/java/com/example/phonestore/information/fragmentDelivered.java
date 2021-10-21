package com.example.phonestore.information;

import static com.example.phonestore.api.customer.ApiGetListOrder.getOrderDetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonestore.R;
import com.example.phonestore.api.customer.ApiGetListOrder;
import com.example.phonestore.entity.Orderdetail.Detail;
import com.example.phonestore.entity.Orderdetail.PhoneOrder;
import com.example.phonestore.entity.Orderdetail.ResponseStatusOrder;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.menu.RecyclerViewAdapter;
import com.example.phonestore.sharedPreferences.DataLocalManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragmentDelivered extends Fragment {

    private RecyclerView myRecyclerviewDelivered;
    private RecyclerViewAdapter adapterDelivered;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivered, container, false);
        // Inflate the layout for this fragment
        initUI(view);
        getApiOrderDetail();
        return view;
    }

    private void initUI(View view) {
        myRecyclerviewDelivered = (RecyclerView) view.findViewById(R.id.Recyclerview_delivered);
    }

    private void getApiOrderDetail(){
        info info = DataLocalManager.getInfo();
        getOrderDetail.getHistory(info.getId()).enqueue(new Callback<ResponseStatusOrder>() {
            @Override
            public void onResponse(Call<ResponseStatusOrder> call, Response<ResponseStatusOrder> response) {
                List<PhoneOrder> phoneOrders = new ArrayList<>();
                ResponseStatusOrder statusOrder = response.body();
                for(Detail detail : statusOrder.getDetails()){
                    for (PhoneOrder order : detail.getListOrder()){
                        if(detail.getStatusId()==3) {
                            order.setDate(detail.getDate());
                            phoneOrders.add(order);
                        }
                    }
                }
                addData(phoneOrders);
            }

            @Override
            public void onFailure(Call<ResponseStatusOrder> call, Throwable t) {

            }
        });
    }

    private void addData(List<PhoneOrder> data){
        myRecyclerviewDelivered.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerviewDelivered.setAdapter(new recycleAdapterMyOrder(data,3));
    }

}