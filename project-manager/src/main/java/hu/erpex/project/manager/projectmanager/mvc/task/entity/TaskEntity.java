package hu.erpex.project.manager.projectmanager.mvc.task.entity;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreEntity;
import hu.erpex.project.manager.projectmanager.mvc.list.entity.ListEntity;
import hu.erpex.project.manager.projectmanager.mvc.enums.TaskLevelEnum;
import hu.erpex.project.manager.projectmanager.mvc.user.entity.UsersEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "task")
@Entity
public class TaskEntity extends CoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private ListEntity list;

    @ManyToMany
    private List<UsersEntity> contributors;

    @Column(nullable = false)
    private Integer positionInList;

    @Column
    private String taskName;

    @Column
    private Date deadline;

    @Column
    private String taskDescription;

    @Column
    private TaskLevelEnum level;

    @Column
    private boolean isDone;

    @Column
    private Date createdOn;






    public Integer getPositionInList() {
        return positionInList;
    }

    public void setPositionInList(Integer positionInList) {
        this.positionInList = positionInList;
    }

    public List<UsersEntity> getContributors() {
        return contributors;
    }

    public void setContributors(List<UsersEntity> contributors) {
        this.contributors = contributors;
    }

    public ListEntity getList() {
        return list;
    }

    public void setList(ListEntity list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskLevelEnum getLevel() {
        return level;
    }

    public void setLevel(TaskLevelEnum level) {
        this.level = level;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
