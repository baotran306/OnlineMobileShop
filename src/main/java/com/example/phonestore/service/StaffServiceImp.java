package com.example.phonestore.service;

import com.example.phonestore.DAO.StaffDAO;
import com.example.phonestore.object.GetStaff;
import com.example.phonestore.object.ResponseMessage;
import com.example.phonestore.object.StaffUpdate;
import com.example.phonestore.object.StaffUpload;
import com.example.phonestore.object.user.ChangePassword;
import com.example.phonestore.object.user.PostStaff;
import com.example.phonestore.object.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StaffServiceImp implements StaffService {
    @Autowired
    StaffDAO staffDAO;

    @Override
    public List<GetStaff> getStaffList() {
        return staffDAO.getStaffList();
    }

    @Override
    public GetStaff getStaffById(String theId) {
        return staffDAO.getStaffById(theId);
    }

    @Override
    public void deleteStaffById(String theId) {
        staffDAO.deleteStaffById(theId);
    }

    @Override
    public void updateStaff(StaffUpdate staffUpdate) {
        staffDAO.updateStaff(staffUpdate);
    }

    @Override
    public Page<GetStaff> findPaginated(Pageable pageable) {
        List<GetStaff> staffs = new ArrayList<>();
        for (GetStaff staff:staffDAO.getStaffList()){
            if(staff.getIsDeleted()==0){
                staffs.add(staff);
            }
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<GetStaff> list;

        if (staffs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, staffs.size());
            list = staffs.subList(startItem, toIndex);
        }

        Page<GetStaff> phonePage
                = new PageImpl<GetStaff>(list, PageRequest.of(currentPage, pageSize), staffs.size());

        return phonePage;
    }

    @Override
    public ResponseMessage postStaff(StaffUpload staffUpload) {
        return staffDAO.postStaff(staffUpload);
    }

    @Override
    public void resetPassword(User user) {
        staffDAO.resetPassword(user);
    }

    @Override
    public PostStaff getProfile(String theId) {
        return staffDAO.getProfile(theId);
    }

    @Override
    public ResponseMessage updateProfile(PostStaff postStaff) {
        return staffDAO.updateProfile(postStaff);
    }

    @Override
    public ResponseMessage changePassword(ChangePassword changePassword) {
        return staffDAO.changePassword(changePassword);
    }
}
