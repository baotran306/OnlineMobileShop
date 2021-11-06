package com.example.phonestore.DAO;

import com.example.phonestore.object.*;
import com.example.phonestore.object.PostColor;

import java.util.List;

public interface PhoneDAO {
    public List<Phone> getListPhone();
    public List<Color> getListColor();
    public List<Brand> getListBrand();
    public void savePhone(PhonePost phone);
    public void updatePhone(PhonePut phone);
    public void postColor(PostColor color);
    public void postBrand(PostBrand brand);
}
