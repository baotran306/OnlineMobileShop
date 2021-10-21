package com.example.phonestore.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;

public class TheSharedPreferences {
    private static final String INFORMATION_USER = "information_user";
    private static final String CART = "CART";
    private Context mContext;
    public TheSharedPreferences(Context mContext){
        this.mContext = mContext;
    }
    public void putStringValue(String key,String value){
        SharedPreferences sharedPreferences = mContext
                .getSharedPreferences(INFORMATION_USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getStringValue(String key){
        SharedPreferences sharedPreferences = mContext
                .getSharedPreferences(INFORMATION_USER,mContext.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public String getProduct(String key){
        SharedPreferences sharedPreferences = mContext
                .getSharedPreferences(CART,mContext.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public void putProduct(String key,String value){
        SharedPreferences sharedPreferences = mContext
                .getSharedPreferences(CART,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }
}
