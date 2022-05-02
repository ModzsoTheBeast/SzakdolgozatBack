package hu.erpex.project.manager.projectmanager.mvc.tasklistitem.dto;

public class TaskListItemDTO {
    private String taskListItemName;
    private Long taskListItemId;
    private Boolean isDone;

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

    public String getTaskListItemName() {
        return taskListItemName;
    }

    public void setTaskListItemName(String taskListItemName) {
        this.taskListItemName = taskListItemName;
    }

    public Long getTaskListItemId() {
        return taskListItemId;
    }

    public void setTaskListItemId(Long taskListItemId) {
        this.taskListItemId = taskListItemId;
    }
}
