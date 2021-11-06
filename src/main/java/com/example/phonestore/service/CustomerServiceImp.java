package com.example.phonestore.service;

import com.example.phonestore.DAO.CustomerDAO;
import com.example.phonestore.object.GetCustomer;
import com.example.phonestore.object.GetStaff;
import com.example.phonestore.object.ResponseMessageGetHistoryOrder;
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
public class CustomerServiceImp implements CustomerService {
    @Autowired
    CustomerDAO customerDAO;
    @Override
    public List<GetCustomer> getListCustomer() {

        return customerDAO.getListCustomer();
    }

    @Override
    public Page<GetCustomer> findPaginatedCustomer(Pageable pageable) {
        List<GetCustomer> customers = customerDAO.getListCustomer();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<GetCustomer> list;

        if (customers.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, customers.size());
            list = customers.subList(startItem, toIndex);
        }

        Page<GetCustomer> customerPage
                = new PageImpl<GetCustomer>(list, PageRequest.of(currentPage, pageSize), customers.size());

        return customerPage;    }

    @Override
    public ResponseMessageGetHistoryOrder getListHistory(String theId) {
        return customerDAO.getListHistory(theId);
    }
}
