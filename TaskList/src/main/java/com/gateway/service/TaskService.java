package com.gateway.service;

import com.gateway.model.Task;
import com.gateway.model.TaskList;
import com.gateway.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Jayavardhan on 12/10/15.
 */
public interface TaskService {

    List<Task> findTaskByTaskList(Long task_list);

    List<TaskList> findTaskListByUser(Long user_id);

    List<Map<String,Object>> getTaskListByUser(List<TaskList> taskLists);


    void addTasklist(TaskList taskList);

    void addTask(Task task);

    TaskList findTaskListById(Long taskListId);

    Task findTaskById(Long id);

    List<Map<String,Object>> getTasksByUser(List<Task> tasks);

    Task updateTask(Task task);
}
