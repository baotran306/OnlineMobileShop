package com.example.phonestore.DAO;

import com.example.phonestore.object.user.User;
import com.example.phonestore.object.user.ResponseLoginMessage;

public interface LoginDAO {
    public ResponseLoginMessage getUser(User user);
}
