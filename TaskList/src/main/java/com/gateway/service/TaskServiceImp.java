package com.gateway.service;

import com.gateway.dao.TaskDao;
import com.gateway.dao.TaskListDao;
import com.gateway.dao.UserDao;
import com.gateway.model.Task;
import com.gateway.model.TaskList;
import com.gateway.model.User;
import com.gateway.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jayavardhan on 12/10/15.
 */
public class TaskServiceImp implements TaskService{

    @Autowired
    TaskDao taskDao;

    @Autowired
    TaskListDao taskListDao;

    @Autowired
    UserService userService;

    private DateFormatter dateFormatter = DateFormatter.getInstance();

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
    public List<Map<String, Object>> getTaskListByUser(List<TaskList> taskLists) {
        List<Map<String, Object>> returnMap = new ArrayList<Map<String, Object>>();
        for(TaskList taskList:taskLists){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("title",taskList.getTilte());
            map.put("id",taskList.getId());
            map.put("createdOn",taskList.getCreatedOn());
            map.put("lastEditedOn",taskList.getLastTimeEdited());
            returnMap.add(map);
        }
        return returnMap;
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

    @Override
    public Task findTaskById(Long id) {
        return taskDao.findById(id);
    }

    @Override
    public List<Map<String, Object>> getTasksByUser(List<Task> tasks) {
        List<Map<String, Object>> returnMap = new ArrayList<Map<String, Object>>();
        for(Task task: tasks){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("title",task.getTitle());
            map.put("id",task.getId());
            map.put("dueDate",dateFormatter.parseDate(task.getDueDate()));
            map.put("completed",task.getIsCompleted());
            map.put("createdOn",task.getCreatedOn());
            map.put("lastEditedOn",task.getLastTimeEdited());
            returnMap.add(map);
        }
        return returnMap;
    }

    @Override
    public Task updateTask(Task task) {
        return taskDao.update(task);
    }


}
