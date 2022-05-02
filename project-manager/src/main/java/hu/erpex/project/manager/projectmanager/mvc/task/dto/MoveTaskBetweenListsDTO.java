package hu.erpex.project.manager.projectmanager.mvc.task.dto;

public class MoveTaskBetweenListsDTO {
    private Long startListId;
    private Long endListId;
    private Integer startTaskPosition;
    private  Integer endTaskPosition;
    private  Integer startListLength;
    private  Integer endListLength;

    public Long getStartListId() {
        return startListId;
    }

    public void setStartListId(Long startListId) {
        this.startListId = startListId;
    }

    public Long getEndListId() {
        return endListId;
    }

    public void setEndListId(Long endListId) {
        this.endListId = endListId;
    }

    public Integer getStartTaskPosition() {
        return startTaskPosition;
    }

    public void setStartTaskPosition(Integer startTaskPosition) {
        this.startTaskPosition = startTaskPosition;
    }

    public Integer getEndTaskPosition() {
        return endTaskPosition;
    }

    public void setEndTaskPosition(Integer endTaskPosition) {
        this.endTaskPosition = endTaskPosition;
    }

    public Integer getStartListLength() {
        return startListLength;
    }

    public void setStartListLength(Integer startListLength) {
        this.startListLength = startListLength;
    }

    public Integer getEndListLength() {
        return endListLength;
    }

    public void setEndListLength(Integer endListLength) {
        this.endListLength = endListLength;
    }
}
