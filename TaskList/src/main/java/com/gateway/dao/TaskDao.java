package com.gateway.dao;

import com.gateway.model.Task;
import com.gateway.model.TaskList;
import com.gateway.model.User;

import java.util.List;

/**
 * Created by Jayavardhan on 12/10/15.
 */
public interface TaskDao extends GenericDao<Task> {

    List<Task> findByTasKList(Long task_id);

}
