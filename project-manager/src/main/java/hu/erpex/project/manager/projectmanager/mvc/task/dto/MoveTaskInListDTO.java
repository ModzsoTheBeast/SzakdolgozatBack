package hu.erpex.project.manager.projectmanager.mvc.task.dto;

public class MoveTaskInListDTO {
    private Long listId;
    private Integer startPosition;
    private Integer endPosition;

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }
}
