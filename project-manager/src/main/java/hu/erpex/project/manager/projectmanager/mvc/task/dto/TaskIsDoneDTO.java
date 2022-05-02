package hu.erpex.project.manager.projectmanager.mvc.task.dto;

public class TaskIsDoneDTO {
    private Long taskId;
    private boolean isDone;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }
}
