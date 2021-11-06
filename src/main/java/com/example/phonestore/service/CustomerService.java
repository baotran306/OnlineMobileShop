package com.example.phonestore.service;

import com.example.phonestore.object.GetCustomer;
import com.example.phonestore.object.GetStaff;
import com.example.phonestore.object.ResponseMessageGetHistoryOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    public List<GetCustomer> getListCustomer();
    public Page<GetCustomer> findPaginatedCustomer(Pageable pageable);
    public ResponseMessageGetHistoryOrder getListHistory(String theId);

}
