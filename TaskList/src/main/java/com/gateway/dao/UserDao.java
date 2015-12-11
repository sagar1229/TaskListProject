package com.gateway.dao;

import com.gateway.model.User;

import java.util.List;

/**
 * Created by Jayavardhan on 12/9/15.
 */
public interface UserDao extends GenericDao<User> {


    User findByEmail(String email);


    List<User> findAllUsers();
}
