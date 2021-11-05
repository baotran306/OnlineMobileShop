package com.example.phonestore.service;

import com.example.phonestore.DAO.LoginDAO;
import com.example.phonestore.object.user.User;
import com.example.phonestore.object.user.ResponseLoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService{
    @Autowired
    LoginDAO loginDAO;
    @Override
    public ResponseLoginMessage getUser(User user) {
        return loginDAO.getUser(user);
    }
}
