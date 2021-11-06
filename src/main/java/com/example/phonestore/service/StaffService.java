package com.example.phonestore.service;

import com.example.phonestore.object.GetStaff;
import com.example.phonestore.object.ResponseMessage;
import com.example.phonestore.object.StaffUpdate;
import com.example.phonestore.object.StaffUpload;
import com.example.phonestore.object.user.ChangePassword;
import com.example.phonestore.object.user.PostStaff;
import com.example.phonestore.object.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {
    public List<GetStaff> getStaffList();
    public GetStaff getStaffById(String theId);
    public void deleteStaffById(String theId);
    public void updateStaff(StaffUpdate staffUpdate);
    public Page<GetStaff> findPaginated(Pageable pageable);
    public void postStaff(StaffUpload staffUpload);
    public void resetPassword(User user);
    public PostStaff getProfile(String theId);
    public ResponseMessage updateProfile(PostStaff postStaff);
    public ResponseMessage changePassword(ChangePassword changePassword);

}
