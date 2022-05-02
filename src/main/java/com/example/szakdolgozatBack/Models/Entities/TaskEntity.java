package com.example.szakdolgozatBack.Models.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "TASK")
@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "TASK_ID")
    private Long task_id;

    @Column(name="TASK_NAME", length=50, nullable=false, unique=true)
    private String task_name;

    @Column(name="TASK_DESC", length=500)
    private String task_desc;

    @Column(name="TASK_COLOR", length=7, nullable=false, unique=true)
    private String task_color;

    @Column(name="TASK_COMMENT", length=50, nullable=false, unique=true)
    private String task_comment;

    @Column(name="TASK_CREATED_ON", length=50, nullable=false)
    private Date task_created_on;

    @Column(name="TASK_CREATED_BY", length=50, nullable=false)
    private String task_created_by;

    @ManyToOne
    @JoinColumn(name="PROJECT_ID")
    private ProjectEntity project;

    public Set<TaskListEntity> getTaskList() {
        return taskList;
    }

    public void setTaskList(Set<TaskListEntity> taskList) {
        this.taskList = taskList;
    }

    @OneToMany(mappedBy = "task")
    private Set<TaskListEntity> taskList;

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getTask_color() {
        return task_color;
    }

    public void setTask_color(String task_color) {
        this.task_color = task_color;
    }

    public String getTask_comment() {
        return task_comment;
    }

    public void setTask_comment(String task_comment) {
        this.task_comment = task_comment;
    }

    public Date getTask_created_on() {
        return task_created_on;
    }

    public void setTask_created_on(Date task_created_on) {
        this.task_created_on = task_created_on;
    }

    public String getTask_created_by() {
        return task_created_by;
    }

    public void setTask_created_by(String task_created_by) {
        this.task_created_by = task_created_by;
    }
}
