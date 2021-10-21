package com.example.phonestore.api.customer;

import com.example.phonestore.entity.ResponseMessage;
import com.example.phonestore.entity.location.City;
import com.example.phonestore.entity.login.ResponseActiveEmail;
import com.example.phonestore.entity.login.SendEmail;
import com.example.phonestore.entity.login.SendOTP;
import com.example.phonestore.entity.login.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiActiveUser {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ApiActiveUser active = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiActiveUser.class);

    @POST("customer/send-verify-reset-password")
    Call<ResponseActiveEmail> getOTP(@Body SendEmail email);
    @POST("customer/reset-password")
    Call<ResponseMessage> changePassword(@Body User user);
    @POST("/customer/active-account")
    Call<ResponseMessage> activeAccount(@Body SendOTP sendOTP);
}
