package com.gateway.dao;

import com.gateway.model.TaskList;
import com.gateway.model.User;

import java.util.List;

/**
 * Created by Jayavardhan on 12/10/15.
 */
public interface TaskListDao extends GenericDao<TaskList> {

    List<TaskList> findTaskListByUser(Long user_id);



}
