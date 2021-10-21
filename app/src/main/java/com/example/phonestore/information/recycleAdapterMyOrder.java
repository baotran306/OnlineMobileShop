package com.example.phonestore.information;

import static com.example.phonestore.api.customer.apiGetPhone.getPhoneFromApi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonestore.R;
import com.example.phonestore.entity.Orderdetail.PhoneOrder;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.menu.RecyclerViewAdapter;
import com.example.phonestore.menu.activityPhone;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class recycleAdapterMyOrder extends RecyclerView.Adapter<recycleAdapterMyOrder.MyViewHolder> {

    private List<PhoneOrder> mData;
    private int Status;
    private Context mContext;
    private static String url = "http://10.0.2.2:5000/get-image/";

    public recycleAdapterMyOrder(List<PhoneOrder> mData, int Status) {
        this.mData = mData;
        this.Status = Status;
    }

    @NonNull
    @Override
    public recycleAdapterMyOrder.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.item_myorder, parent, false);
        mContext = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleAdapterMyOrder.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        holder.textViewInformation.setText(mData.get(position).getPhoneName());
        holder.textViewPrice.setText(formatter.format(mData.get(position).getPrice()) + " đ" + " x "
                + mData.get(position).getQuantity() + " sản phẩm" + " | " + "Ngày mua " + mData.get(position).getDate());
        Picasso.with(mContext).load(url+mData.get(position).getImage()).into(holder.imageView);
        holder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShowPhone(position);
            }
        });
    }
    private void openShowPhone(int position){
        getPhoneFromApi.getPhone().enqueue(new Callback<ArrayList<Phone>>() {
            @Override
            public void onResponse(Call<ArrayList<Phone>> call, Response<ArrayList<Phone>> response) {
                for(Phone phone : response.body()){
                    if(phone.getId()==mData.get(position).getPhoneId()){
                        Intent intent = new Intent(mContext, activityPhone.class);
                        intent.putExtra("Phone",phone);
                        intent.putExtra("show","show");
                        mContext.startActivity(intent);
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Phone>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewInformation, textViewPrice;
        Button buttonDetail, buttonReBuy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewMyOrder);
            textViewInformation = (TextView) itemView.findViewById(R.id.informationItem_myOrder_id);
            textViewPrice = (TextView) itemView.findViewById(R.id.priceMyorder_id);
            buttonDetail = (Button) itemView.findViewById(R.id.btn_details_id);
        }
    }
}
