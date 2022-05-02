package hu.erpex.project.manager.projectmanager.mvc.list.dto;

import hu.erpex.project.manager.projectmanager.mvc.task.dto.TaskDTO;

import java.util.List;

public class DetailedListDTO {
    private Long listId;
    private String listName;
    private Integer listPosition;
    private List<TaskDTO> tasks;


    public Integer getListPosition() {
        return listPosition;
    }

    public void setListPosition(Integer listPosition) {
        this.listPosition = listPosition;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
