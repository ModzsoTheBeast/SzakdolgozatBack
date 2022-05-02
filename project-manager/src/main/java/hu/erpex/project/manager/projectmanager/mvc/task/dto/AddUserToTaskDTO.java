package hu.erpex.project.manager.projectmanager.mvc.task.dto;

import java.util.List;

public class AddUserToTaskDTO {
    private Long taskId;
    private List<Long> userId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public List<Long> getUserId() {
        return userId;
    }

    public void setUserId(List<Long> userId) {
        this.userId = userId;
    }
}
