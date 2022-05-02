package hu.erpex.project.manager.projectmanager.mvc.task.dto;

import java.util.Date;

public class UpdateTaskDeadlineDTO {
    private Date deadline;
    private Long taskId;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
