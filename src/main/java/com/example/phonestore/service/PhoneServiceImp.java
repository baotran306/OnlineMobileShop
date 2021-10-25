package com.example.phonestore.service;

import com.example.phonestore.DAO.PhoneDAO;
import com.example.phonestore.object.Phone;
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
}
