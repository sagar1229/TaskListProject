package com.gateway.service;

import com.gateway.dao.UserDao;
import com.gateway.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Jayavardhan on 12/9/15.
 */
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public User findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void onCreate(User user) {
        dao.save(user);
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }
}
