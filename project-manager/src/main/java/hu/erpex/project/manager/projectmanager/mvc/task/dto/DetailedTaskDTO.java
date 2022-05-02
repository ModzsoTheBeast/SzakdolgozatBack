package hu.erpex.project.manager.projectmanager.mvc.task.dto;

import hu.erpex.project.manager.projectmanager.mvc.enums.TaskLevelEnum;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.dto.TaskCommentDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.dto.TaskListDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UsersOnTaskDTO;

import java.util.Date;
import java.util.List;

public class DetailedTaskDTO {
    private Long taskId;
    private String taskDescription;
    private String taskName;
    private Date createdOn;
    private List<UsersOnTaskDTO> contributors;
    private List<TaskListDTO> taskLists;
    private Boolean isDone;
    private List<TaskCommentDTO> comments;
    private Date deadline;
    private TaskLevelEnum level;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskLevelEnum getLevel() {
        return level;
    }

    public void setLevel(TaskLevelEnum level) {
        this.level = level;
    }

    public List<TaskCommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<TaskCommentDTO> comments) {
        this.comments = comments;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<UsersOnTaskDTO> getContributors() {
        return contributors;
    }

    public void setContributors(List<UsersOnTaskDTO> contributors) {
        this.contributors = contributors;
    }

    public List<TaskListDTO> getTaskLists() {
        return taskLists;
    }

    public void setTaskLists(List<TaskListDTO> taskLists) {
        this.taskLists = taskLists;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
