package com.example.phonestore.DAO;

import com.example.phonestore.object.GetStaff;
import com.example.phonestore.object.StaffUpdate;
import com.example.phonestore.object.StaffUpload;

import java.util.List;

public interface StaffDAO {
    public List<GetStaff> getStaffList();
    public GetStaff getStaffById(String theId);
    public void deleteStaffById(String theId);
    public void updateStaff(StaffUpdate staffUpdate);
    public void postStaff(StaffUpload staffUpload);

}
