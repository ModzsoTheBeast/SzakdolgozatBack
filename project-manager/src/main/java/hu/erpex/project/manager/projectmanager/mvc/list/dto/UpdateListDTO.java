package hu.erpex.project.manager.projectmanager.mvc.list.dto;

public class UpdateListDTO {
    private Long listId;
    private String listName;

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
}
