package com.example.phonestore.api.apiLocation;

import com.example.phonestore.entity.location.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiLocationService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    apiLocationService apiLocation = new Retrofit.Builder()
            .baseUrl("https://provinces.open-api.vn/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(apiLocationService.class);

    @GET("api/")
    Call<ArrayList<City>> convertCity(@Query("depth") int depth);
}
