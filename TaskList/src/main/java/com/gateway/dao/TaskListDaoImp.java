package com.gateway.dao;

import com.gateway.model.TaskList;
import com.gateway.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Jayavardhan on 12/10/15.
 */
public class TaskListDaoImp extends GenericDaoImp<TaskList> implements TaskListDao{


    @Override
    public List<TaskList> findTaskListByUser(Long user_id) {
        Criteria criteria = getCriteria().add(Restrictions.eq("user.id",user_id));
        return criteria.list();
    }
}
