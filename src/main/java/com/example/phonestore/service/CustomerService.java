package com.example.phonestore.service;

import com.example.phonestore.object.GetCustomer;
import com.example.phonestore.object.GetStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    public List<GetCustomer> getListCustomer();
    public Page<GetCustomer> findPaginatedCustomer(Pageable pageable);
}
