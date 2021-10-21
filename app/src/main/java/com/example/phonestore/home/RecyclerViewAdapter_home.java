package com.example.phonestore.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonestore.R;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.menu.activityPhone;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class RecyclerViewAdapter_home extends RecyclerView.Adapter<RecyclerViewAdapter_home.MyViewHolder> {

    List<Phone> listPhone;
    Context context;
    private static String url = "http://10.0.2.2:5000/get-image/";

    public RecyclerViewAdapter_home(List<Phone> listPhone, Context context) {
        this.listPhone = listPhone;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.cardview_item_phone,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.tv_phone_title.setText(listPhone.get(position).getPhoneName());
        Picasso.with(context).load(url+listPhone.get(position).getImage()).into(holder.img_phone_thumbnail);
        holder.phonePrice.setText(formatter.format(listPhone.get(position).getPrice())+" VNĐ");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPhone(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhone.size();
    }

    public void openActivityPhone(int position){
        Intent intent = new Intent(context, activityPhone.class);

        Phone phone = new Phone();
        phone = listPhone.get(position);
        intent.putExtra("Phone", phone);
        intent.putExtra("show", "disShow");
        context.startActivity(intent);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_phone_title;
        ImageView img_phone_thumbnail;
        TextView phonePrice;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_phone_title = (TextView) itemView.findViewById(R.id.phone_title_id);
            img_phone_thumbnail = (ImageView) itemView.findViewById(R.id.img_phone_id);
            phonePrice =(TextView) itemView.findViewById(R.id.phonePrice_id);
            cardView = (CardView) itemView.findViewById((R.id.cardview_id));
        }
    }
}
