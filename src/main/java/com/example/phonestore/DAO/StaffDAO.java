package com.example.phonestore.DAO;

import com.example.phonestore.object.GetStaff;
import com.example.phonestore.object.ResponseMessage;
import com.example.phonestore.object.StaffUpdate;
import com.example.phonestore.object.StaffUpload;
import com.example.phonestore.object.user.ChangePassword;
import com.example.phonestore.object.user.PostStaff;
import com.example.phonestore.object.user.User;

import java.util.List;

public interface StaffDAO {
    public List<GetStaff> getStaffList();
    public GetStaff getStaffById(String theId);
    public void deleteStaffById(String theId);
    public void updateStaff(StaffUpdate staffUpdate);
    public void postStaff(StaffUpload staffUpload);
    public void resetPassword(User user);
    public PostStaff getProfile(String theId);
    public ResponseMessage updateProfile(PostStaff postStaff);
    public ResponseMessage changePassword(ChangePassword changePassword);
}
