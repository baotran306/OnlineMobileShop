package com.example.phonestore.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.phonestore.entity.Phone;
import com.example.phonestore.entity.login.info;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataLocalManager {

    public static final String INFORMATION_USER_OBJECT = "INFORMATION_USER_OBJECT";
    public static final String PRODUCTS_CART = "PRODUCTS_CART";
    private static DataLocalManager instance;
    private TheSharedPreferences theSharedPreferences;

    public static void init(Context mContext) {
        instance = new DataLocalManager();
        instance.theSharedPreferences = new TheSharedPreferences(mContext);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setInformationUser(info info) {
        Gson gson = new Gson();
        String jSonInfo = gson.toJson(info);
        DataLocalManager.getInstance().theSharedPreferences.putStringValue(INFORMATION_USER_OBJECT, jSonInfo);
    }

    public static info getInfo() {
        String jsonInfo = DataLocalManager.getInstance().theSharedPreferences.getStringValue(INFORMATION_USER_OBJECT);
        Gson gson = new Gson();
        info info = gson.fromJson(jsonInfo, info.class);
        return info;
    }

    public static void setAddProduct(Phone phoneAdd) {
        Gson gson = new Gson();
        String jsonSave = DataLocalManager.getInstance().theSharedPreferences.getProduct(PRODUCTS_CART);
        String jsonNewProductAdd = gson.toJson(phoneAdd);
        JsonArray jsonArrayListProduct = new JsonArray();
        try {
            if (jsonSave.length()>13) {
                jsonArrayListProduct.add(jsonArrayListProduct);
            }
            jsonArrayListProduct.add(jsonNewProductAdd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataLocalManager.getInstance().theSharedPreferences.putProduct(PRODUCTS_CART, jsonArrayListProduct.toString());
    }

    public static String getListCart() {
        Gson gson = new Gson();
        List<Phone> listProduct = new ArrayList<>();
        String jsonListPhone = getInstance().theSharedPreferences.getProduct(PRODUCTS_CART);
        JsonArray jsonArrayProduct = new JsonArray();
        try {
            if (jsonListPhone.length() != 0) {
                jsonArrayProduct.add(jsonListPhone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            for (int i = 0; i < jsonArrayProduct.length(); i++) {
//                JSONObject jsonObject = jsonArrayProduct.getJSONObject(i);
//                Phone phone = new Phone();
//                phone.setId(Integer.parseInt(jsonObject.getString("id")));
//                phone.setPhoneName(jsonObject.getString(""));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return jsonListPhone.toString();
    }
}
