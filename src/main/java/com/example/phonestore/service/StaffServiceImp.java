package com.example.phonestore.service;

import com.example.phonestore.DAO.StaffDAO;
import com.example.phonestore.object.GetStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImp implements StaffService {
    @Autowired
    StaffDAO staffDAO;

    @Override
    public List<GetStaff> getStaffList() {
        return staffDAO.getStaffList();
    }
}
