package hu.erpex.project.manager.projectmanager.mvc.task.dto;

public class RemoveUserFromTaskDTO {
    private Long userId;
    private Long taskId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
