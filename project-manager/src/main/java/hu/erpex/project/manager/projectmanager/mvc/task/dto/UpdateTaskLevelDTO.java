package hu.erpex.project.manager.projectmanager.mvc.task.dto;

import hu.erpex.project.manager.projectmanager.mvc.enums.TaskLevelEnum;

public class UpdateTaskLevelDTO {
    private Long taskId;
    private TaskLevelEnum priority;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public TaskLevelEnum getPriority() {
        return priority;
    }

    public void setPriority(TaskLevelEnum priority) {
        this.priority = priority;
    }
}
