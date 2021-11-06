package com.example.phonestore.api.customer;

import com.example.phonestore.entity.ChangePassword;
import com.example.phonestore.entity.Person;
import com.example.phonestore.entity.ResponseMessage;
import com.example.phonestore.entity.login.User;
import com.example.phonestore.entity.login.responseLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface apiUser {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    apiUser apiPostUser = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(apiUser.class);

    @Headers("Content-Type: application/json")
    @POST("customer/login")
    Call<responseLogin> postUser(@Body User user);

    @POST("person/change-info")
    Call<ResponseMessage> updateUser(@Body Person person);

    @POST("customer/change-password")
    Call<ResponseMessage> changePassword(@Body ChangePassword changePassword);
}
