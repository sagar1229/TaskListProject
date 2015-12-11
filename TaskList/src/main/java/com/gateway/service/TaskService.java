package com.gateway.service;

import com.gateway.model.Task;
import com.gateway.model.TaskList;
import com.gateway.model.User;

import java.util.List;

/**
 * Created by Jayavardhan on 12/10/15.
 */
public interface TaskService {

    List<Task> findTaskByTaskList(Long task_list);

    List<TaskList> findTaskListByUser(Long user_id);


    void addTasklist(TaskList taskList);

    void addTask(Task task);

    TaskList findTaskListById(Long taskListId);
}
