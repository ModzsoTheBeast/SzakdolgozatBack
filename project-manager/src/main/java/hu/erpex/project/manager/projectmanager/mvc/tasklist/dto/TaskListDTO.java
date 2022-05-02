package hu.erpex.project.manager.projectmanager.mvc.tasklist.dto;

import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.dto.TaskListItemDTO;

import java.util.List;

public class TaskListDTO {
    private String taskListName;
    private Long taskListId;
    private List<TaskListItemDTO> taskListItems;

    public String getTaskListName() {
        return taskListName;
    }

    public void setTaskListName(String taskListName) {
        this.taskListName = taskListName;
    }

    public Long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Long taskListId) {
        this.taskListId = taskListId;
    }

    public List<TaskListItemDTO> getTaskListItems() {
        return taskListItems;
    }

    public void setTaskListItems(List<TaskListItemDTO> taskListItems) {
        this.taskListItems = taskListItems;
    }
}
