package com.example.phonestore.service;

import com.example.phonestore.DAO.PhoneDAO;
import com.example.phonestore.object.Brand;
import com.example.phonestore.object.Color;
import com.example.phonestore.object.Phone;
import com.example.phonestore.object.PhonePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImp implements PhoneService {
    @Autowired
    PhoneDAO phoneDAO;
    @Override
    public List<Phone> getListPhone() {
        return phoneDAO.getListPhone();
    }

    @Override
    public List<Color> getListColor() {
        return phoneDAO.getListColor();
    }

    @Override
    public List<Brand> getListBrand() {
        return phoneDAO.getListBrand();
    }

    @Override
    public void savePhone(PhonePost phone) {
        phoneDAO.savePhone(phone);
    }
}
