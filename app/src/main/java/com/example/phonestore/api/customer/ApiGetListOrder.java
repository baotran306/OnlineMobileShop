package com.example.phonestore.api.customer;

import com.example.phonestore.entity.Orderdetail.ResponseStatusOrder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiGetListOrder {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();
    ApiGetListOrder getOrderDetail = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiGetListOrder.class);

    @GET("customer/check-history-order/{customerID}")
    Call<ResponseStatusOrder> getHistory(@Path("customerID")String id);
}
