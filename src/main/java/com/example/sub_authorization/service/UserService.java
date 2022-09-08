package com.example.sub_authorization.service;

import com.example.sub_authorization.dao.UserDao;
import com.example.sub_authorization.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;

    public User addUser(User newUser){
        userDao.insert(newUser);
        return newUser;
    }

    public User getUserByUsername(String username){
        User user = userDao.getUserByUsername(username);
        return user;
    }
}
