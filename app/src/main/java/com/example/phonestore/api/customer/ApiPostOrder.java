package com.example.phonestore.api.customer;

import com.example.phonestore.entity.CustomerOrder;
import com.example.phonestore.entity.ResponseMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiPostOrder {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ApiPostOrder postOrder = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiPostOrder.class);

    @POST("customer/insert-order")
    Call<ResponseMessage> postOrder(@Body CustomerOrder customerOrder);
}
