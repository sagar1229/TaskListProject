package com.gateway.service;

import com.gateway.dao.TaskDao;
import com.gateway.dao.TaskListDao;
import com.gateway.model.Task;
import com.gateway.model.TaskList;
import com.gateway.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Jayavardhan on 12/10/15.
 */
public class TaskServiceImp implements TaskService{

    @Autowired
    TaskDao taskDao;

    @Autowired
    TaskListDao taskListDao;

    @Override
    public List<Task> findTaskByTaskList(Long task_id) {
        return taskDao.findByTasKList(task_id);
    }

    @Override
    public List<TaskList> findTaskListByUser(Long user_id) {
        List<TaskList> taskLists = taskListDao.findTaskListByUser(user_id);
        return taskLists;
    }

    @Override
    public void addTasklist(TaskList taskList) {
        taskListDao.save(taskList);
    }

    @Override
    public void addTask(Task task){
        taskDao.save(task);
    }

    @Override
    public TaskList findTaskListById(Long taskListId) {
        return taskListDao.findById(taskListId);
    }


}
