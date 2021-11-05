package com.example.phonestore.DAO;

import com.example.phonestore.object.*;

import java.util.List;

public interface PhoneDAO {
    public List<Phone> getListPhone();
    public List<Color> getListColor();
    public List<Brand> getListBrand();
    public void savePhone(PhonePost phone);
    public void updatePhone(PhonePut phone);
}