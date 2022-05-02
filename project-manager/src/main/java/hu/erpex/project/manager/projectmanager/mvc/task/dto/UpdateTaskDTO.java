package hu.erpex.project.manager.projectmanager.mvc.task.dto;

import hu.erpex.project.manager.projectmanager.mvc.enums.TaskLevelEnum;

import java.util.Date;

public class UpdateTaskDTO {

    private Long taskId;
    private String taskDescription;
    private String taskName;

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
}
