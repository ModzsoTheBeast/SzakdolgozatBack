package com.example.szakdolgozatBack.Models.Entities;

import javax.persistence.*;
import java.util.Date;

@Table(name = "TASK_LIST")
@Entity
public class TaskListEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "TASK_LIST_ID")
    private Long task_list_id;

    @Column(name="TASK_LIST_ITEM_NAME", length=300, nullable=false)
    private Date task_list_item_name;

    @Column(name="TASK_LIST_CREATED_ON", length=50, nullable=false)
    private Date task_list_created_on;

    @Column(name="TASK_LIST_CREATED_BY", length=50, nullable=false)
    private String task_list_created_by;

    @ManyToOne
    @JoinColumn(name="TASK_ID")
    private TaskEntity task;

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public Long getTask_list_id() {
        return task_list_id;
    }

    public void setTask_list_id(Long task_list_id) {
        this.task_list_id = task_list_id;
    }

    public Date getTask_list_item_name() {
        return task_list_item_name;
    }

    public void setTask_list_item_name(Date task_list_item_name) {
        this.task_list_item_name = task_list_item_name;
    }

    public Date getTask_list_created_on() {
        return task_list_created_on;
    }

    public void setTask_list_created_on(Date task_list_created_on) {
        this.task_list_created_on = task_list_created_on;
    }

    public String getTask_list_created_by() {
        return task_list_created_by;
    }

    public void setTask_list_created_by(String task_list_created_by) {
        this.task_list_created_by = task_list_created_by;
    }

}
