package com.gateway.dao;

import com.gateway.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Jayavardhan on 12/9/15.
 */
public class UserDaoImp extends GenericDaoImp<User> implements UserDao {



    @Override
    public User findByEmail(String email) {
        Criteria criteria= getCriteria().add(Restrictions.eq("email",email));
        return (User)criteria.uniqueResult();
    }
    

    @Override
    public List<User> findAllUsers() {
        Criteria criteria = getCriteria();
        return criteria.list();
    }
}
