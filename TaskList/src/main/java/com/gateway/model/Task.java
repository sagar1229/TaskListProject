package com.gateway.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jayavardhan on 12/9/15.
 */

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="title",nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="list_id", updatable = false, nullable = false)

    private TaskList taskList;


    @Column(name="created_on")
    private Date createdOn;

    @Column(name="last_edited_on")
    private Date  lastTimeEdited;

    @Column(name="due_on")
    private Date  dueDate;

    @Column(name="is_completed")
    private Boolean isCompleted;

    @Column(name="mark_important")
    private Boolean isImportant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getLastTimeEdited() {
        return lastTimeEdited;
    }

    public void setLastTimeEdited(Date lastTimeEdited) {
        this.lastTimeEdited = lastTimeEdited;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Boolean isImportant) {
        this.isImportant = isImportant;
    }
}