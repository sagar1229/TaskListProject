package com.gateway.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jayavardhan on 12/9/15.
 */

@Entity
@Table(name="task_list")
public class TaskList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="title",nullable = false)
    private String tilte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="user_id", nullable = false)

    private User user;


    @Column(name="created_on")
    private Date  createdOn;

    @Column(name="last_edited_on")
    private Date  lastTimeEdited;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastTimeEdited() {
        return lastTimeEdited;
    }

    public void setLastTimeEdited(Date lastTimeEdited) {
        this.lastTimeEdited = lastTimeEdited;
    }
}
