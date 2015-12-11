package com.gateway.service;

import com.gateway.model.User;

import java.util.List;

/**
 * Created by Jayavardhan on 12/9/15.
 */
public interface UserService {


    User findByEmail(String email);

    List<User> findAllUsers();


    User findById(Long id);

    void onCreate(User user);

    void updateUser(User user);


}
