package com.example.phonestore.api.customer;

import com.example.phonestore.entity.login.Register;
import com.example.phonestore.entity.login.ResponseActiveEmail;
import com.example.phonestore.entity.login.User;
import com.example.phonestore.entity.login.responseLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiRegisterAccount {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ApiRegisterAccount apiPostRegister = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiRegisterAccount.class);

    @Headers("Content-Type: application/json")
    @POST("customer/register")
    Call<ResponseActiveEmail> registerAccount(@Body Register register);

}
