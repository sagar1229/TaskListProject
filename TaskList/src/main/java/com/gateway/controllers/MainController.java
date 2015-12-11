package com.gateway.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gateway.model.Task;
import com.gateway.model.TaskList;
import com.gateway.model.UserProfile;
import com.gateway.model.User;
import com.gateway.service.TaskService;
import com.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Jayavardhan on 12/9/15.
 */

@RestController

public class MainController {

    @Autowired
    private OAuth2RestOperations oauth2RestTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;


    @RequestMapping(method = RequestMethod.GET,value = "/tasklist/home")
    public ModelAndView  getUserInfo() throws JsonProcessingException {

        ModelAndView modelAndView = new ModelAndView("homePage");
        UserProfile profile = getGoogleProfile();
        User user = userService.findByEmail(profile.getEmail());
        if(user==null){
            user = new User();
            user.setEmail(profile.getEmail());
            user.setName(profile.getName());
            user.setCreatedOn(new Date());
            userService.onCreate(user);
        }
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        User currentUser = userService.findById(id);

        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());

        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/{user_id}/tasklists",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskList>> getTasklists(@PathVariable("user_id") Long user_id){

        System.out.println("Fetching TaskList with user_id  " + user_id);
        List<TaskList> taskLists = taskService.findTaskListByUser(user_id);

        if(taskLists==null){
            System.out.println("No TaskLists with user: " + user_id);
            return new ResponseEntity<List<TaskList>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<TaskList>>(taskLists,HttpStatus.OK);
    }

    @RequestMapping(value = "/api/{taskList_id}/tasks",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getTasks(@PathVariable("taskList_id") Long taskList_id){

        System.out.println("Fetching TaskList with user_id  " + taskList_id);
        List<Task> tasks = taskService.findTaskByTaskList(taskList_id);

        if(tasks==null){
            System.out.println("No TaskLists with usesr: " + taskList_id);
            return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Task>>(tasks,HttpStatus.OK);
    }


    @RequestMapping(value = "/api/{user_id}/taskLists/", method = RequestMethod.POST)
    public ResponseEntity<Map> addTaskList(@PathVariable Long user_id,@RequestBody Map map) {
        System.out.println("Creating Tasklist " + map.get("title"));
        TaskList taskList = new TaskList();
        taskList.setTilte((String) map.get("title"));
        taskList.setCreatedOn(new Date());
        taskList.setUser(userService.findById(user_id));
        taskList.setLastTimeEdited(null);
        taskService.addTasklist(taskList);
        return new ResponseEntity<Map>(map, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/{tasklist_id}/tasks/", method = RequestMethod.POST)
    public ResponseEntity<Map> addTask(@PathVariable Long taskList_id,@RequestBody Map map) {
        System.out.println("Creating Task " + map.get("title"));
        Task task = new Task();
        task.setCreatedOn(new Date());
        task.setLastTimeEdited(null);
        task.setDueDate((Date) map.get("dueDate"));
        task.setIsCompleted(false);
        task.setIsImportant(false);
        task.setTitle((String) map.get("title"));
        task.setTaskList(taskService.findTaskListById(taskList_id));
        taskService.addTask(task);
        return new ResponseEntity<Map>(map, HttpStatus.CREATED);
    }

    private UserProfile getGoogleProfile() {
        String url = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + oauth2RestTemplate.getAccessToken();
        ResponseEntity<UserProfile> forEntity = oauth2RestTemplate.getForEntity(url, UserProfile.class);
        return forEntity.getBody();
    }

}
