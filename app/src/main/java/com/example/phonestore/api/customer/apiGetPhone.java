package com.example.phonestore.api.customer;

import com.example.phonestore.entity.Brands;
import com.example.phonestore.entity.Colors;
import com.example.phonestore.entity.Phone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface apiGetPhone {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();
    apiGetPhone getPhoneFromApi = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(apiGetPhone.class);
    @GET("admin/get-list-phone")
    Call<ArrayList<Phone>> getPhone();

    @GET("/admin/get-list-phone-type")
    Call<ArrayList<Brands>> getBrands();

    @GET("/admin/get-list-phone-color")
    Call<ArrayList<Colors>> getColors();

}
