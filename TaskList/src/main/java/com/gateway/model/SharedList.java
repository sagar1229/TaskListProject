package com.gateway.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;

/**
 * Created by Jayavardhan on 12/9/15.
 */
public class SharedList  {


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="user_id", updatable = false, nullable = false)

    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="list_id", updatable = false, nullable = false)

    private TaskList taskList;

    @Column(name="shared_on",nullable = false)
    private Date sharedOn;

    public Date getSharedOn() {
        return sharedOn;
    }

    public void setSharedOn(Date sharedOn) {
        this.sharedOn = sharedOn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}
