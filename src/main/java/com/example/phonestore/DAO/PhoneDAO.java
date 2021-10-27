package com.example.phonestore.DAO;

import com.example.phonestore.object.Brand;
import com.example.phonestore.object.Color;
import com.example.phonestore.object.Phone;
import com.example.phonestore.object.PhonePost;

import java.util.List;

public interface PhoneDAO {
    public List<Phone> getListPhone();
    public List<Color> getListColor();
    public List<Brand> getListBrand();
    public void savePhone(PhonePost phone);
}
