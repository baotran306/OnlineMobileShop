package com.example.phonestore.service;

import com.example.phonestore.object.user.User;
import com.example.phonestore.object.user.ResponseLoginMessage;

public interface LoginService {
    public ResponseLoginMessage getUser(User user);
}
