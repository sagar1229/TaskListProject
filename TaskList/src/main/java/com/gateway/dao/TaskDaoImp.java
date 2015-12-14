package com.gateway.dao;

import com.gateway.model.Task;
import com.gateway.model.TaskList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Jayavardhan on 12/10/15.
 */
public class TaskDaoImp extends GenericDaoImp<Task>  implements TaskDao {


    @Override
    public List<Task> findByTasKList(Long task_id) {
       Criteria criteria = getCriteria().add(Restrictions.eq("taskList.id",task_id));
        return criteria.list();
    }


}
