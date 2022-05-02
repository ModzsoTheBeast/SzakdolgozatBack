package hu.erpex.project.manager.projectmanager.mvc.task.dto;

import hu.erpex.project.manager.projectmanager.mvc.enums.TaskLevelEnum;

public class TaskDTO {
    private Long taskId;
    private String TaskName;
    private String taskDescription;
    private TaskLevelEnum priority;

    public TaskLevelEnum getPriority() {
        return priority;
    }

    public void setPriority(TaskLevelEnum priority) {
        this.priority = priority;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

}
