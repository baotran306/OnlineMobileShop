package com.example.phonestore.service;

import com.example.phonestore.DAO.OrderDAO;
import com.example.phonestore.object.CustomerOrder;
import com.example.phonestore.object.SearchDateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Override
    public List<CustomerOrder> getListOrder(SearchDateOrder searchDateOrder) {
        return orderDAO.getListOrder(searchDateOrder);
    }
}
